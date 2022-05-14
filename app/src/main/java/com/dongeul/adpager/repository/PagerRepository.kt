package com.dongeul.adpager.repository

import com.dongeul.adpager.db.PagerDao
import com.dongeul.adpager.model.Content
import com.dongeul.adpager.model.ContentsType
import com.dongeul.adpager.model.Result
import com.dongeul.adpager.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PagerRepository @Inject constructor(
    private val pagerDao: PagerDao,
    private val apiService: ApiService
) : Repository {
    fun getRemoteData() = flow {
        emit(Result.Loading)
        var campaigns: List<Content>
        coroutineScope {
            val config = async { apiService.getConfig() }.await()// 광고 3 : 컨텐트 1
            val ad = async { apiService.getAd() }.await()
            val content = async { apiService.getContent() }.await()
            ad.campaigns.sortedByDescending { it.firstDisplayPriority }
                .map { it.contentType = ContentsType.AD }
            content.campaigns.sortedByDescending { it.firstDisplayPriority }
                .map { it.contentType = ContentsType.CONTENT }
            campaigns = ad.campaigns.plus(content.campaigns)

            val shuffledCampaigns = mutableListOf<Content>()


            pagerDao.insertLocalData(campaigns)
            pagerDao.getLocalData().collect {
                if (it.isNotEmpty()) {
                    emit(Result.Success(it))
                } else {
                    emit(Result.Success(campaigns))
                }
            }
        }
    }.flowOn(Dispatchers.Default)

    suspend fun updateContent(content: Content) = pagerDao.updateContent(content)
    suspend fun deleteContent(content: Content) = pagerDao.deleteContent(content)
}