package compose.statesdemo

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach

class CalculatorViewModel(
    state: SavedStateHandle,
) : ViewModel() {
    var v1 by state.mutableStateOf("0")
    var v2 by state.mutableStateOf("0")

    val result by state.mutableStateOf("0") { valueLoadedFromState, setter ->
        snapshotFlow { v1 to v2 }
            .drop(if (valueLoadedFromState != null) 1 else 0)
            .mapLatest {
                sum(it.first, it.second)
            }
            .onEach {
                setter(it)
            }
            .launchIn(viewModelScope)
    }

    private suspend fun sum(v1: String, v2: String) = try {
        (v1.toInt() + v2.toInt()).toString()
    } catch (e: NumberFormatException) {
        "Parsing error :("
    }
}
