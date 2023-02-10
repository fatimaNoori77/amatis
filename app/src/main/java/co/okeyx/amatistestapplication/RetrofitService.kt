package co.okeyx.amatistestapplication

import co.okeyx.amatistestapplication.model.CoinResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("live-price")
    fun callApi(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<CoinResponseModel>
}