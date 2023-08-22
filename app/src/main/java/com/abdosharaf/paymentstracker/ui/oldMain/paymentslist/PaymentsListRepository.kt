package com.abdosharaf.paymentstracker.ui.oldMain.paymentslist

import com.abdosharaf.paymentstracker.db.ExpensesDao
import javax.inject.Inject

class PaymentsListRepository @Inject constructor(private val dao: ExpensesDao) {

    fun getAllPayments() = dao.getAllPayments()

    suspend fun deleteAll() = dao.deleteAll()

}