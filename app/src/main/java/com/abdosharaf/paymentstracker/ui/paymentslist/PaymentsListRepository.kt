package com.abdosharaf.paymentstracker.ui.paymentslist

import com.abdosharaf.paymentstracker.db.PaymentsDao
import javax.inject.Inject

class PaymentsListRepository @Inject constructor(private val dao: PaymentsDao) {

    fun getAllPayments() = dao.getAllPayments()

    suspend fun deleteAll() = dao.deleteAll()

}