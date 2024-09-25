package uz.gita.otabek.bankauthcompose.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.domain.useCase.auth.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(private val direction: SignUpContract.Direction, private val signUpUseCase: SignUpUseCase) :
    ViewModel(), SignUpContract.ViewModel {

    override fun onEventDispatcher(intent: SignUpContract.Intent) = intent {
        when (intent) {
            is SignUpContract.Intent.ClickNext -> {
                signUpUseCase(
                    AuthRequest.SignUp(
                        phone = intent.number,
                        firstName = intent.name,
                        lastName = intent.surname,
                        bornDate = intent.bornDate,
                        password = intent.password,
                        gender = intent.gender
                    )
                ).onEach {
                    it.onSuccess {
                        direction.moveToCode(intent.number)
                    }.onFailure {
                        postSideEffect(SignUpContract.SideEffect.ResultMessage(it.message.toString()))
                    }
                }.launchIn(viewModelScope)
            }

            SignUpContract.Intent.MoveToBack -> {
                direction.moveToBack()
            }
        }
    }

    override val container = container<SignUpContract.UiState, SignUpContract.SideEffect>(SignUpContract.UiState())
}