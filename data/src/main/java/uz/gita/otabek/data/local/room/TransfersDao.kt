package uz.gita.otabek.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.otabek.common.RecentTransfersData

@Dao
interface TransfersDao {

    @Query("SELECT * FROM recent_transfers")
    fun getAllRecentTransfers(): List<RecentTransfersData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecentTransfers(list: List<RecentTransfersData>)
}