package com.abdosharaf.paymentstracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdosharaf.paymentstracker.models.ExpenseItem
import com.abdosharaf.paymentstracker.models.IncomeItem

@Database(
    entities = [ExpenseItem::class, IncomeItem::class],
    version = 2
)
abstract class PaymentDatabase : RoomDatabase() {
    abstract fun expensesDao() : ExpensesDao
    abstract fun incomesDao() : IncomesDao
}

// TODO: Use this to export a table from the database!
/*private fun exportPaymentsTable(list: List<ExpenseItem>) {
        val directory = requireContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val csvFile = File(directory, "payments.csv")

        try {
            csvFile.bufferedWriter().use { out ->
                out.write("ID, Name, Value, Description\n")
                for (record in list) {
                    val line = "${record.print()}\n"
                    out.write(line)
                }
            }

            showSuccessToast("Done ✅")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }*/