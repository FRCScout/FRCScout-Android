package com.alphadevelopmentsolutions.frcscout.view.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alphadevelopmentsolutions.frcscout.classes.RDatabase
import com.alphadevelopmentsolutions.frcscout.classes.table.ScoutCardInfo
import com.alphadevelopmentsolutions.frcscout.repository.ScoutCardInfoRepository
import io.reactivex.Flowable
import kotlinx.coroutines.launch

class ScoutCardInfoKeyViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
            ScoutCardInfoRepository(RDatabase.getInstance(application).scoutCardInfoDao())

    /**
     * Gets all [ScoutCardInfo] objects from the database
     * @see ScoutCardInfoRepository.objs
     */
    val objs: Flowable<List<ScoutCardInfo>>

    /**
     * Gets all [ScoutCardInfo] objects from the database based on [ScoutCardInfo.id]
     * @param id specified the id to sort the [ScoutCardInfo] object by
     * @see ScoutCardInfoRepository.objWithId
     */
    fun objWithId(id: String) = repository.objWithId(id)

    init {
        objs = repository.objs
    }

    /**
     * Inserts a [ScoutCardInfo] object into the database
     * @see ScoutCardInfoRepository.insert
     */
    fun insert(scoutCardInfo: ScoutCardInfo) = viewModelScope.launch {
        repository.insert(scoutCardInfo)
    }

    /**
     * Inserts a [ScoutCardInfo] object into the database
     * @see ScoutCardInfoRepository.insert
     */
    fun insertAll(scoutCardInfos: List<ScoutCardInfo>) = viewModelScope.launch {
        repository.insertAll(scoutCardInfos)
    }
}