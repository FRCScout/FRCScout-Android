package com.alphadevelopmentsolutions.frcscout.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.enums.ChecklistStatus
import com.alphadevelopmentsolutions.frcscout.interfaces.TableName
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

@Entity(
    tableName = TableName.CHECKLIST_ITEM_RESULT,
    inheritSuperIndices = true
)
class ChecklistItemResult(
    @SerializedName("checklist_item_id") @ColumnInfo(name = "checklist_item_id", index = true) var checklistItemId: ByteArray,
    @SerializedName("match_id") @ColumnInfo(name = "match_id", index = true) var matchId: ByteArray,
    @SerializedName("status") @ColumnInfo(name = "status") var status: ChecklistStatus? = null,
    @SerializedName("completed_date") @ColumnInfo(name = "completed_date") var completedDate: Date,
    @SerializedName("completed_by_id") @ColumnInfo(name = "completed_by_id", index = true) var completedById: ByteArray,
    @SerializedName("is_public") @ColumnInfo(name = "is_public", defaultValue = "0") var isPublic: Boolean = false
) : SubmittableTable(null, null, ByteArray(0)) {

    companion object : StaticTable<ChecklistItemResult> {
        override fun create(): ChecklistItemResult =
            ChecklistItemResult(
                ByteArray(0),
                ByteArray(0),
                null,
                Date(),
                ByteArray(0),
                false
            )
    }

    override fun toString(): String =
        status?.toString() ?: ""
}