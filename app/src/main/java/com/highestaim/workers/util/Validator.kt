package com.highestaim.workers.util

object Validator {

    fun isValidUserName(name: String?): Boolean {
        name?.let {
            return !(it.isEmpty() || it.length < 2)
        }
        return false
    }

    fun isValidSalary(salary: String?): Boolean {
        salary?.let {
            return !(it.isEmpty() || it.toDouble() <= 0)
        }
        return false
    }

    fun isValidAge(age: String?): Boolean {
        age?.let {
            return !(it.isEmpty() || it.toDouble() <= 0 || it.toDouble() > 80)
        }
        return false
    }
}