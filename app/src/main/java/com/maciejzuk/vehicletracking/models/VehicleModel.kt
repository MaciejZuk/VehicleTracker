package com.maciejzuk.vehicletracking.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maciejzuk.vehicletracking.utils.Distance
import kotlinx.parcelize.Parcelize

@Entity(tableName = "vehicle_model")
@Parcelize
data class VehicleModel(
    @PrimaryKey
    val vehicleId: String,
    var location: VehicleLocation? = null,
    var isClose: Distance = Distance.UNKNOWN,
) : Parcelable

@Entity(tableName = "vehicle_location")
@Parcelize
data class VehicleLocation(
    val latitude: Double,
    val longitude: Double
) : Parcelable
