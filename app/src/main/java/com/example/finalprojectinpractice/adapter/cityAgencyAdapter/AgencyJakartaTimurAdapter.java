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
import com.example.finalprojectinpractice.model.modelJakartaTimur.ModelAgencyJakartaTimurResultItem;

import java.util.ArrayList;

public class AgencyJakartaTimurAdapter extends RecyclerView.Adapter<AgencyJakartaTimurAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ListenerJakartaTimur listener;
    private ArrayList<ModelAgencyJakartaTimurResultItem> modelJakartaTimurResultItem = new ArrayList<>();
    private ArrayList<ModelAgencyJakartaTimurResultItem> modelJakartaTimurResultItemFiltered = new ArrayList<>();

    public AgencyJakartaTimurAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<ModelAgencyJakartaTimurResultItem> items) {
        modelJakartaTimurResultItem.clear();
        modelJakartaTimurResultItem.addAll(items);
        notifyDataSetChanged();

        modelJakartaTimurResultItemFiltered.clear();
        modelJakartaTimurResultItemFiltered.addAll(items);
        notifyDataSetChanged();
    }

    public interface ListenerJakartaTimur {
        void onClick(int position);
    }

    public void setListener(ListenerJakartaTimur listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public AgencyJakartaTimurAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new AgencyJakartaTimurAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgencyJakartaTimurAdapter.ViewHolder holder, final int position) {
        holder.tvAgencyName.setText(modelJakartaTimurResultItemFiltered.get(position).getNamaInstansi());
        holder.tvAgencyPhoneNumber.setText(modelJakartaTimurResultItemFiltered.get(position).getNomorInstansi());
        holder.tvAgencyAddress.setText(modelJakartaTimurResultItemFiltered.get(position).getAlamatInstansi());
        holder.tvAgencyCity.setText(modelJakartaTimurResultItemFiltered.get(position).getNamaKabupaten());

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
        return modelJakartaTimurResultItem.size();
    }

    @Override
    public Filter getFilter() {


        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();

                if(Key.isEmpty()) {
                    modelJakartaTimurResultItemFiltered = modelJakartaTimurResultItem;
                } else {
                    ArrayList<ModelAgencyJakartaTimurResultItem> listFiltered = new ArrayList<>();
                    for (ModelAgencyJakartaTimurResultItem row : modelJakartaTimurResultItem) {
                        if (row.getNamaInstansi().toLowerCase().contains(Key.toLowerCase())) {
                            listFiltered.add(row);
                        }
                    }
                    modelJakartaTimurResultItemFiltered = listFiltered;
                }

                FilterResults results = new FilterResults();
                results.values = modelJakartaTimurResultItemFiltered;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                modelJakartaTimurResultItemFiltered = (ArrayList<ModelAgencyJakartaTimurResultItem>) results.values;
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
