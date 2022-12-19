package com.example.springproject.service;
import com.example.springproject.Mapper.AddInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
@Service
public class addInfoImpl implements AddInfo{
    private static AddInfoMapper addInfoMapper;
    @Autowired
    private AddInfoMapper addInfoMapper2;

    @PostConstruct
    public void init(){
        addInfoMapper = addInfoMapper2;
    }


    @Override
    public Boolean addRepos(String name) {
        return addInfoMapper.addRepos(name);
    }

    @Override
    public int getRepoId(String name){
        return addInfoMapper.getRepoId(name);
    }

    @Override
    public Boolean addDevelopers(String user_name, int contribution,String avatar_url,String html_url, int repo_id) {
        return addInfoMapper.addDevelopers(user_name,contribution,avatar_url,html_url,repo_id);
    }

    @Override
    public Boolean addIssues(String title, String description, String state, Timestamp start_time, Timestamp solve_time, int repo_id) {
        return addInfoMapper.addIssues(title,description,state,start_time,solve_time,repo_id);
    }

    @Override
    public int getIssueId(String title) {
        return addInfoMapper.getIssueId(title);
    }

    @Override
    public Boolean addComments(String comment, int issues_id) {
        return addInfoMapper.addComments(comment,issues_id);
    }

    @Override
    public Boolean addRelease(int id,Timestamp publish_time, Timestamp next_publish,int repo_id) {
        return addInfoMapper.addRelease(id,publish_time,next_publish,repo_id);
    }

    @Override
    public int getReleaseId(Timestamp commit_time,int repo_id) {
        return addInfoMapper.getReleaseId(commit_time,repo_id);
    }

    @Override
    public Boolean addCommit(Timestamp commit_time,int release_id, int repo_id) {
        return addInfoMapper.addCommit(commit_time,release_id,repo_id);
    }
}
