package com.etdvlpr.gads_2020practiceproject.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etdvlpr.gads_2020practiceproject.R;
import com.etdvlpr.gads_2020practiceproject.adapter.LeaderBoardAdapter;
import com.etdvlpr.gads_2020practiceproject.model.Learner;
import com.etdvlpr.gads_2020practiceproject.service.LearnerService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillIqFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final ProgressBar progressBar = root.findViewById(R.id.main_progress);
        final RecyclerView recyclerView = root.findViewById(R.id.main_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LearnerService service = retrofit.create(LearnerService.class);
        Call<List<Learner>> learners = service.getSkillIQLeaders();
        learners.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> call, Response<List<Learner>> response) {
                progressBar.animate().alpha(0).setDuration(100);
                LeaderBoardAdapter adapter = new LeaderBoardAdapter((ArrayList<Learner>) response.body(), "Skill IQ");
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Learner>> call, Throwable t) {

            }
        });

        return root;
    }
}
