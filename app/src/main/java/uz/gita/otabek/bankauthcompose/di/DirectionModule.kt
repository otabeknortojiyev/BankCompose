package uz.gita.otabek.bankauthcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita.otabek.bankauthcompose.screens.addCard.AddCardDirections
import uz.gita.otabek.bankauthcompose.screens.language.LanguageDirections
import uz.gita.otabek.bankauthcompose.screens.monitoring.MonitoringDirections
import uz.gita.otabek.bankauthcompose.screens.password.PasswordDirections
import uz.gita.otabek.bankauthcompose.screens.signUp.SignUpDirections
import uz.gita.otabek.bankauthcompose.screens.signUpVerify.SignUpVerifyDirections
import uz.gita.otabek.bankauthcompose.screens.splash.SplashDirections
import uz.gita.otabek.bankauthcompose.screens.tabs.home.HomeDirections
import uz.gita.otabek.presenter.addCard.AddCardContract
import uz.gita.otabek.presenter.language.LanguageContract
import uz.gita.otabek.presenter.monitoring.MonitoringContract
import uz.gita.otabek.presenter.password.PasswordContract
import uz.gita.otabek.presenter.signUp.SignUpContract
import uz.gita.otabek.presenter.signUpVerify.SignUpVerifyContract
import uz.gita.otabek.presenter.splash.SplashContract
import uz.gita.otabek.presenter.tabs.home.HomeContracts

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds ViewModelScoped]
    fun bindSignUpDirection(impl: SplashDirections): SplashContract.Direction

    @[Binds ViewModelScoped]
    fun bindLanguageDirection(impl: LanguageDirections): LanguageContract.Direction

    @[Binds ViewModelScoped]
    fun bindRegisterDirection(impl: SignUpDirections): SignUpContract.Direction

    @[Binds ViewModelScoped]
    fun bindCodeDirection(impl: SignUpVerifyDirections): SignUpVerifyContract.Directions

    @[Binds ViewModelScoped]
    fun bindPasswordDirection(impl: PasswordDirections): PasswordContract.Directions

    @[Binds ViewModelScoped]
    fun bindHomeDirection(impl: HomeDirections): HomeContracts.Directions

    @[Binds ViewModelScoped]
    fun bindMonitoringDirection(impl: MonitoringDirections): MonitoringContract.Directions

    @[Binds ViewModelScoped]
    fun bindAddCardDirection(impl: AddCardDirections): AddCardContract.Directions
}