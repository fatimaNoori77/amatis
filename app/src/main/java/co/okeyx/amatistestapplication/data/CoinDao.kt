package co.okeyx.amatistestapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import co.okeyx.amatistestapplication.model.CoinEntity

@Dao
interface CoinDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCoin(vararg coinModel: CoinEntity)

}