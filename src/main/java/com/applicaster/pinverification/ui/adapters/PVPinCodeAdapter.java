package com.applicaster.pinverification.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applicaster.pinverification.R;
import com.applicaster.util.OSUtil;


public class PVPinCodeAdapter extends RecyclerView.Adapter<PVPinCodeAdapter.ViewHolder> {

    private int numberOfCells = 0;
    private Context context;

    public PVPinCodeAdapter(int numberOfCells, Context context) {

        this.numberOfCells = numberOfCells;
        this.context = context;

    }


    @NonNull
    @Override
    public PVPinCodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        String layoutName = "single_pincode_item";

        return new ViewHolder(OSUtil.getLayoutInflater(context).inflate(OSUtil.getLayoutResourceIdentifier(layoutName), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PVPinCodeAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return numberOfCells;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView pinTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pinTV = itemView.findViewById(R.id.single_pin_EDT);

        }


    }
}
