package br.com.servicesmarketing.Services;

import br.com.servicesmarketing.Models.Person;
import br.com.servicesmarketing.Models.SendMessages;

public record WhatsappService(Person person) implements SendMessages {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message sent to Whatsapp service: "+ person.getName()+ " - " + message);
    }
}
