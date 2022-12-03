package com.abdosharaf.paymentstracker.repositories

import com.abdosharaf.paymentstracker.db.PaymentDatabase
import com.abdosharaf.paymentstracker.models.PaymentItem

class PaymentsRepository(private val appDB : PaymentDatabase) {

    suspend fun getAllPayments() = appDB.paymentDao().getAllPayments()

    suspend fun addPayment(item: PaymentItem) = appDB.paymentDao().addPayment(item)

    suspend fun deletePayment(item: PaymentItem) = appDB.paymentDao().deletePayment(item)

    suspend fun deleteAll() = appDB.paymentDao().deleteAll()

}