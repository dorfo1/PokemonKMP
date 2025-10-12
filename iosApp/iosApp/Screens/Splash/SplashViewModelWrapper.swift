import Shared

final class SplashViewModelWrapper: BaseIOSViewModel<SplashState, SplashAction, SplashEvent> {
    init() {
        super.init(viewModel: DependenciesProvider.shared.provideSplashViewModel())
    }
}

