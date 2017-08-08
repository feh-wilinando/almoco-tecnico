package br.com.caelum.almoco.tecnico.controllers

import br.com.caelum.almoco.tecnico.domain.Book
import br.com.caelum.almoco.tecnico.domain.representations.AuthorRepresentation
import br.com.caelum.almoco.tecnico.domain.representations.BookRepresentation
import br.com.caelum.almoco.tecnico.repositories.AuthorRepository
import br.com.caelum.almoco.tecnico.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("books")
class BookController {

    @Autowired
    lateinit var bookRepository: BookRepository


    @Autowired
    lateinit var authorRepository: AuthorRepository

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id:Long): BookRepresentation {
        val book = bookRepository.findOne(id)
        return BookRepresentation.from(book)
    }


    @GetMapping("/", "")
    fun listAll(): Iterable<BookRepresentation> {
        return bookRepository.findAll().map { book -> BookRepresentation.from(book) }
    }

    @GetMapping("/{id}/authors")
    fun listAuthors(@PathVariable("id") id:Long): Iterable<AuthorRepresentation>{
        val authors = bookRepository.findOne(id).authors

        return authors.map { author -> AuthorRepresentation.from(author) }
    }


    @PatchMapping("/{bookId}/authors/{authorId}")
    fun addAuthor(@PathVariable("bookId")  bookId:Long, @PathVariable("authorId") authorId:Long): ResponseEntity<Any>{

        val author = authorRepository.findOne(authorId)
        val book = bookRepository.findOne(bookId)

        if (book.authors.contains(author))
            return ResponseEntity.status(HttpStatus.GONE).build()

        book.add(author)

        bookRepository.save(book)

        return ResponseEntity.accepted(). build()

    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    fun removeAuthor(@PathVariable("bookId")  bookId:Long, @PathVariable("authorId") authorId:Long): ResponseEntity<Any>{

        val author = authorRepository.findOne(authorId)
        val book = bookRepository.findOne(bookId)


        if (!book.authors.contains(author))
            return ResponseEntity.status(HttpStatus.GONE).build()

        book.remove(author)

        bookRepository.save(book)

        return ResponseEntity.accepted(). build()
    }


    @PostMapping("/", "")
    fun save(@RequestBody representation: BookRepresentation): ResponseEntity<BookRepresentation>{
        val book = representation.toEntity()

        bookRepository.save(book)

        val uri = URI.create("/books/${book.id}")

        return ResponseEntity.created(uri).body(BookRepresentation.from(book))
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