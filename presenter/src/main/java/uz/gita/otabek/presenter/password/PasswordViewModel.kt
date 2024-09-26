package uz.gita.otabek.presenter.password

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.GetPINUseCase
import uz.gita.otabek.domain.useCase.auth.SavePINUseCase
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val directions: PasswordContract.Directions,
    private val savePINUseCase: SavePINUseCase,
    private val getPINUseCase: GetPINUseCase
) : ViewModel(), PasswordContract.ViewModel {
    override fun onEventDispatcher(intent: PasswordContract.Intent) = intent {
        when (intent) {
            is PasswordContract.Intent.MoveToHome -> {
                if (getPINUseCase() == "") {
                    directions.moveToHome()
                    savePINUseCase.invoke(intent.pin)
                } else if (intent.pin != "" && getPINUseCase() == intent.pin) {
                    directions.moveToHome()
                } else {
                    reduce { state.copy(error = true) }
                }
            }
        }
    }
    override val container = container<PasswordContract.UiState, PasswordContract.SideEffect>(PasswordContract.UiState())
}