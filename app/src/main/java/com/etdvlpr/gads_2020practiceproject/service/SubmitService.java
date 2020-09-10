package com.etdvlpr.gads_2020practiceproject.service;

import com.etdvlpr.gads_2020practiceproject.model.Submission;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SubmitService {

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Submission> submitForm(@Body Submission submission);
}
