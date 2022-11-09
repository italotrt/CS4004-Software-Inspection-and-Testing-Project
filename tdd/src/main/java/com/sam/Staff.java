package com.sam;

import java.util.ArrayList;

public class Staff {
    public String name;
    public String course;

    public ArrayList<String> inbox = new ArrayList<>();
    public Staff(String name, String course){
        this.name = name;
        this.course = course;
    }
    public void sendToUser(User user, String message){
        user.inbox.add(message);
    }
}
