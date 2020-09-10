package com.etdvlpr.gads_2020practiceproject.service;

import com.etdvlpr.gads_2020practiceproject.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LearnerService {
    @GET("/api/hours")
    Call<List<Learner>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<Learner>> getSkillIQLeaders();
}
