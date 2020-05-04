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

public class PoliceStationAdapter extends RecyclerView.Adapter<PoliceStationAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerPoliceStation listener;
    private ArrayList<ModelAllAgenciesResultItem> modelAllAgencies = new ArrayList<>();
    private ArrayList<ModelAllAgenciesResultItem> modelPoliceStation = new ArrayList<>();
    public static ArrayList<ModelAllAgenciesResultItem> modelPoliceStationFiltered = new ArrayList<>();

    public PoliceStationAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAllAgenciesResultItem> items) {
        modelAllAgencies.clear();
        modelAllAgencies.addAll(items);
        notifyDataSetChanged();

        setDataPoliceStation();
        modelPoliceStationFiltered = modelPoliceStation;
    }

    private void setDataPoliceStation() {
        String KeyType = "Polisi";
        ArrayList<ModelAllAgenciesResultItem> listPolice = new ArrayList<>();
        for (ModelAllAgenciesResultItem row : modelAllAgencies) {
            if (row.getJenisInstansi().contains(KeyType)) {
                listPolice.add(row);
            }
        }
        modelPoliceStation = listPolice;
    }

    public interface ListenerPoliceStation {
        void onClick(int position);
    }

    public void setListener(ListenerPoliceStation listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public PoliceStationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PoliceStationAdapter.ViewHolder holder, final int position) {

        holder.tvAgencyName.setText(modelPoliceStationFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelPoliceStationFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelPoliceStationFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelPoliceStationFiltered.get(position).getNamaKabupaten());

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
        return modelPoliceStationFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                modelPoliceStationFiltered=modelPoliceStation;
                if(Key.isEmpty()) {
                    modelPoliceStationFiltered = modelPoliceStation;
                } else {
                    ArrayList<ModelAllAgenciesResultItem> listFiltered = new ArrayList<>();
                    for (ModelAllAgenciesResultItem row : modelPoliceStation) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelPoliceStationFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelPoliceStationFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelPoliceStationFiltered = (ArrayList<ModelAllAgenciesResultItem>) results.values;
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
