package com.applicaster.pinverification.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.applicaster.pinverification.R;
import com.applicaster.pinverification.interfaces.OnPincodeReady;
import com.applicaster.util.OSUtil;


public class PVPinCodeAdapter extends RecyclerView.Adapter<PVPinCodeAdapter.ViewHolder> {

    private int numberOfCells;
    private Context context;
    private RecyclerView recyclerView;
    private static String pinCode = "";
    private OnPincodeReady pinCodeListener;

    public PVPinCodeAdapter(int numberOfCells, Context context, RecyclerView view, OnPincodeReady listener) {

        if (numberOfCells == 0) {
            this.numberOfCells = 4; //Default value
        } else {
            this.numberOfCells = numberOfCells;
        }

        this.context = context;
        this.recyclerView = view;
        this.pinCodeListener = listener;

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


    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher {

        private TextView pinTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pinTV = itemView.findViewById(R.id.single_pin_EDT);
            pinTV.addTextChangedListener(this);

        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d("IgorTest", "beforeTextChanged");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d("IgorTest", "onTextChanged");
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.d("IgorTest", "afterTextChanged");

            itemView.setFocusable(false);
            if ((getAdapterPosition() + 1) < getItemCount()) {
                recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() + 1).itemView.requestFocus();
                Log.d("IgorTest", "afterTextChanged false");
            } else if (getItemCount() == getItemCount()) {
                if (pinCodeListener != null) {
                    pinCodeListener.pinCodeReady(true);
                }
                Log.d("IgorTest", "afterTextChanged true");
                hideKeyBoard();
                pinTV.setFocusable(false);
            }

        }

        protected void hideKeyBoard() {
            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (NullPointerException ex) {

            }
        }


    }

    private void setPinCode(String data) {
        pinCode += data;
    }

    public static String getPinCode() {
        return pinCode;
    }


}
