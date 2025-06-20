package com.ricky.services;

import com.google.gson.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExportadorPDF {

    public static void exportarParaPDF(JsonObject character, String caminho) throws FileNotFoundException {
        Document documento = new Document();
        FileOutputStream FileOutputStream = new FileOutputStream(caminho);
        try {
            PdfWriter.getInstance(documento, FileOutputStream);
            documento.open();

            documento.add(new Paragraph("Character" , FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
            documento.add(new Paragraph(" ")); // espaço

            PdfPTable tabela = new PdfPTable(4);
            tabela.setWidthPercentage(100);
            tabela.setWidths(new int[]{2, 3, 2, 2});

            int id = character.get("id").getAsInt();
            String nome = character.get("name").getAsString();
            String status = character.get("status").getAsString();
            String especie = character.get("species").getAsString();

            adicionarCabecalho(tabela);
            tabela.addCell(String.valueOf(id));
            tabela.addCell(nome);
            tabela.addCell(status);
            tabela.addCell(especie);
            documento.add(tabela);
            documento.close();
            System.out.println("foi");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void adicionarCabecalho(PdfPTable tabela) {
        Font fonte = FontFactory.getFont(FontFactory.COURIER_BOLD);
        tabela.addCell(new PdfPCell(new Phrase("ID", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Nome", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Status", fonte)));
        tabela.addCell(new PdfPCell(new Phrase("Espécie", fonte)));

    }
}


