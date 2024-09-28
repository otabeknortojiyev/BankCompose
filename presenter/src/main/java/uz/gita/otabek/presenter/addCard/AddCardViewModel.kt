package uz.gita.otabek.presenter.addCard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.card.AddCardUseCase
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val directions: AddCardContract.Directions,
    private val addCardUeCase: AddCardUseCase
) : ViewModel(), AddCardContract.ViewModel {
    override fun onEventDispatcher(intent: AddCardContract.Intent) = intent {
        when (intent) {
            AddCardContract.Intent.MoveToHome -> {
                directions.moveToHome()
            }

            is AddCardContract.Intent.AddCard -> {
                reduce { state.copy(isLoading = true) }
                viewModelScope.launch {
                    val result = addCardUeCase(intent.addCardRequest)
                    result.onSuccess {
                        reduce { state.copy(success = true) }
                        reduce { state.copy(isLoading = false) }
                    }.onFailure {
                        reduce { state.copy(isLoading = false) }
                    }
                }
            }

            AddCardContract.Intent.CloseDialog -> {
                reduce { state.copy(success = false) }
            }
        }
    }

    override val container = container<AddCardContract.UiState, AddCardContract.SideEffect>(AddCardContract.UiState())
}
