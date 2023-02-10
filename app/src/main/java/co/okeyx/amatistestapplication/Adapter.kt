package co.okeyx.amatistestapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.okeyx.amatistestapplication.databinding.ItemListBinding
import co.okeyx.amatistestapplication.helper.StringHelper
import co.okeyx.amatistestapplication.helper.TypefaceUtil
import co.okeyx.amatistestapplication.model.CoinResponseModel
import com.squareup.picasso.Picasso

class Adapter : RecyclerView.Adapter<Adapter.MainViewHolder>() {
    var coinModels = mutableListOf<CoinResponseModel.Result>()

    fun setList(coins: List<CoinResponseModel.Result>) {
        this.coinModels = coins.toMutableList()
        notifyDataSetChanged()
    }

    class MainViewHolder(val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemListBinding.inflate(
            LayoutInflater.from(MyApplication.currentActivity),
            parent,
            false
        )
        TypefaceUtil.overrideFonts(binding.root)
        TypefaceUtil.overrideFonts(binding.txtRank, MyApplication.iranSansMediumTF)
        return MainViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = coinModels[position]

        holder.binding.txtPrice.text = "${StringHelper.toPersianDigits((model.currentPrice.toString()))} تومان "
        holder.binding.txtRank.text = StringHelper.toPersianDigits(model.marketCapRank.toString())
        Picasso.get().load(model.imageUrl).error(R.drawable.ic_round_downloading_24).into(holder.binding.imgCoin)

    }

    override fun getItemCount(): Int {
        return coinModels.size
    }
}