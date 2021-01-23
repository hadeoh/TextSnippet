package com.example.snippet.controllers;

import com.example.snippet.dto.SnippetDTO;
import com.example.snippet.dto.SnippetRequest;
import com.example.snippet.services.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SnippetController {

    private SnippetService snippetService;

    @Autowired
    public SnippetController(SnippetService snippetService) {
        this.snippetService = snippetService;
    }

    @PostMapping("/snippets")
    public ResponseEntity<SnippetDTO> addSnippet(@RequestBody SnippetRequest snippetRequest) {
        return new ResponseEntity<>(snippetService.addSnippet(snippetRequest), HttpStatus.CREATED);
    }

    @GetMapping("/snippets/{name}")
    public ResponseEntity<SnippetDTO> getSnippet(@PathVariable String name) {
        SnippetDTO snippetDTO = snippetService.getSnippet(name);
        ResponseEntity<SnippetDTO> response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        if (snippetDTO.getName() != null) {
            response = new ResponseEntity<>(snippetDTO, HttpStatus.OK);
        }
        return response;
    }

    @PutMapping("/snippets/{name}")
    public  ResponseEntity<SnippetDTO> editSnippet(@PathVariable String name, @RequestBody SnippetRequest snippetRequest) {
        snippetRequest.setName(name);
        SnippetDTO snippetDTO = snippetService.editSnippet(snippetRequest);
        ResponseEntity<SnippetDTO> response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        if (snippetDTO.getPassword() != null) {
            response = new ResponseEntity<>(snippetDTO, HttpStatus.OK);
        }
        return response;
    }
}
