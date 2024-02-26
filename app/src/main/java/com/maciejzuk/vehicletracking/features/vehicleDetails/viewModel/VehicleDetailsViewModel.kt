package com.maciejzuk.vehicletracking.features.vehicleDetails.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciejzuk.vehicletracking.features.vehicleDetails.state.VehicleDetailsUiState
import com.maciejzuk.vehicletracking.repository.local.repository.VehicleRepository
import com.maciejzuk.vehicletracking.utils.Distance
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(
    private val vehicleRepository: VehicleRepository
) : ViewModel() {

    private val _vehicleDetailsUiState = MutableStateFlow(VehicleDetailsUiState())
    val vehicleDetailsUiState: StateFlow<VehicleDetailsUiState> =
        _vehicleDetailsUiState.asStateFlow()

    fun init(vehicleId: String) {
        viewModelScope.launch {
            getVehicleListFromDB(vehicleId)
        }
    }

    private suspend fun getVehicleListFromDB(vehicleId: String) {
        vehicleRepository.getVehicleListFromDBFlow.collect { vehicleModelList ->
            val vehicleModel = vehicleModelList.firstOrNull { it.vehicleId == vehicleId }
            val count = vehicleModelList.count { it.isClose == Distance.CLOSE }
            _vehicleDetailsUiState.value = VehicleDetailsUiState(vehicleModel, count)
        }
    }
}