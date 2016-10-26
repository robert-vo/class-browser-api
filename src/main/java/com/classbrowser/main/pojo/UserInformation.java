package com.classbrowser.main.pojo;

import java.io.Serializable;

/**
 * Created by Robert on 10/26/16.
 */
public class UserInformation implements Serializable {

    private String firstName;
    private String lastName;
    private String theirEmailAddress;
    private String feedback;

    public UserInformation() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTheirEmailAddress() {
        return theirEmailAddress;
    }

    public void setTheirEmailAddress(String theirEmailAddress) {
        this.theirEmailAddress = theirEmailAddress;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
