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
    public List<developers> getDevelopers(@PathVariable int id){
        return getInfoServices.getDevelopers(id);
    }

    @GetMapping("info/{id}/issues")
    public List<issues> getAllIssues(@PathVariable int id){
        return getInfoServices.getAllIssues(id);
    }

    @GetMapping("info/{id}/issues/state")
    public List<issueState> getIssuesNum(@PathVariable int id){
        return getInfoServices.getIssuesNum(id);
    }

    @GetMapping("info/{id}/issues/analysis")
    public List<issueAnalyse> getIssuesAnalysis(@PathVariable int id){
        return getInfoServices.getIssuesAnalysis(id);
    }

    @GetMapping("info/{id}/issues/titleAnalysis")
    public List<issues> getTitleAnalysis(@PathVariable int id){
        return getInfoServices.getTitleAnalysis(id);
    }

    @GetMapping("info/issues/{issues_id}/comment")
    public List<comments> getAllComment(@PathVariable int issues_id){
        return getInfoServices.getAllComment(issues_id);
    }

    @GetMapping("info/{id}/issues/{issues_id}/commentAnalysis")
    public List<comments> getCommentAnalysis(@PathVariable int id,@PathVariable int issues_id){
        return getInfoServices.getCommentAnalysis(id,issues_id);
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

    @GetMapping("info/{id}/getCommitByDay")
    public List<commitByDay> getCommitByDay(@PathVariable int id){
        return getInfoServices.getCommitByDay(id);
    }

}
