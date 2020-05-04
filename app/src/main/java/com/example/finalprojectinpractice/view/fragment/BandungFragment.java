package com.example.finalprojectinpractice.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyBandungAdapter;
import com.example.finalprojectinpractice.model.modelKotaBandung.ModelAgencyBandungResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyBandung;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAgencyBandung;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BandungFragment extends Fragment {

    private AgencyBandungAdapter agencyBandungAdapter;
    private ViewModelAgencyBandung viewModelAgencyBandung;
    private RecyclerView recyclerViewBandungFragment;
    private EditText searchEditText;

    public BandungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bandung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewBandungFragment = view.findViewById(R.id.fragmentBandung_RecyclerView);
        recyclerViewBandungFragment.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyBandungAdapter = new AgencyBandungAdapter(getContext());
        agencyBandungAdapter.notifyDataSetChanged();

        recyclerViewBandungFragment.setAdapter(agencyBandungAdapter);

        viewModelAgencyBandung = new ViewModelProvider(this).get(ViewModelAgencyBandung.class);
        viewModelAgencyBandung.setAgenciesBandungModel();
        viewModelAgencyBandung.getAgenciesBandungModel().observe(this, getAgencyBandungViewModel);

        searchEditText = view.findViewById(R.id.fragmentBandung_searchBar);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                agencyBandungAdapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private Observer<ArrayList<ModelAgencyBandungResultItem>> getAgencyBandungViewModel = new Observer<ArrayList<ModelAgencyBandungResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyBandungResultItem> modelAgencyBandungResultItems) {
            agencyBandungAdapter.setData(modelAgencyBandungResultItems);

            agencyBandungAdapter.setListener(new AgencyBandungAdapter.ListenerBandung() {
                @Override
                public void onClick(int position) {
                    ModelAgencyBandungResultItem agencies = modelAgencyBandungResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyBandung.class);
                    intent.putExtra(DetailAgencyBandung.EXTRA_AGENCY_ID, agencies);
                    getActivity().startActivity(intent);
                }
            });
        };
    };
}
