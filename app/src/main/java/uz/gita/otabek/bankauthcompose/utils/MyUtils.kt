package uz.gita.otabek.bankauthcompose.utils

import android.content.Context

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

enum class Type(val value: String) {
    INCOME("income"), OUTCOME("outcome")
}

enum class Phone(val value: String) {
    PREFIX("+998"),
    MASK("##-###-##-##")
}

enum class Card(val value: String) {
    MASK("#### #### #### ####")
}

enum class Date(val value: String) {
    MASK("##.##.####")
}

fun String.formatNumberWithSpaces(): String {
    return this.reversed()
        .chunked(3)
        .joinToString(" ")
        .reversed()
}