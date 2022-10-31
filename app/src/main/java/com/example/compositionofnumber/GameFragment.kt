package com.example.compositionofnumber

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.compositionofnumber.databinding.FragmentGameBinding
import com.example.compositionofnumber.databinding.FragmentWelcomeBinding
import com.example.compositionofnumber.domain.entities.GameResult
import com.example.compositionofnumber.domain.entities.GameSettings
import com.example.compositionofnumber.domain.entities.Level
import com.example.compositionofnumber.presentation.GameViewModel
import java.lang.RuntimeException

class GameFragment : Fragment() {

    private lateinit var level:Level

    //через Lazy
    private val viewModel by lazy {
        ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application)
        )[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }

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
         observeViewModel()
        viewModel.startGame(level)
        setClickListenersToOptions()

       

    }
    private fun setClickListenersToOptions() {
        for (option in tvOptions) {
            option.setOnClickListener{
                viewModel.chooseAnswer(option.text.toString().toInt())
            }
        }
    }

    private  fun observeViewModel() {
        viewModel.questions.observe(viewLifecycleOwner){
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentageOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it,true)
        }
        viewModel.enoughCountOfAnswers.observe(viewLifecycleOwner) {
            val colorResId = if(it) {
                android.R.color.holo_green_light
            }   else {
                android.R.color.holo_green_dark
            }
            val color = ContextCompat.getColor(requireContext(), colorResId)
            binding.tvAnswersProgress.setTextColor(color)
        }
        viewModel.enoughPercentageOfAnswers.observe(viewLifecycleOwner) {
            val colorResId = if(it) {
                android.R.color.holo_red_light
            }   else {
                android.R.color.holo_red_dark
            }
            val color = ContextCompat.getColor(requireContext(), colorResId)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner){
                        binding.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.tvAnswersProgress.text = it
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
