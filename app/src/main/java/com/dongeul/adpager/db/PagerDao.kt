package com.dongeul.adpager.db

import androidx.room.*
import com.dongeul.adpager.model.Campaigns
import com.dongeul.adpager.model.Content
import kotlinx.coroutines.flow.Flow


@Dao
interface PagerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalData(contentList: List<Content>)

    @Query("SELECT * FROM Content")
    fun getLocalData() : Flow<List<Content>>

    @Delete
    suspend fun deleteContent(content: Content)

    @Update
    fun updateContent(content:Content)



}