package com.djm.model;

import org.springframework.stereotype.Component;

/**
 * @author dingjiamin
 * @workcode wb-djm332505
 * @date 2017/11/3
 */
@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUsers(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
