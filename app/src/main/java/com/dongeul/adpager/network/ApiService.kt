package com.dongeul.adpager.network

import com.dongeul.adpager.model.Campaigns
import com.dongeul.adpager.model.Content
import com.dongeul.adpager.model.Ratio
import retrofit2.http.GET

interface ApiService {

    @GET("test_config.json")
    suspend fun getConfig():Ratio

    @GET("test_ads.json")
    suspend fun getAd():Campaigns

    @GET("test_articles.json")
    suspend fun getContent():Campaigns
}