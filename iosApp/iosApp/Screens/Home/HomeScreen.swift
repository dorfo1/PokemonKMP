import SwiftUI
import Shared
import Combine

struct HomeScreen: View {
    @StateObject private var viewModel = HomeViewModelWrapper()
    @Binding var path: NavigationPath
    
    private let columns = [
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: columns, spacing: 8) {
                ForEach(viewModel.state.pokemons, id: \.id) { pokemon in
                    CardView(pokemon: pokemon, onPokemonClick: {_ in })
                }
            }
            .padding(.horizontal, 8)
            .padding(.top, 8)
        }
        .navigationTitle("Pokemons")
    }
}

struct CardView: View {
    let pokemon: PokemonModel
    let onPokemonClick : (PokemonModel) -> Void
    
    var body: some View {
        VStack(alignment: .center, spacing: 4) {
            AsyncImage(url: URL(string: pokemon.thumbnail)) { phase in
                switch phase {
                case .empty:
                    ProgressView()
                        .frame(width: 80, height: 80)
                case .success(let image):
                    image
                        .resizable()
                        .scaledToFit()
                        .frame(width: 80, height: 80)
                case .failure:
                    Image(systemName: "photo")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 80, height: 80)
                @unknown default:
                    EmptyView()
                }
            }
            Text(pokemon.name)
                .font(.subheadline)
                .multilineTextAlignment(.center)
        }
        .padding(.vertical, 8)
        .frame(maxWidth: .infinity)
        .background(Color(uiColor: .secondarySystemBackground))
        .cornerRadius(8)
        .shadow(color: .black.opacity(0.1), radius: 2, x: 0, y: 1)
        .padding(4)
        .onTapGesture {
            
        }
    }
}
