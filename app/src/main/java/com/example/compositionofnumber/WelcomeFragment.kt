package ru.sumin.composition.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.compositionofnumber.ChooseLevelFragment
import com.example.compositionofnumber.R
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException


class WelcomeFragment : Fragment() {

    private  var _binding: FragmentWelcomeBinding? = null
    private  val  binding: FragmentWelcomeBinding
    get() {    return _binding ?: throw  RuntimeException("Fragment not found")}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener{
            launchChooseLevelFragment()

        }
    }
//     BEFORE JETPACK NAVIGATION
//    private fun launchChooseLevelFragment() {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.main_container,ChooseLevelFragment.newInstance())
//            .addToBackStack(ChooseLevelFragment.NAME)
//            .commit()
//
//    }
    private fun launchChooseLevelFragment() {
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment2)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}