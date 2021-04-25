package com.viks.currencyconverter.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.viks.currencyconverter.R
import com.viks.currencyconverter.databinding.FragmentHomeBinding
import com.viks.currencyconverter.ui.adapter.CurrencyAdapter
import com.viks.currencyconverter.ui.viewmodel.HomeFragmentViewModel
import com.viks.currencyconverter.utils.Calculation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    @Inject
    lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        initRecyclerView()
        setupObservers()
        initEditTextListener()

        viewModel.checkAndFetchData()
    }

    private fun initEditTextListener() {
        binding.etAmount.doAfterTextChanged {
            if (!it.isNullOrEmpty()) {
                try {
                    currencyAdapter.updateAmount(newMap, it.toString().toDouble())
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvCurrency.apply {
            adapter = currencyAdapter
            layoutManager = GridLayoutManager(activity, 3)
        }
    }

    private var oldMap = mapOf<String, Double>()
    private fun setupObservers() {
        viewModel.currencyList.observe(viewLifecycleOwner, {
            if (it.isNotEmpty())
                it[0].currencies?.let { it1 -> showCurrencyDropDown(it1) }
        })

        viewModel.liveExchange.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                showRecyclerView(it[0].quotes)
                oldMap = it[0].quotes!!
            }
            hideProgressBar()
        })
    }

    private fun showRecyclerView(mapLiveExchange: Map<String, Double>?) {
        if (!mapLiveExchange.isNullOrEmpty()) {
            currencyAdapter.updateRecyclerView(mapLiveExchange)
        }
    }

    var newMap = mapOf<String, Double>()
    private fun showCurrencyDropDown(mapCurrencyList: Map<String, String>) {
        val currencyList: List<String> = mapCurrencyList.toList().map { it.first }
        binding.spinnerCurrency.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            currencyList
        )
        // Set $1 and dropdown selection USD by default because our free api does not provide currency exchange from backend
        binding.spinnerCurrency.setSelection(currencyList.indexOf("USD"))

        binding.spinnerCurrency.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCurrency = parent?.getItemAtPosition(position).toString()
                    // reset to 1 when currency changed from drop down
                    binding.etAmount.setText("1")
                    newMap = Calculation(oldMap).getChangedData(
                        selectedCurrency,
                        binding.etAmount.text.toString().toDouble()
                    )

                    if (!newMap.isNullOrEmpty())
                        currencyAdapter.updateRecyclerView(newMap)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }

    private fun showProgressBar() {
        binding.llProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.llProgressBar.visibility = View.GONE
    }
}