package com.java.urlShortenerService.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Request object for POST Api")
public class UrlRequest {

    @ApiModelProperty(required = true, notes = "Url to convert to short url")
    private String longUrl;

    @ApiModelProperty(required = true, notes = "clientId")
    private String clientId;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getClientId() { return clientId; }

    public void setClientId(String clientId) { this.clientId = clientId; }
}
