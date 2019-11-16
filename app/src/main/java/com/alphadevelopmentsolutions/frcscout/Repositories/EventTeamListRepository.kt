package com.alphadevelopmentsolutions.frcscout.Repositories

import com.alphadevelopmentsolutions.frcscout.Classes.Tables.EventTeamList
import com.alphadevelopmentsolutions.frcscout.Dao.EventTeamListDao
import io.reactivex.Flowable


class EventTeamListRepository(private val eventTeamListDao: EventTeamListDao) {

    /**
     * Gets all [EventTeamList] objects from the database
     * @see EventTeamListDao.getObjs
     */
    val objs: Flowable<List<EventTeamList>> = eventTeamListDao.getObjs()

    /**
     * Gets all [EventTeamList] objects from the database based on [EventTeamList.id]
     * @param id specified the id to sort the [EventTeamList] object by
     * @see EventTeamListDao.getObjWithId
     */
    fun objWithId(id: String) = eventTeamListDao.getObjWithId(id)

    /**
     * Inserts a [EventTeamList] object into the database
     * @see EventTeamListDao.insert
     */
    suspend fun insert(eventTeamList: EventTeamList) {
        eventTeamListDao.insert(eventTeamList)
    }

    /**
     * Inserts a [EventTeamList] object into the database
     * @see EventTeamListDao.insertAll
     */
    suspend fun insertAll(eventTeamLists: List<EventTeamList>) {
        eventTeamListDao.insertAll(eventTeamLists)
    }

}