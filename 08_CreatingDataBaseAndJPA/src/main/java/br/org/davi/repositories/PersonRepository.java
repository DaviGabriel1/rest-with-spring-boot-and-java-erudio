package br.org.davi.repositories;

import br.org.davi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {// provê operações de CRUD Básicos
}
