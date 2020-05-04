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
import com.example.finalprojectinpractice.model.modelJakartaUtara.ModelAgencyJakartaUtaraResultItem;

import java.util.ArrayList;

public class AgencyJakartaUtaraAdapter extends RecyclerView.Adapter<AgencyJakartaUtaraAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerJakartaUtara listener;
    private ArrayList<ModelAgencyJakartaUtaraResultItem> modelJakartautaraResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyJakartaUtaraResultItem> modelJakartautaraResultItemFiltered = new ArrayList<>();

    public AgencyJakartaUtaraAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyJakartaUtaraResultItem> items) {
        modelJakartautaraResultItem.clear();
        modelJakartautaraResultItem.addAll(items);
        notifyDataSetChanged();

        modelJakartautaraResultItemFiltered.clear();
        modelJakartautaraResultItemFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public interface ListenerJakartaUtara {
        void onClick(int position);
    }

    public void setListener(ListenerJakartaUtara listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyJakartaUtaraAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AgencyJakartaUtaraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyJakartaUtaraAdapter.ViewHolder holder, final int position) {

        holder.tvAgencyName.setText(modelJakartautaraResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelJakartautaraResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelJakartautaraResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelJakartautaraResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelJakartautaraResultItemFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                    modelJakartautaraResultItemFiltered = modelJakartautaraResultItem;
                } else {
                    ArrayList<ModelAgencyJakartaUtaraResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyJakartaUtaraResultItem row : modelJakartautaraResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelJakartautaraResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelJakartautaraResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelJakartautaraResultItemFiltered = (ArrayList<ModelAgencyJakartaUtaraResultItem>) results.values;
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
