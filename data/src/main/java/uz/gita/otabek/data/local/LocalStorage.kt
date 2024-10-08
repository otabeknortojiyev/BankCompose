package uz.gita.otabek.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {
    var token by strings("")
    var refreshToken by strings("")
    var accessToken by strings("")
    var pin by strings("")
    var language by strings("")
    var totalBalance by ints(0)
    var userName by strings("")
    var userAge by ints(0)
    var genderType by ints(0)
}