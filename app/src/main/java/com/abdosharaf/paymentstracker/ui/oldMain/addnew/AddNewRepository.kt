package com.abdosharaf.paymentstracker.ui.oldMain.addnew

import com.abdosharaf.paymentstracker.db.ExpensesDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class AddNewRepository @Inject constructor(private val dao: ExpensesDao) {

    suspend fun addNewPayment(item: PaymentItem) = dao.addPayment(item)
}