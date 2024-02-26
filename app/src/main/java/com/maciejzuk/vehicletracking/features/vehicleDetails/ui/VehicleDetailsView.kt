package com.maciejzuk.vehicletracking.features.vehicleDetails.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maciejzuk.vehicletracking.common.ui.LoadingView
import com.maciejzuk.vehicletracking.features.closeVehicles.CloseVehiclesView
import com.maciejzuk.vehicletracking.features.vehicleDetails.viewModel.VehicleDetailsViewModel

@Composable
fun VehicleDetailsView(
    vehicleDetailsViewModel: VehicleDetailsViewModel,
    vehicleId: String
) {

    val vehicleDetailsUiState by vehicleDetailsViewModel.vehicleDetailsUiState.collectAsState()

    LaunchedEffect(key1 = vehicleId) {
        vehicleDetailsViewModel.init(vehicleId)
    }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        CloseVehiclesView(closeVehicles = vehicleDetailsUiState.closeVehicles)
        if (vehicleDetailsUiState.vehicleModel == null) {
            LoadingView()
        } else {
            VehicleDetailsCardView(vehicleDetailsUiState.vehicleModel!!)
        }
    }
}