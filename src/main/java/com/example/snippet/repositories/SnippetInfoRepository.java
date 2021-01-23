package com.example.snippet.repositories;

import com.example.snippet.models.SnippetInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnippetInfoRepository extends JpaRepository<SnippetInfo, Integer> {
    SnippetInfo findByName(String name);
}
