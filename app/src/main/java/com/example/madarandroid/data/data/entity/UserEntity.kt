package com.example.madarandroid.data.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = tableName)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val userAge: Int,
    val userJobTitle: String,
    val userGender: String
)
const val tableName = "UserEntity"