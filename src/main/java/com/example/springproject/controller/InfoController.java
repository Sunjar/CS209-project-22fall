package com.example.springproject.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.service.AddInfo;
import com.example.springproject.service.addInfoImpl;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
@RestController
@CrossOrigin
@RequestMapping("/add")
public class InfoController {
    
    final String token = "token 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";

    @SneakyThrows
    @PostMapping("/crawler")
    public void addInfos(@RequestParam("url") String urls) {
        AddInfo addInfo = new addInfoImpl();
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(urls))
                .setHeader("Authorization", token)
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String t = response.body().substring(1, response.body().length() - 1);
        if (t.charAt(0) != '{') {
            t = "{" + t + "}";
        }
        JSONObject jsonObject = JSON.parseObject(t);
        String name = jsonObject.getString("name");
        addInfo.addRepos(name);
        int repo_Id = addInfo.getRepoId(name);
        JSONArray jsonArray;
        int q = 1;
        while (true) {
            String urlIss = urls + "/issues?state=all&page=" + q + "&per_page=100";
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlIss))
                    .setHeader("Authorization", token)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            t = response.body().substring(1, response.body().length() - 1);
            if (t.equals("")) {
                break;
            }
            t = "[" + t + "]";
            jsonArray = JSONArray.parseArray(t);
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String z = jsonObject.getString("created_at");
                Timestamp start_time;
                DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                if (z != null) {
                    z = z.replace("T", "");
                    z = z.replace("Z", "");
                    start_time = new Timestamp(format.parse(z).getTime());
                } else {
                    start_time = null;
                }
                z = jsonObject.getString("closed_at");
                Timestamp close_time;
                if (z != null) {
                    z = z.replace("T", "");
                    z = z.replace("Z", "");
                    close_time = new Timestamp(format.parse(z).getTime());
                } else {
                    close_time = null;
                }
                addInfo.addIssues(jsonObject.getString("title"), jsonObject.getString("body"), jsonObject.getString("state"), start_time, close_time, repo_Id);
                String url = urls + "/issues/" + (i + 1) + "/comments";
                request = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .setHeader("Authorization", token)
                        .build();
                response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                t = response.body().substring(1, response.body().length() - 1);
                t = "[" + t + "]";
                JSONArray jsonArrayCom = JSONArray.parseArray(t);
                int issues_id = addInfo.getIssueId(jsonObject.getString("title"));
                for (int j = 0; j < jsonArrayCom.size(); j++) {
                    jsonObject = jsonArrayCom.getJSONObject(j);
                    addInfo.addComments(jsonObject.getString("body"), issues_id);
                }
            }
            q += 1;
        }

        q = 1;
        while (true) {
            String urlDeveloper = urls + "/contributors?page=" + q + "&per_page=100";
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlDeveloper))
                    .setHeader("Authorization", token)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            t = response.body().substring(1, response.body().length() - 1);
            if (t.equals("")) break;
            t = "[" + t + "]";
            jsonArray = JSONArray.parseArray(t);
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                addInfo.addDevelopers(jsonObject.getString("login"), Integer.parseInt(jsonObject.getString("contributions")), jsonObject.getString("avatar_url"), jsonObject.getString("html_url"), repo_Id);
            }
            q += 1;
        }
        q = 1;
        while (true) {
            String urlRelease = urls + "/releases?page=" + q + "&per_page=100";
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlRelease))
                    .setHeader("Authorization", token)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            t = response.body().substring(1, response.body().length() - 1);
            if (t.equals("")) break;
            t = "[" + t + "]";
            jsonArray = JSONArray.parseArray(t);
            for (int i = jsonArray.size()-1; i >=0 ; i--) {
                jsonObject = jsonArray.getJSONObject(i);
                String z = jsonObject.getString("published_at");
                Timestamp release_time;
                DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                if (z != null) {
                    z = z.replace("T", "");
                    z = z.replace("Z", "");
                    release_time = new Timestamp(format.parse(z).getTime());
                } else {
                    release_time = null;
                }
                Timestamp nextRelease;
                if (i!=0){
                    jsonObject =  jsonArray.getJSONObject(i-1);
                    z = jsonObject.getString("published_at");
                    if (z != null) {
                        z = z.replace("T", "");
                        z = z.replace("Z", "");
                        nextRelease= new Timestamp(format.parse(z).getTime());
                    } else {
                       nextRelease = null;
                    }
                }
                else {
                    nextRelease = new Timestamp(format.parse("2022-12-2500:00:00").getTime());
                }
                addInfo.addRelease(jsonArray.size()-i,release_time, nextRelease,repo_Id);
            }
            q += 1;
        }
        q = 1;
        while (true) {
            String urlCommits = urls + "/commits?page=" + q + "&per_page=100";
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(urlCommits))
                    .setHeader("Authorization", token)
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            t = response.body().substring(1, response.body().length() - 1);
            if (t.equals("")) break;
            t = "[" + t + "]";
            jsonArray = JSONArray.parseArray(t);
            for (int i = 0; i < jsonArray.size(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String z = jsonObject.getJSONObject("commit").getJSONObject("author").getString("date");
                Timestamp commit_time;
                DateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                if (z != null) {
                    z = z.replace("T", "");
                    z = z.replace("Z", "");
                    commit_time = new Timestamp(format.parse(z).getTime());
                } else {
                    commit_time = null;
                }
                if (commit_time!=null){
                    int release_id = addInfo.getReleaseId(commit_time,repo_Id);
                    addInfo.addCommit(commit_time,release_id, repo_Id);
                }
            }
            q += 1;
        }
//        JSONArray js = jsonObject.getJSONArray("abilities");
////        System.out.println(js);
//        System.out.print("Abilities: ");
//        List<String> list = new ArrayList<>();
//        for (Object j : js) {
//            JSONObject js1 = (JSONObject) j;
//            list.add(js1.getJSONObject("ability").getString("name"));
//        }
//        System.out.println(list);
    }
}
