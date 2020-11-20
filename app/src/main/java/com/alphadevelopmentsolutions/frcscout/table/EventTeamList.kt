package com.alphadevelopmentsolutions.frcscout.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.interfaces.TableName
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(
    tableName = TableName.EVENT_TEAM_LIST,
    inheritSuperIndices = true
)
class EventTeamList(
    @SerializedName("team_id") @ColumnInfo(name = "team_id", index = true) var teamId: ByteArray,
    @SerializedName("event_id") @ColumnInfo(name = "event_id", index = true) var eventId: ByteArray
) : Table() {

    companion object : StaticTable<EventTeamList> {
        override fun create(): EventTeamList =
            EventTeamList(
                ByteArray(0),
                ByteArray(0)
            )
    }

    override fun toString(): String =
        ""
}
