package uz.gita.otabek.bankauthcompose.screens.services

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen

object ServicesScreen : Screen {
    private fun readResolve(): Any = ServicesScreen

    @Composable
    override fun Content() {
        ServicesScreenContent()
    }

}

@Composable
private fun ServicesScreenContent() {

}