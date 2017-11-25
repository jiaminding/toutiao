package com.djm.dao;

import com.djm.model.News;
import com.djm.model.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by djm on 2017/10/22
 */
@Mapper
public interface NewsDAO {

    String TABLE_NAME = "news";
    String INSERT_FIELDS = "title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title}, #{link}, #{image}, #{likeCount}, #{commentCount}, #{createdDate}, #{userId})"})
    int addNews(News news);

    List<News> selectByUserIdAndOffset(@Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    News getById(int id);

    @Update({"update ", TABLE_NAME, " set comment_count=#{count} where id=#{id}"})
    int updateCommentCount(@Param("id") int id, @Param("count") int count);

    @Update({"update ", TABLE_NAME, " set like_count=#{count} where id=#{id}"})
    int updateLikeCount(@Param("id") int id, @Param("count") int count);
}
