package com.example.moviesTrimble.controller

import org.springframework.beans.factory.annotation.Autowired

import com.example.moviesTrimble.repo.MoviesRepository
import com.example.moviesTrimble.model.Movies
import org.aspectj.weaver.patterns.ThrowsPattern
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.xml.ws.Response
import kotlin.RuntimeException

class TestException(message:String): Exception(message)
@RestController
class MoviesController {

    @Autowired
    lateinit var repository: MoviesRepository

//    @RequestMapping("/movies")
//    fun save(): String {
//        repository.save(Customer("Jack", "Smith"))
//        repository.save(Customer("Adam", "Johnson"))
//        repository.save(Customer("Kim", "Smith"))
//        repository.save(Customer("David", "Williams"))
//        repository.save(Customer("Peter", "Davis"))
//
//        return "Done"
//    }


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
    fun findTitles(): Iterable<Movies>
//    @RequestMapping("/findbyid/{id}")
//    fun findById(@PathVariable id: Int)
//            = repository.findById(id)
//
//    @RequestMapping("findbylastname/{lastName}")
//    fun findByLastName(@PathVariable lastName: String)
//            = repository.findById(lastName)

}