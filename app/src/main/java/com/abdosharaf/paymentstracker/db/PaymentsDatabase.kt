package com.abdosharaf.paymentstracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdosharaf.paymentstracker.models.PaymentItem

@Database(
    entities = [PaymentItem::class],
    version = 1
)
abstract class PaymentDatabase : RoomDatabase() {
    abstract fun paymentDao() : PaymentsDao
}