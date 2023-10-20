package com.abdosharaf.paymentstracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdosharaf.paymentstracker.Constants.EXPENSES_TABLE
import java.io.Serializable

@Entity(tableName = EXPENSES_TABLE)
data class ExpenseItem(
    val name: String,
    val value: String,
    val desc: String?,
    val date: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) : Serializable {

    fun print() = "${this.id}, ${this.name}, ${this.value}, ${this.getDescription()}"

    private fun getDescription(): String? {
        return if(this.desc?.contains("\n") == true) {
            var description = ""
            for (i in this.desc) {
                if(i != '\n') {
                    description += i
                } else {
                    description += " & "
                }
            }

            description
        } else {
            this.desc
        }
    }
}

