package com.abdosharaf.paymentstracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abdosharaf.paymentstracker.models.PaymentItem

@Database(
    entities = [PaymentItem::class],
    version = 1
)
abstract class PaymentDatabase : RoomDatabase() {

    abstract fun paymentDao() : PaymentsDao

    companion object {

        @Volatile
        private var INSTANCE : PaymentDatabase? = null

        fun getDatabase(context: Context) : PaymentDatabase {
            val temp = INSTANCE
            if(temp != null){
                return temp
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PaymentDatabase::class.java,
                    "payments_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}