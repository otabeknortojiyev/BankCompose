package uz.gita.otabek.presenter.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.CheckLanguageUseCase
import uz.gita.otabek.domain.useCase.auth.CheckPINUseCase
import uz.gita.otabek.presenter.utils.Lang
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashContract.Direction, private val checkPIN: CheckPINUseCase, private val checkLanguage: CheckLanguageUseCase
) : ViewModel(), SplashContract.ViewModel {

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {
        when (intent) {
            SplashContract.Intent.MoveToPIN -> {
                viewModelScope.launch {
                    val result = checkPIN()
                    result.onSuccess {
                        if (it) {
                            direction.moveToPIN()
                        } else {
                            direction.moveToLanguage()
                        }
                    }.onFailure {

                    }
                }
            }

            SplashContract.Intent.CheckLanguage -> {
                viewModelScope.launch {
                    val result = checkLanguage()
                    result.onSuccess {
                        when (it) {
                            Lang.UZ.value -> {
                                reduce { state.copy(language = Lang.UZ.value) }
                            }

                            Lang.RU.value -> {
                                reduce { state.copy(language = Lang.RU.value) }
                            }

                            Lang.EN.value -> {
                                reduce { state.copy(language = Lang.EN.value) }
                            }
                        }
                    }.onFailure {

                    }
                }
            }
        }
    }

    override val container = container<SplashContract.UiState, SplashContract.SideEffect>(SplashContract.UiState())
}