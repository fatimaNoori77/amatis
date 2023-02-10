package co.okeyx.amatistestapplication.model

import com.google.gson.annotations.SerializedName

class CoinResponseModel {
    @SerializedName("status")
    var status: Boolean = true

    @SerializedName("msg")
    var message: String = ""

    @SerializedName("total")
    var total: Int = 0

    @SerializedName("result")
    var result = ArrayList<Result>()

    class Result{

        @SerializedName("id")
        var id : String = ""

        @SerializedName("current_price")
        var currentPrice : Float = 0f

        @SerializedName("image")
        var imageUrl : String = ""

        @SerializedName("market_cap_rank")
        var marketCapRank : Int = 0
    }
}