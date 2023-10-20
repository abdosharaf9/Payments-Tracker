package com.abdosharaf.paymentstracker.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdosharaf.paymentstracker.Constants.INCOMES_TABLE
import java.io.Serializable

@Entity(tableName = INCOMES_TABLE)
data class IncomeItem(
    val name: String,
    val value: String,
    val desc: String?,
    val date: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L
) : Serializable
