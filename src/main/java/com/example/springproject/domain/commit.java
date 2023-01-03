package com.example.springproject.domain;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
