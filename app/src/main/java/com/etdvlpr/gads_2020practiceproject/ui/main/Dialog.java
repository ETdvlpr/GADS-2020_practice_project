package com.etdvlpr.gads_2020practiceproject.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.etdvlpr.gads_2020practiceproject.R;
import com.etdvlpr.gads_2020practiceproject.model.Submission;
import com.etdvlpr.gads_2020practiceproject.service.SubmitService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dialog extends AppCompatDialogFragment{
    private Submission submission;

    public Dialog(String fname, String lname, String email, String plink) {
        submission = new Submission(fname,lname,email,plink);
    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_confirm, null);
        Button submit = view.findViewById(R.id.confirm_yes_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://docs.google.com/forms/d/e/").addConverterFactory(GsonConverterFactory.create()).build();
                SubmitService service = retrofit.create(SubmitService.class);
                Call<Submission> call = service.submitForm(submission);
                call.enqueue(new Callback<Submission>() {
                    @Override
                    public void onResponse(Call<Submission> call, Response<Submission> response) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            new AlertDialog.Builder(getActivity()).setView(R.layout.dialog_success).show();
                        } else {
                            View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_success, null);
                            new AlertDialog.Builder(getActivity()).setView(view).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Submission> call, Throwable t) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            new AlertDialog.Builder(getActivity()).setView(R.layout.dialog_error).show();
                        } else {
                            View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_error, null);
                            new AlertDialog.Builder(getActivity()).setView(view).show();
                        }
                    }
                });
            }
        });
        FloatingActionButton close_fab = view.findViewById(R.id.confirm_close_fab);
        close_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { Dialog.this.getDialog().cancel();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
