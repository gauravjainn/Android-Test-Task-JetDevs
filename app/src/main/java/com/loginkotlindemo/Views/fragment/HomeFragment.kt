package com.loginkotlindemo.Views.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.loginkotlindemo.R
import com.loginkotlindemo.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set Login User Details
        val preference=
            activity?.getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        if(preference!=null)
        {
            binding.textviewSecondId.setText(""+preference.getLong("id", 0))
            binding.textviewSecondName.setText(""+preference.getString("name", ""))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}