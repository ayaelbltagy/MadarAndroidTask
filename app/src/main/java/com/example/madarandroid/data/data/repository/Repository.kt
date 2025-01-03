package com.example.madarandroid.data.data.repository

import com.example.madarandroid.data.data.dataSource.UserInfoDao
import com.example.madarandroid.data.data.entity.UserEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface Repository {

    suspend fun insert(user: UserEntity)

    suspend fun getAllUsers(): Flow<List<UserEntity>>

}

class RepositoryImpl @Inject constructor(
    private val dao: UserInfoDao,
) : Repository {
    override suspend fun insert(user: UserEntity) {
        withContext(IO) {
            dao.insert(user)
        }
    }

    override suspend fun getAllUsers(): Flow<List<UserEntity>> {
        return withContext(IO) {
            dao.getAllUsers()
        }
    }

}