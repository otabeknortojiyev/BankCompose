package uz.gita.otabek.bankauthcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita.otabek.bankauthcompose.screens.language.LanguageContract
import uz.gita.otabek.bankauthcompose.screens.language.LanguageDirections
import uz.gita.otabek.bankauthcompose.screens.password.PasswordContract
import uz.gita.otabek.bankauthcompose.screens.password.PasswordDirections
import uz.gita.otabek.bankauthcompose.screens.signUp.SignUpContract
import uz.gita.otabek.bankauthcompose.screens.signUp.SignUpDirections
import uz.gita.otabek.bankauthcompose.screens.signUpVerify.SignUpVerifyContract
import uz.gita.otabek.bankauthcompose.screens.signUpVerify.SignUpVerifyDirections
import uz.gita.otabek.bankauthcompose.screens.splash.SplashContract
import uz.gita.otabek.bankauthcompose.screens.splash.SplashDirections
import uz.gita.otabek.bankauthcompose.screens.tabs.home.HomeContracts
import uz.gita.otabek.bankauthcompose.screens.tabs.home.HomeDirections

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
}