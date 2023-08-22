package com.abdosharaf.paymentstracker.ui.oldMain.singlepayment

import com.abdosharaf.paymentstracker.db.ExpensesDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class SinglePaymentRepository @Inject constructor(private val dao: ExpensesDao) {

    suspend fun getPaymentItem(id: Long) = dao.getSinglePayment(id)

    suspend fun deleteItem(item: PaymentItem) = dao.deletePayment(item)
}