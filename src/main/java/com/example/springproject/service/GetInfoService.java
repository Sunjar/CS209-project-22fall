package com.example.springproject.service;

import com.example.springproject.domain.*;
import java.util.List;

public interface GetInfoService {
    public List<repos> getRepos(int id);

    public List<repos> getAllRepos();

    public List<commit> getAllCommit();

    public List<developers> gerDevelopers(int id);

    public List<issueState> gerIssuesNum(int id);

    public List<issueAnalyse> gerIssuesAnalysis(int id);

    public int getReleaseNum(int id);

    public List<Integer> getCommitByRelease(int id);

    public List<commitHour> getCommitByHourRelease(int id);

    public List<commitByDay> getCommitByDay(int id);
}
