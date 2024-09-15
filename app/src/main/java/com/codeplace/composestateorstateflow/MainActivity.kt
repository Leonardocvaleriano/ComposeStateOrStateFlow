package com.codeplace.composestateorstateflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codeplace.composestateorstateflow.ui.theme.ComposeStateOrStateFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ComposeStateOrStateFlowTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = viewModel<MainViewModel>()

                    // StateFlow

                    /** collectAsState() Benefits
                     * LifeCycle Awareness (this state, so it will automatically start,stop and clean up resources based on lifecycle state its parent):
                        - Avoid memory leaks by collecting the flow in a lifecycle-aware manner
                     * Observe state changes, so every time that the value changes, the compose will recompose the UI.
                     */
                    val flowIsUserFirstAccess by viewModel.isUserFirstAccess.collectAsState()

                    // ComposeState
                    val composeIsUserFirstAccess = viewModel.composeStateIsUserFirstAccess

                    HomeScreen(isUserFirstAccess = flowIsUserFirstAccess, setFirstAccessOnClick = {
                        viewModel.recognizeFirstUserAccess()
                    }
                    )
                }

            }
        }
    }

    @Composable
    fun HomeScreen(
        isUserFirstAccess: Boolean,
        setFirstAccessOnClick: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(all = 12.dp),
                text = "$isUserFirstAccess",
                fontSize = 26.sp
            )
            Button(onClick = setFirstAccessOnClick) {
                Text(text = "Set first access")
            }
        }

    }

    @Preview
    @Composable
    fun HomeScreenPreview() {
        ComposeStateOrStateFlowTheme {
            HomeScreen(isUserFirstAccess = false, setFirstAccessOnClick = {})
        }
    }

}



