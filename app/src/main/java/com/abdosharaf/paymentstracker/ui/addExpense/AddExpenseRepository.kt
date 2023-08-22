package com.abdosharaf.paymentstracker.ui.addExpense

import com.abdosharaf.paymentstracker.db.ExpensesDao
import com.abdosharaf.paymentstracker.models.PaymentItem
import javax.inject.Inject

class AddExpenseRepository @Inject constructor(private val dao: ExpensesDao) {

    suspend fun addNewExpense(item: PaymentItem) = dao.addPayment(item)
}