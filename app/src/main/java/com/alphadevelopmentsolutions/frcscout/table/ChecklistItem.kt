package com.alphadevelopmentsolutions.frcscout.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.interfaces.TableName
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = TableName.CHECKLIST_ITEM,
    inheritSuperIndices = true
)
class ChecklistItem(
    @SerializedName("team_account_id") @ColumnInfo(name = "team_account_id", index = true) var teamAccountId: ByteArray,
    @SerializedName("year_id") @ColumnInfo(name = "year_id", index = true) var yearId: ByteArray,
    @SerializedName("title") @ColumnInfo(name = "title") var title: String,
    @SerializedName("description") @ColumnInfo(name = "description") var description: String
) : SubmittableTable(null, null, ByteArray(0)) {

    companion object : StaticTable<ChecklistItem> {
        override fun create(): ChecklistItem =
            ChecklistItem(
                ByteArray(0),
                ByteArray(0),
                "",
                ""
            )
    }

    override fun toString(): String =
        title
}