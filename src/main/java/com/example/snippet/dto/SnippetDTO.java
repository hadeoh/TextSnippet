package com.example.snippet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetDTO {
    private String url;
    private String name;
    private Instant expires_at;
    private String snippet;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getExpires_at() {
        return expires_at;
    }

    public void setExpires_at(Instant expires_at) {
        this.expires_at = expires_at;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
}
