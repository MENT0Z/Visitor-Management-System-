package com.example.pre_approve_service.utils;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class QRCodeGenerator {

    public String generateQRCode(Integer visitorId) {
//        ByteArrayOutputStream stream = QRCode.from("Visitor ID: " + visitorId)
//                .withSize(250, 250)
//                .stream();
//        return Base64.getEncoder().encodeToString(stream.toByteArray());
        return "qr code generated";
    }

    public String generateQRCodeBase64(String text, int width, int height) {
        try {
            // Define encoding hints
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());

            // Generate QR code BitMatrix
            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(text, BarcodeFormat.QR_CODE, width, height, hints);

            // Convert BitMatrix to BufferedImage
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,
                    new MatrixToImageConfig());

            // Convert BufferedImage to Base64 String
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            byte[] qrBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(qrBytes);
        } catch (WriterException | java.io.IOException e) {
            throw new RuntimeException("Error generating QR code", e);
        }
    }
}


