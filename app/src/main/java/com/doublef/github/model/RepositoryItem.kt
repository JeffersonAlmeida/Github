package com.doublef.github.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepositoryItem(
    @SerializedName( "id") val id: String,
    @SerializedName( "full_name") val name: String?,
    @SerializedName( "description") val description: String?
): Parcelable
