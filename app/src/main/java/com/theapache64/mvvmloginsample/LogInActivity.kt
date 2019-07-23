package com.theapache64.mvvmloginsample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.theapache64.mvvmloginsample.databinding.ActivityLoginBinding

class LogInActivity : AppCompatActivity(), LogInHandler {

    private lateinit var viewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Binding
        val binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        // ViewModel
        this.viewModel = ViewModelProviders.of(this).get(LogInViewModel::class.java)

        // Setting binding params
        binding.viewModel = viewModel
        binding.handler = this

        // Watching for login result
        viewModel.getLogInResult().observe(this, Observer { result ->
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onLogInClicked() {
        viewModel.performValidation()
    }

}
