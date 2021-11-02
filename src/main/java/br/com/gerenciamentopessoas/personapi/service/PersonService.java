package br.com.gerenciamentopessoas.personapi.service;

import br.com.gerenciamentopessoas.personapi.dto.request.PersonDTO;
import br.com.gerenciamentopessoas.personapi.dto.response.MessageResponseDTO;
import br.com.gerenciamentopessoas.personapi.entity.Person;
import br.com.gerenciamentopessoas.personapi.mapper.PersonMapper;
import br.com.gerenciamentopessoas.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
