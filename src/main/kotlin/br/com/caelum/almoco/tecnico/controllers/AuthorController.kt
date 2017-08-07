package br.com.caelum.almoco.tecnico.controllers

import br.com.caelum.almoco.tecnico.domain.representations.AuthorRepresentation
import br.com.caelum.almoco.tecnico.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Link
import org.springframework.http.HttpEntity
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("authors")
class AuthorController {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id:Long): HttpEntity<AuthorRepresentation> {

        val author = authorRepository.findOne(id)

        val representation = AuthorRepresentation.from(author)

        return HttpEntity(representation)
    }

    @GetMapping("/", "")
    fun listAll(): Iterable<AuthorRepresentation>{
       return authorRepository.findAll().map { author ->  AuthorRepresentation.from(author) }
    }


    @PostMapping("/", "")
    fun save(@RequestBody representation: AuthorRepresentation): HttpEntity<AuthorRepresentation>{

        val author = representation.toEntity()

        authorRepository.save(author)

        val uri = URI.create("/authors/${author.id}")

        return ResponseEntity.created(uri).body(AuthorRepresentation.from(author))
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:Long): ResponseEntity<Any>{

        if (authorRepository.exists(id)) {

            authorRepository.delete(id)

            return ResponseEntity.accepted().build()
        }

        return ResponseEntity.noContent().build()

    }
}