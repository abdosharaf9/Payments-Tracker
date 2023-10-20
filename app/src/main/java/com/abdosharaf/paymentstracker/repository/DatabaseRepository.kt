package com.abdosharaf.paymentstracker.repository

import com.abdosharaf.paymentstracker.db.ExpensesDao
import com.abdosharaf.paymentstracker.db.IncomesDao
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.models.IncomeItem
import javax.inject.Inject

class DatabaseRepository @Inject constructor(
    private val expensesDao: ExpensesDao,
    private val incomesDao: IncomesDao
) {

    suspend fun addNewExpense(item: ExpenseItem) = expensesDao.addExpense(item)

    fun getAllExpenses() = expensesDao.getAllExpenses()

    suspend fun deleteAllExpenses() = expensesDao.deleteAllExpenses()

    suspend fun getSingleExpense(id: Long) = expensesDao.getSingleExpense(id)

    suspend fun deleteSingleExpense(item: ExpenseItem) = expensesDao.deleteSingleExpense(item)


    suspend fun addNewIncome(item: IncomeItem) = incomesDao.addIncome(item)

    fun getAllIncomes() = incomesDao.getAllIncomes()

    suspend fun deleteAllIncomes() = incomesDao.deleteAllIncomes()

    suspend fun getSingleIncome(id: Long) = incomesDao.getSingleIncome(id)

    suspend fun deleteSingleIncome(item: IncomeItem) = incomesDao.deleteSingleIncome(item)
}