package com.example.idealhome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

        ImageView img_member;
        Context context;
        ProfileListener profileListener;
     public ProfileFragment(){

        }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        profileListener = (ProfileListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vh = inflater.inflate(R.layout.fragment_profile,container,false);

        img_member = vh.findViewById(R.id.img_member);
        img_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileListener.onProfilemethod();
            }
        });
        return vh;

    }
    public interface  ProfileListener{
        void onProfilemethod();
    }
}
