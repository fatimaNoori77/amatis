package co.okeyx.amatistestapplication

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
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = coinModels[position]

        holder.binding.txtPrice.text =
            "${StringHelper.toPersianDigits((model.currentPrice.toString()))} تومان "
        var image = R.mipmap.golden
        when (model.marketCapRank) {
            1 -> {
                image = R.mipmap.golden
            }
            2 -> {
                image = R.mipmap.silver
            }
            3 -> {
                image = R.mipmap.bronze
            }
        }
        holder.binding.imgRank.setImageResource(image)
        Picasso.get().load(model.imageUrl).error(R.drawable.ic_round_downloading_24).into(holder.binding.imgCoin)

    }

    override fun getItemCount(): Int {
        return coinModels.size
    }
}