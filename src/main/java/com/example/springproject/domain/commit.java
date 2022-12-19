package com.example.springproject.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class commit {
    private int id;
    private Timestamp commit_time;
    private  int release_id;
    private  int repo_id;
}
