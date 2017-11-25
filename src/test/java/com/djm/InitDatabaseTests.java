package com.djm;

import com.djm.dao.*;
import com.djm.model.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.security.krb5.internal.Ticket;

import javax.swing.text.html.parser.Entity;
import java.util.Date;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ToutiaoApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;
    @Autowired
    NewsDAO newsDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    CommentDAO commentDAO;
    @Autowired
    MessageDAO messageDAO;


    @Test
    public void testSelect() {
        User user = userDAO.selectByName("x");
        Assert.assertNotNull(user);
    }
    @Test
    public void contextLoads() {
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

            user.setPassword("newpassword");
            userDAO.updatePassword(user);

            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            news.setCreatedDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i + 1);
            news.setUserId(i + 1);
            news.setTitle(String.format("题目{%d}",i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html",i));
            newsDAO.addNews(news);

            for (int j = 0; j < 3; j++) {
                Comment comment = new Comment();
                comment.setContent("comment" + String.valueOf(j));
                comment.setCreatedDate(new Date());
                comment.setEntityId(news.getId());
                comment.setEntityType(EntityType.ENTITY_NEWS);
                comment.setUserId(i + 1);
                comment.setStatus(0);
                commentDAO.addComment(comment);
            }

            LoginTicket ticket = new LoginTicket();
            ticket.setStatus(0);
            ticket.setExpired(date);
            ticket.setUserId(i + 1);
            ticket.setTicket(String.format("TICKET%d", i + 1));
            loginTicketDAO.addTicket(ticket);
            loginTicketDAO.updateStatus(2, ticket.getTicket());
        }



        Assert.assertEquals("newpassword", userDAO.selectById(1).getPassword());
        userDAO.deleteById(1);
        Assert.assertNull(userDAO.selectById(1));
        Assert.assertEquals(loginTicketDAO.selectByTicket("TICKET1").getUserId(), 1);
        Assert.assertEquals(loginTicketDAO.selectByTicket("TICKET1").getStatus(), 2);
    }

}
