package com.example.android_codingchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android_codingchallenge.domain.entities.ImageEntity

@Database(entities = [ImageEntity::class], version = 2, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE images ADD COLUMN likes INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE images ADD COLUMN downloads INTEGER DEFAULT 0 NOT NULL")
                db.execSQL("ALTER TABLE images ADD COLUMN comments INTEGER DEFAULT 0 NOT NULL")
            }
        }
    }
}
