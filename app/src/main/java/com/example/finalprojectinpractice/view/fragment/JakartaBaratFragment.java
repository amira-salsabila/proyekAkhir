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
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyJakartaBaratAdapter;
import com.example.finalprojectinpractice.model.modelJakartaBarat.ModelAgencyJakartaBaratResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyJakartaBarat;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAgencyJakartaBarat;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JakartaBaratFragment extends Fragment {

    private AgencyJakartaBaratAdapter agencyJakartaBaratAdapter;
    private ViewModelAgencyJakartaBarat viewModelAgencyJakartaBarat;
    private RecyclerView recyclerViewJakartaBarat;
    private EditText searchEditTextJakartaBarat;


    public JakartaBaratFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jakarta_barat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewJakartaBarat = view.findViewById(R.id.fragmentJakartaBarat_RecyclerView);
        recyclerViewJakartaBarat.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyJakartaBaratAdapter = new AgencyJakartaBaratAdapter(getContext());
        agencyJakartaBaratAdapter.notifyDataSetChanged();

        recyclerViewJakartaBarat.setAdapter(agencyJakartaBaratAdapter);

        viewModelAgencyJakartaBarat = new ViewModelProvider(this).get(ViewModelAgencyJakartaBarat.class);
        viewModelAgencyJakartaBarat.setAgenciesJakartaBaratModel();
        viewModelAgencyJakartaBarat.getAgenciesModelJakartaBarat().observe(this, getAgencyJakartaBaratViewModel);

        searchEditTextJakartaBarat = view.findViewById(R.id.fragmentJakartaBarat_searchBar);

        searchEditTextJakartaBarat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agencyJakartaBaratAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAgencyJakartaBaratResultItem>> getAgencyJakartaBaratViewModel = new Observer<ArrayList<ModelAgencyJakartaBaratResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyJakartaBaratResultItem> modelAgencyJakartaBaratResultItems) {
            agencyJakartaBaratAdapter.setData(modelAgencyJakartaBaratResultItems);

            agencyJakartaBaratAdapter.setListener(new AgencyJakartaBaratAdapter.ListenerJakartaBarat() {
                @Override
                public void onClick(int position) {
                    ModelAgencyJakartaBaratResultItem agencies = modelAgencyJakartaBaratResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyJakartaBarat.class);
                    intent.putExtra(DetailAgencyJakartaBarat.EXTRA_AGENCY_ID_JAKARTA_BARAT, agencies);
                    getActivity().startActivity(intent);
                }
            });

        }
    };
}
