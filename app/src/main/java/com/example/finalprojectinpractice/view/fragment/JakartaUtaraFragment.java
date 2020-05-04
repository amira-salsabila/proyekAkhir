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
import com.example.finalprojectinpractice.adapter.cityAgencyAdapter.AgencyJakartaUtaraAdapter;
import com.example.finalprojectinpractice.model.modelJakartaUtara.ModelAgencyJakartaUtaraResultItem;
import com.example.finalprojectinpractice.view.activity.detail.detailCity.DetailAgencyJakartaUtara;
import com.example.finalprojectinpractice.view.viewmodel.ViewModelJakartaUtara;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class JakartaUtaraFragment extends Fragment {

    private AgencyJakartaUtaraAdapter agencyJakartaUtaraAdapter;
    private ViewModelJakartaUtara viewModelJakartaUtara;
    private RecyclerView recyclerViewJakartaUtara;
    private EditText searchEditTextJakartaUtara;


    public JakartaUtaraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jakarta_utara, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewJakartaUtara = view.findViewById(R.id.fragmentJakartaUtara_RecyclerView);
        recyclerViewJakartaUtara.setLayoutManager(new LinearLayoutManager(getContext()));

        agencyJakartaUtaraAdapter = new AgencyJakartaUtaraAdapter(getContext());
        agencyJakartaUtaraAdapter.notifyDataSetChanged();

        recyclerViewJakartaUtara.setAdapter(agencyJakartaUtaraAdapter);

        viewModelJakartaUtara = new ViewModelProvider(this).get(ViewModelJakartaUtara.class);
        viewModelJakartaUtara.setAgenciesJakartaUtaraModel();
        viewModelJakartaUtara.getAgenciesJakartaUtaraModel().observe(this, getViewModelJakartaUtara);

        searchEditTextJakartaUtara = view.findViewById(R.id.fragmentJakartaUtara_searchBar);

        searchEditTextJakartaUtara.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                agencyJakartaUtaraAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private Observer<ArrayList<ModelAgencyJakartaUtaraResultItem>> getViewModelJakartaUtara = new Observer<ArrayList<ModelAgencyJakartaUtaraResultItem>>() {
        @Override
        public void onChanged(final ArrayList<ModelAgencyJakartaUtaraResultItem> modelAgencyJakartaUtaraResultItems) {
            agencyJakartaUtaraAdapter.setData(modelAgencyJakartaUtaraResultItems);

            agencyJakartaUtaraAdapter.setListener(new AgencyJakartaUtaraAdapter.ListenerJakartaUtara() {
                @Override
                public void onClick(int position) {
                    ModelAgencyJakartaUtaraResultItem agencies = modelAgencyJakartaUtaraResultItems.get(position);
                    Intent intent = new Intent(getActivity(), DetailAgencyJakartaUtara.class);
                    intent.putExtra(DetailAgencyJakartaUtara.EXTRA_AGENCY_ID_JAKARTA_UTARA, agencies);
                    getActivity().startActivity(intent);
                }
            });
        }
    };
}
