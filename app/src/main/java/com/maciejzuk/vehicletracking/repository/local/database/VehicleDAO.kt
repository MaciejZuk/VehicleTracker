package com.maciejzuk.vehicletracking.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maciejzuk.vehicletracking.models.VehicleLocation
import com.maciejzuk.vehicletracking.models.VehicleModel
import com.maciejzuk.vehicletracking.utils.Distance
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVehicleList(vehicleList: List<VehicleModel>)

    @Query("SELECT * FROM 'vehicle_model'")
    fun getVehicleListFlow(): Flow<List<VehicleModel>>

    @Query("SELECT * FROM 'vehicle_model'")
    fun getVehicleList(): List<VehicleModel>

    @Query("SELECT * FROM 'vehicle_model' WHERE vehicleId=:vehicleId")
    suspend fun getVehicleDetails(vehicleId: String): VehicleModel

    @Query("UPDATE 'vehicle_model' SET location=:location, isClose=:isClose WHERE vehicleId=:vehicleId")
    suspend fun updateVehicle(vehicleId: String, location: VehicleLocation, isClose: Distance)
}