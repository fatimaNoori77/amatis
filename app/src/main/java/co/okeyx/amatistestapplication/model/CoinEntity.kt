package co.okeyx.amatistestapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tblCoin")
data class CoinEntity(
    @PrimaryKey var id : String, // bitcoin
    val currentPrice : Float,
    val marketCapRank : Int
    )
