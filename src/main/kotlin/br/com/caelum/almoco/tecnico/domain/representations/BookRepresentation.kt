package br.com.caelum.almoco.tecnico.domain.representations

import br.com.caelum.almoco.tecnico.domain.Book
import org.springframework.hateoas.Link
import org.springframework.hateoas.ResourceSupport

class BookRepresentation(
        var title:String,
        var summary:String
): ResourceSupport() {


    private constructor(): this("", "")


    companion object {
        fun from(book: Book): BookRepresentation{


            val representation = BookRepresentation(book.title, book.summary)

            representation.add(Link("/books/${book.id}"))
            representation.add(Link("/books/${book.id}/authors", "authors"))

            return representation
        }
    }

    fun toEntity(): Book {
        return Book(0,title,summary, arrayListOf())
    }

}