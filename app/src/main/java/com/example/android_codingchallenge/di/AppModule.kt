package com.example.android_codingchallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.Context
import androidx.room.Room
import com.example.android_codingchallenge.data.local.ImageDao
import com.example.android_codingchallenge.data.local.ImageDatabase
import com.example.android_codingchallenge.data.remote.PixabayApi
import com.example.android_codingchallenge.data.repository.ImageRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePixabayApi(): PixabayApi =
        Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PixabayApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            "image_database"
        )
        .addMigrations(ImageDatabase.MIGRATION_1_2)
        .build()
    }

    @Provides
    fun provideImageDao(database: ImageDatabase): ImageDao {
        return database.imageDao()
    }

    @Provides
    @Singleton
    fun provideRepository(api: PixabayApi, dao: ImageDao): ImageRepository =
        ImageRepository(api, dao)
}
