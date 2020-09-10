package com.etdvlpr.gads_2020practiceproject.model;

import com.google.gson.annotations.SerializedName;

public class Submission {
    public Submission(String firstName, String lastName, String email, String projectLink) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.projectLink = projectLink;
    }

    @SerializedName("entry.1877115667")
    String firstName;

    @SerializedName("entry.2006916086")
    String lastName;

    @SerializedName("entry.1824927963")
    String email;

    @SerializedName("entry.284483984")
    String projectLink;
}
