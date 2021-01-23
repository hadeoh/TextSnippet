package com.example.snippet.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "snippets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SnippetInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Instant expires_at;
    private String snippet;
    private  String password;

    public SnippetInfo(String name, Instant expires_at, String snippet, String password) {
        this.name = name;
        this.expires_at = expires_at;
        this.snippet = snippet;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpires_at(Instant expires_at) {
        this.expires_at = expires_at;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SnippetInfo() {
    }

    public SnippetInfo(String name, Instant expires_at, String snippet) {
        this.name = name;
        this.expires_at = expires_at;
        this.snippet = snippet;
    }

    public Instant getExpires_at() {
        return expires_at;
    }

    public String getSnippet() {
        return snippet;
    }

}
