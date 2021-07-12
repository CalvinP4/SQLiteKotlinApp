package com.example.sqliteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


interface CustomerClickListener {
    fun onCustomerClickListener(customer: CustomerModel)
}

class MainActivity : AppCompatActivity(), CustomerClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = DatabaseHelper(this)

        showCustomersInRecyclerView()

        btn_Add.setOnClickListener {

            var customer = CustomerModel()

            try {
                customer = CustomerModel(-1, et_Name.text.toString(), et_Age.text.toString().toInt(), switch_ActiveCustomer.isChecked)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

            // TODO remove toast
            Toast.makeText(this, customer.toString(), Toast.LENGTH_LONG).show()

            val success = db.addCustomer(customer)

            if (success) {
                // TODO remove toast
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()


                showCustomersInRecyclerView()
            }
        }

        btn_ViewAll.setOnClickListener{
            showCustomersInRecyclerView()
        }
    }

    private fun showCustomersInRecyclerView() {
        val db = DatabaseHelper(this)
        var adapter = CustomerAdapter(db.getAllCustomers(), this)

        rv_DisplayCustomers.adapter = adapter
        rv_DisplayCustomers.layoutManager = LinearLayoutManager(this)
    }

    override fun onCustomerClickListener(customer: CustomerModel) {
        Toast.makeText(this,customer.toString(), Toast.LENGTH_SHORT).show()

        val db = DatabaseHelper(this)
        db.deleteCustomer(customer)
        this.showCustomersInRecyclerView()
    }
}