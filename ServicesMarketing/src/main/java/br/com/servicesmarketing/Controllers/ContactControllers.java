package br.com.servicesmarketing.Controllers;

import br.com.servicesmarketing.Exception.MapContactsInvalidException;
import br.com.servicesmarketing.Models.Logger;
import br.com.servicesmarketing.Models.Person;

import java.util.HashMap;
import java.util.Map;


public class ContactControllers implements Logger {
    private final Map<String, Person> contacts = new HashMap<>();

    public void addContacts(Person contact) {
        try {
            if (contacts.isEmpty()) {
                contacts.put(contact.getName(), contact);
            } else if (!contacts.containsKey(contact.getName())) {
                contacts.put(contact.getName(), contact);
            } else {
                throw new MapContactsInvalidException("Added contact failed");
            }
        } catch(MapContactsInvalidException e){
            logger.error("Error while adding contact", e);
        }
    }
    public Map<String, Person> getContacts() {
        return contacts;
    }
}
