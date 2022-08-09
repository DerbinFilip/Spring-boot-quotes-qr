package com.derbin.petclinic.controller;

import com.derbin.petclinic.service.QrCodeService;
import com.derbin.petclinic.service.QuotesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final int WIDTH = 250;
    private static final int HEIGHT = 250;
    private static final int DELAY = 5000;
    private static final Logger logger = LogManager.getLogger(QrCodeController.class);

    public QrCodeController(QuotesService quotesService) {
        this.quotesService = quotesService;
    }

    @GetMapping("/")
    public String getQR(Model model) {
        String text = quotesService.getQuotesById(quotesService.randomQuotes()).getText();
        String text1 = quotesService.getQuotesById(quotesService.randomQuotes()).getText();

        byte[] image = new byte[0];
        try {
            image = QrCodeService.getQR(text, WIDTH, HEIGHT);
            QrCodeService.generateQR(text1, WIDTH, HEIGHT, QR_PATH);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    deleteImage();
                }
            }, DELAY);
        } catch (Exception e) {
            logger.error(e);
        }

        String qrCode = Base64.getEncoder().encodeToString(image);

        model.addAttribute("text", text);
        model.addAttribute("text1", text1);
        model.addAttribute("qrcode", qrCode);
        return "qrcode";
    }

    public void deleteImage() {
        try {
            Files.delete(Path.of(QR_PATH));
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
