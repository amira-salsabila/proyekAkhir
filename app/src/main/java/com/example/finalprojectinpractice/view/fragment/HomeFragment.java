package com.example.finalprojectinpractice.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.view.activity.typeAgencyActivity.FireStationAgencyActivity;
import com.example.finalprojectinpractice.view.activity.typeAgencyActivity.HospitalAgencyActivity;
import com.example.finalprojectinpractice.view.activity.typeAgencyActivity.PoliceStationAgencyActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    TextView containerTop;
    CardView containerLeft, containerRight, containerBottom;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        containerTop = view.findViewById(R.id.fragmentHome_headerTop);
        containerLeft = view.findViewById(R.id.fragmentHome_cardViewLeft);
        containerRight = view.findViewById(R.id.fragmentHome_cardViewRight);
        containerBottom = view.findViewById(R.id.fragmentHome_cardViewBottom);

        Animation animBottomTop = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_home_bottom_to_top);
        Animation animTopBottom = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_home_top_to_bottom);
        Animation animLeftRight = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_home_left_to_right);
        Animation animRightLeft = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_home_right_to_left);

        containerBottom.setAnimation(animBottomTop);
        containerTop.setAnimation(animTopBottom);
        containerLeft.setAnimation(animLeftRight);
        containerRight.setAnimation(animRightLeft);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        containerLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HospitalAgencyActivity.class);
                startActivity(intent);

            }
        });

        containerRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FireStationAgencyActivity.class);
                startActivity(intent);

            }
        });

        containerBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PoliceStationAgencyActivity.class);
                startActivity(intent);

            }
        });

    }
}
