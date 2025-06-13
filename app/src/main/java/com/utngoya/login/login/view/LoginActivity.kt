package com.utngoya.login.login.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.utngoya.login.databinding.ActivityLoginBinding
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.utngoya.login.home.view.HomeActivity
import com.utngoya.login.login.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                setUiEnabled(false)
                binding.progressBar.visibility = View.VISIBLE
                viewModel.login(username, password)
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { result ->
            setUiEnabled(true)
            binding.progressBar.visibility = View.GONE
            result.onSuccess {
                saveToken(it.token)
                goToHome(it.username)
            }.onFailure {
                Toast.makeText(this, it.message ?: "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUiEnabled(enabled: Boolean) {
        binding.etUsername.isEnabled = enabled
        binding.etPassword.isEnabled = enabled
        binding.btnLogin.isEnabled = enabled
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit { putString("auth_token", token) }
    }

    private fun goToHome(username: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("USERNAME_KEY", username)
        startActivity(intent)
        finish()

    }
}
