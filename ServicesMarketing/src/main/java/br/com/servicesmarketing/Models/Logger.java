package br.com.servicesmarketing.Models;

import org.slf4j.LoggerFactory;

public interface Logger {
//    static final org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);

    default org.slf4j.Logger getLogger() {
        return org.slf4j.LoggerFactory.getLogger(this.getClass());
    }
}
