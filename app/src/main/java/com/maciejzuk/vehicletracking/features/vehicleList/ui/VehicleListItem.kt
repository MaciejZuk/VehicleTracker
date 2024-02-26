package com.maciejzuk.vehicletracking.features.vehicleList.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maciejzuk.vehicletracking.R
import com.maciejzuk.vehicletracking.models.VehicleLocation
import com.maciejzuk.vehicletracking.models.VehicleModel
import com.maciejzuk.vehicletracking.ui.theme.VehicleTrackingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleListItem(
    item: VehicleModel,
    onVehicleClick: (vehicleId: String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        onClick = { onVehicleClick(item.vehicleId) }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 8.dp),
                painter = painterResource(id = R.drawable.ic_truck),
                contentDescription = ""
            )

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .size(width = 1.dp, height = 50.dp)
            )

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Vehicle ID: ${item.vehicleId}",
                )
                Text(
                    text = "Vehicle location: ${item.isClose.name}",
                )
            }
        }
    }
}

@Preview
@Composable
fun VehicleListItemPreview() {
    VehicleTrackingTheme {
        VehicleListItem(
            item = VehicleModel(
                "1234",
                VehicleLocation(12.0, 12.0)
            ),
            onVehicleClick = { }
        )
    }
}
