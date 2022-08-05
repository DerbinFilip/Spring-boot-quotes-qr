package com.derbin.petclinic.controller;

import com.derbin.petclinic.service.QrCodeService;
import com.derbin.petclinic.service.QuotesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Timer;
import java.util.TimerTask;

@Controller
public class QrCodeController {
    private static final String QR_PATH = "./src/main/resources/static/img/QRCODE.png";
    private final QuotesService quotesService;

    public QrCodeController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping("/")
    public String getQR(Model model) {
        String text = quotesService.getQuotesById(quotesService.randomQuotes()).getText();
        String text1 = quotesService.getQuotesById(quotesService.randomQuotes()).getText();

        byte[] image = new byte[0];

        try {
            image = QrCodeService.getQR(text, 250, 250);
            QrCodeService.generateQR(text1, 250, 250, QR_PATH);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    deleteImage();
                }
            }, 5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String qrCode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("text", text);
        model.addAttribute("text1", text1);
        model.addAttribute("qrcode", qrCode);
        return "qrcode";
    }
    public void deleteImage(){
        try {
            Files.delete(Path.of(QR_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
