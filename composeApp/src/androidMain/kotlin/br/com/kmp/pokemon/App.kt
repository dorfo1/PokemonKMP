package br.com.kmp.pokemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.kmp.pokemon.domain.model.PokemonModel
import br.com.kmp.pokemon.home.HomeScreen
import br.com.kmp.pokemon.splash.SplashScreen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.ui.tooling.preview.Preview

val LocalNavController = compositionLocalOf<NavHostController> {
    error("CompositionLocal LocalNavController not present")
}

@Serializable
object SplashRoute

@Serializable
object HomeRoute

@Serializable
data class DetailsRoute(val pokemonModel: PokemonModel)

@Composable
@Preview
fun App() {
    val navController = rememberNavController()


    MaterialTheme {
        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(navController = navController, startDestination = SplashRoute) {
                composable<SplashRoute> { SplashScreen() }
                composable<HomeRoute> { HomeScreen() }
                composable<DetailsRoute> { backStackEntry ->
                    val args = backStackEntry.toRoute<DetailsRoute>()

                }
            }
        }

    }
}