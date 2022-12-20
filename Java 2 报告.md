Java 2 报告

整体框架：springboot+vue

爬虫思路：利用httpclient获取rest-api中仓库的相关信息。并通过jpa将信息存储入数据库中，方便后续信息的获取

相关方法：

```java
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
```

获取信息部分：

后端：

整体结构：

后端分为controller，domain，service，mapper 四个部分，其中domain部分为数据处理过程中会涉及到的相关数据库的字段信息，controller部分监听相关端口是否被访问，及访问后返回前端相关信息，mapper部分通过注释从相关数据库的表格中获取信息，services部分对mapper传回的数据进行处理并传回controller。

相关代码举例：

获取不同时间段提交数量：

```
    @GetMapping("info/{id}/getCommitByHour")
    public List<commitHour> getCommitByHourRelease(@PathVariable int id){
        return getInfoServices.getCommitByHourRelease(id);
    }
```

```java
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
```

```java
    @Select("select count(id) from commit where date_part('hour',commit_time)>=#{time} and date_part('hour',commit_time)<(#{time}+6) and repo_id = #{id};")
    public int getCommitByHourRelease(int id,int time);
```

