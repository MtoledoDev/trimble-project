package com.example.moviesTrimble.controller

import org.springframework.beans.factory.annotation.Autowired
import com.example.moviesTrimble.repo.MoviesRepository
import com.example.moviesTrimble.model.Movies
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.Query
import kotlin.RuntimeException
import kotlin.collections.ArrayList


class TestException(message:String): Exception(message)
@RestController
class MoviesController {

    @Autowired
    lateinit var repository: MoviesRepository

    @GetMapping("/movies")
    fun findAll(): Iterable<Movies> = repository.findAll()

    @GetMapping("/movies/{id}")
    fun findByID(@PathVariable id: Int): Optional<Movies> = repository.findById(id)

    @PostMapping("/movies")
    fun create(@RequestBody movie: Movies) = ResponseEntity.ok(repository.save(movie))

    @PutMapping("/movies/{id}")
    fun update(@PathVariable id: Int, @RequestBody movie: Movies): ResponseEntity<String> {

        try {
            val movieDB = repository.findById(id)
            val updateQuery = movieDB.orElseThrow { RuntimeException("Movie Id not found: $id") }
            if (movie.usr_rating.toInt() > 10 || movie.usr_rating.toInt() < 0) {
                return ResponseEntity.badRequest().body("usr_rating must be between 0 and 10")
            }
            repository.save(updateQuery.copy(usr_rating = movie.usr_rating))
            return ResponseEntity.ok("Updated!")
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body("Error: $e")
        }
    }

    @GetMapping("/movies/letter_metrics_top10")
    fun getAllMovies(): List<Pair<Char, Int>> {
        val movies: MutableList<String> = ArrayList()
        repository.findAll().map { movie ->
            movies.add(movie.title)
        }
        val movie: MutableList<String> = ArrayList()
        var count: Int = 0
        var C: Char = 'A'
        var list: MutableList<Pair<Char, Int>> = ArrayList()
        while (C <= 'Z') {
            movies.map { titulo ->
                if(titulo.contains("$C", ignoreCase = true)) {
                ++count
                }
            }
            //list.add("letra $C: quantidade:  $count" )
            list.add(Pair(first = C,second = count))
            count = 0
            ++C
        }
        list.sortByDescending { it.second }
        return list
    }



}