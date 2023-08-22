package com.abdosharaf.paymentstracker.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abdosharaf.paymentstracker.R
import com.abdosharaf.paymentstracker.base.BaseActivity
import com.abdosharaf.paymentstracker.databinding.ActivityHomeBinding
import com.abdosharaf.paymentstracker.ui.addExpense.AddExpenseActivity
import com.abdosharaf.paymentstracker.ui.addIncome.AddIncomeActivity
import com.abdosharaf.paymentstracker.ui.dialogs.DialogAddNew
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialog: DialogAddNew

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
        initMainClicks()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.homeNavHost) as NavHostFragment
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        binding.bottomNavigation.background = null
        binding.bottomNavigation.menu.getItem(2).isEnabled = false
    }

    private fun initMainClicks() {
        binding.fab.setOnClickListener {
            dialog = DialogAddNew(
                incomeListener = {
                    startActivity(Intent(this, AddIncomeActivity::class.java))
                },
                expenseListener = {
                    startActivity(Intent(this, AddExpenseActivity::class.java))
                },
                context = this
            )
        }
    }
}