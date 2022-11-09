package com.sam;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    public String name;
    public int age;
    public String course;
    public String department;
    public boolean passedCaptcha;
    public String university;
    public String phoneNumber;
    public String passEncrypted;
    public ArrayList<Book> rentedBooks = new ArrayList<>();
    public ArrayList<String> inbox = new ArrayList<>();

    public User(String name, int age, String course, String department, boolean passedCaptcha, String university, String phoneNumber, String passEncrypted){
        if(passedCaptcha){
        this.name = name;
        this.age = age;
        this.course = course;
        this.department = department;
        this.passedCaptcha = passedCaptcha;
        this.university = university;
        this.phoneNumber = phoneNumber;
        this.passEncrypted = passEncrypted;
    }else{
            System.out.println("Captcha failed. Account could not be created");
        }
    }
    public void rentExternalBook(Book book){
        if(Objects.equals(book.uniOfOrigin, university)){
            rentedBooks.add(book);
        }else{
            System.out.println("you don't have permission!");
        }

    }

    public void sendToStaff(Staff staff, String message){
        staff.inbox.add(message);

    }
    public String toString(){
        StringBuilder s  = new StringBuilder();
        s.append(String.format("Name: %s %nAge: %d %nCourse: %s %nDepartment: %s %nPhone Number: %s %nUniversity: %s", name, age, course, department, phoneNumber, university));
        return s.toString();
    }
}
