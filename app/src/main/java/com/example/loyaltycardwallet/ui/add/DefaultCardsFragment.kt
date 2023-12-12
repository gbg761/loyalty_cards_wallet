package com.example.loyaltycardwallet.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loyaltycardwallet.App
import com.example.loyaltycardwallet.databinding.FragmentDefaultCardsBinding
import com.example.loyaltycardwallet.di.AppComponent
import com.example.loyaltycardwallet.model.InputCardWithType

class DefaultCardsFragment : Fragment() {

    private lateinit var appComponent: AppComponent
    private lateinit var defaultCardsViewModel: DefaultCardsViewModel

    private val defaultCardsAdapter = DefaultCardsAdapter()
    private var _binding: FragmentDefaultCardsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDefaultCardsBinding.inflate(inflater, container, false)

        appComponent = (requireActivity().application as App).appComponent
        val factory = appComponent.getDefaultCardsViewModelFactory()
        defaultCardsViewModel = ViewModelProvider(this, factory)[DefaultCardsViewModel::class.java]

        initUi()
        subscribeUi()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUi() {
        binding.defaultCardsRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.defaultCardsRecyclerView.adapter = defaultCardsAdapter
    }

    private fun subscribeUi() {
        defaultCardsViewModel.inputCards.observe(viewLifecycleOwner) { cards: List<InputCardWithType> ->
            defaultCardsAdapter.updateList(cards)
        }
    }
}