package com.maciejzuk.vehicletracking.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maciejzuk.vehicletracking.features.vehicleDetails.ui.VehicleDetailsView
import com.maciejzuk.vehicletracking.features.vehicleDetails.viewModel.VehicleDetailsViewModel
import com.maciejzuk.vehicletracking.features.vehicleList.ui.VehicleListView
import com.maciejzuk.vehicletracking.features.vehicleList.viewModel.VehicleListViewModel

@Composable
fun AppNavHost(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = NavigationItem.VehicleList.route
    ) {
        composable(
            route = NavigationItem.VehicleList.route
        ) {
            VehicleListView(
                hiltViewModel<VehicleListViewModel>()
            ) {
                navController.navigate("${NavigationItem.VehicleDetails.route}/$it")
            }
        }

        composable(
            route = "${NavigationItem.VehicleDetails.route}/{vehicleId}",
            arguments = listOf(navArgument("vehicleId") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("vehicleId")?.let { vehicleId ->
                VehicleDetailsView(
                    hiltViewModel<VehicleDetailsViewModel>(),
                    vehicleId = vehicleId
                )
            }
        }
    }
}