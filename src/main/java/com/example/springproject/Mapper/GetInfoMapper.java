package com.example.springproject.Mapper;

import com.example.springproject.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface GetInfoMapper {
    @Select("select * from repos where id = #{id}")
    public List<repos> getReposById(int id);

    @Select("select * from repos")
    public List<repos> getAllRepos();

    @Select("select * from developers where repo_id = #{id} order by contributions desc limit 5")
    public  List<developers> gerDevelopers(int id);

    @Select("select * from commit")
    public List<commit> getAllCommits();

    @Select("select state,count(state) as issue_num from issues where repo_id = #{id} group by state")
    public List<issueState> getIssueNum(int id);
    @Select("select avg(solve_time-start_time) as avg,(max(solve_time-start_time)-min(solve_time-start_time)) as extremum from issues where repo_id = #{id} and state = 'closed';")
    public List<issueAnalyse> gerIssuesAnalysis(int id);

    @Select("select count(id) from release where repo_id = #{id}")
    public int getReleaseNum(int id);

    @Select("select count(id) as num from commit where repo_id = #{id} group by release_id order by release_id")
    public List<Integer> getCommitByRelease(int id);

    @Select("select count(id) from commit where date_part('hour',commit_time)>=#{time} and date_part('hour',commit_time)<(#{time}+6) and repo_id = #{id};")
    public int getCommitByHourRelease(int id,int time);

    @Select("select to_char(commit_time::DATE, 'YYYY-MM-DD') as data, count(id) as commitNum\n" +
            "from commit\n" +
            "where repo_id = #{id}  group by data order by data")
    public List<commitByDay> getCommitByDay(int id);
}
