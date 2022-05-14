package com.dongeul.adpager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
@Entity
data class
Content(
    @PrimaryKey
    @field:Json(name = "id") var id: Int? = null,
    @field:Json(name = "name") var name: String? = null,
    @field:Json(name = "imageUrl") var imageUrl: String? = null,
    @field:Json(name = "firstDisplayPriority") var firstDisplayPriority: Int? = null,
    @field:Json(name = "firstDisplayWeight") var firstDisplayWeight: Int? = null,
    @field:Json(name = "frequency") var frequency: Int? = null,
    @field:Json(name = "landing_url") var landingUrl: String? = null,
    var contentType: ContentsType? = null,
    var isLiked: Boolean = false,
    var showingPriority:Int = 0
) : Parcelable

enum class ContentsType {
    AD, CONTENT
}

@JsonClass(generateAdapter = true)
data class Campaigns(
    @field:Json(name = "campaigns") var campaigns: List<Content> = listOf()
)

