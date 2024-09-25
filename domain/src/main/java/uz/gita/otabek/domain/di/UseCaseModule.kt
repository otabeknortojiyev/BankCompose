package uz.gita.otabek.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import uz.gita.otabek.domain.useCase.auth.CheckLanguageUseCase
import uz.gita.otabek.domain.useCase.auth.CheckPINUseCase
import uz.gita.otabek.domain.useCase.auth.GetPINUseCase
import uz.gita.otabek.domain.useCase.auth.SavePINUseCase
import uz.gita.otabek.domain.useCase.auth.SetLanguageUseCase
import uz.gita.otabek.domain.useCase.auth.SignInResendUseCase
import uz.gita.otabek.domain.useCase.auth.SignInUseCase
import uz.gita.otabek.domain.useCase.auth.SignInVerifyUseCase
import uz.gita.otabek.domain.useCase.auth.SignUpResendUseCase
import uz.gita.otabek.domain.useCase.auth.SignUpUseCase
import uz.gita.otabek.domain.useCase.auth.SignUpVerifyUseCase
import uz.gita.otabek.domain.useCase.auth.UpdateTokenUseCase
import uz.gita.otabek.domain.useCase.auth.impl.CheckLanguageUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.CheckPINUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.GetPINUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SavePINUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SetLanguageUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SignInResendUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SignInUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SignInVerifyUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SignUpResendUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.SignUpUseCaseImpl
import uz.gita.otabek.domain.useCase.auth.impl.UpdateTokenUseCaseImpl
import uz.gita.otabek.domain.useCase.card.AddCardUseCase
import uz.gita.otabek.domain.useCase.card.DeleteCardUseCase
import uz.gita.otabek.domain.useCase.card.GetCardsUseCase
import uz.gita.otabek.domain.useCase.card.UpdateCardUseCase
import uz.gita.otabek.domain.useCase.card.impl.AddCardUseCaseImpl
import uz.gita.otabek.domain.useCase.card.impl.DeleteCardUseCaseImpl
import uz.gita.otabek.domain.useCase.card.impl.GetCardsUseCaseImpl
import uz.gita.otabek.domain.useCase.card.impl.UpdateCardUseCaseImpl
import uz.gita.otabek.domain.useCase.home.BasicInfoUseCase
import uz.gita.otabek.domain.useCase.home.FullInfoUseCase
import uz.gita.otabek.domain.useCase.home.LastTransfersUseCase
import uz.gita.otabek.domain.useCase.home.TotalBalanceUseCase
import uz.gita.otabek.domain.useCase.home.UpdateInfoUseCase
import uz.gita.otabek.domain.useCase.home.impl.BasicInfoUseCaseImpl
import uz.gita.otabek.domain.useCase.home.impl.FullInfoUseCaseImpl
import uz.gita.otabek.domain.useCase.home.impl.LastTransfersUseCaseImpl
import uz.gita.otabek.domain.useCase.home.impl.TotalBalanceUseCaseImpl
import uz.gita.otabek.domain.useCase.home.impl.UpdateInfoUseCaseImpl
import uz.gita.otabek.domain.useCase.impl.SignUpVerifyUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.GetCardOwnerByPanUseCase
import uz.gita.otabek.domain.useCase.transfer.GetFeeUseCase
import uz.gita.otabek.domain.useCase.transfer.GetHistoryUseCase
import uz.gita.otabek.domain.useCase.transfer.TransferResendUseCase
import uz.gita.otabek.domain.useCase.transfer.TransferUseCase
import uz.gita.otabek.domain.useCase.transfer.TransferVerifyUseCase
import uz.gita.otabek.domain.useCase.transfer.impl.GetCardOwnerByPanUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.impl.GetFeeUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.impl.GetHistoryUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.impl.TransferResendUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.impl.TransferUseCaseImpl
import uz.gita.otabek.domain.useCase.transfer.impl.TransferVerifyUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
internal interface UseCaseModule {

    @[Binds ViewModelScoped]
    fun provideSignUpUC(useCaseImpl: SignUpUseCaseImpl): SignUpUseCase

