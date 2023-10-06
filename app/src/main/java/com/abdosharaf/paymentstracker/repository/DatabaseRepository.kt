package com.abdosharaf.paymentstracker.repository

import com.abdosharaf.paymentstracker.db.ExpensesDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val paymentDao: ExpensesDao) {

    suspend fun addNewExpense(item: PaymentItem) = paymentDao.addPayment(item)

    fun getAllPayments() = paymentDao.getAllPayments()

    suspend fun deleteAll() = paymentDao.deleteAll()

    suspend fun getPaymentItem(id: Long) = paymentDao.getSinglePayment(id)

    suspend fun deleteItem(item: PaymentItem) = paymentDao.deletePayment(item)
}