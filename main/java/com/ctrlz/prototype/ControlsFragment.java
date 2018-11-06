package com.ctrlz.prototype;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

public class ControlsFragment extends Fragment {

    Button forwardBtn, rightBtn, leftBtn, backwardBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_controls, container, false);

         forwardBtn = (Button) v.findViewById(R.id.forwardBtn);
         rightBtn = (Button) v.findViewById(R.id.rightBtn);
         leftBtn = (Button) v.findViewById(R.id.leftBtn);
         backwardBtn = (Button) v.findViewById(R.id.backwardBtn);

         forwardBtn.setOnClickListener(clk);
         rightBtn.setOnClickListener(clk);
         leftBtn.setOnClickListener(clk);
         backwardBtn.setOnClickListener(clk);


        return v;
    }
    public View.OnClickListener clk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String words = "";
            switch (v.getId()) {
                case R.id.forwardBtn:
                    words = "Forward Button has been pressed!!!";
                    break;

                case R.id.rightBtn:
                    words = "Right Button has been pressed!!!";
                    break;

                case R.id.leftBtn:
                    words = "Left Button has been pressed!!!";
                    break;

                case R.id.backwardBtn:
                    words = "Back Button has been pressed!!!";
                    break;
            }

            Toast toast = Toast.makeText(v.getContext(), words, Toast.LENGTH_SHORT);
            toast.show();
        }

    };


    }




