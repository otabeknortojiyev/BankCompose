package uz.gita.otabek.bankauthcompose.screens.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.CheckLanguageUseCase
import uz.gita.otabek.domain.useCase.auth.CheckPINUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashContract.Direction,
    private val checkPIN: CheckPINUseCase,
    private val checkLanguage: CheckLanguageUseCase
) : ViewModel(), SplashContract.ViewModel {

    override fun onEventDispatcher(intent: SplashContract.Intent) = intent {
        when (intent) {
            SplashContract.Intent.MoveToPIN -> {
                if (checkPIN.invoke()) {
                    direction.moveToPIN()
                } else {
                    direction.moveToLanguage()
                }
            }

            SplashContract.Intent.CheckLanguage -> {
                val language = checkLanguage()
                when (language) {
                    "uz" -> {
                        reduce { state.copy(language = "uz") }
                    }

                    "ru" -> {
                        reduce { state.copy(language = "ru") }
                    }

                    "en" -> {
                        reduce { state.copy(language = "en") }
                    }
                }
            }
        }
    }

    override val container = container<SplashContract.UiState, SplashContract.SideEffect>(SplashContract.UiState())
}