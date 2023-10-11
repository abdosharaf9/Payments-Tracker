package com.abdosharaf.paymentstracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "payments")
data class PaymentItem(
    val name: String,
    val value: String,
    val desc: String?,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) : Serializable {

    fun print() = "${this.id}, ${this.name}, ${this.value}, ${this.getDescription()}"

    fun getDescription(): String? {
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

