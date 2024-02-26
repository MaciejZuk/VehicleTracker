package com.maciejzuk.vehicletracking.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maciejzuk.vehicletracking.models.VehicleModel

@Database(
    entities = [
        VehicleModel::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(VehicleConverter::class)
abstract class VehicleDatabase : RoomDatabase() {
    abstract fun getVehicleDAO(): VehicleDAO
}