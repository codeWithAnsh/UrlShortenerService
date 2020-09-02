package com.java.urlShortenerService.service;

import com.java.urlShortenerService.repository.UrlConversionRepository;
import com.java.urlShortenerService.dto.UrlRequest;
import com.java.urlShortenerService.entity.Url;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UrlConversionService {

    private final UrlConversionRepository urlConversionRepository;
    private final Conversion conversion;


    public UrlConversionService(UrlConversionRepository urlConversionRepository, Conversion conversion) {
        this.urlConversionRepository = urlConversionRepository;
        this.conversion = conversion;
    }

    /*
      This api converts the long url to short url.
     */
    public String convertToShortUrl(UrlRequest request) {
        long id;
        Optional<Url> optionalUrlFromDb = urlConversionRepository.findByUrlAndClientId(request.getLongUrl(), request.getClientId());
        if(optionalUrlFromDb.isEmpty()) {
            var url = new Url();
            url.setLong_url(request.getLongUrl());
            url.setCreated_at(new Date());
            url.setClient_id(request.getClientId());
            url.setHits(0);
            var entity = urlConversionRepository.save(url);
            id = entity.getId();
        }
        else {
            id = optionalUrlFromDb.get().getId();
        }
        return conversion.encodeTheId(id);
    }

    /*
      This api returns the long url with short url.
     */
    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decodeTheUrl(shortUrl);
        var entity = urlConversionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity present with " + shortUrl));

        entity.setHits(entity.getHits() + 1);
        urlConversionRepository.save(entity);

        return entity.getLong_url();
    }

    /*
      This api returns the hits for the particular short url.
     */
    public int getHitsOfShortUrl(String shortUrl) {
        var id = conversion.decodeTheUrl(shortUrl);
        var entity = urlConversionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity present with " + shortUrl));

        return entity.getHits();
    }
}
