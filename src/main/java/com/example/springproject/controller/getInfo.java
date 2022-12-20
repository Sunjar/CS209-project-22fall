package com.example.springproject.controller;
import com.example.springproject.domain.*;
import com.example.springproject.service.GetInfoService;
import com.example.springproject.service.getInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.*;
import java.util.List;

@RestController
@RequestMapping("/repo")
@CrossOrigin
@Validated
public class getInfo {
    private GetInfoService getInfoServices = new getInfoServiceImpl();

    @GetMapping ("info")
    public List<repos> getAllRepos(){
        return getInfoServices.getAllRepos();
    }

    @GetMapping ("info/{id}")
    public List<repos> getRepos(@PathVariable int id){
        return getInfoServices.getRepos(id);
    }

    @GetMapping("info/{id}/developers")
    public List<developers> gerDevelopers(@PathVariable int id){
        return getInfoServices.gerDevelopers(id);
    }

    @GetMapping("info/{id}/issues/state")
    public List<issueState> gerIssuesNum(@PathVariable int id){
        return getInfoServices.gerIssuesNum(id);
    }


    @GetMapping("info/info/{id}/issues/analysis")
    public List<issueAnalyse> gerIssuesAnalysis(@PathVariable int id){
        return getInfoServices.gerIssuesAnalysis(id);
    }

    @GetMapping("info/{id}/release")
    public int getReleaseNum(@PathVariable int id){
        return getInfoServices.getReleaseNum(id);
    }

    @GetMapping("info/{id}/getCommitByRelease")
    public List<Integer> getCommitByRelease(@PathVariable int id){
        return getInfoServices.getCommitByRelease(id);
    }

    @GetMapping ("info/{id}/allCommit")
    public List<commit> getCommit(@PathVariable int id){
        return getInfoServices.getAllCommit();
    }

    @GetMapping("info/{id}/getCommitByHour")
    public List<commitHour> getCommitByHourRelease(@PathVariable int id){
        return getInfoServices.getCommitByHourRelease(id);
    }



}
