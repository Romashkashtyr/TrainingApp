package com.example.main.ui.water_fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.base.BaseFragment
import com.example.main.data.entitieModules.Water
import com.example.main.databinding.WaterFragmentBinding
import com.example.main.di.MainComponent
import com.example.main.di.modules.WaterFragmentFactory
import com.example.main.ui.water_rc_view.WaterAdapter
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class WaterFragment: BaseFragment(), WaterView {

    private var _binding: WaterFragmentBinding? = null

    val binding get() = _binding!!

    @Inject
    lateinit var factory: WaterFragmentFactory

    private val presenter by moxyPresenter {
        factory.createWaterFragmentPresenter()
    }

    private lateinit var adapter: WaterAdapter

    override fun onAttach(context: Context) {
        MainComponent.getMainInstance().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = WaterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()

        binding.arrowBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun showTodayWater(amount: Int) {
        binding.todayWater.text =  "$amount мл"

        binding.waterProgress.progress = amount
    }

    override fun showWaterHistory(history: List<Water>) {
        adapter.submitList(history)
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun stopLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun initRecycler() {
        adapter = WaterAdapter()

        binding.historyRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@WaterFragment.adapter
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newWaterInstance() = WaterFragment()
    }
}