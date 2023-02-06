package com.diya.dementiacare;

public class User {
    public String id;
    public String userName;
    public String email;
    public String password;
    public String contact1name;
    public String contact2name;
    public String contact1no;
    public String contact2no;

    public User(String id, String userName, String email, String password, String contact1name, String contact2name, String contact1no, String contact2no) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.contact1name = contact1name;
        this.contact2name = contact2name;
        this.contact1no = contact1no;
        this.contact2no = contact2no;
    }
}
