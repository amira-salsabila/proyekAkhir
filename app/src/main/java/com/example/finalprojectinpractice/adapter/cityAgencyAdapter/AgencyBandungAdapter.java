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
import com.example.finalprojectinpractice.model.modelKotaBandung.ModelAgencyBandungResultItem;
import java.util.ArrayList;

public class AgencyBandungAdapter extends RecyclerView.Adapter<AgencyBandungAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerBandung listener;
    private ArrayList<ModelAgencyBandungResultItem> modelBandungResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyBandungResultItem> modelBandungResultItemFiltered = new ArrayList<>();

    public AgencyBandungAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyBandungResultItem> items) {
        modelBandungResultItem.clear();
        modelBandungResultItem.addAll(items);
        notifyDataSetChanged();

        modelBandungResultItemFiltered.addAll(items);
    }

    public interface ListenerBandung {
        void onClick(int position);
    }

    public void setListener(ListenerBandung listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyBandungAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyBandungAdapter.ViewHolder holder, final int position) {
        holder.tvAgencyName.setText(modelBandungResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelBandungResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelBandungResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelBandungResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelBandungResultItemFiltered.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                    modelBandungResultItemFiltered = modelBandungResultItem;
                } else {
                    ArrayList<ModelAgencyBandungResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyBandungResultItem row : modelBandungResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelBandungResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelBandungResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelBandungResultItemFiltered = (ArrayList<ModelAgencyBandungResultItem>) results.values;
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
