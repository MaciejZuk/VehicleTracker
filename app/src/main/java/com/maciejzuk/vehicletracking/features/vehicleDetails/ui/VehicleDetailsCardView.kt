package com.maciejzuk.vehicletracking.features.vehicleDetails.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import com.maciejzuk.vehicletracking.utils.Distance

@Composable
fun VehicleDetailsCardView(
    vehicleModel: VehicleModel
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_truck),
                    contentDescription = ""
                )
                Text(
                    text = "Vehicle ID: ${vehicleModel.vehicleId}",
                )
            }

            Divider(
                color = Color.Gray,
                thickness = 2.dp
            )

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = ""
                )
                Column {
                    Text(
                        text = "Latitude: ${vehicleModel.location?.latitude}",
                    )
                    Text(
                        text = "Longitude: ${vehicleModel.location?.longitude}",
                    )
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 2.dp
            )

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 8.dp),
                    painter = painterResource(id = R.drawable.ic_distance),
                    contentDescription = ""
                )
                Text(
                    text = "Vehicle Location: ${vehicleModel.isClose}"
                )
            }
        }
    }
}

@Preview
@Composable
fun VehicleDetailsCardViewPreview() {
    VehicleTrackingTheme {
        VehicleDetailsCardView(
            vehicleModel = VehicleModel(
                vehicleId = "123",
                VehicleLocation(12.000, 12.000),
                Distance.CLOSE
            )
        )
    }
}