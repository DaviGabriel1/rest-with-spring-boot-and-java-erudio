package br.org.davi.repositories;

import br.org.davi.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Books,Long> {
}
