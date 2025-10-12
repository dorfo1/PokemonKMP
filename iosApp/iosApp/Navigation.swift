import SwiftUI
import Shared

struct RootNavigation: View {
    @State private var path = NavigationPath()
    
    var body: some View {
        NavigationStack(path: $path) {
            SplashScreen(path: $path)
                .navigationDestination(for: NavigationRoutes.self) { route in
                    switch route {
                    case .splash:
                        SplashScreen(path: $path)
                    case .home:
                        HomeScreen(path: $path)
                        //                        case .details(let pokemon):
                        //                           DetailsScreen(pokemon: pokemon)
                        //                        }
                    }
                }
        }
    }
}

enum NavigationRoutes: Hashable {
    case home
    case splash
//    case details(pokemon: PokemonModel)
}
