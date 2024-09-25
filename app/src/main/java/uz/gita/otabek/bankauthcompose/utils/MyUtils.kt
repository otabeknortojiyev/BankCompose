package uz.gita.otabek.bankauthcompose.utils

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

fun String.isLetter(): Boolean {
    var count: Int = 0
    this.forEach {
        if ((it in 'A'..'Z') ||
            (it in 'a'..'z')
        ) count++
    }
    return count == this.length
}

fun setLanguage(language: Lang, context: Context) {
    val config = context.resources.configuration
    val locale = java.util.Locale(language.name)
    java.util.Locale.setDefault(locale)
    config.setLocale(locale)
    context.createConfigurationContext(config)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

enum class Lang {
    uz, ru, en
}

enum class Phone(val value: String) {
    PREFIX("+998"),
    MASK("##-###-##-##")
}

enum class Date(val value: String) {
    MASK("##.##.####")
}