package com.example.springproject.service;

import java.sql.Timestamp;

public interface AddInfo {
    public Boolean addRepos(String name);

    public int getRepoId(String name);

    public Boolean addDevelopers(String user_name, int contribution,
                                 String avatar_url, String html_url, int repo_id);

    public Boolean addIssues(String title, String description, String state,
                             Timestamp start_time, Timestamp solve_time, int repo_id);

    public int getIssueId(String title);

    public Boolean addComments(String comment, int repo_id);

    public Boolean addRelease(int id, Timestamp publish_time, Timestamp next_publish,int repo_id);

    public int getReleaseId(Timestamp commit_time, int repo_id);

    public Boolean addCommit(Timestamp commit_time, int release_id, int repo_id);
}
