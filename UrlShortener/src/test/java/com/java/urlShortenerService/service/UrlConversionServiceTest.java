package com.java.urlShortenerService.service;

import com.java.urlShortenerService.dto.UrlRequest;
import com.java.urlShortenerService.entity.Url;
import com.java.urlShortenerService.repository.UrlConversionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlConversionServiceTest {

    @Mock
    UrlConversionRepository mockUrlConversionRepository;

    @Mock
    Conversion mockConversion;

    @InjectMocks
    UrlConversionService urlConversionService;

    @Test
    public void convertToShortUrlTest() {
        var url = new Url();
        url.setLong_url("https://en.wikipedia.org/wiki/Cricket");
        url.setId(5);
        url.setClient_id("111");
        url.setHits(0);

        //when(mockUrlConversionRepository.findByUrlAndClientId(url.getLong_url(), url.getClient_id())).thenReturn(Optional.empty());
        when(mockUrlConversionRepository.save(any(Url.class))).thenReturn(url);
        when(mockConversion.encodeTheId(url.getId())).thenReturn("f");

        var urlRequest = new UrlRequest();
        urlRequest.setLongUrl("https://en.wikipedia.org/wiki/Cricket");

        assertEquals("f", urlConversionService.convertToShortUrl(urlRequest));
    }

    @Test
    public void getOriginalUrlTest() {
        when(mockConversion.decodeTheUrl("h")).thenReturn((long) 7);

        var url = new Url();
        url.setLong_url("https://en.wikipedia.org/wiki/Cricket");
        url.setId(7);
        url.setClient_id("111");
        when(mockUrlConversionRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://en.wikipedia.org/wiki/Cricket", urlConversionService.getOriginalUrl("h"));

    }

    @Test
    public void getHitsOfShortUrl() {
        when(mockConversion.decodeTheUrl("h")).thenReturn((long) 7);

        var url = new Url();
        url.setLong_url("https://en.wikipedia.org/wiki/Cricket");
        url.setId(7);
        url.setClient_id("111");
        url.setHits(2);
        when(mockUrlConversionRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals(2, urlConversionService.getHitsOfShortUrl("h"));

    }

}
