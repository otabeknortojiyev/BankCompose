package uz.gita.otabek.presenter.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
                viewModelScope.launch {
                    val result = signInUseCase(AuthRequest.SignIn(intent.phone, intent.password))
                    result.onSuccess {
                        directions.moveToVerify(intent.phone)
                    }.onFailure {

                    }
                }
            }

            SignInContract.Intent.MoveToRegister -> {
                directions.moveToRegister()
            }
        }
    }

    override val container = container<SignInContract.UiState, SignInContract.SideEffect>(SignInContract.UiState())
}