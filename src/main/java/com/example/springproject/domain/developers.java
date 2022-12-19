package com.example.springproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class developers {
    private int id;
    private String user_name;
    private int contributions;
    private String avatar_url;
    private String html_url;
    private int repo_id;
}
