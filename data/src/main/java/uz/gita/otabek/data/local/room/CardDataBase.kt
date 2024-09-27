package uz.gita.otabek.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.otabek.common.response.CardResponse
import uz.gita.otabek.dictionaryapp.room.CardDao

@Database(entities = [CardResponse.CardItem::class], version = 1)
abstract class CardDataBase : RoomDatabase() {

    abstract fun cardDao(): CardDao
}