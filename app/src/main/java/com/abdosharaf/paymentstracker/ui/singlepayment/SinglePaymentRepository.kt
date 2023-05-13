package com.abdosharaf.paymentstracker.ui.singlepayment

import com.abdosharaf.paymentstracker.db.PaymentsDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class SinglePaymentRepository @Inject constructor(private val dao: PaymentsDao) {

    suspend fun getPaymentItem(id: Long) = dao.getSinglePayment(id)

    suspend fun deleteItem(item: PaymentItem) = dao.deletePayment(item)
}