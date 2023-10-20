package com.abdosharaf.paymentstracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abdosharaf.paymentstracker.Constants.INCOMES_TABLE
import com.abdosharaf.paymentstracker.models.IncomeItem

@Dao
interface IncomesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIncome(item: IncomeItem)

    @Query("SELECT * FROM $INCOMES_TABLE")
    fun getAllIncomes(): LiveData<List<IncomeItem>>

    @Query("SELECT * FROM $INCOMES_TABLE WHERE id = :id")
    suspend fun getSingleIncome(id: Long): IncomeItem

    @Query("DELETE FROM $INCOMES_TABLE")
    suspend fun deleteAllIncomes()

    @Delete
    suspend fun deleteSingleIncome(item: IncomeItem)
}