package com.example.springproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class issueAnalyse {
    private double avg;
    private double extremum;
    private double max;
    private double min;
    private double variance;
    private double stedev;
}
