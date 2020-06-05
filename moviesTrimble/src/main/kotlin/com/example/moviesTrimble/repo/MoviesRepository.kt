package com.example.moviesTrimble.repo

import com.example.moviesTrimble.model.Movies
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MoviesRepository : JpaRepository<Movies, Int>