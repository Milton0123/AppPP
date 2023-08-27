package com.example.apppp.back

import android.content.Intent
import android.widget.Toast
import com.example.apppp.databinding.ActivitySaleAdminBinding
import com.example.apppp.ui.AdminActivity
import com.example.apppp.ui.EmployeeActivity

object Utils {

    private fun checkPassword(pass: String): Boolean {
        return pass.length in 6..15 && !pass.contains(" ") && pass.all { it.isLetterOrDigit() }
    }

    private fun checkUser(user: String): Boolean {
        return user.length in 4..15 && !user.contains(" ") && user.all { it.isLetterOrDigit() }
    }

    fun checkFields(user: String, pass: String): Boolean {
        return checkUser(user) && checkPassword(pass)
    }

    fun checkField(field: Int, barcode: String): ProductData {
        var emptyStock: ProductData
        var stockLimitActual = 0
        ProductBD.list.forEach {
            if (barcode == it.name) {
                stockLimitActual = it.quantity
            }
        }
        emptyStock = ProductData("error", "", 0, stockLimitActual)
        ProductBD.list.forEach {
            if (barcode == it.name) {
                if (it.quantity >= field) {
                    emptyStock = ProductData(it.barcode, barcode, it.price, field)
                }
            }
        }
        return emptyStock
    }
}

