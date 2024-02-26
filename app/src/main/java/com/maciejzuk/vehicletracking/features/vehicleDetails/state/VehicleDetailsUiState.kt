package com.maciejzuk.vehicletracking.features.vehicleDetails.state

import com.maciejzuk.vehicletracking.models.VehicleModel

data class VehicleDetailsUiState(
    var vehicleModel: VehicleModel? = null,
    var closeVehicles: Int? = null
)