package com.zl.mapper;

import com.zl.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question (id,title,description,gmt_create,gmt_modified,creator,comment_count,view_count," +
            "like_count,tag) values(null,#{title},#{description},#{gmtCreate},#{gmtModified},#{creator}," +
            "#{commentCount},#{viewCount},#{likeCount},#{tag})")
    int insert(Question question);

    @Select("select question.id,title,question.description,question.gmt_create,question.gmt_modified,question.creator,comment_count,view_count," +
            "like_count,tag,user.avatar_url as avatarUrl from question left join user on question.creator = user.id " +
            "limit #{offset} ,#{size}")
    List<Question> list(@Param("offset") Integer offset,@Param("size") Integer size);

    @Select("select count(1) from question")
    int count();

    @Select("select question.id,title,question.description,question.gmt_create,question.gmt_modified,question.creator,comment_count,view_count," +
            "like_count,tag,user.avatar_url as avatarUrl from question left join user on question.creator = user.id " +
            "and question.creator=#{creator} " +
            "limit #{offset} ,#{size}")
    List<Question> listByCreator(@Param("creator") Integer creator,@Param("offset") Integer offset,
                          @Param("size") Integer size);

    @Select("select count(1) from question where question.creator=#{creator}")
    int countByCreator(@Param("creator") Integer creator);



}
