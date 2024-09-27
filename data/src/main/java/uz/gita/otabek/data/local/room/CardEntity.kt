package uz.gita.otabek.data.local.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("cards")
data class CardEntity(
    @ColumnInfo("english")
    val english: String?,
    @ColumnInfo("uzbek")
    val uzbek: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("transcript")
    val transcript: String?,
    @ColumnInfo("is_favourite")
    val isFavourite: Int?,
    @ColumnInfo("type")
    val type: String?,
    @ColumnInfo("countable")
    val countable: String?
)