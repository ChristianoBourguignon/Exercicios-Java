package br.com.servicesmarketing.Models;

import br.com.servicesmarketing.Exception.NumberLenghtInvalidExeception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.InvalidParameterException;


public class Person {
    private String name;
    private String whatsapp;
    private String number;
    private String email;
    private static final Logger logger = LoggerFactory.getLogger(Person.class);

    public Person(String name, String number, String email) {
        try {

            this.name = name.toUpperCase();
            this.email = email.toUpperCase();

            if(name.isEmpty() || number.isEmpty() || email.isEmpty()) {
                throw new InvalidParameterException("Invalid parameters for create person");
            }
            if (number.length() == 11) {
                this.number = number;
                this.whatsapp = number;
            } else {
                this.number = "Not informed";
                this.whatsapp = "Not informed";
                throw new NumberLenghtInvalidExeception("Number not should be 11 character");
            }
            if(name.length() < 3 ) {
                this.name = "Not Informed Name";
                throw new InvalidParameterException("Name must be at least 3 characters");
            }
            if(!email.contains("@") ) {
                this.email = "Not Informed Email";
                throw new InvalidParameterException("Email address must be a valid email address");
            }

        } catch (NumberLenghtInvalidExeception | InvalidParameterException e) {
            logger.error("Error in process create people: ", e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWhatsapp() {
        return whatsapp;
    }
    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
