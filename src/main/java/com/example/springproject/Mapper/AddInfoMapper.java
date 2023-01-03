package com.example.springproject.Mapper;

import java.sql.Timestamp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AddInfoMapper {
    @Insert("insert into repos(name) values (#{name})")
    public Boolean addRepos(String name);

    @Select("select max(id) from repos where name = #{name}")
    public int getRepoId(String name);

    @Insert("insert into issues(title,description,state,start_time,solve_time,repo_id) values (#{title},#{description},#{state},#{start_time},#{solve_time},#{repo_id})")
    public Boolean addIssues(String title, String description, String state, Timestamp start_time, Timestamp solve_time, int repo_id);

    @Select("select max(id) from issues where title = #{title}")
    public int getIssueId(String title);

    @Insert("insert into comments(comment,issues_id) values (#{comment},#{issues_id})")
    public Boolean addComments(String comment, int issues_id);

    @Insert("insert into developers(user_name,contributions,avatar_url,html_url,repo_id) values (#{user_name},#{contribution},#{avatar_url},#{html_url},#{repo_id})")
    public Boolean addDevelopers(String user_name, int contribution, String avatar_url,String html_url, int repo_id);

    @Insert("insert into release(id,publish_time,next_publish,repo_id) values (#{id},#{publish_time},#{next_publish},#{repo_id})")
    public Boolean addRelease(int id, Timestamp publish_time,Timestamp next_publish, int repo_id);

    @Select("select coalesce(sum(id),0)as id from release where publish_time<=#{commit_time} and next_publish>#{commit_time} and repo_id = #{repo_id}")
    public int getReleaseId (Timestamp commit_time, int repo_id);

    @Insert("insert into commit(commit_time,release_id,repo_id) values (#{commit_time},#{release_id},#{repo_id})")
    public Boolean addCommit(Timestamp commit_time, int release_id, int repo_id);
}
