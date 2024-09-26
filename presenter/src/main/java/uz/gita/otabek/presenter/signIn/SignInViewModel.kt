package uz.gita.otabek.presenter.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.common.request.AuthRequest
import uz.gita.otabek.domain.useCase.auth.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val directions: SignInContract.Directions,
    private val signInUseCase: SignInUseCase
) : ViewModel(), SignInContract.ViewModel {
    override fun onEventDispatcher(intent: SignInContract.Intent) = intent {
        when (intent) {
            is SignInContract.Intent.MoveToVerify -> {
                signInUseCase(AuthRequest.SignIn(intent.phone, intent.password))
                    .onEach {
                        it.onSuccess {
                            directions.moveToVerify(intent.phone)
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
            }

            SignInContract.Intent.MoveToRegister -> {
                directions.moveToRegister()
            }
        }
    }

    override val container = container<SignInContract.UiState, SignInContract.SideEffect>(SignInContract.UiState())
}