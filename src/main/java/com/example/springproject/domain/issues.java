package com.example.springproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class issues {
    private int id;
    private String title;
    private String description;
    private String state;
    private Timestamp start_time;
    private Timestamp solve_time;
    private int repo_id;
}
