package com.djm.model;

import lombok.Data;

import java.util.Date;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/14
 */
@Data
public class Message {

    private int id;

    private int fromId;

    private int toId;

    private String content;

    private Date createdDate;

    private int hasRead;

    private String conversationId;

}
