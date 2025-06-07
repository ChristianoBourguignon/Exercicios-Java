package com.ricky.controllers;

import com.google.gson.JsonObject;
import com.ricky.services.ExportadorPDF;


public class Controllers {
    public void ControllerExportPDF(JsonObject json) {
        try {
            ExportadorPDF.exportarParaPDF(json, "J:/Projetos/Projetos Java/ApiRicky/src/main/resources/teste.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
