package com.example.springproject.Mapper;

import com.example.springproject.domain.*;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface GetInfoMapper {
    @Select("select * from repos where id = #{id}")
    public List<repos> getReposById(int id);

    @Select("select * from repos")
    public List<repos> getAllRepos();

    @Select("select * from developers where repo_id = #{id} order by contributions desc")
    public  List<developers> getDevelopers(int id);

    @Select("select * from commit")
    public List<commit> getAllCommits();

    @Select("select state,count(state) as issue_num from issues where repo_id = #{id} group by state")
    public List<issueState> getIssueNum(int id);

    @Select("select avg(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP))) as avg,\n" +
            "       (max(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP)))\n" +
            "            -min(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP)))) as extremum,\n" +
            "       max(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP))) as max,\n" +
            "       min(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP))) as min,\n" +
            "       variance(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP))) as variance,\n" +
            "       stddev(date_part('day',cast(solve_time as TIMESTAMP)-cast(start_time as TIMESTAMP))) as stedev from issues where repo_id = #{id} and state = 'closed'")
    public List<issueAnalyse> getIssuesAnalysis(int id);

    @Select("select count(id) from release where repo_id = #{id}")
    public int getReleaseNum(int id);

    @Select("select count(id) as num from commit where repo_id = #{id} group by release_id order by release_id")
    public List<Integer> getCommitByRelease(int id);

    @Select("select count(id) from commit where date_part('hour',commit_time)>=#{time} and date_part('hour',commit_time)<(#{time}+6) and repo_id = #{id};")
    public int getCommitByHourRelease(int id, int time);

    @Select("select to_char(commit_time::DATE, 'YYYY-MM-DD') as data, count(id) as commitNum\n"
            + "from commit\n"
            + "where repo_id = #{id}  group by data order by data")
    public List<commitByDay> getCommitByDay(int id);

    @Select("SELECT * FROM issues where position((select name from repos where id = #{{id}) in title)!=0 and repo_id = #{id};")
    public List<issues> getTitleAnalysis(int id);

    @Select("SELECT * FROM issues where position((select name from repos where id = #{{id}) in title)!=0 and issues_id = #{issue_id};")
    public List<comments> getCommentAnalysis(int id, int issue_id);

    @Select("select * from issues where repo_id = #{id}")
    public List<issues> getAllIssues(int id);

    @Select("select * from comments where issues_id = #{issues_id}")
    public List<comments> getAllComment(int issues_id);
}
