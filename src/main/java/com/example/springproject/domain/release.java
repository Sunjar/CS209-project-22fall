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
public class release {
    private int id;
    private Timestamp publish_time;
    private Timestamp next_publish;
    private int repo_id;
}
