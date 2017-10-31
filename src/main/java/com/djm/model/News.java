package com.djm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class News {

    private int id;

    private String title;

    private String link;

    private String image;

    private int likeCount;

    private int commentCount;

    private Date createdDate;

    private int userId;
}
