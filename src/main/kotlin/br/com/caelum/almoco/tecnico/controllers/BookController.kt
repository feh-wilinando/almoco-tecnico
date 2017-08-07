package br.com.caelum.almoco.tecnico.controllers

import br.com.caelum.almoco.tecnico.domain.Book
import br.com.caelum.almoco.tecnico.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("books")
class BookController {

    @Autowired
    lateinit var bookRepository: BookRepository


    @GetMapping("/{id}")
    fun show(@PathVariable("id") id:Long): Book {
        return bookRepository.findOne(id)
    }


    @GetMapping("/", "")
    fun listAll(): Iterable<Book> {
        return bookRepository.findAll()
    }


    @PostMapping("/", "")
    fun save(@RequestBody book: Book): ResponseEntity<Book>{
        bookRepository.save(book)

        val uri = URI.create("/books/${book.id}")

        return ResponseEntity.created(uri).body(book)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:Long): ResponseEntity<Any>{

        if (bookRepository.exists(id)){
            bookRepository.delete(id)

            return ResponseEntity.accepted().build()
        }

        return ResponseEntity.noContent().build()
    }
}