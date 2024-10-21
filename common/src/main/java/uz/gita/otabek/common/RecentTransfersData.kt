package uz.gita.otabek.common

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("recent_transfers")
data class RecentTransfersData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val type: String,
    val name: String,
    val pan: String
)
