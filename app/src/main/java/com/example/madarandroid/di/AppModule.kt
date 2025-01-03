package com.example.madarandroid.di

import android.app.Application
import androidx.room.Room
import com.example.madarandroid.data.data.dataSource.UserDatabase
import com.example.madarandroid.data.data.repository.Repository
import com.example.madarandroid.data.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            "MyDataBase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }



    @Provides
    @Singleton
    fun provideMyRepository(mydb: UserDatabase) : Repository {
        return RepositoryImpl(mydb.dao)
    }
}