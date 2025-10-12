import SwiftUI
import Shared

struct SplashScreen: View {
    @StateObject private var viewModel = SplashViewModelWrapper()
    @Binding var path: NavigationPath

    var body: some View {
            VStack {
                if viewModel.state.isLoading {
                    Text("Baixando pokemons...")
                    ProgressView().padding(.top, 8)
                } else if viewModel.state.isError {
                    Text("Falha ao baixar pokemons")
                    Button("Tentar novamente") {
                        viewModel.onEvent(SplashEventFetchPokemons())
                    }
                    .padding(.top, 8)
                }
                Text("Pokedex").padding(.top, 8)
            }
            .padding()
            .navigationTitle("Splash")
            .navigationBarTitleDisplayMode(.inline)
            .onReceive(viewModel.actionPublisher) { action in
                handleAction(action)
            }
    }
    
    private func handleAction(_ action: SplashAction) {
        switch action {
            case is SplashActionSuccessFetch:
                path.append(NavigationRoutes.home)
            default:
                break
        }
    }
}
