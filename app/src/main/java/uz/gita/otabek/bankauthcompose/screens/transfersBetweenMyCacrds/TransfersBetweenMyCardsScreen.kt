package uz.gita.otabek.bankauthcompose.screens.transfersBetweenMyCacrds

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.otabek.presenter.transfersBetweenMyCards.TransfersBetweenMyCardsContract
import uz.gita.otabek.presenter.transfersBetweenMyCards.TransfersBetweenMyCardsViewModel


object TransfersBetweenMyCardsScreen : Screen {
    private fun readResolve(): Any = TransfersBetweenMyCardsScreen

    @Composable
    override fun Content() {
        val viewModel: TransfersBetweenMyCardsContract.ViewModel = getViewModel<TransfersBetweenMyCardsViewModel>()
        val uiState = viewModel.collectAsState()
        TransfersBetweenMyCardsScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("ResourceAsColor")
@Composable
private fun TransfersBetweenMyCardsScreenContent(
    uiState: State<TransfersBetweenMyCardsContract.UiState>, onEventDispatcher: (TransfersBetweenMyCardsContract.Intent) -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Color.White)
}