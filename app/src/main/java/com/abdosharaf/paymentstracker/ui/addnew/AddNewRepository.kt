package com.abdosharaf.paymentstracker.ui.addnew

import com.abdosharaf.paymentstracker.db.PaymentsDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class AddNewRepository @Inject constructor(private val dao: PaymentsDao) {

    suspend fun addNewPayment(item: PaymentItem) = dao.addPayment(item)
}