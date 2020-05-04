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
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyPurwakartaAdapter;
import com.example.finalprojectinpractice.model.modelKabupatenPurwakarta.ModelAgencyPurwakartaResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyPurwakarta;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelPurwakarta;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PurwakartaFragment extends Fragment {

    private AgencyPurwakartaAdapter agencyPurwakartaAdapter;
    private ViewModelPurwakarta viewModelPurwakarta;
    private RecyclerView recyclerViewPurwakarta;
    private EditText searchEditTextPurwakarta;


    public PurwakartaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_purwakarta, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewPurwakarta = view.findViewById(R.id.fragmentPurwakarta_RecyclerView);
        recyclerViewPurwakarta.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyPurwakartaAdapter = new AgencyPurwakartaAdapter(getContext());
        agencyPurwakartaAdapter.notifyDataSetChanged();

        recyclerViewPurwakarta.setAdapter(agencyPurwakartaAdapter);

        viewModelPurwakarta = new ViewModelProvider(this).get(ViewModelPurwakarta.class);
        viewModelPurwakarta.setAgenciesPurwakartaModel();
        viewModelPurwakarta.getAgenciesPurwakartaModel().observe(this, getAgencyPurwakartaViewModel);

        searchEditTextPurwakarta = view.findViewById(R.id.fragmentPurwakarta_searchBar);
        searchEditTextPurwakarta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agencyPurwakartaAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAgencyPurwakartaResultItem>> getAgencyPurwakartaViewModel = new Observer<ArrayList<ModelAgencyPurwakartaResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyPurwakartaResultItem> modelAgencyPurwakartaResultItems) {
            agencyPurwakartaAdapter.setData(modelAgencyPurwakartaResultItems);

            agencyPurwakartaAdapter.setListener(new AgencyPurwakartaAdapter.ListenerPurwakarta() {
                @Override
                public void onClick(int position) {
                    ModelAgencyPurwakartaResultItem agencies = modelAgencyPurwakartaResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyPurwakarta.class);
                    intent.putExtra(DetailAgencyPurwakarta.EXTRA_AGENCY_ID_PURWAKARTA, agencies);
                    getActivity().startActivity(intent);
                }
            });
        }
    };
}
