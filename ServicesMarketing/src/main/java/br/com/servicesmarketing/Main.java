package br.com.servicesmarketing;

import br.com.servicesmarketing.Controllers.ContactControllers;
import br.com.servicesmarketing.Models.Person;
import br.com.servicesmarketing.Models.SendMessages;
import br.com.servicesmarketing.Services.EmailService;
import br.com.servicesmarketing.Services.SmsService;
import br.com.servicesmarketing.Services.WhatsappService;
import br.com.servicesmarketing.Views.PersonView;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ContactControllers cc = new ContactControllers();
        PersonView pv = new PersonView();
        Person p1 = new Person("Ch","99999999999","christiano@test.com");
        Person p2 = new Person("Pedro","8888888888","pedro@test.com");
        Person p3 = new Person("Patrick","77777777777","patricktest.com");
        cc.addContacts(p1);
        cc.addContacts(p2);
        cc.addContacts(p3);
        for (Person person : cc.getContacts().values()) {
            SendMessages email = new EmailService(person);
            email.sendMessage("Marketing Message!!");
        }
        for (Person person : cc.getContacts().values()) {
            SendMessages sms = new SmsService(person);
            sms.sendMessage("Marketing Message!!");
        }
        for (Person person : cc.getContacts().values()) {
            SendMessages wpp = new WhatsappService(person);
            wpp.sendMessage("Marketing Message!!");
        }
    }
}