package uz.gita.otabek.presenter.signUpVerify

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                signUpVerifyUseCase(intent.code)
                    .onEach {
                        it.onSuccess {
                            direction.moveToPassword()
                        }.onFailure {
                            Log.d("TTT", it.message.toString())
                        }
                    }.launchIn(viewModelScope)
            }

            SignUpVerifyContract.Intent.ResendCode -> {
                signUpResendUseCase.invoke()
                    .onEach {
                        it.onSuccess {

                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
            }

            SignUpVerifyContract.Intent.MoveToBack -> {
                direction.moveToBack()
            }
        }
    }

    override val container = container<SignUpVerifyContract.UiState, SignUpVerifyContract.SideEffect>(SignUpVerifyContract.UiState())

}