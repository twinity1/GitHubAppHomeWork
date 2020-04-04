package com.example.githubhomework

import android.app.Application
import androidx.room.Room
import com.example.githubhomework.persistence.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, "master")
            .fallbackToDestructiveMigration()
            .build()
        myModule.single { db }

        startKoin {
            androidContext(applicationContext)
            modules(myModule)
        }
    }
}