package compose.statesdemo

import androidx.compose.runtime.snapshots.Snapshot
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test

class ViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val initialState = mutableMapOf<String?, Any?>()

    private val viewModel by lazy { CalculatorViewModel(SavedStateHandle(initialState)) }

    @Test
    fun `set the result when an input changes`() {
        Snapshot.withMutableSnapshot {
            viewModel.v1 = "10"
            viewModel.v2 = "20"
        }
        assertThat(viewModel.result).isEqualTo("30")
    }

    @Test
    fun `loads the value from the saved state`() {
        initialState["v1"] = "1"
        initialState["v2"] = "2"
        //wrong value to be sure that the value is not calculated
        initialState["result"] = "4"
        assertThat(viewModel.result).isEqualTo("4")
    }
}
