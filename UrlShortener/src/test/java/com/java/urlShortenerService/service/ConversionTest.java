package com.java.urlShortenerService.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ConversionTest {

    private Conversion conversion = new Conversion();

    @Test
    public void encodeLessThan62() {
        assertEquals("f", conversion.encodeTheId(5));
    }

    @Test
    public void encodeGreaterThan62() {
        assertEquals("br", conversion.encodeTheId(79));
    }

    @Test
    public void decodeTheShortUrl() {
        assertEquals(7, conversion.decodeTheUrl("h"));
    }
}
