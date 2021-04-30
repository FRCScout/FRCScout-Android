package com.alphadevelopmentsolutions.frcscout.data.repositories

import com.alphadevelopmentsolutions.frcscout.data.dao.MatchDao
import com.alphadevelopmentsolutions.frcscout.data.models.Event
import com.alphadevelopmentsolutions.frcscout.data.models.Match
import com.alphadevelopmentsolutions.frcscout.data.models.Team

class MatchRepository(private val dao: MatchDao) : MasterRepository<Match>(dao) {
    override suspend fun deleteAll() =
        dao.deleteAll()

    fun getForEvent(event: Event, team: Team?) =
        dao.getForEvent(event.id, team?.id)
}