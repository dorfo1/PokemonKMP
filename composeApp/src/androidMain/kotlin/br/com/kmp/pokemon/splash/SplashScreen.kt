package br.com.kmp.pokemon.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.kmp.pokemon.HomeRoute
import br.com.kmp.pokemon.LocalNavController
import br.com.kmp.pokemon.presentation.splash.SplashAction
import br.com.kmp.pokemon.presentation.splash.SplashEvent
import br.com.kmp.pokemon.presentation.splash.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplashScreen(viewModel: SplashViewModel = koinViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        viewModel.action.collect {
            when (it) {
                SplashAction.SuccessFetch -> navController.navigate(HomeRoute) {
                    popUpTo(0)
                }
            }
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Pokemons") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (state.isLoading) {
                Text("Baixando pokemons...")
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
            }

            if (state.isError) {
                Text("Falha ao baixar pokemons")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.onEvent(SplashEvent.FetchPokemons) }) { Text("Tentar novamente") }
            }

            Text("Pokedex")

        }
    }
}