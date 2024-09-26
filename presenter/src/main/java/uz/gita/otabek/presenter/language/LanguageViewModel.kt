package uz.gita.otabek.presenter.language

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.auth.SetLanguageUseCase
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val direction: LanguageContract.Direction,
    private val setLanguageUseCase: SetLanguageUseCase
) : ViewModel(), LanguageContract.ViewModel {
    override fun onEventDispatcher(intent: LanguageContract.Intent) = intent {
        when (intent) {
            LanguageContract.Intent.ClickLanguage -> {
                direction.moveToRegister()
            }

            is LanguageContract.Intent.SetLanguage -> {
                setLanguageUseCase(intent.lang)
            }
        }
    }

    override val container = container<LanguageContract.UiState, LanguageContract.SideEffect>(LanguageContract.UiState())
}