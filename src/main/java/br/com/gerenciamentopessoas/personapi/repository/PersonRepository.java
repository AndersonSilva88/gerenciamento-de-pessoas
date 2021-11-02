package br.com.gerenciamentopessoas.personapi.repository;

import br.com.gerenciamentopessoas.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
