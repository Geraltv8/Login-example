package com.utngoya.login.commons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewbinding.ViewBinding
import com.utngoya.login.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    abstract val bindingInflater: (LayoutInflater) -> VB
    abstract val toolbar: Toolbar?

    open val showBackButton: Boolean = false
    open val toolbarTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        applyInsetsToMainView()
        setupToolbar()
    }

    private fun applyInsetsToMainView() {
        val mainView = binding.root.findViewById<View>(R.id.main)
         mainView?.let {
             ViewCompat.setOnApplyWindowInsetsListener(it) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
             }
         }
    }


    private fun setupToolbar() {
        toolbar?.let {
            setSupportActionBar(it)
            supportActionBar?.apply {
                title = toolbarTitle
                setDisplayHomeAsUpEnabled(showBackButton)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
