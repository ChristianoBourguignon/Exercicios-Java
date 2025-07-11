package br.com.servicesmarketing.Views;

import br.com.servicesmarketing.Controllers.ContactControllers;
import br.com.servicesmarketing.Models.Person;

import java.util.Formatter;
import java.util.Map;

public record PersonView() {
    public void viewPerson(Person person) {
        System.out.printf("=== View %s ===\n",person.getName());
        System.out.println("Number: " +
                String.valueOf(person.getNumber()).replaceFirst("(\\d{2})(\\d{5})(\\d+)", "(0$1)$2-$3"));
        System.out.println("Email: " + person.getEmail());
        System.out.println("=== End Person ===");
    }
    public void viewContacts(Map<String, Person> contacts) {
        System.out.println("=== View Contacts ===");
        contacts.forEach((name, person) -> {
            viewPerson(person);
        });
        System.out.println("=== End Contacts ===");
    }
}
