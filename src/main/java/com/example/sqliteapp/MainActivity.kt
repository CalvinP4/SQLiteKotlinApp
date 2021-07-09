package com.example.sqliteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_Add.setOnClickListener {

            var customer = CustomerModel()

            try {
                customer = CustomerModel(-1, et_Name.text.toString(), et_Age.text.toString().toInt(), switch_ActiveCustomer.isChecked)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

            Toast.makeText(this, customer.toString(), Toast.LENGTH_LONG).show()

            val db = DatabaseHelper(this)
            val success = db.addCustomer(customer)

            if (success) {
                Toast.makeText(this, "SUCCESS", Toast.LENGTH_LONG).show()
            }
        }

        btn_ViewAll.setOnClickListener {
            val db = DatabaseHelper(this)
            val customers = db.getAllCustomers()

            Toast.makeText(this, customers.toString(), Toast.LENGTH_LONG).show()

            val adapter = CustomerAdapter(customers)

            recycler_DisplayCustomers.adapter = adapter
            recycler_DisplayCustomers.layoutManager = LinearLayoutManager(this)
            val itemDecoration: ItemDecoration =
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

            recycler_DisplayCustomers.addItemDecoration(itemDecoration)
        }
    }
}