package com.applicaster.pinverification.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.applicaster.pinverification.Constants;
import com.applicaster.pinverification.R;
import com.applicaster.pinverification.utils.Configurations;
import com.applicaster.pinverification.utils.CustomizationHelper;
import com.applicaster.util.OSUtil;


public class PVPinCodeAdapter extends RecyclerView.Adapter<PVPinCodeAdapter.ViewHolder> {

    private static final String TAG = PVPinCodeAdapter.class.getSimpleName();
    private static final int DEFAULT_CELLS_VALUE = 4;

    private int numberOfCells;
    private Context context;
    private RecyclerView recyclerView;
    Configurations configurations;
    StringBuilder pinCodeStringBuilder;
    String[] codes;

    public PVPinCodeAdapter(Context context, RecyclerView view, Configurations configurations) {
        this.configurations = configurations;
        this.context = context;
        this.recyclerView = view;

        pinCodeStringBuilder = new StringBuilder();
        pinCodeStringBuilder.setLength(numberOfCells);

        updateNumberOfCells();
    }

    private void updateNumberOfCells() {
        int numberOfCells = configurations.getPinCodeLength();

        codes = new String[numberOfCells];

        if (numberOfCells == 0) {
            this.numberOfCells = DEFAULT_CELLS_VALUE;
        } else {
            this.numberOfCells = numberOfCells;
        }
    }

    @NonNull
    @Override
    public PVPinCodeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        String layoutName = Constants.RECYCLER_VIEW_PIN_CODE_ITEM;
        return new ViewHolder(OSUtil.getLayoutInflater(context).inflate(OSUtil.getLayoutResourceIdentifier(layoutName), viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PVPinCodeAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return numberOfCells;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements TextWatcher {

        private EditText tvPin;
        private ConstraintLayout pinCardinal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPin = itemView.findViewById(R.id.single_pin_EDT);
            pinCardinal = itemView.findViewById(R.id.single_pin_cardinal);
            tvPin.addTextChangedListener(this);

            CustomizationHelper.setEditTextPropsWithBackground(tvPin,
                    configurations.getPinCodeFont(),
                    configurations.getPinCodeTextSize(),
                    configurations.getPinCodeTextColor(),
                    configurations.getPinCodeBackgroundColor(),
                    5f);
            tvPin.setTypeface(tvPin.getTypeface(), Typeface.BOLD);


        }

        private void bind(int position) {
            if (position > 0) {
                tvPin.setFocusable(false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.d(TAG, "beforeTextChanged: ");
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.d(TAG, "onTextChanged: ");
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s)) {
                pinHasBeenEntered();
            } else {
                pinCodeHasBeenRemoved();
            }
        }

        private void pinHasBeenEntered() {
            if ((getAdapterPosition() + 1) < getItemCount()) {
                recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() + 1).itemView.findViewById(R.id.single_pin_EDT).setFocusableInTouchMode(true);
                recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() + 1).itemView.findViewById(R.id.single_pin_EDT).requestFocus();

                codes[getAdapterPosition()] = tvPin.getText().toString();
            } else if ((getAdapterPosition() + 1) >= getItemCount()) {

                codes[getAdapterPosition()] = tvPin.getText().toString();
                pinCardinal.requestFocus();

                hideKeyBoard();
            }
        }

        private void pinCodeHasBeenRemoved() {
            if ((getAdapterPosition() - 1) >= 0) {

                codes[getAdapterPosition()] = tvPin.getText().toString();

                EditText pinField = recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() - 1).itemView.findViewById(R.id.single_pin_EDT);
                pinField.requestFocus();

                if (!TextUtils.isEmpty(pinField.getText().toString())) {
                    ((EditText) recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() - 1).itemView.findViewById(R.id.single_pin_EDT)).setSelection(1);
                } else {
                    ((EditText) recyclerView.findViewHolderForAdapterPosition(getAdapterPosition() - 1).itemView.findViewById(R.id.single_pin_EDT)).setSelection(0);
                }
            }
        }

        private void hideKeyBoard() {
            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception exception) {
                Log.d(TAG, "hideKeyBoard: " + exception.getMessage());
            }
        }
    }

    public String getPinCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i <= numberOfCells - 1; i++) {
            stringBuilder.append(codes[i]);
        }

        return stringBuilder.toString();
    }

    public boolean isPinCodeReady() {
        for (int i = 0; i <= numberOfCells - 1; i++) {
            if (TextUtils.isEmpty(codes[i])) {
                return false;
            }
        }
        return true;
    }

}
