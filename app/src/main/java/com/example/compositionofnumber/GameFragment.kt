package com.example.compositionofnumber

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compositionofnumber.databinding.FragmentGameBinding
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import com.example.compositionofnumber.domain.entities.GameResult
import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private lateinit var level:Level

    private var _binding: FragmentGameBinding? = null
    private  val binding: FragmentGameBinding
    get() = _binding ?:  throw  RuntimeException("Fragment not found")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArguments()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvLeftNumber.setOnClickListener{
            launchGameFinishedFragment(GameResult(
                false,10,20, GameSettings(10,
                    23,44,10)))
        }

        
    }

    private  fun launchGameFinishedFragment (gameResult: GameResult) {
              requireActivity().supportFragmentManager.beginTransaction()
                  .replace(R.id.main_container,GameFinishedFragment.newInstance(gameResult))
                  .addToBackStack(null)
                  .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArguments() {
           requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
               level = it
           }
    }

    companion object {

        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level):GameFragment {
            return  GameFragment().apply {
                arguments = Bundle().apply {
                             putParcelable(KEY_LEVEL,level)
                }
            }
        }
    }
}
