package com.alphadevelopmentsolutions.frcscout.repository

import com.alphadevelopmentsolutions.frcscout.classes.table.account.ScoutCardInfo
import com.alphadevelopmentsolutions.frcscout.dao.ScoutCardInfoDao
import io.reactivex.Flowable
import java.util.*


class ScoutCardInfoRepository(private val scoutCardInfoDao: ScoutCardInfoDao) {

    /**
     * Gets all [ScoutCardInfo] objects from the database
     * @see ScoutCardInfoDao.getObjs
     */
    val objs: Flowable<List<ScoutCardInfo>> = scoutCardInfoDao.getObjs()

    /**
     * Gets all [ScoutCardInfo] objects from the database based on [ScoutCardInfo.id]
     * @param id specified the id to sort the [ScoutCardInfo] object by
     * @see ScoutCardInfoDao.getObjWithId
     */
    fun objWithId(id: String) = scoutCardInfoDao.getObjWithId(id)

    fun objsViewForTeam(teamId: UUID, matchId: UUID) = scoutCardInfoDao.getObjsViewForTeam(teamId, matchId)


    /**
     * Inserts a [ScoutCardInfo] object into the database
     * @see ScoutCardInfoDao.insert
     */
    suspend fun insert(scoutCardInfo: ScoutCardInfo) {
        scoutCardInfoDao.insert(scoutCardInfo)
    }

    /**
     * Inserts a [ScoutCardInfo] object into the database
     * @see ScoutCardInfoDao.insertAll
     */
    suspend fun insertAll(scoutCardInfos: List<ScoutCardInfo>) {
        scoutCardInfoDao.insertAll(scoutCardInfos)
    }

}