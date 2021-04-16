package com.example.sd6501_assignment1_2192400;

//UserInfo class to store and update user inputted User Information.
public class UserInfo {

    //Define UserInfo variables
    private String Username;
    private String userPassword;

    //Construct variables
    UserInfo(String usernameInfo, String userPasswordInfo){
        this.Username = usernameInfo;
        this.userPassword = userPasswordInfo;
    }

    // Getters and Setters for Username and Password variables
    public String getUsername() {
        return Username;
    }

    public void setUsername(String usernameInfo) {
        Username = usernameInfo;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPasswordInfo) {
        userPassword = userPasswordInfo;
    }
}