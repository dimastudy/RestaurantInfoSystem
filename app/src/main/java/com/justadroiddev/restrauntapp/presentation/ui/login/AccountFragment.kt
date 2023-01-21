package com.justadroiddev.restrauntapp.presentation.ui.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.justadroiddev.restrauntapp.R
import com.justadroiddev.restrauntapp.databinding.FragmentAccountBinding
import com.justadroiddev.restrauntapp.presentation.BaseFragment
import com.justadroiddev.restrauntapp.presentation.ui.findTopNavController


class AccountFragment : BaseFragment<FragmentAccountBinding>(FragmentAccountBinding::inflate) {

    private val viewModel: AccountsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observeUser(viewLifecycleOwner) { user ->
            if (user != null){
                binding.apply {
                    userName.text = user.firstName
                    userSecondName.text = user.lastName
                    userMail.text = user.email
                }
            }
        }

        val sharedPreferences = activity?.getPreferences(Context.MODE_PRIVATE)
        binding.logOutButton.setOnClickListener {
            viewModel.logOut(sharedPreferences!!)
            findTopNavController().navigate(R.id.loginFragment, null, navOptions{
                popUpTo(R.id.tabsFragment){
                    inclusive = true
                }
            })
        }
        binding.ordersButton.setOnClickListener {
            this.findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToOrdersFragment())
        }

    }

}