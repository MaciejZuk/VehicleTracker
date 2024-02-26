package com.maciejzuk.vehicletracking.features.vehicleList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maciejzuk.vehicletracking.models.VehicleLocation
import com.maciejzuk.vehicletracking.models.VehicleModel
import com.maciejzuk.vehicletracking.ui.theme.VehicleTrackingTheme
import com.maciejzuk.vehicletracking.utils.Distance

@Composable
fun VehicleColumnView(
    vehicleModelList: List<VehicleModel>,
    onVehicleClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(vehicleModelList) {
            VehicleListItem(it) { vehicleId ->
                onVehicleClick(vehicleId)
            }
        }
    }
}

@Preview
@Composable
fun VehicleColumnViewPreview() {
    VehicleTrackingTheme {
        VehicleColumnView(
            vehicleModelList = listOf(
                VehicleModel(
                    vehicleId = "1",
                    VehicleLocation(11.000, 11.000),
                    Distance.CLOSE
                ),
                VehicleModel(
                    vehicleId = "2",
                    VehicleLocation(12.000, 12.000),
                    Distance.FAR
                )
            ),
            onVehicleClick = {}
        )
    }
}