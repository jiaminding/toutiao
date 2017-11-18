package com.djm.service;

import com.djm.dao.MessageDAO;
import com.djm.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/15
 */
@Service
public class MessageService {

    @Autowired
    MessageDAO messageDAO;

    public int addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return  messageDAO.getConversationDetail(conversationId, offset, limit);
    }
}
