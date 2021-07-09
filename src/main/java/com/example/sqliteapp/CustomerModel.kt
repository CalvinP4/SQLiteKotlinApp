package com.example.sqliteapp

data class CustomerModel(var Id: Int = Int.MIN_VALUE, var name: String = "", var age:Int = Int.MIN_VALUE, var isActive: Boolean = false) {
    override fun toString(): String {
        return "ID: $Id \n Name: $name Age: $age Active: $isActive"
    }
}
