package ru.geekbrains.ponomarevss.popllib.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String?,
    @Expose val name: String?,
    @Expose val forksCount: String?,
    @Expose val fullName: String?
) : Parcelable