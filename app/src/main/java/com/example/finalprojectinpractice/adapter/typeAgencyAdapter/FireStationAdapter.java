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

public class FireStationAdapter extends RecyclerView.Adapter<FireStationAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerFireStation listener;
    private ArrayList<ModelAllAgenciesResultItem> modelAllAgencies = new ArrayList<>();
    private  ArrayList<ModelAllAgenciesResultItem> modelFireStation = new ArrayList<>();
    public static ArrayList<ModelAllAgenciesResultItem> modelFireStationFiltered = new ArrayList<>();

    public FireStationAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAllAgenciesResultItem> items) {
        modelAllAgencies.clear();
        modelAllAgencies.addAll(items);
        notifyDataSetChanged();

        setDataFireFighters();
        modelFireStationFiltered = modelFireStation;
    }

    private void setDataFireFighters() {
        String KeyType = "Pemadam";
        ArrayList<ModelAllAgenciesResultItem> listFire = new ArrayList<>();
        for (ModelAllAgenciesResultItem row : modelAllAgencies) {
            if (row.getJenisInstansi().equals(KeyType)) {
                listFire.add(row);
            }
        }
        modelFireStation = listFire;
    }

    private ArrayList<ModelAllAgenciesResultItem> getDataFireStation() {
        return modelFireStation;
    }

    public interface ListenerFireStation {
        void onClick(int position);
    }

    public void setListener (ListenerFireStation listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FireStationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FireStationAdapter.ViewHolder holder, final int position) {
        holder.tvAgencyName.setText(modelFireStationFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelFireStationFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelFireStationFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelFireStationFiltered.get(position).getNamaKabupaten());

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
        return modelFireStationFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                modelFireStationFiltered=modelFireStation;
                if(Key.isEmpty()) {
                    modelFireStationFiltered = modelFireStation;
                } else {
                    ArrayList<ModelAllAgenciesResultItem> listFiltered = new ArrayList<>();
                    for (ModelAllAgenciesResultItem row : modelFireStation) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelFireStationFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelFireStationFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelFireStationFiltered = (ArrayList<ModelAllAgenciesResultItem>) results.values;
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
