package co.okeyx.amatistestapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.okeyx.amatistestapplication.model.CoinEntity
import co.okeyx.amatistestapplication.model.CoinResponseModel

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCoin(vararg coinModel: CoinEntity)

    @Query("select * from tblCoin")
    fun getCoins(): List<CoinEntity>

}