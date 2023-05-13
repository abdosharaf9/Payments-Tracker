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
) : Serializable

