package com.djm.model;

import lombok.Data;

import java.util.Date;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/12
 */
@Data
public class Comment {

    private int id;

    private String content;

    private Date createdDate;

    private int userId;

    private int entityId;

    private int entityType;

    private int status;

}
