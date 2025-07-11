package br.com.servicesmarketing.Services;

import br.com.servicesmarketing.Models.Person;
import br.com.servicesmarketing.Models.SendMessages;

public record EmailService(Person person) implements SendMessages {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message sent to email "+ person.getEmail() + ": " + person.getName()+ " - " + message);
    }
}
