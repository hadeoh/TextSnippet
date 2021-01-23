package com.example.snippet.services;

import com.example.snippet.dto.SnippetDTO;
import com.example.snippet.dto.SnippetRequest;

public interface SnippetService {

    SnippetDTO addSnippet(SnippetRequest snippetRequest);
    SnippetDTO getSnippet(String name);
    SnippetDTO editSnippet(SnippetRequest snippetRequest);
}
