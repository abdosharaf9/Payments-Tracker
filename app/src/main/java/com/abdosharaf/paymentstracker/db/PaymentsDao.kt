package com.abdosharaf.paymentstracker.db

import androidx.room.*
import com.abdosharaf.paymentstracker.models.PaymentItem

@Dao
interface PaymentsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPayment(item : PaymentItem)

    @Query("SELECT * FROM payments")
    suspend fun getAllPayments() : List<PaymentItem>

    @Query("DELETE FROM payments")
    suspend fun deleteAll()

    @Delete
    suspend fun deletePayment(item: PaymentItem)
}