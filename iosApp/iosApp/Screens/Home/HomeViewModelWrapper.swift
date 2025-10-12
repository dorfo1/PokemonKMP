import Shared

final class HomeViewModelWrapper: BaseIOSViewModel<HomeState, HomeAction, HomeEvent> {
    init() {
        super.init(viewModel: DependenciesProvider.shared.provideHomeViewModel())
    }
}

