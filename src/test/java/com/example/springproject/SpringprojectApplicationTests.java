package com.example.springproject;

import com.example.springproject.controller.InfoController;
import com.example.springproject.controller.getInfo;
import com.example.springproject.domain.commitHour;
import com.example.springproject.domain.issueAnalyse;
import com.example.springproject.service.AddInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringprojectApplicationTests {
    @Autowired
    AddInfo addInfo;
	@Test
    void testInsertRepos(){
        InfoController infoController1 = new InfoController();
        String url = "https://api.github.com/repos/z-pattern-matching/z";
        infoController1.addInfos(url);
//        getInfo getInfo = new getInfo();
//        List<issueAnalyse> z = getInfo.gerIssuesAnalysis(1);
//        List<commitHour> x = getInfo.getCommitByHourRelease(1);
//
       int a = 0;
    }

}

