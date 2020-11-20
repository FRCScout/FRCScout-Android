package com.alphadevelopmentsolutions.frcscout.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.extension.toByteArray
import com.alphadevelopmentsolutions.frcscout.interfaces.Constants
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity()
abstract class Table protected constructor(
    @PrimaryKey @ColumnInfo(name = "id", index = true) var id: ByteArray = Constants.UUID_GENERATOR.generate().toByteArray(),
    @SerializedName("last_modified") @ColumnInfo(name = "last_modified_date", defaultValue = "(strftime('%s','now'))") var lastModifiedDate: Date = Date(),
    @Transient @ColumnInfo(name = "is_draft", defaultValue = "0") var isDraft: Boolean = false
) {

    abstract override fun toString(): String
}
