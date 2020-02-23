package com.alphadevelopmentsolutions.frcscout.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alphadevelopmentsolutions.frcscout.classes.table.account.ChecklistItemResult
import com.alphadevelopmentsolutions.frcscout.interfaces.TableName
import com.alphadevelopmentsolutions.frcscout.view.database.ChecklistItemDatabaseView
import io.reactivex.Flowable
import java.util.*

@Dao
interface ChecklistItemResultDao {

    /**
     * Gets all [ChecklistItemResult] objects from the database
     */
    @Query("SELECT * FROM checklist_item_results")
    fun getObjs(): Flowable<List<ChecklistItemResult>>

    /**
     * Gets all [ChecklistItemResult] objects from the database based on [ChecklistItemResult.id]
     * @param id specified the id to sort the [ChecklistItemResult] object by
     */
    @Query("SELECT * FROM checklist_item_results where id = :id")
    fun getObjWithId(id: String): Flowable<ChecklistItemResult>

    @Query(
            """
                SELECT * FROM ${TableName.CHECKLIST_ITEM}
                LEFT JOIN ${TableName.CHECKLIST_ITEM_RESULT} ON ${TableName.CHECKLIST_ITEM_RESULT}.checklistItemId = ${TableName.CHECKLIST_ITEM}.id
                WHERE ${TableName.CHECKLIST_ITEM_RESULT}.matchId = :matchId
                """
    )
    fun getObjsViewWithMatch(matchId: UUID): Flowable<List<ChecklistItemDatabaseView>>

    /**
     * Inserts a new [ChecklistItemResult] object into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(checklistItemResult: ChecklistItemResult)

    /**
     * Inserts a list of [ChecklistItemResult] object into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(checklistItemResults: List<ChecklistItemResult>)

    /**
     * Deletes all [ChecklistItemResult] objects from the database
     */
    @Query("DELETE FROM checklist_item_results")
    suspend fun clear()

}