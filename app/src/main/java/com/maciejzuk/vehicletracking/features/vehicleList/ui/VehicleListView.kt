package com.maciejzuk.vehicletracking.features.vehicleList.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.maciejzuk.vehicletracking.common.ui.LoadingView
import com.maciejzuk.vehicletracking.features.closeVehicles.CloseVehiclesView
import com.maciejzuk.vehicletracking.features.vehicleList.viewModel.VehicleListViewModel

@Composable
fun VehicleListView(
    vehicleListViewModel: VehicleListViewModel,
    onVehicleClick: (vehicleId: String) -> Unit
) {
    val vehicleListUiState by vehicleListViewModel.vehicleListUiState.collectAsState()

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        CloseVehiclesView(closeVehicles = vehicleListUiState.closeVehicles)
        if (vehicleListUiState.vehicleModelList.isEmpty()) {
            LoadingView()
        } else {
            VehicleColumnView(
                vehicleModelList = vehicleListUiState.vehicleModelList,
                onVehicleClick = onVehicleClick
            )
        }
    }
}