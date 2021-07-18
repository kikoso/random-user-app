package com.enrique.randomuserapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enrique.randomuserapp.persistence.dao.UserDao
import com.enrique.randomuserapp.persistence.dbEntity.UserDBEntity

@Database(
    entities = [UserDBEntity::class],
    version = 1,
    exportSchema = true
)
abstract class RandomUserAppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: RandomUserAppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(applicationContext: Context): RandomUserAppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                RandomUserAppDatabase::class.java, "randomuser-db"
            ).build()
        }
    }
}


