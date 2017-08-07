package br.com.caelum.almoco.tecnico.repositories

import br.com.caelum.almoco.tecnico.domain.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Long>