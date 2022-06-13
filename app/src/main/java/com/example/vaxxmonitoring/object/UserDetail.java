package com.example.vaxxmonitoring.object;

public class UserDetail {

    private String uid;
    private String name;
    private String email;
    private String password;
    private String cpassword ;

    private String first_name;
    private String last_name;

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getCpassword() { return cpassword; }

    public void setCpassword(String cpassword) { this.cpassword = cpassword; }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public void setName(String name) {
        String[] nameArray = name.split(" ");
        first_name = nameArray[0];
        last_name = nameArray[1];
    }


    public void setfName(String fName)  {
        this.first_name = first_name;
    }

    public void setlName(String lName) {
        this.last_name = last_name;
    }
}
