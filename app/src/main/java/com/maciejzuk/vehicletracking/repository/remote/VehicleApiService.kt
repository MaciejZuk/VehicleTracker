package com.maciejzuk.vehicletracking.repository.remote

import com.maciejzuk.vehicletracking.models.VehicleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface VehicleApiService {

    @GET("dev/vehicles")
    suspend fun getVehicleList(): Response<List<VehicleModel>>

    @GET("dev/vehicles/{vehicleId}")
    suspend fun getVehicleDetails(@Path("vehicleId") vehicleId: String): Response<VehicleModel>
}