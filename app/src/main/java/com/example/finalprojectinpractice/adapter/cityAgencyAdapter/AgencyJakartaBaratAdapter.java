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
import com.example.finalprojectinpractice.model.modelJakartaBarat.ModelAgencyJakartaBaratResultItem;

import java.util.ArrayList;

public class AgencyJakartaBaratAdapter extends RecyclerView.Adapter<AgencyJakartaBaratAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerJakartaBarat listener;
    private ArrayList<ModelAgencyJakartaBaratResultItem> modelJakartaBaratResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyJakartaBaratResultItem> modelJakartaBaratResultItemFiltered = new ArrayList<>();

    public AgencyJakartaBaratAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyJakartaBaratResultItem> items) {
        modelJakartaBaratResultItem.clear();
        modelJakartaBaratResultItem.addAll(items);
        notifyDataSetChanged();

        modelJakartaBaratResultItemFiltered.clear();
        modelJakartaBaratResultItemFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public interface ListenerJakartaBarat {
        void onClick(int position);
    }

    public void setListener(ListenerJakartaBarat listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyJakartaBaratAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AgencyJakartaBaratAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyJakartaBaratAdapter.ViewHolder holder, final int position) {
        holder.tvAgencyName.setText(modelJakartaBaratResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelJakartaBaratResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelJakartaBaratResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelJakartaBaratResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelJakartaBaratResultItemFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                    modelJakartaBaratResultItemFiltered = modelJakartaBaratResultItem;
                } else {
                    ArrayList<ModelAgencyJakartaBaratResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyJakartaBaratResultItem row : modelJakartaBaratResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelJakartaBaratResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelJakartaBaratResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelJakartaBaratResultItemFiltered = (ArrayList<ModelAgencyJakartaBaratResultItem>) results.values;
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
