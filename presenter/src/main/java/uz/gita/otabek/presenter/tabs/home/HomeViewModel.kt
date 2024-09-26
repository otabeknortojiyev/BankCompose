package uz.gita.otabek.presenter.tabs.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.otabek.domain.useCase.card.GetCardsUseCase
import uz.gita.otabek.domain.useCase.home.BasicInfoUseCase
import uz.gita.otabek.domain.useCase.home.TotalBalanceUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val direction: HomeContracts.Directions,
    private val totalBalanceUseCase: TotalBalanceUseCase,
    private val basicInfoUseCase: BasicInfoUseCase,
    private val getCardsUseCase: GetCardsUseCase
) : HomeContracts.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: HomeContracts.Intent) = intent {
        when (intent) {
            HomeContracts.Intent.GetInitData -> {
                totalBalanceUseCase()
                    .onEach {
                        it.onSuccess {
                            reduce { state.copy(balance = it.totalBalance) }
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)

                basicInfoUseCase()
                    .onEach {
                        it.onSuccess {
                            reduce { state.copy(name = it.firstName) }
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
                getCardsUseCase()
                    .onEach {
                        it.onSuccess {
                            reduce { state.copy(cards = it) }
                        }.onFailure {

                        }
                    }.launchIn(viewModelScope)
            }

            HomeContracts.Intent.MoveToMonitoring -> {
                direction.moveToMonitoring()
            }

            HomeContracts.Intent.MoveToAddCard -> {
                direction.moveToAddCard()
            }
        }
    }

    override val container = container<HomeContracts.UiState, HomeContracts.SideEffect>(HomeContracts.UiState())
}