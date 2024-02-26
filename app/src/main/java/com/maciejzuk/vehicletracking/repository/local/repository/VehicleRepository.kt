package com.maciejzuk.vehicletracking.repository.local.repository

import com.maciejzuk.vehicletracking.models.VehicleModel
import com.maciejzuk.vehicletracking.repository.local.database.VehicleDatabase
import com.maciejzuk.vehicletracking.repository.remote.VehicleApiService
import com.maciejzuk.vehicletracking.utils.Distance
import com.maciejzuk.vehicletracking.utils.DistanceCheckUtil
import kotlinx.coroutines.delay
import javax.inject.Inject

class VehicleRepository @Inject constructor(
    private val vehicleApiService: VehicleApiService,
    private val vehicleDatabase: VehicleDatabase,
    private val distanceCheckUtil: DistanceCheckUtil,
) {
    suspend fun getVehicleListFromApi() {
        val result = vehicleApiService.getVehicleList()
        val vehicleModelList = result.body()
        if (result.isSuccessful && vehicleModelList != null) {
            vehicleModelList.map {
                updateDetails(it)
            }
            storeVehicleListInDb(vehicleModelList)
        }
    }

    private suspend fun updateDetails(vehicleModel: VehicleModel) =
        getVehicleDetails(vehicleModel.vehicleId)?.let { vehicleDetails ->
            vehicleModel.location = vehicleDetails.location
            val isClose = distanceCheckUtil.getDistance(vehicleDetails.location)
            vehicleModel.isClose = isClose
            VehicleModel(vehicleModel.vehicleId, vehicleDetails.location, isClose)
        } ?: {
            VehicleModel(vehicleModel.vehicleId, null, Distance.UNKNOWN)
        }

    private suspend fun storeVehicleListInDb(list: List<VehicleModel>) =
        vehicleDatabase.getVehicleDAO().addVehicleList(list)

    val getVehicleListFromDBFlow = vehicleDatabase.getVehicleDAO().getVehicleListFlow()

    private fun getVehicleListFromDB() = vehicleDatabase.getVehicleDAO().getVehicleList()

    private suspend fun getVehicleDetails(vehicleId: String): VehicleModel? {
        val result = vehicleApiService.getVehicleDetails(vehicleId)
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!
        } else {
            null
        }
    }

    suspend fun startUpdatingLocation() {
        while (true) {
            val vehicleList = getVehicleListFromDB()
            vehicleList.map {
                updateDetails(it)
            }
            storeVehicleListInDb(vehicleList)
            delay(5000)
        }
    }
}