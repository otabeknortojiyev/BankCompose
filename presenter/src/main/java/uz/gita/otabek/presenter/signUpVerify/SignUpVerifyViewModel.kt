package uz.gita.otabek.presenter.signUpVerify

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.SignUpResendUseCase
import uz.gita.otabek.domain.useCase.auth.SignUpVerifyUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpVerifyViewModel @Inject constructor(
    private val direction: SignUpVerifyContract.Directions,
    private val signUpVerifyUseCase: SignUpVerifyUseCase,
    private val signUpResendUseCase: SignUpResendUseCase
) :
    ViewModel(), SignUpVerifyContract.ViewModel {
    override fun onEventDispatcher(intent: SignUpVerifyContract.Intent) = intent {
        when (intent) {
            is SignUpVerifyContract.Intent.ClickNext -> {
                viewModelScope.launch {
                    val result = signUpVerifyUseCase(intent.code)
                    result.onSuccess {
                        direction.moveToPassword()

                    }.onFailure {

                    }
                }
            }

            SignUpVerifyContract.Intent.ResendCode -> {
                viewModelScope.launch {
                    val result = signUpResendUseCase.invoke()
                    result.onSuccess {

                    }.onFailure {

                    }
                }
            }

            SignUpVerifyContract.Intent.MoveToBack -> {
                direction.moveToBack()
            }
        }
    }

    override val container = container<SignUpVerifyContract.UiState, SignUpVerifyContract.SideEffect>(SignUpVerifyContract.UiState())

}