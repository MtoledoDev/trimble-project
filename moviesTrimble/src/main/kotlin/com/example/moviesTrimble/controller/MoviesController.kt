package com.example.moviesTrimble.controller

import com.example.moviesTrimble.model.Letters
import org.springframework.beans.factory.annotation.Autowired
import com.example.moviesTrimble.repo.MoviesRepository
import com.example.moviesTrimble.model.Movies
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.regex.Pattern
import kotlin.RuntimeException
import kotlin.collections.ArrayList


@RestController
class MoviesController {

    val p: Pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE)

    @Autowired
    lateinit var repository: MoviesRepository

    @GetMapping("/movies")
    fun findAll(): Iterable<Movies> = repository.findAll()

    @GetMapping("/movies/{id}")
    fun findByID(@PathVariable id: Int): Optional<Movies> = repository.findById(id)

    @PostMapping("/movies")
    fun create(@RequestBody movie: Movies): ResponseEntity<String> {

        return if (movie.title.length > 30) {
            ResponseEntity.badRequest().body("Title must have a maximum of 30 characters")
        } else if (movie.usr_rating.toInt() > 10 || movie.usr_rating.toInt() < 0) {
            ResponseEntity.badRequest().body("usr_rating must be between 0 and 10")
        } else if (p.matcher(movie.title.toString()).find()) {
            ResponseEntity.badRequest().body("Special characters are not allowed")
        } else {
            repository.save(movie)
            ResponseEntity.ok("Saved!")
        }
    }
    //ResponseEntity.ok(repository.save(movie))

    @PutMapping("/movies/{id}")
    fun update(@PathVariable id: Int, @RequestBody movie: Movies): ResponseEntity<String> {

        val movieDB = repository.findById(id)
        val updateQuery = movieDB.orElseThrow { RuntimeException("Movie Id not found: $id") }
        return if (movie.usr_rating.toInt() > 10 || movie.usr_rating.toInt() < 0) {
            ResponseEntity.badRequest().body("usr_rating must be between 0 and 10")
        } else {
            repository.save(updateQuery.copy(usr_rating = movie.usr_rating))
            ResponseEntity.ok("Updated!")
        }
    }

    @GetMapping("/movies/letter_metrics_top10")
    fun getAllMovies(): List<Letters> {
        val movies: MutableList<String> = ArrayList()
        repository.findAll().map { movie ->
            movies.add(movie.title)
        }
        var count: Int = 0
        var C: Char = 'A'
        var list: MutableList<Pair<Char, Int>> = ArrayList()
        var finalList: MutableList<Letters> = ArrayList()
        while (C <= 'Z') {
            movies.map { titulo ->
                if(titulo.contains("$C", ignoreCase = true)) {
                ++count
                }
            }
            list.add(Pair(first = C, second = count))
            count = 0
            ++C
        }
        list.sortByDescending { it.second }
        for (x in 0 until 10) {
            finalList.add(Letters(list.elementAt(x).first.toString(), list.elementAt(x).second.toString()))
        }
        return finalList
    }



}
