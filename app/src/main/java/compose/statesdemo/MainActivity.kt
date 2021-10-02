package compose.statesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import compose.statesdemo.ui.theme.ComposeStatesDemoTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStatesDemoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CalculatorScreen()
                }
            }
        }
    }
}

@Composable
fun CalculatorScreen() {
    val viewModel = viewModel<CalculatorViewModel>()
    Calculator(state = viewModel)
}

@Composable
fun Calculator(state: CalculatorViewModel) {
    Column {
        TextField(value = state.v1, onValueChange = { state.v1 = it })
        TextField(value = state.v2, onValueChange = { state.v2 = it })
        Text(text = state.result)
    }
}