package br.com.caelum.almoco.tecnico.domain

import javax.persistence.*

@Entity
class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long,

    var title:String,

    @Lob
    var summary:String,

    @ManyToMany
    var authors:MutableList<Author> = arrayListOf()

){

    private constructor():this(0,"","", arrayListOf())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Book

        if (id != other.id) return false
        if (title != other.title) return false
        if (authors != other.authors) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + authors.hashCode()
        return result
    }

    fun add(author: Author) {
        authors.add(author)
    }

    fun remove(author: Author) {
        authors.remove(author)
    }

    override fun toString(): String {
        return "Book(id=$id, title='$title', summary='$summary', authors=$authors)"
    }


}