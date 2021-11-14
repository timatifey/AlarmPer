package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.databinding.Fragment2Binding

class Fragment2 : Fragment() {

    private var binding: Fragment2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
            bnToFirst.setOnClickListener {
                findNavController().navigate(R.id.action_fragment2_to_fragment1)
            }

            bnToThird.setOnClickListener {
                findNavController().navigate(R.id.action_fragment2_to_fragment3)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}