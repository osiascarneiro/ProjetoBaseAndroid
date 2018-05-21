package com.solutis.base.baseproj.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "post")
class Post {

    @SerializedName("userId")
    @ColumnInfo(name = "user_id")
    var userId = ""

    @SerializedName("id")
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id = 0

    @SerializedName("title")
    @ColumnInfo(name = "title")
    var title = ""

    @SerializedName("body")
    @ColumnInfo(name = "body")
    var body = ""

}