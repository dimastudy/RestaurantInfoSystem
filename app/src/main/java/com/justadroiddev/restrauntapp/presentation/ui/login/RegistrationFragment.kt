package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.restrauntapp.databinding.FragmentRegistrationBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>(FragmentRegistrationBinding::inflate) {

    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeResultData(viewLifecycleOwner) {
            if (it != null){
                if (it){
                    this.findNavController().navigate(RegistrationFragmentDirections.actionRegistrationFragmentToLoginFragment())
                    viewModel.doneRegistration()
                } else {
                    Snackbar.make(requireView(), "Something went wrong!", Snackbar.LENGTH_LONG).show()
                    viewModel.doneRegistration()
                }
            }
        }

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.apply {
            loginButton.setOnClickListener {
                viewModel.registerAccount(sharedPref!!, etName.text.toString(), etLastName.text.toString(), etMail.text.toString(), etPassword.text.toString())
            }
        }

    }

}