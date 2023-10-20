package com.abdosharaf.paymentstracker.ui.addPayment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.abdosharaf.paymentstracker.Constants.ADD_INCOME
import com.abdosharaf.paymentstracker.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddPaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_payment)

        if (intent.getBooleanExtra(ADD_INCOME, false)) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.addPaymentNavHost) as NavHostFragment
            val navController = navHostFragment.navController
            val graph = navController.navInflater.inflate(R.navigation.add_payment_navigation)

            graph.setStartDestination(R.id.addIncomeFragment)
            navController.setGraph(graph, intent.extras)
        }
    }
}