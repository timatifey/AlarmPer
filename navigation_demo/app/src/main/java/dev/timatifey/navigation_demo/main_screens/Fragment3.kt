package dev.timatifey.navigation_demo.main_screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dev.timatifey.navigation_demo.R
import dev.timatifey.navigation_demo.databinding.Fragment3Binding

class Fragment3: Fragment() {

    private var binding: Fragment3Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = Fragment3Binding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding!!) {
            bnToFirst.setOnClickListener {
                findNavController().navigate(R.id.action_fragment3_to_fragment1)
            }

            bnToSecond.setOnClickListener {
                findNavController().navigate(R.id.action_fragment3_to_fragment2)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}