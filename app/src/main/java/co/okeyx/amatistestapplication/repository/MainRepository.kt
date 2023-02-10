package co.okeyx.amatistestapplication.repository

import co.okeyx.amatistestapplication.RetrofitService
import retrofit2.http.Query

class MainRepository(private val retrofitService: RetrofitService, val page: Int, val size: Int) {

    fun fetchCoins() = retrofitService.callApi(page,size)
}