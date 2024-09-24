package org.demoappjpa;

import org.demoappjpa.entities.Person;
import org.demoappjpa.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import java.util.Scanner;
import java.util.List;

@SpringBootApplication
public class DemoAppJpaApplication implements CommandLineRunner {
	
    @Autowired
	private PersonRepository personRepository;
    public static void main(String[] args) {
        SpringApplication.run(DemoAppJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //create();
        //FindByLanguage();
        List<Person> persons = (List<Person>) personRepository.buscarByProgrammingLanguage("Cobol");
		persons.stream().forEach(person -> System.out.println(person));

    }
    @Transactional
	public void create() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el nombre:");
		String name = scanner.next();
		System.out.println("Ingrese el apellido:");
		String lastname = scanner.next();
		System.out.println("Ingrese el lenguaje de programacion:");
		String programmingLanguage = scanner.next();
		System.out.println("Ingrese el email:");
		String email = scanner.next();
		scanner.close();

		Person person = new Person(null,name, lastname, programmingLanguage,email);

		Person personNew = personRepository.save(person);
		System.out.println(personNew);

		personRepository.findById(personNew.getId()).ifPresent(System.out::println);

	}

    @Transactional

    public void FindByLanguage() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el lenguaje de programacion:");
        String programmingLanguage = scanner.next();
        scanner.close();

        List<Person> expertos = personRepository.findByProgrammingLanguage(programmingLanguage);

        expertos.forEach(persona -> System.out.println(persona.getName()));

    }
}
