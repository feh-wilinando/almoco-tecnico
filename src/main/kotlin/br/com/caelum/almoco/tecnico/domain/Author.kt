package br.com.caelum.almoco.tecnico.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Author (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long,
    val name:String

){
    private constructor():this(0,"")
}
