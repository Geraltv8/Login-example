package com.utngoya.login.courses.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.utngoya.login.R
import com.utngoya.login.commons.BaseActivity
import com.utngoya.login.databinding.ActivityCoursesBinding
import com.utngoya.login.databinding.ActivityStudentsBinding

class CoursesActivity : BaseActivity<ActivityCoursesBinding>() {

    override val bindingInflater: (LayoutInflater) -> ActivityCoursesBinding
        get() = ActivityCoursesBinding::inflate


    override val toolbar: Toolbar
        get() = binding.toolbar


    override val toolbarTitle = "Cursos"
    override val showBackButton = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courses)
    }
}