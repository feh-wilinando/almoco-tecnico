package br.com.caelum.almoco.tecnico.controllers

import br.com.caelum.almoco.tecnico.domain.Author
import br.com.caelum.almoco.tecnico.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("authors")
class AuthorController {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @GetMapping("/{id}")
    fun show(@PathVariable("id") id:Long): Author{
        return authorRepository.findOne(id)
    }

    @GetMapping("/", "")
    fun listAll(): Iterable<Author>{
       return authorRepository.findAll()
    }


    @PostMapping("/", "")
    fun save(@RequestBody author: Author): ResponseEntity<Author>{

        authorRepository.save(author)

        val uri = URI.create("/authors/${author.id}")

        return ResponseEntity.created(uri).body(author)
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