package com.alphadevelopmentsolutions.frcscout.repository

import android.content.Context
import com.alphadevelopmentsolutions.frcscout.dao.MatchDao
import com.alphadevelopmentsolutions.frcscout.table.Match

class MatchRepository(private val dao: MatchDao, private val context: Context) : MasterRepository<Match>(dao), SubmittableTable<Match> {
    override suspend fun deleteAll() =
        dao.deleteAll()

    override fun getAllRaw(isDraft: Boolean?): List<Match> =
        dao.getAllRaw(isDraft)

    fun getAll() =
        dao.getAll()
}