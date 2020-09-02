package com.java.urlShortenerService.controller;

import com.java.urlShortenerService.dto.UrlRequest;
import com.java.urlShortenerService.service.UrlConversionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/shortener/")
public class UrlConversionController {

    private final UrlConversionService urlConversionService;

    public UrlConversionController(UrlConversionService urlConversionService) {
        this.urlConversionService = urlConversionService;
    }

    @ApiOperation(value = "Convert to short url", notes = "Converts long url to short url")
    @PostMapping("convertLongUrlToShort")
    public String convertToShortUrl(@RequestBody UrlRequest request) {
        return urlConversionService.convertToShortUrl(request);
    }

    @ApiOperation(value = "Redirect", notes = "It Finds original url from short url and redirects to original url")
    @GetMapping(value = "{shortUrl}")
    public ResponseEntity<Void> getOriginalUrl(@PathVariable String shortUrl) {
        var url = urlConversionService.getOriginalUrl(shortUrl);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(url))
                .build();
    }

    @ApiOperation(value = "get the hits", notes = "it gives the hits for particular short url")
    @GetMapping(value = "hits/{shortUrl}")
    public int getHitsOfShortUrl(@PathVariable String shortUrl) {
        return urlConversionService.getHitsOfShortUrl(shortUrl);
    }
}
