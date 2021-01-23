package com.example.snippet.services;

import com.example.snippet.dto.SnippetDTO;
import com.example.snippet.dto.SnippetRequest;
import com.example.snippet.models.SnippetInfo;
import com.example.snippet.repositories.SnippetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

@Service
public class SnippetServiceImpl implements SnippetService{

    private SnippetInfoRepository snippetInfoRepository;

    @Autowired
    public SnippetServiceImpl(SnippetInfoRepository snippetInfoRepository) {
        this.snippetInfoRepository = snippetInfoRepository;
    }

    @Override
    public SnippetDTO addSnippet(SnippetRequest snippetRequest) {
        SnippetDTO snippetDTO = new SnippetDTO();
        try {
            snippetDTO.setSnippet(snippetRequest.getSnippet());
            snippetDTO.setName(snippetRequest.getName());
            snippetDTO.setUrl("https://localhost:8080/snippets/" + snippetRequest.getName());
            Calendar calendar = Calendar.getInstance();
            System.out.println("Current Date = " + calendar.getTime());
            calendar.add(Calendar.SECOND, snippetRequest.getExpires_in());
            System.out.println("Updated Date = " + calendar.getTime().toInstant());
            snippetDTO.setExpires_at(calendar.getTime().toInstant());
            snippetDTO.setPassword(snippetRequest.getPassword());
            SnippetInfo snippetInfo = new SnippetInfo(snippetDTO.getName(), snippetDTO.getExpires_at(),
                    snippetDTO.getSnippet(), snippetDTO.getPassword());
            snippetInfoRepository.save(snippetInfo);
        } catch (Exception e) {
            System.out.println("An error was encountered" + e.getMessage());
        }
        return snippetDTO;
    }

    @Override
    public SnippetDTO getSnippet(String name) {
        SnippetDTO snippetDTO = new SnippetDTO();
        try {
            SnippetInfo snippetInfo = snippetInfoRepository.findByName(name);
            Calendar calendar = Calendar.getInstance();
            Instant snippetTime = snippetInfo.getExpires_at();
            Instant currentTime = calendar.getTime().toInstant();
            System.out.println("The first Instant object is: " + snippetTime);
            System.out.println("The second Instant object is: " + currentTime);
            int val = snippetTime.compareTo(currentTime);
            if(val < 0) {
                snippetDTO.setName(null);
            } else {
                Instant newSnippetTime = snippetTime.plusSeconds(30);
                System.out.println(newSnippetTime);
                snippetInfo.setExpires_at(newSnippetTime);
                snippetInfoRepository.save(snippetInfo);
                snippetDTO.setSnippet(snippetInfo.getSnippet());
                snippetDTO.setExpires_at(snippetInfo.getExpires_at());
                snippetDTO.setUrl("https://localhost:8080/snippets/" + name);
                snippetDTO.setName(snippetInfo.getName());
            }
        } catch(Exception e) {
            System.out.println("An error was encountered due to" + e.getMessage());
        }
        return snippetDTO;
    }

    @Override
    public SnippetDTO editSnippet(SnippetRequest snippetRequest) {
        SnippetDTO snippetDTO = new SnippetDTO();
        try {
            SnippetInfo snippetInfo = snippetInfoRepository.findByName(snippetRequest.getName());
            if (snippetInfo.getPassword().equals(snippetRequest.getPassword())) {
                Instant snippetTime = snippetInfo.getExpires_at();
                Integer timeTAdd = 30;
                if (snippetRequest.getExpires_in() != null) {
                    timeTAdd = snippetRequest.getExpires_in();
                }
                Instant newSnippetTime = snippetTime.plusSeconds(timeTAdd);
                System.out.println(newSnippetTime);
                snippetInfo.setExpires_at(newSnippetTime);
                snippetInfo.setSnippet(snippetRequest.getSnippet());
                snippetInfoRepository.save(snippetInfo);
                snippetDTO.setSnippet(snippetInfo.getSnippet());
                snippetDTO.setExpires_at(snippetInfo.getExpires_at());
                snippetDTO.setUrl("https://localhost:8080/snippets/" + snippetDTO.getName());
                snippetDTO.setName(snippetInfo.getName());
                snippetDTO.setPassword(snippetRequest.getPassword());
            } else {
                snippetDTO.setPassword(null);
            }

        } catch (Exception e) {
            System.out.println("An error occured due to: " + e.getMessage());
        }
        return snippetDTO;
    }
}
