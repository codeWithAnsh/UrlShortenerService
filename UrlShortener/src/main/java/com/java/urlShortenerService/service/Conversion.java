package com.java.urlShortenerService.service;

import org.springframework.stereotype.Service;

@Service
public class Conversion {

    private static final String allowedStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final char[] allowedChars = allowedStr.toCharArray();
    private final int base = allowedChars.length;

    public String encodeTheId(long data) {
        var encodedString = new StringBuilder();

        if(data == 0) {
            return String.valueOf(allowedChars[0]);
        }

        while (data > 0) {
            encodedString.append(allowedChars[(int) (data % base)]);
            data = data / base;
        }

        return encodedString.reverse().toString();
    }

    public long decodeTheUrl(String data) {
        var characters = data.toCharArray();
        var length = characters.length;

        var decodedNumber = 0;

        var count = 1;
        for (char character : characters) {
            decodedNumber += allowedStr.indexOf(character) * Math.pow(base, length - count);
            count++;
        }
        return decodedNumber;
    }
}
