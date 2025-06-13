package com.utngoya.login.students.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.utngoya.login.R
import com.utngoya.login.commons.BaseActivity
import com.utngoya.login.databinding.ActivityHomeBinding
import com.utngoya.login.databinding.ActivityStudentsBinding

class StudentsActivity : BaseActivity<ActivityStudentsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityStudentsBinding
        get() = ActivityStudentsBinding::inflate

    override val toolbar: Toolbar
        get() = binding.toolbar

    override val toolbarTitle = "Estudiantes"
    override val showBackButton = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)
    }
}