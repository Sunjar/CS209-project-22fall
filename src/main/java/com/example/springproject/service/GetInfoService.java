package com.example.springproject.service;

import com.example.springproject.domain.*;
import java.util.List;

public interface GetInfoService {
    public List<repos> getRepos(int id);

    public List<repos> getAllRepos();

    public List<commit> getAllCommit();

    public List<developers> getDevelopers(int id);

    public List<issueState> getIssuesNum(int id);

    public List<issueAnalyse> getIssuesAnalysis(int id);

    public int getReleaseNum(int id);

    public List<Integer> getCommitByRelease(int id);

    public List<commitHour> getCommitByHourRelease(int id);

    public List<commitByDay> getCommitByDay(int id);

    public List<issues> getTitleAnalysis(int id);

    public List<comments> getCommentAnalysis(int id, int issue_id);

    public List<issues> getAllIssues(int id);

    public List<comments> getAllComment(int issues_id);
}
