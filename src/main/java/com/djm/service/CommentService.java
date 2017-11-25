package com.djm.service;

import com.djm.dao.CommentDAO;
import com.djm.model.Comment;
import com.djm.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/12
 */
@Service
public class CommentService {

    @Autowired
    CommentDAO commentDAO;

    public List<Comment> getCommentsByEntity(int entityType, int entityId) {
         return commentDAO.getComments(entityType, entityId);
    }

    public int addComment(Comment comment) {
        return commentDAO.addComment(comment);
    }

    public int getCommentCount(int entityType, int entityId) {
        return commentDAO.getCommentCount(entityType, entityId);
    }

    public void deleteComment(int entityId, int entityType) {
        commentDAO.updateStatus(entityId, entityType, 1);
    }

}
