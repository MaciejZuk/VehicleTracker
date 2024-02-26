package com.maciejzuk.vehicletracking.repository.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.maciejzuk.vehicletracking.models.VehicleLocation

class VehicleConverter {
    @TypeConverter
    fun vehicleLocationToJson(vehicleLocation: VehicleLocation?): String =
        Gson().toJson(vehicleLocation)

    @TypeConverter
    fun jsonToVehicleLocation(json: String): VehicleLocation? =
        Gson().fromJson(json, VehicleLocation::class.java)
}