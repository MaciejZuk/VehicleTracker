package com.maciejzuk.vehicletracking.utils

import com.maciejzuk.vehicletracking.models.VehicleLocation
import javax.inject.Inject
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

enum class Distance {
    FAR, CLOSE, UNKNOWN
}

class DistanceCheckUtil @Inject constructor() {

    private val lausanneLatitude = 46.5223916
    private val lausanneLongitude = 6.6314437

    fun getDistance(vehicleLocation: VehicleLocation?): Distance {
        if (vehicleLocation == null) {
            return Distance.UNKNOWN
        }
        val distance = getDistanceBetweenVehicleAndLausanne(vehicleLocation)
        return if (distance < 1.0) Distance.CLOSE else Distance.FAR
    }


    // Calculate distance between Vehicle current position and Lausanne position
    // using Haversine distance formula
    private fun getDistanceBetweenVehicleAndLausanne(vehicleLocation: VehicleLocation): Double {
        val rad = 6372.8 //Earth's radius in kilometers
        val dLat = Math.toRadians(vehicleLocation.latitude - lausanneLatitude)
        val dLon = Math.toRadians(vehicleLocation.longitude - lausanneLongitude)
        val lat1 = Math.toRadians(vehicleLocation.latitude)
        val lat2 = Math.toRadians(lausanneLatitude)
        val a = sin(dLat / 2) * sin(dLat / 2) + sin(dLon / 2) * sin(dLon / 2) *
                cos(lat1) * cos(lat2)
        val c = 2 * asin(sqrt(a))
        return rad * c
    }
}