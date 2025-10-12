import Shared
import Foundation
import Combine

@MainActor
class BaseIOSViewModel<State: AnyObject, Action: AnyObject, Event: AnyObject>: ObservableObject {
    let viewModel: BaseViewModel<State, Action, Event>

    @Published var state: State
    // Publisher que a View vai escutar
    let actionPublisher = PassthroughSubject<SplashAction, Never>()

    private var stateCloseable: Ktor_ioCloseable?
    private var actionCloseable: Ktor_ioCloseable?

    init(viewModel: BaseViewModel<State, Action, Event>) {
        self.viewModel = viewModel
        self.state = viewModel.state.value as! State
      

        // Observa o StateFlow
        stateCloseable = viewModel.watchState { state in
            self.state = state as! State
        }

        actionCloseable = viewModel.watchAction { action in
            if let action = action as? SplashAction {
                DispatchQueue.main.async {
                    self.actionPublisher.send(action)
                }
            }
        }
    }

    deinit {
        stateCloseable?.close()
        actionCloseable?.close()
    }

    func onEvent(_ event: Event) {
        viewModel.onEvent(event: event)
    }
}
