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
import com.example.finalprojectinpractice.model.modelJakartaSelatan.ModelAgencyJakartaSelatanResultItem;
import java.util.ArrayList;

public class AgencyJakartaSelatanAdapter extends RecyclerView.Adapter<AgencyJakartaSelatanAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerJakartaSelatan listener;
    private ArrayList<ModelAgencyJakartaSelatanResultItem> modelJakartaSelatanResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyJakartaSelatanResultItem> modelJakartaSelatanResultItemFiltered = new ArrayList<>();

    public AgencyJakartaSelatanAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyJakartaSelatanResultItem> items) {
        modelJakartaSelatanResultItem.clear();
        modelJakartaSelatanResultItem.addAll(items);
        notifyDataSetChanged();

        modelJakartaSelatanResultItemFiltered.clear();
        modelJakartaSelatanResultItemFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public interface ListenerJakartaSelatan {
        void onClick(int position);
    }

    public void setListener(ListenerJakartaSelatan listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyJakartaSelatanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AgencyJakartaSelatanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyJakartaSelatanAdapter.ViewHolder holder, final int position) {

        holder.tvAgencyName.setText(modelJakartaSelatanResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelJakartaSelatanResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelJakartaSelatanResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelJakartaSelatanResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelJakartaSelatanResultItemFiltered.size();
    }


    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                     modelJakartaSelatanResultItemFiltered = modelJakartaSelatanResultItem;
                } else {
                    ArrayList<ModelAgencyJakartaSelatanResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyJakartaSelatanResultItem row : modelJakartaSelatanResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelJakartaSelatanResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelJakartaSelatanResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelJakartaSelatanResultItemFiltered = (ArrayList<ModelAgencyJakartaSelatanResultItem>) results.values;
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
