package com.example.finalprojectinpractice.adapter.typeAgencyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalprojectinpractice.R;
import com.example.finalprojectinpractice.model.modelAllAgencies.ModelAllAgenciesResultItem;
import java.util.ArrayList;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerHospital listener;
    private ArrayList<ModelAllAgenciesResultItem> modelAllAgencies = new ArrayList<>();
    private  ArrayList<ModelAllAgenciesResultItem> modelHospital = new ArrayList<>();
    public static ArrayList<ModelAllAgenciesResultItem> modelHospitalFiltered = new ArrayList<>();

    public HospitalAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAllAgenciesResultItem> items) {
        modelAllAgencies.clear();
        modelAllAgencies.addAll(items);
        notifyDataSetChanged();

        setDataHospital();
        modelHospitalFiltered = modelHospital;
    }

    private void setDataHospital() {
        String KeyType = "Rumkit";
        ArrayList<ModelAllAgenciesResultItem> listHospital = new ArrayList<>();
        for (ModelAllAgenciesResultItem row : modelAllAgencies) {
            if (row.getJenisInstansi().equals(KeyType)) {
                listHospital.add(row);
            }
        }
        modelHospital = listHospital;
    }

    private ArrayList<ModelAllAgenciesResultItem> getDataHospital() {
        return modelHospital;
    }

    public interface ListenerHospital {
        void onClick(int position);
    }

    public void setListener(ListenerHospital listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, final int position) {
        holder.tvAgencyName.setText(modelHospitalFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelHospitalFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelHospitalFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelHospitalFiltered.get(position).getNamaKabupaten());

        holder.cardViewListContainer.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_scale_animation));
        holder.tvHolderBrownAccent.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

        holder.cardViewListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelHospitalFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                modelHospitalFiltered=modelHospital;
                if(Key.isEmpty()) {
                    modelHospitalFiltered = modelHospital;
                } else {
                    ArrayList<ModelAllAgenciesResultItem> listFiltered = new ArrayList<>();
                    for (ModelAllAgenciesResultItem row : modelHospital) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelHospitalFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelHospitalFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelHospitalFiltered = (ArrayList<ModelAllAgenciesResultItem>) results.values;
                notifyDataSetChanged();

            }
        };

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAgencyName, tvAgencyPhoneNumber, tvAgencyAddress, tvAgencyCity, tvAgencyType;
        private TextView tvHolderBrownAccent;
        private CardView cardViewListContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAgencyName = itemView.findViewById(R.id.itemlist_AgencyName);
            tvAgencyPhoneNumber = itemView.findViewById(R.id.itemlist_AgencyPhoneNumber);
            tvAgencyAddress = itemView.findViewById(R.id.itemlist_AgencyAddress);
            tvAgencyCity = itemView.findViewById(R.id.itemlist_AgencyCity);

            tvHolderBrownAccent = itemView.findViewById(R.id.holder_brownAccent);
            cardViewListContainer = itemView.findViewById(R.id.itemlist_cardView);
        }
    }
}
