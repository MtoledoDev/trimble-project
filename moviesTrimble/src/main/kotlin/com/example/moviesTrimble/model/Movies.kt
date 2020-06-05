package com.example.moviesTrimble.model




import javax.persistence.*


@Entity
data class Movies(

       @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
       val id: Int = 0,
       val title: String = "",
       val release_date: String = "",
       val synopsis: String,
       val usr_rating: Int
)

class Letters(var letra: String, var quantidade: String)
