package com.etdvlpr.gads_2020practiceproject;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.etdvlpr.gads_2020practiceproject.ui.main.Dialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class SubmitActivity extends AppCompatActivity{
    private TextInputEditText fName, lName, eMail, pLink;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        fName = findViewById(R.id.text_input_fname);
        lName = findViewById(R.id.text_input_lname);
        eMail = findViewById(R.id.text_input_email);
        pLink = findViewById(R.id.text_input_p_link);
        Toolbar toolbar = findViewById(R.id.submit_activity_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        MaterialButton submit = findViewById(R.id.submit_activity_submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(fName.getText())){
                    fName.setError("First name is required.");
                } else if (TextUtils.isEmpty(lName.getText())){
                    lName.setError("Last Name is required.");
                } else if (TextUtils.isEmpty(eMail.getText())){
                    eMail.setError("an Email address is required.");
                } else if (TextUtils.isEmpty(pLink.getText())){
                    pLink.setError("The project link is required.");
                } else {
                    Dialog submitDialog = new Dialog(fName.getText().toString(), lName.getText().toString(), eMail.getText().toString(), pLink.getText().toString());
                    submitDialog.show(getSupportFragmentManager(),"submit?");
                }
            }
        });
    }
}
