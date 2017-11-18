package com.djm.dao;

import com.djm.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/12
 */
@Mapper
public interface CommentDAO {

    String TABLE_NAME = " comment ";

    String INSERT_FIELDS = " content, entity_id, entity_type, created_date, user_id, status ";

    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(" + INSERT_FIELDS, ") values(#{content}, #{entityId}, #{entityType}, #{createdDate}, #{userId}, #{status})"})
    public int addComment(Comment comment);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
    public List<Comment> getComments(@Param("entityType") int entityType,
                                    @Param("entityId") int entityId);

    @Select({"select count(id) from ", TABLE_NAME, " where entity_type=#{entityType} and entity_id=#{entityId}"})
    public int getCommentCount(@Param("entityType") int entityType,
                                    @Param("entityId") int entityId);

    @Update({"updata", TABLE_NAME, "set status=#{status} where entity_id=#{entityId} and entity_type={entityType}"})
    public void updateStatus(@Param("entityId") int entityId, @Param("entityType") int entityType, @Param("status") int status);

}
