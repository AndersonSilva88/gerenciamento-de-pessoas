package br.com.gerenciamentopessoas.personapi.service;

import br.com.gerenciamentopessoas.personapi.dto.request.PersonDTO;
import br.com.gerenciamentopessoas.personapi.dto.response.MessageResponseDTO;
import br.com.gerenciamentopessoas.personapi.entity.Person;
import br.com.gerenciamentopessoas.personapi.exception.PersonNotFoundException;
import br.com.gerenciamentopessoas.personapi.mapper.PersonMapper;
import br.com.gerenciamentopessoas.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }


    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
      Person person = personRepository.findById(id)
              .orElseThrow(() -> new PersonNotFoundException(id));

        return personMapper.toDTO(person);
    }
}
