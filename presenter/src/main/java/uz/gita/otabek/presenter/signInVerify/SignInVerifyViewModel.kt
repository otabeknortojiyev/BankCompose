package uz.gita.otabek.presenter.signInVerify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.SignInResendUseCase
import uz.gita.otabek.domain.useCase.auth.SignInVerifyUseCase
import javax.inject.Inject

@HiltViewModel
class SignInVerifyViewModel @Inject constructor(
    private val directions: SignInVerifyContract.Directions,
    private val signInVerifyUseCase: SignInVerifyUseCase,
    private val signInVerifyResendUseCase: SignInResendUseCase,
) : ViewModel(), SignInVerifyContract.ViewModel {
    override fun onEventDispatcher(intent: SignInVerifyContract.Intent) = intent {
        when (intent) {
            is SignInVerifyContract.Intent.MoveToPassword -> {
                signInVerifyUseCase.invoke(intent.code).onEach { result ->
                    result.onSuccess {
                        directions.moveToPassword()
                    }.onFailure {

                    }
                }.launchIn(viewModelScope)
            }

            SignInVerifyContract.Intent.ResendCode -> {
                signInVerifyResendUseCase.invoke().onEach { result ->
                    result.onSuccess {
                        directions.moveToPassword()
                    }.onFailure {

                    }
                }.launchIn(viewModelScope)
            }

            SignInVerifyContract.Intent.MoveToSignIn -> {
                directions.moveToSignIn()
            }
        }
    }

    override val container = container<SignInVerifyContract.UiState, SignInVerifyContract.SideEffect>(SignInVerifyContract.UiState())
}