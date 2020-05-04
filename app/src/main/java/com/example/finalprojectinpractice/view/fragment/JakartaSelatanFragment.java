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
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyJakartaSelatanAdapter;
import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyJakartaSelatan;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAgencyJakartaSelatan;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JakartaSelatanFragment extends Fragment {

    private AgencyJakartaSelatanAdapter agencyJakartaSelatanAdapter;
    private ViewModelAgencyJakartaSelatan viewModelAgencyJakartaSelatan;
    private RecyclerView recyclerViewJakartaSelatan;
    private EditText searchEditTextJakartaSelatan;


    public JakartaSelatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jakarta_selatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewJakartaSelatan = view.findViewById(R.id.fragmentJakartaSelatan_RecyclerView);
        recyclerViewJakartaSelatan.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyJakartaSelatanAdapter = new AgencyJakartaSelatanAdapter(getContext());
        agencyJakartaSelatanAdapter.notifyDataSetChanged();

        recyclerViewJakartaSelatan.setAdapter(agencyJakartaSelatanAdapter);

        viewModelAgencyJakartaSelatan = new ViewModelProvider(this).get(ViewModelAgencyJakartaSelatan.class);
        viewModelAgencyJakartaSelatan.setAgenciesJakartaSelatanModel();
        viewModelAgencyJakartaSelatan.getAgenciesJakartaSelatanModel().observe(this, getAgencyJakartaSelatanViewModel);

        searchEditTextJakartaSelatan = view.findViewById(R.id.fragmentJakartaSelatan_searchBar);

        searchEditTextJakartaSelatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agencyJakartaSelatanAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAgencyJakartaSelatanResultItem>> getAgencyJakartaSelatanViewModel = new Observer<ArrayList<ModelAgencyJakartaSelatanResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyJakartaSelatanResultItem> modelAgencyJakartaSelatanResultItems) {
            agencyJakartaSelatanAdapter.setData(modelAgencyJakartaSelatanResultItems);

            agencyJakartaSelatanAdapter.setListener(new AgencyJakartaSelatanAdapter.ListenerJakartaSelatan() {
                @Override
                public void onClick(int position) {
                    ModelAgencyJakartaSelatanResultItem agencies = modelAgencyJakartaSelatanResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyJakartaSelatan.class);
                    intent.putExtra(DetailAgencyJakartaSelatan.EXTRA_AGENCY_ID_JAKARTA_SELATAN, agencies);
                    getActivity().startActivity(intent);
                }
            });
        }
    };
}
