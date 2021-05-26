package com.loginkotlindemo.Views.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.loginkotlindemo.R
import com.loginkotlindemo.Repository.LoginResponse
import com.loginkotlindemo.ViewModels.LoginViewModel
import com.loginkotlindemo.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!
    lateinit var loginViewModel: LoginViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //binding login layout UI here
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Login Button click event and handle validation and webservice calling here
        binding.loginbtn.setOnClickListener(View.OnClickListener {
            Log.d("LoginDatabinding", binding.editTextUserName.getText().toString())
            val username: String = binding.editTextUserName.getText().toString()
            val password: String = binding.editTextpassID.text.toString()
            //validate username and password
            loginViewModel.validateCredentials(username, password).observe(viewLifecycleOwner, object :
                Observer<String> {
                override fun onChanged(t: String?) {
                    if (t.equals("Successful Login")) {
                        //after validate call api to check username and password is correct
                        loginViewModel.getLoginObservable()
                            .observe(viewLifecycleOwner, object : Observer<LoginResponse> {
                                override fun onChanged(response: LoginResponse?) {
                                    if (response != null && response.getErrorCode()
                                            .equals("00") && response.getUser() != null
                                    ) {
                                        //Success api call
                                        val user = response.getUser();
                                        val preference = activity?.getSharedPreferences(
                                            resources.getString(R.string.app_name),
                                            Context.MODE_PRIVATE
                                        )
                                        val editor = preference?.edit()
                                        if (editor != null && user != null) {
                                            editor.putLong("id", user.getId())
                                            editor.putString("name", user.getUserName())
                                            editor.commit()
                                        }
                                        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                                    } else {
                                        ////Failed api call
                                        Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
                                    }
                                }
                            })
                    } else {
                        Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
                    }
                }
            })
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}