package com.example.main.ui.steps_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.main.data.entitieModules.Steps
import com.example.main.databinding.StepsFragmentBinding
import com.example.main.di.MainComponent
import com.example.main.di.modules.StepsFragmentFactory
import com.example.main.ui.steps_rc_view.StepsAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class StepsFragment: BaseFragment(), StepsView {

    private var _binding: StepsFragmentBinding? = null

    val binding get() = _binding!!

    @Inject
    lateinit var factory: StepsFragmentFactory

    private val presenter by moxyPresenter {
        factory.createPresenter()
    }

    private lateinit var adapter: StepsAdapter

    override fun onAttach(context: Context) {
        MainComponent.getMainInstance().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("STEPS_NAV", "StepsFragment created")
        _binding = StepsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun showTodaySteps(steps: Int) {
        binding.todaySteps.text =  "$steps шагов"

        binding.stepsProgress.progress = steps
    }

    override fun showStepsHistory(history: List<Steps>) {
        adapter.submitList(history)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun initRecycler() {
        adapter = StepsAdapter()

        binding.historyRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@StepsFragment.adapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newStepsInstance() = StepsFragment()
    }
}