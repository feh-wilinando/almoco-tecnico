package br.com.caelum.almoco.tecnico.repositories

import br.com.caelum.almoco.tecnico.domain.Author
import org.springframework.data.repository.CrudRepository

interface AuthorRepository : CrudRepository<Author, Long>