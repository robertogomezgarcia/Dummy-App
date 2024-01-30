package com.example.dummyapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.dummyapp.databinding.LoginFragmentBinding
import com.example.dummyapp.domain.model.UserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel : LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.state.collect {
                    when(it){
                        is LoginState.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        is LoginState.Loading -> Toast.makeText(context, "Cargando", Toast.LENGTH_SHORT).show()
                        is LoginState.Success -> findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProductFragment())
                    }

                }
            }
        }

    }

    private fun initUI() {
        initListener()
    }

    private fun initListener() {
        binding.btnLogin.setOnClickListener{
            login()
        }
    }

    private fun login() {

        val userField = binding.editUser.text.toString()
        val passField = binding.editPass.text.toString()
        val userModel = UserModel(userField,passField)
        loginViewModel.login(userModel)
//        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToProductFragment())
    }
}