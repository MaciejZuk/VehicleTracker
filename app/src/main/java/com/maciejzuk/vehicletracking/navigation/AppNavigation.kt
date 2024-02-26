package com.maciejzuk.vehicletracking.navigation

enum class Screen {
    VEHICLE_LIST,
    VEHICLE_DETAILS
}

sealed class NavigationItem(val route: String) {
    data object VehicleList : NavigationItem(Screen.VEHICLE_LIST.name)
    data object VehicleDetails : NavigationItem(Screen.VEHICLE_DETAILS.name)
}