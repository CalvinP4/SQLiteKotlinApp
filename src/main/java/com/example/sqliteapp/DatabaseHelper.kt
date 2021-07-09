package com.example.sqliteapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DatabaseHelper(
    context: Context?,
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Customer.db"
        const val CUSTOMER_TABLE = "CUSTOMER_TABLE"
        const val COLUMN_ID = "ID"
        const val COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME"
        const val COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE"
        const val COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableStatement = "CREATE TABLE $CUSTOMER_TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_CUSTOMER_NAME TEXT, $COLUMN_CUSTOMER_AGE INT, $COLUMN_ACTIVE_CUSTOMER BOOL)"
        db?.execSQL(createTableStatement)
        db?.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addCustomer(customer: CustomerModel): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_CUSTOMER_NAME, customer.name)
        cv.put(COLUMN_CUSTOMER_AGE, customer.age)
        cv.put(COLUMN_ACTIVE_CUSTOMER, customer.isActive)

        val result = db.insert(CUSTOMER_TABLE, null, cv)
        return result != -1L
    }

    fun getAllCustomers(): List<CustomerModel> {
        val customers = ArrayList<CustomerModel>()
        val selectAllStatement = "SELECT * FROM $CUSTOMER_TABLE"
        val db = this.readableDatabase

        val cursor = db.rawQuery(selectAllStatement, null)

        if(cursor.moveToFirst()){
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val age = cursor.getInt(2)
                val active = cursor.getInt(3) == 1

                val customer = CustomerModel(id, name, age, active)
                customers.add(customer)
            }while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return customers
    }
}