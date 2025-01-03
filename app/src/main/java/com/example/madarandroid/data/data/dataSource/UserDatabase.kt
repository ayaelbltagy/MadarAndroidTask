package com.example.madarandroid.data.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.madarandroid.data.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 2
)
abstract  class UserDatabase : RoomDatabase(){
    abstract val dao: UserInfoDao
}