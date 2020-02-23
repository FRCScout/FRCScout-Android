package com.alphadevelopmentsolutions.frcscout.converter

import androidx.room.TypeConverter
import com.alphadevelopmentsolutions.frcscout.classes.table.core.Match
import com.alphadevelopmentsolutions.frcscout.enums.MatchType

class MatchTypeConverter {

    @TypeConverter
    fun matchTypeToString(matchType: MatchType) = matchType.toString()

    @TypeConverter
    fun matchTypeFromString(matchType: String) = MatchType.fromString(matchType)
}