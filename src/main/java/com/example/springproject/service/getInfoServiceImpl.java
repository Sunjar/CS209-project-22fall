package com.example.springproject.service;

import com.example.springproject.Mapper.AddInfoMapper;
import com.example.springproject.Mapper.GetInfoMapper;
import com.example.springproject.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Service
public class getInfoServiceImpl implements GetInfoService {

    private static GetInfoMapper getInfoMapper;
    @Autowired
    private GetInfoMapper getInfoMapper2;

    @PostConstruct
    public void init(){
        getInfoMapper = getInfoMapper2;
    }
    @Override
    public List<repos> getRepos(int id) {
        return getInfoMapper.getReposById(id);
    }

    @Override
    public List<repos> getAllRepos() {
        return getInfoMapper.getAllRepos();
    }

    @Override
    public List<commit> getAllCommit() {
        List<commit> z = getInfoMapper.getAllCommits();
        return z;
    }

    @Override
    public List<developers> gerDevelopers(int id) {
        return getInfoMapper.gerDevelopers(id);
    }

    @Override
    public List<issueState> gerIssuesNum(int id) {


        return getInfoMapper.getIssueNum(id);
    }

    @Override
    public List<issueAnalyse> gerIssuesAnalysis(int id) {
        return getInfoMapper.gerIssuesAnalysis(id);
    }

    @Override
    public int getReleaseNum(int id) {
        return getInfoMapper.getReleaseNum(id);
    }

    @Override
    public List<Integer> getCommitByRelease(int id) {
        return getInfoMapper. getCommitByRelease(id);
    }

    @Override
    public List<commitHour> getCommitByHourRelease(int id) {
        List<commitHour> ans = new ArrayList<>();
        String[] interval = {"before dawn","morning","afternoon","evening"};
        for (int j = 0; j < 4; j++) {
            commitHour a = new commitHour();
            int z = getInfoMapper.getCommitByHourRelease(id, j*6);
            a.Time_interval = interval[j];
            a.commit_num = z;
            ans.add(a);
        }
        return ans;
    }
}