    @[Binds ViewModelScoped]
    fun provideSignUpVerifyUC(useCaseImpl: SignUpVerifyUseCaseImpl): SignUpVerifyUseCase

    @[Binds ViewModelScoped]
    fun provideSignInUC(useCaseImpl: SignInUseCaseImpl): SignInUseCase

    @[Binds ViewModelScoped]
    fun provideSignInVerifyUC(useCaseImpl: SignInVerifyUseCaseImpl): SignInVerifyUseCase

    @[Binds ViewModelScoped]
    fun provideUpdateTokenUC(useCaseImpl: UpdateTokenUseCaseImpl): UpdateTokenUseCase

    @[Binds ViewModelScoped]
    fun provideSignUpResendUC(useCaseImpl: SignUpResendUseCaseImpl): SignUpResendUseCase

    @[Binds ViewModelScoped]
    fun provideSignInResendUC(useCaseImpl: SignInResendUseCaseImpl): SignInResendUseCase

    @[Binds ViewModelScoped]
    fun provideSavePINUC(useCaseImpl: SavePINUseCaseImpl): SavePINUseCase

    @[Binds ViewModelScoped]
    fun provideCheckPINUC(useCaseImpl: CheckPINUseCaseImpl): CheckPINUseCase

    @[Binds ViewModelScoped]
    fun provideGetPINUC(useCaseImpl: GetPINUseCaseImpl): GetPINUseCase

    @[Binds ViewModelScoped]
    fun provideSetLanguageUC(useCaseImpl: SetLanguageUseCaseImpl): SetLanguageUseCase

    @[Binds ViewModelScoped]
    fun provideCheckLanguageUC(useCaseImpl: CheckLanguageUseCaseImpl): CheckLanguageUseCase

    @[Binds ViewModelScoped]
    fun provideBasicInfoUC(useCaseImpl: BasicInfoUseCaseImpl): BasicInfoUseCase

    @[Binds ViewModelScoped]
    fun provideFullInfoUC(useCaseImpl: FullInfoUseCaseImpl): FullInfoUseCase

    @[Binds ViewModelScoped]
    fun provideLastTransfersUC(useCaseImpl: LastTransfersUseCaseImpl): LastTransfersUseCase

    @[Binds ViewModelScoped]
    fun provideTotalBalanceUC(useCaseImpl: TotalBalanceUseCaseImpl): TotalBalanceUseCase

    @[Binds ViewModelScoped]
    fun provideUpdateInfoUC(useCaseImpl: UpdateInfoUseCaseImpl): UpdateInfoUseCase

    @[Binds ViewModelScoped]
    fun provideAddCardUC(useCaseImpl: AddCardUseCaseImpl): AddCardUseCase

    @[Binds ViewModelScoped]
    fun provideDeleteCardUC(useCaseImpl: DeleteCardUseCaseImpl): DeleteCardUseCase

    @[Binds ViewModelScoped]
    fun provideGetCardsUC(useCaseImpl: GetCardsUseCaseImpl): GetCardsUseCase

    @[Binds ViewModelScoped]
    fun provideUpdateCardUC(useCaseImpl: UpdateCardUseCaseImpl): UpdateCardUseCase

    @[Binds ViewModelScoped]
    fun provideGetCardOwnerByPanUC(useCaseImpl: GetCardOwnerByPanUseCaseImpl): GetCardOwnerByPanUseCase

    @[Binds ViewModelScoped]
    fun provideGetFeeUC(useCaseImpl: GetFeeUseCaseImpl): GetFeeUseCase

    @[Binds ViewModelScoped]
    fun provideGetHistoryUC(useCaseImpl: GetHistoryUseCaseImpl): GetHistoryUseCase

    @[Binds ViewModelScoped]
    fun provideTransferResendUC(useCaseImpl: TransferResendUseCaseImpl): TransferResendUseCase

    @[Binds ViewModelScoped]
    fun provideTransferUC(useCaseImpl: TransferUseCaseImpl): TransferUseCase

    @[Binds ViewModelScoped]
    fun provideTransferVerifyUC(useCaseImpl: TransferVerifyUseCaseImpl): TransferVerifyUseCase
}