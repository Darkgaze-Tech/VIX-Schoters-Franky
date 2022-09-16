package com.example.mynewsapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mynewsapp.model.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun NewsDao(): NewsDao

    companion object {

        @Volatile
        private var INSTANCE: NewsDataBase? = null

        fun getDatabase(context: Context): NewsDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): NewsDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                NewsDataBase::class.java,
                "news_database.db"
            )
                .build()
        }
    }

}