package com.alphadevelopmentsolutions.frcscout.repository

import com.alphadevelopmentsolutions.frcscout.dao.MasterDao

abstract class MasterRepository<Table>(private val dao: MasterDao<Table>) {

    open suspend fun insert(obj: Table) = dao.upsert(obj)
    open suspend fun insertAll(objs: List<Table>) = dao.upsertAll(objs)
    open suspend fun delete(obj: Table) = dao.delete(obj)

    abstract suspend fun deleteAll()
}