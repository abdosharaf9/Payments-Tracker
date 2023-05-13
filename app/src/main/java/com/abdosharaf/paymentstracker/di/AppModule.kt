package com.abdosharaf.paymentstracker.di

import android.content.Context
import androidx.room.Room
import com.abdosharaf.paymentstracker.Constants.DATABASE_NAME
import com.abdosharaf.paymentstracker.db.PaymentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, PaymentDatabase::class.java, DATABASE_NAME)
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: PaymentDatabase) = db.paymentDao()
}