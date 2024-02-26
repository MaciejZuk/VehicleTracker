package com.maciejzuk.vehicletracking.features.vehicleList.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciejzuk.vehicletracking.features.vehicleList.state.VehicleListUiState
import com.maciejzuk.vehicletracking.repository.local.repository.VehicleRepository
import com.maciejzuk.vehicletracking.utils.Distance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleListViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicleListUiState = MutableStateFlow(VehicleListUiState())
    val vehicleListUiState: StateFlow<VehicleListUiState> = _vehicleListUiState.asStateFlow()

    init {
        viewModelScope.launch {
            getVehicleListFromDB()
        }
        viewModelScope.launch(Dispatchers.IO) {
            async {
                loadVehiclesFromApi()
            }.await()
            startUpdatingLocation()
        }
    }

    private suspend fun getVehicleListFromDB() {
        vehicleRepository.getVehicleListFromDBFlow.collect { vehicleModelList ->
            val count = vehicleModelList.count { it.isClose == Distance.CLOSE }
            _vehicleListUiState.value = VehicleListUiState(vehicleModelList, count)
        }
    }

    private suspend fun loadVehiclesFromApi() {
        vehicleRepository.getVehicleListFromApi()
    }

    private suspend fun startUpdatingLocation() {
        vehicleRepository.startUpdatingLocation()
    }
}