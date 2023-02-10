package co.okeyx.amatistestapplication

import android.app.Activity
import android.app.Application
import android.graphics.Typeface
import co.okeyx.amatistestapplication.data.CoinDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MyApplication : Application() {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var myApi: RetrofitService
        lateinit var database: CoinDatabase
        lateinit var currentActivity: Activity

        const val iranSans = "fonts/IRANSans.otf"
        const val iranSansBold = "fonts/IRANSANS_BOLD.TTF"
        const val iranSansMedium = "fonts/IRANSANS_MEDIUM.TTF"
        lateinit var iranSansTF: Typeface
        lateinit var iranSansBoldTF: Typeface
        lateinit var iranSansMediumTF: Typeface

        fun getRetrofitService(): RetrofitService {
            return myApi
        }

    }

    override fun onCreate() {
        super.onCreate()
        database = CoinDatabase.getDatabase(applicationContext)
        retrofitInstance()
        initTypeFace()
    }

    private fun initTypeFace() {
        iranSansTF = Typeface.createFromAsset(assets, iranSans)
        iranSansBoldTF = Typeface.createFromAsset(assets, iranSansBold)
        iranSansMediumTF = Typeface.createFromAsset(assets, iranSansMedium)
    }

    private fun retrofitInstance() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
// java.net.UnknownHostException: Unable to resolve host "ok-ex.io": No address associated with hostname
        retrofit = Retrofit
            .Builder()
            .baseUrl("https://ok-ex.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        myApi = retrofit.create(RetrofitService::class.java)
    }

}