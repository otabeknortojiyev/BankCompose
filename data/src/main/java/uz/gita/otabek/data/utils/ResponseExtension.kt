package uz.gita.otabek.data.utils

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import uz.gita.otabek.common.ErrorMessage
import uz.gita.otabek.common.request.CardRequest

inline fun <T, R> Response<T>.toResult(
    gson: Gson,
    onSuccess: (T) -> Result<R>,
): Result<R> {
    return if (isSuccessful && body() != null) {
        onSuccess(body()!!)
    } else if (errorBody() != null) {
        val error = gson.fromJson(errorBody()!!.string(), ErrorMessage::class.java)
        Result.failure(Exception(error.message))
    } else {
        Result.failure(Throwable(message()))
    }
}