package co.okeyx.amatistestapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import co.okeyx.amatistestapplication.databinding.ActivityMainBinding
import co.okeyx.amatistestapplication.helper.NetworkUtil
import co.okeyx.amatistestapplication.helper.NetworkUtil.TYPE_NOT_CONNECTED
import co.okeyx.amatistestapplication.model.CoinResponseModel
import co.okeyx.amatistestapplication.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val adapter = Adapter()
    var list = ArrayList<CoinResponseModel.Result>()
    var page = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData(page)
        initList()

        binding.nestedView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page = page.plus(1)
                binding.loading.visibility = View.VISIBLE
                loadData(page)
            }
        })
    }

    private fun loadData(pageNo: Int) {
        viewModel = MainViewModel(MyApplication.getRetrofitService(), pageNo)

        if (NetworkUtil.getConnectivityStatus(applicationContext) == TYPE_NOT_CONNECTED) {
            binding.vfList.displayedChild = 1
            adapter.setList(fetchDataFromDB())
            return
        }
        viewModel.coinList.observe(this) {
            binding.vfList.displayedChild = 1
            list.addAll(it)
            adapter.setList(list)
        }
        viewModel.errorMessage.observe(this) {
            binding.vfList.displayedChild = 2
            Log.e("TAG", "onCreate: $it")
            if (NetworkUtil.getConnectivityStatus(applicationContext) == TYPE_NOT_CONNECTED) {
                adapter.setList(fetchDataFromDB())
            }
        }

        viewModel.getAllCoins()
    }

    private fun fetchDataFromDB(): ArrayList<CoinResponseModel.Result> {
        var list = ArrayList<CoinResponseModel.Result>()
        for (i in 0 until MyApplication.database.coinDao().getCoins().size) {
            val model = CoinResponseModel.Result()
            model.marketCapRank = MyApplication.database.coinDao().getCoins()[i].marketCapRank
            model.imageUrl = MyApplication.database.coinDao().getCoins()[i].imageUrl
            model.currentPrice = MyApplication.database.coinDao().getCoins()[i].currentPrice
            list.add(model)

            adapter.setList(list)
        }
        return list
    }

    private fun initList() {
        binding.recyclerView.adapter = adapter
    }


    override fun onStart() {
        super.onStart()
        MyApplication.currentActivity = this
    }

    override fun onResume() {
        super.onResume()
        MyApplication.currentActivity = this
    }
}