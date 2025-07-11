package br.com.servicesmarketing.Services;

import br.com.servicesmarketing.Models.Person;
import br.com.servicesmarketing.Models.SendMessages;

public record SmsService(Person person) implements SendMessages {
    @Override
    public void sendMessage(String message) {
        System.out.println("Message sent to sms " +
                String.valueOf(person.getNumber()).replaceFirst("(\\d{2})(\\d{5})(\\d+)", "(0$1)$2-$3")
                + ": " + person.getName()+ " - " + message);
    }
}
