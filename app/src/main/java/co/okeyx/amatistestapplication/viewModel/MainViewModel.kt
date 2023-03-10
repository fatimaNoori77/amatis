package co.okeyx.amatistestapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.okeyx.amatistestapplication.MyApplication
import co.okeyx.amatistestapplication.RetrofitService
import co.okeyx.amatistestapplication.model.CoinEntity
import co.okeyx.amatistestapplication.model.CoinResponseModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val retrofitService: RetrofitService, val page: Int) : ViewModel() {
    val coinList = MutableLiveData<List<CoinResponseModel.Result>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCoins() {
        val response = retrofitService.callApi(page,30)
        response.enqueue(object : Callback<CoinResponseModel> {
            override fun onResponse(
                call: Call<CoinResponseModel>,
                response: Response<CoinResponseModel>
            ) {
                if (response.isSuccessful) {
                    if (response.body()!!.status) {

                        for (i in 0 until response.body()!!.result.size) {
                            val coin = CoinEntity(
                                response.body()!!.result[i].id,
                                response.body()!!.result[i].currentPrice,
                                response.body()!!.result[i].marketCapRank,
                                response.body()!!.result[i].imageUrl
                            )
                            MyApplication.database.coinDao().insertCoin(coin)
                        }

                        coinList.postValue(response.body()!!.result)

                    }
                }
            }

            override fun onFailure(call: Call<CoinResponseModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}