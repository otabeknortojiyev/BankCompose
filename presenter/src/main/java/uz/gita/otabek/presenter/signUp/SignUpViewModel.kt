package uz.gita.otabek.presenter.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.domain.useCase.auth.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val direction: SignUpContract.Direction, private val signUpUseCase: SignUpUseCase
) : ViewModel(), SignUpContract.ViewModel {

    override fun onEventDispatcher(intent: SignUpContract.Intent) = intent {
        when (intent) {
            is SignUpContract.Intent.ClickNext -> {
                viewModelScope.launch {
                    val result = signUpUseCase(
                        AuthRequest.SignUp(
                            phone = intent.number,
                            firstName = intent.name,
                            lastName = intent.surname,
                            bornDate = intent.bornDate,
                            password = intent.password,
                            gender = intent.gender
                        )
                    )
                    result.onSuccess {
                        direction.moveToCode(intent.number)
                    }.onFailure {
                        postSideEffect(SignUpContract.SideEffect.ResultMessage(it.message.toString()))
                    }
                }
            }

            SignUpContract.Intent.MoveToBack -> {
                direction.moveToBack()
            }

            SignUpContract.Intent.MoveToSignIn -> {
                direction.moveToSignIn()
            }
        }
    }

    override val container = container<SignUpContract.UiState, SignUpContract.SideEffect>(SignUpContract.UiState())
}