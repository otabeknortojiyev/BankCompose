package uz.gita.otabek.presenter.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.SavePINUseCase
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val directions: PasswordContract.Directions,
    private val savePINUseCase: SavePINUseCase
) : ViewModel(), PasswordContract.ViewModel {
    override fun onEventDispatcher(intent: PasswordContract.Intent) = intent {
        when (intent) {
            is PasswordContract.Intent.MoveToHome -> {
                directions.moveToHome()
                viewModelScope.launch {
                    savePINUseCase.invoke(intent.pin)
                }
            }
        }
    }

    override val container = container<PasswordContract.UiState, PasswordContract.SideEffect>(PasswordContract.UiState())
}