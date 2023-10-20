package com.abdosharaf.paymentstracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.models.IncomeItem

@Database(
    entities = [ExpenseItem::class, IncomeItem::class],
    version = 2
)
abstract class PaymentDatabase : RoomDatabase() {
    abstract fun expensesDao() : ExpensesDao
    abstract fun incomesDao() : IncomesDao
}