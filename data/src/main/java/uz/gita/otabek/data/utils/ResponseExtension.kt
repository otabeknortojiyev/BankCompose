package uz.gita.otabek.data.utils

import retrofit2.Response

fun <T> Response<T>.toResult() {
    val code = code()
    
}