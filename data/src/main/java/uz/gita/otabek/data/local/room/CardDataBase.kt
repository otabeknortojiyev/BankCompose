package uz.gita.otabek.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.otabek.common.RecentTransfersData
import uz.gita.otabek.common.response.CardResponse

@Database(entities = [CardResponse.CardItem::class, RecentTransfersData::class], version = 1)
abstract class CardDataBase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    abstract fun transfersDao(): TransfersDao
}