package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.databinding.FragmentLoginBinding
import com.justadroiddev.restrauntapp.domain.ResultDomain
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {


    private val viewModel: LoginViewModel by viewModels()
    private lateinit var sharedPreferences: SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)!!

        viewModel.observeUserData(viewLifecycleOwner) { userData ->
            if (userData != null) {
                binding.apply {
                    etMail.setText(userData.first)
                    etPassword.setText(userData.second)
                    if (userData.third){
                        progressLogin.visibility = View.VISIBLE
                        viewModel.login(sharedPreferences, userData.first, userData.second)
                    }
                }
            }
        }

        viewModel.observeLoginData(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is ResultDomain.Success -> {
                        binding.progressLogin.visibility = View.GONE
                        this.findNavController()
                            .navigate(LoginFragmentDirections.actionLoginFragmentToTabsFragment())
                    }
                    is ResultDomain.Error -> {
                        binding.progressLogin.visibility = View.GONE
                        Snackbar.make(requireView(), result.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }


        binding.apply {
            loginButton.setOnClickListener {
                viewModel.login(
                    sharedPreferences,
                    etMail.text.toString(),
                    etPassword.text.toString()
                )
                progressLogin.visibility = View.VISIBLE
            }
            regButton.setOnClickListener {
                this@LoginFragment.findNavController()
                    .navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFragment())
            }
        }

    }

    override fun onResume() {
        viewModel.updateUserData(sharedPreferences)
        super.onResume()
    }


}