package com.example.moviesTrimble.repo

import com.example.moviesTrimble.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository




@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findById(lastName: String): Iterable<Customer>
}