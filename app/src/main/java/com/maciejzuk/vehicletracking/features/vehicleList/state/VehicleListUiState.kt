package com.maciejzuk.vehicletracking.features.vehicleList.state

import com.maciejzuk.vehicletracking.models.VehicleModel

data class VehicleListUiState(
    var vehicleModelList: List<VehicleModel> = listOf(),
    var closeVehicles: Int? = null
)