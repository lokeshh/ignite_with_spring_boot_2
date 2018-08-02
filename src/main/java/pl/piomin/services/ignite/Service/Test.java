package pl.piomin.services.ignite.Service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.piomin.services.ignite.model.Person;
import pl.piomin.services.ignite.repository.PersonRepository;

import javax.annotation.Resource;
import java.util.TreeMap;

@Component
public class Test {
    @Resource
    private PersonRepository personRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void testInsert() {
        TreeMap<Long, Person> persons = new TreeMap<Long, Person>();

        persons.put(1L, new Person(1L, 2000L, "John", "Smith", 15000, "Worked for Apple"));
        persons.put(2L, new Person(2L, 2000L, "Brad", "Pitt", 16000, "Worked for Oracle"));
        persons.put(3L, new Person(3L, 1000L, "Mark", "Tomson", 10000, "Worked for Sun"));
        persons.put(4L, new Person(4L, 2000L, "Erick", "Smith", 13000, "Worked for Apple"));
        persons.put(5L, new Person(5L, 1000L, "John", "Rozenberg", 25000, "Worked for RedHat"));
        persons.put(6L, new Person(6L, 2000L, "Denis", "Won", 35000, "Worked for CBS"));
        persons.put(7L, new Person(7L, 1000L, "Abdula", "Adis", 45000, "Worked for NBC"));
        persons.put(8L, new Person(8L, 2000L, "Roman", "Ive", 15000, "Worked for Sun"));

        // Adding data into the repository.
        personRepository.save(persons);
    }

    @Scheduled(fixedRate = 5000)
    public void testQuery() {
        System.out.println("\n\n\n\n\n\n\n" + personRepository.findByFirstName("John") + "\n\n\n\n\n\n\n\n\n\n");
    }

}
