package uz.gita.otabek.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.otabek.common.response.CardResponse

@Dao
interface CardDao {

    @Query("SELECT * FROM cards")
    fun getAllCards(): List<CardResponse.CardItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCards(cards: List<CardResponse.CardItem>)
}