package com.alphadevelopmentsolutions.frcscout.table

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.interfaces.TableName
import com.google.gson.annotations.SerializedName
import java.io.File
import java.util.*

@Entity(
    tableName = TableName.TEAM,
    inheritSuperIndices = true
)
class Team(
    @SerializedName("number") @ColumnInfo(name = "number") var number: Int,
    @SerializedName("name") @ColumnInfo(name = "name") var name: String,
    @SerializedName("city") @ColumnInfo(name = "city") var city: String? = null,
    @SerializedName("state_province") @ColumnInfo(name = "state_province") var stateProvince: String? = null,
    @SerializedName("country") @ColumnInfo(name = "country") var country: String? = null,
    @SerializedName("rookie_year") @ColumnInfo(name = "rookie_year") var rookieYear: Int? = null,
    @SerializedName("facebook_url") @ColumnInfo(name = "facebook_url") var facebookUrl: String? = null,
    @SerializedName("instagram_url") @ColumnInfo(name = "instagram_url") var instagramUrl: String? = null,
    @SerializedName("twitter_url") @ColumnInfo(name = "twitter_url") var twitterUrl: String? = null,
    @SerializedName("youtube_url") @ColumnInfo(name = "youtube_url") var youtubeUrl: String? = null,
    @SerializedName("website_url") @ColumnInfo(name = "website_url") var websiteUrl: String? = null,
    @SerializedName("avatar_uri") @ColumnInfo(name = "avatar_uri") var avatarUri: String? = null
) : Table() {

    companion object : StaticTable<Team> {
        override fun create(): Team =
            Team(
                -1,
                ""
            )
    }

    override fun toString(): String =
        name
}
