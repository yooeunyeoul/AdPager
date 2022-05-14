package com.dongeul.adpager.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Ratio(
    @field:Json(name="firstAdRatio" ) var firstAdRatio : String? = null
):Parcelable
