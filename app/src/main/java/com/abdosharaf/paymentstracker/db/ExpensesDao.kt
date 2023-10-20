package com.abdosharaf.paymentstracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abdosharaf.paymentstracker.Constants.EXPENSES_TABLE
import com.abdosharaf.paymentstracker.models.ExpenseItem

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addExpense(item: ExpenseItem)

    @Query("SELECT * FROM $EXPENSES_TABLE")
    fun getAllExpenses(): LiveData<List<ExpenseItem>>

    @Query("SELECT * FROM $EXPENSES_TABLE WHERE id = :id")
    suspend fun getSingleExpense(id: Long): ExpenseItem

    @Query("DELETE FROM $EXPENSES_TABLE")
    suspend fun deleteAllExpenses()

    @Delete
    suspend fun deleteSingleExpense(item: ExpenseItem)
}