/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Film;
import beans.Spettacolo;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
/**
 *
 * @author Paolo
 */
public class Classi {
    public static class FilmSpettacolo{
        private Film f;
        private Spettacolo s;

        public Film getF() {
            return f;
        }

        public void setF(Film f) {
            this.f = f;
        }

        public Spettacolo getS() {
            return s;
        }

        public void setS(Spettacolo s) {
            this.s = s;
        }
        
    }
    
    public static void inviaEmail() throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("biglietto.pdf"));
        
        document.open();

        LineSeparator UNDERLINE
                = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);

        //CARICA IL QRCODE IN FORMATO PNG SUL PDF
        Image qrcode = Image.getInstance("qrcode.png");
        document.add(qrcode);
        qrcode.scaleAbsolute(20,20);
        //QUI ANDRANNO INSERITI I CAMPI DEL PDF, AL POSTO DEGLI ASTERISCHI ANDRANNO I VALORI DEL DB
        document.add(new Paragraph("    Nome Utente: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Cognome Utente: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Titolo Film: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Data Proiezione: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Orario Proiezione: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Tipologia di Biglietto/i: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Prezzo totale: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Posto/i: *****", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("    Grazie per aver acquistato il tuo biglietto online con noi!", FontFactory.getFont(FontFactory.HELVETICA, 15)));
        document.add(new Paragraph("    Stampa questo pdf, portalo con te al cinema e goditi il film!", FontFactory.getFont(FontFactory.HELVETICA, 15)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        //FOOTER, DA NON TOCCARE!
        document.add(UNDERLINE);
        document.add(new Paragraph("Per info Telefono: numeroditelefono", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Via Sommarive 9, Povo TN", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("email: mycinema@email.com", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Prenota i tuoi biglietti online su: www.mycinema.com", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Like us on Facebook! www.facebook.com/mycinema", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.close();
        
    }
}

