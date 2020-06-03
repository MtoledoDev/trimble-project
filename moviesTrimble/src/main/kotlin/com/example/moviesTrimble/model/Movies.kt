package com.example.moviesTrimble.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Movies(

       val title: String,
       @Id @GeneratedValue
       val id: Int,
       val release_date: String,
       val synopsis: String,
       val usr_rating: Int
)
