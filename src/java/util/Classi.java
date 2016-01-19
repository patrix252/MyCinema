/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import beans.Film;
import beans.Posto;
import beans.Prenotazione;
import beans.Spettacolo;
import beans.Utente;
import java.io.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
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
    
    //il percorso dove creare il biglietto
    public static final String DEST = "/biglietto.pdf";
    
    public static void inviaEmail(ServletContext context, HttpSession session){
        String relativeWebPath = "/biglietto.pdf";
        String absoluteDiskPath = context.getRealPath(relativeWebPath);
        File file = new File(absoluteDiskPath);
        try {
            new Classi().createPDF(absoluteDiskPath, session, context);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Classi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createPDF(String dest, HttpSession session, ServletContext context) throws DocumentException, IOException {
        
        Document document = new Document();
        Utente utente = (Utente)session.getAttribute("utente");
        Spettacolo spett = (Spettacolo)session.getAttribute("spettacoloPrenotazione");
        Film film = (Film)session.getAttribute("film");
        List<Posto> postiInteri = (List<Posto>)session.getAttribute("postiInteri");
        String pi = "";
        for(int i=0; i<postiInteri.size(); i++){
            pi = pi.concat(postiInteri.get(i).getRiga() + "-" + postiInteri.get(i).getColonna()+" ");
        }
        List<Posto> postiRidotti = (List<Posto>)session.getAttribute("postiRidotti");
        String pr = "";
        for(int i=0; i<postiRidotti.size(); i++){
            pr = pr.concat(postiRidotti.get(i).getRiga() + "-" + postiRidotti.get(i).getColonna()+" ");
        }
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        LineSeparator UNDERLINE = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
        //CARICA IL QRCODE IN FORMATO PNG SUL PDF
        String testoQR = utente.getNome()+" "+utente.getCognome()+"\n"+
                         film.getTitolo()+"\n"+
                         "MyCinema\n" +
                         spett.getData() + " " + spett.getOra() + "\n" +
                         "posti ridotti: " + postiRidotti.size() + "\n" +
                         pr + "\n" +
                         "posti interi: " + postiInteri.size() + "\n" +
                         pi + "\n";                     
        Image qrcode = Image.getInstance(createQrCode(context, testoQR));
        document.add(qrcode);
        qrcode.scaleAbsolute(20,20);
        document.add(new Paragraph("    Nome Utente: " + utente.getNome(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Cognome Utente: " + utente.getCognome(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Titolo Film: " + film.getTitolo(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Data Proiezione: " + spett.getData(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Orario Proiezione: " + spett.getOra() , FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Prezzo totale: " + session.getAttribute("totale") + "€", FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Tipologia di Biglietto: ridotti " + postiRidotti.size(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Posti: " + pr, FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Tipologia di Biglietto: interi " + postiInteri.size(), FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph("    Posti: " + pi, FontFactory.getFont(FontFactory.TIMES, 19)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("    Grazie per aver acquistato il tuo biglietto online con noi!", FontFactory.getFont(FontFactory.HELVETICA, 15)));
        document.add(new Paragraph("    Stampa questo pdf, portalo con te al cinema e goditi il film!", FontFactory.getFont(FontFactory.HELVETICA, 15)));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        //FOOTER, DA NON TOCCARE!
        document.add(UNDERLINE);
        document.add(new Paragraph("Per info Telefono: numeroditelefono", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Via Sommarive 9, Povo TN", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("email: mycinemastar@gmail.com", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Prenota i tuoi biglietti online su: www.mycinema.com", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.add(new Paragraph("Like us on Facebook! www.facebook.com/mycinema", FontFactory.getFont(FontFactory.COURIER, 10)));
        document.close();  
        inviaPDF(dest, utente.getEmail());
        
    }
    
    public static void inviaPDF(String dest, String mail){
        String to=mail;//change accordingly   
        final String user="mycinemastar@gmail.com";//change accordingly     
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(props, auth);  
      
        try{   
            Message message = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject("Ticket MyCinema");
            // -- Set some other header information --
            message.setHeader("MyMail", "Mr. XYZ" );
            message.setSentDate(new Date());
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("In allegato troverà il pdf con il biglietto da stampare e presentare al cinema il giorno della proiezione. Grazie per aver scelto MyCinema!");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(dest);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(dest);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);  
            Transport.send(message);      
            System.out.println("message sent....");   

        }catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
    
    public String createQrCode(ServletContext context, String myCodeText){
        
        // change path as per your laptop/desktop location
        String relativeWebPath = "/QrCode.png";
        String absoluteDiskPath = context.getRealPath(relativeWebPath);
        int size = 256;
        String fileType = "png";
        File myFile = new File(absoluteDiskPath);
        try {
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText,BarcodeFormat.QR_CODE, size, size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
 
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
 
            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, myFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
        return absoluteDiskPath;
    }

}

class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = "mycinemastar@gmail.com";           // specify your email id here (sender's email id)
            String password = "mycinema252";                      // specify your password here
            return new PasswordAuthentication(username, password);
        }
  }