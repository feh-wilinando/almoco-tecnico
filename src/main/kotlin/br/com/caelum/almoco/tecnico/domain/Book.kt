package br.com.caelum.almoco.tecnico.domain

import javax.persistence.*

@Entity
class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    var title:String = ""

    @Lob
    var summary:String = ""

    @ManyToMany
    var authors:List<Author> = arrayListOf()
    

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


}