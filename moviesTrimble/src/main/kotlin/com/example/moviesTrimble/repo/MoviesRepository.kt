package com.example.moviesTrimble.repo

import com.example.moviesTrimble.model.Movies
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.ArrayList


interface MoviesRepository : JpaRepository<Movies, Int> {

}