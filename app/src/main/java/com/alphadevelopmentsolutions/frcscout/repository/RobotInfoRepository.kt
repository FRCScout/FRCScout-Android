package com.alphadevelopmentsolutions.frcscout.repository

import com.alphadevelopmentsolutions.frcscout.classes.table.account.RobotInfo
import com.alphadevelopmentsolutions.frcscout.dao.RobotInfoDao
import io.reactivex.Flowable
import java.util.*


class RobotInfoRepository(private val robotInfoKeyDao: RobotInfoDao) {

    /**
     * Gets all [RobotInfo] objects from the database
     * @see RobotInfoDao.getObjs
     */
    val objs: Flowable<List<RobotInfo>> = robotInfoKeyDao.getObjs()

    /**
     * Gets all [RobotInfo] objects from the database based on [RobotInfo.id]
     * @param id specified the id to sort the [RobotInfo] object by
     * @see RobotInfoDao.getObjWithId
     */
    fun objWithId(id: String) = robotInfoKeyDao.getObjWithId(id)

    fun objsViewForTeam(teamId: UUID) = robotInfoKeyDao.getObjsViewForTeam(teamId)

    /**
     * Inserts a [RobotInfo] object into the database
     * @see RobotInfoDao.insert
     */
    suspend fun insert(robotInfoKey: RobotInfo) {
        robotInfoKeyDao.insert(robotInfoKey)
    }

    /**
     * Inserts a [RobotInfo] object into the database
     * @see RobotInfoDao.insertAll
     */
    suspend fun insertAll(robotInfoKeys: List<RobotInfo>) {
        robotInfoKeyDao.insertAll(robotInfoKeys)
    }

    fun delete(robotInfo: RobotInfo) {
        robotInfoKeyDao.delete(robotInfo)
    }

    suspend fun clearData() = robotInfoKeyDao.clear()

}