package com.utngoya.login.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.utngoya.login.login.model.LoginRequest
import com.utngoya.login.login.model.LoginResponse
import com.utngoya.login.restClient.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    private val apiService = RestClient.instance
    val loginResult = MutableLiveData<Result<LoginResponse>>()

    fun login(username: String, password: String) {
        val request = LoginRequest(username, password)
        apiService.postLogin(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        loginResult.postValue(Result.success(it))
                    } ?: loginResult.postValue(Result.failure(Exception("Respuesta vacía")))
                } else {
                    loginResult.postValue(Result.failure(Exception("Credenciales inválidas")))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginResult.postValue(Result.failure(t))
            }
        })
    }
}
