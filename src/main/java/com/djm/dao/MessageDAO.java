package com.djm.dao;

import com.djm.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/14
 */
@Mapper
public interface MessageDAO {

    String TABLE_NAME =" message ";

    String INSERT_FIELDS =" from_id, to_id, content, created_date, has_read, conversation_id ";

    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS, ")values(#{fromId}, #{toId}, #{content}, #{createdDate}, #{hasRead}, #{conversationId})"})
    int addMessage(Message message);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAME, "where conversation_id=#{conversationId} order by id desc limit #{offset}, #{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    @Select({"select count(id) from", TABLE_NAME, "where to_id=#{userId} and has_read=0 and conversation_id=#{conversationId}"})
    int getConversationUnreadCount(@Param("userId") int userId, @Param("conversationId") String conversationId);

    @Select({"select", INSERT_FIELDS, "count(id) as id from (select", SELECT_FIELDS, "from", TABLE_NAME, "where from_id=#{userId} or to_id=#{userId} order by id desc) as tt group by conversation_id order created_date desc limit #{offset}, #{limit}" })
    List<Message> getConversationList(@Param("userId") int userId,
                                      @Param("offset") int offset,
                                      @Param("limit") int limit);
}
