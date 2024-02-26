package com.maciejzuk.vehicletracking.features.closeVehicles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maciejzuk.vehicletracking.R
import com.maciejzuk.vehicletracking.ui.theme.VehicleTrackingTheme

@Composable
fun CloseVehiclesView(closeVehicles: Int?) {
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    text = "Number of close vehicles:",
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_distance),
                        contentDescription = ""
                    )
                    if (closeVehicles == null) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    } else {
                        Text(
                            modifier = Modifier.padding(start = 8.dp),
                            text = "$closeVehicles",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Divider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Gray,
            thickness = 2.dp
        )
    }
}

@Preview
@Composable
fun CloseVehicleViewPreview() {
    VehicleTrackingTheme {
        CloseVehiclesView(2)
    }
}

@Preview
@Composable
fun CloseVehicleViewLoadingPreview() {
    VehicleTrackingTheme {
        CloseVehiclesView(null)
    }
}