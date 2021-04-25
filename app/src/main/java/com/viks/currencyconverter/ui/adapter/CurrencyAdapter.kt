package com.viks.currencyconverter.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viks.currencyconverter.databinding.RvItemCurrencyBinding
import com.viks.currencyconverter.utils.Calculation

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    private var mainList: MutableList<Pair<String, Double>> = mutableListOf()
    fun updateRecyclerView(d: Map<String, Double>) {
            mainList = d.toList() as MutableList<Pair<String, Double>>
            notifyDataSetChanged()
    }

    inner class CurrencyViewHolder(private val binding: RvItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pair: Pair<String, Double>) {
            binding.tvCurrency.text = pair.first.substring(3, 6)
            binding.tvAmount.text = pair.second.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding =
            RvItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int {
        return mainList.count()
    }

    fun updateAmount(newMap: Map<String, Double>, num: Double) {
        if (newMap.count() > 0) {
            mainList.clear()
            mainList = newMap.let { Calculation(it).getChangedData(num) }
                .toList() as MutableList<Pair<String, Double>>
            notifyDataSetChanged()

            println()
            println("Num ======== $num")
            println()
            println("newMap ======== ${mainList}}")
        }
    }
}
