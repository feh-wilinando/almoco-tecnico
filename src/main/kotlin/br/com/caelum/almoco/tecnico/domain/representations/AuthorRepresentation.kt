package br.com.caelum.almoco.tecnico.domain.representations

import br.com.caelum.almoco.tecnico.domain.Author
import org.springframework.hateoas.Link
import org.springframework.hateoas.ResourceSupport

class AuthorRepresentation (var name: String): ResourceSupport() {

    fun toEntity(): Author {
        return Author(id = 0, name = name)
    }

    private constructor(): this("")

    companion object {
        fun from(author:Author): AuthorRepresentation {
            val representation = AuthorRepresentation(name = author.name)

            representation.add(Link("/authors/${author.id}"))

            return representation
        }
    }
}