package com.example.finalprojectinpractice.adapter.cityAgencyAdapter;

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
import com.example.finalprojectinpractice.model.modelKabupatenPurwakarta.ModelAgencyPurwakartaResultItem;

import java.util.ArrayList;

public class AgencyPurwakartaAdapter extends RecyclerView.Adapter<AgencyPurwakartaAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerPurwakarta listener;
    private ArrayList<ModelAgencyPurwakartaResultItem> modelPurwakartaResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyPurwakartaResultItem> modelPurwakartaResultItemFiltered = new ArrayList<>();

    public AgencyPurwakartaAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyPurwakartaResultItem> items) {
        modelPurwakartaResultItem.clear();
        modelPurwakartaResultItem.addAll(items);
        notifyDataSetChanged();

        modelPurwakartaResultItemFiltered.clear();
        modelPurwakartaResultItemFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public interface ListenerPurwakarta {
        void onClick(int position);
    }

    public void setListener(ListenerPurwakarta listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyPurwakartaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AgencyPurwakartaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyPurwakartaAdapter.ViewHolder holder, final int position) {

        holder.tvAgencyName.setText(modelPurwakartaResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelPurwakartaResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelPurwakartaResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelPurwakartaResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelPurwakartaResultItemFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                    modelPurwakartaResultItemFiltered = modelPurwakartaResultItem;
                } else {
                    ArrayList<ModelAgencyPurwakartaResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyPurwakartaResultItem row : modelPurwakartaResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelPurwakartaResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelPurwakartaResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelPurwakartaResultItemFiltered = (ArrayList<ModelAgencyPurwakartaResultItem>) results.values;
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
