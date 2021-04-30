package com.alphadevelopmentsolutions.frcscout.data.repositories

import com.alphadevelopmentsolutions.frcscout.data.dao.ChecklistItemDao
import com.alphadevelopmentsolutions.frcscout.data.models.ChecklistItem

class ChecklistItemRepository(private val dao: ChecklistItemDao) : MasterRepository<ChecklistItem>(dao) {
    override suspend fun deleteAll() =
        dao.deleteAll()
}