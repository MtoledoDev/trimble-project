package com.example.moviesTrimble.repo

import com.example.moviesTrimble.model.Movies
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface MoviesRepository : CrudRepository<Movies, Int> {

    @Query(value = "SELECT title FROM movies", nativeQuery = true)
    
}