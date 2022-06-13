package com.example.vaxxmonitoring;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SettingsFragment extends Fragment implements View.OnClickListener{
     FirebaseAuth mAuth;

     Button mLogout;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.settings_fragment, container, false);

        mLogout = (Button) view.findViewById(R.id.buttonLogout);
        mLogout.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        return view;


    }

    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        mAuth.signOut();
        startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));

    };





}
