package uz.gita.otabek.presenter.passwordVerify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.GetPINUseCase
import javax.inject.Inject

@HiltViewModel
class PasswordVerifyViewModel @Inject constructor(
    private val directions: PasswordVerifyContract.Directions,
    private val getPINUseCase: GetPINUseCase,
) : ViewModel(), PasswordVerifyContract.ViewModel {
    override fun onEventDispatcher(intent: PasswordVerifyContract.Intent) = intent {
        when (intent) {
            is PasswordVerifyContract.Intent.MoveToHome -> {
                getPINUseCase.invoke().onEach { result ->
                    result.onSuccess {
                        if (it == "") {
                            directions.moveToHome()
                        } else if (intent.pin != "" && it == intent.pin) {
                            directions.moveToHome()
                        } else {
                            reduce { state.copy(error = true) }
                        }
                    }.onFailure {

                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    override val container = container<PasswordVerifyContract.UiState, PasswordVerifyContract.SideEffect>(PasswordVerifyContract.UiState())
}