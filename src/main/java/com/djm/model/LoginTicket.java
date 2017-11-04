package com.djm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.zip.DataFormatException;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/10/31
 */
@Setter
@Getter
public class LoginTicket {

    private int id;

    private int userId;

    private Date expired;

    private int status;

    private String ticket;

}
