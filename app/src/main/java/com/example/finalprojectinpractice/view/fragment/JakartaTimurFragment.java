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
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyJakartaTimurAdapter;
import com.example.finalprojectinpractice.model.modelJakartaTimur.ModelAgencyJakartaTimurResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyJakartaTimur;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelAgencyJakartaTimur;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JakartaTimurFragment extends Fragment {

    private AgencyJakartaTimurAdapter agencyJakartaTimurAdapter;
    private ViewModelAgencyJakartaTimur viewModelAgencyJakartaTimur;
    private RecyclerView recyclerViewJakartaTimur;
    private EditText searchEditTextJakartaTimur;


    public JakartaTimurFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jakarta_timur, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerViewJakartaTimur = view.findViewById(R.id.fragmentJakartaTimur_RecyclerView);
        recyclerViewJakartaTimur.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyJakartaTimurAdapter = new AgencyJakartaTimurAdapter(getContext());
        agencyJakartaTimurAdapter.notifyDataSetChanged();

        recyclerViewJakartaTimur.setAdapter(agencyJakartaTimurAdapter);

        viewModelAgencyJakartaTimur = new ViewModelProvider(this).get(ViewModelAgencyJakartaTimur.class);
        viewModelAgencyJakartaTimur.setAgenciesJakartaTimurModel();
        viewModelAgencyJakartaTimur.getAgenciesJakartaTimurModel().observe(this, getAgencyJakartaTimurViewModel);

        searchEditTextJakartaTimur = view.findViewById(R.id.fragmentJakartaTimur_searchBar);

        searchEditTextJakartaTimur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agencyJakartaTimurAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAgencyJakartaTimurResultItem>> getAgencyJakartaTimurViewModel = new Observer<ArrayList<ModelAgencyJakartaTimurResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyJakartaTimurResultItem> modelAgencyJakartaTimurResultItems) {
            agencyJakartaTimurAdapter.setData(modelAgencyJakartaTimurResultItems);

            agencyJakartaTimurAdapter.setListener(new AgencyJakartaTimurAdapter.ListenerJakartaTimur() {
                @Override
                public void onClick(int position) {
                    ModelAgencyJakartaTimurResultItem agencies = modelAgencyJakartaTimurResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyJakartaTimur.class);
                    intent.putExtra(DetailAgencyJakartaTimur.EXTRA_AGENCY_ID_JAKARTA_TIMUR, agencies);
                    getActivity().startActivity(intent);
                }
            });
        }
    };
}
