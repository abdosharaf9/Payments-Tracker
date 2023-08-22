package com.abdosharaf.paymentstracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.abdosharaf.paymentstracker.models.PaymentItem

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPayment(item: PaymentItem)

    @Query("SELECT * FROM payments")
    fun getAllPayments(): LiveData<List<PaymentItem>>

    @Query("SELECT * FROM payments WHERE id = :id")
    suspend fun getSinglePayment(id: Long): PaymentItem

    @Query("DELETE FROM payments")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePayment(item: PaymentItem)
}