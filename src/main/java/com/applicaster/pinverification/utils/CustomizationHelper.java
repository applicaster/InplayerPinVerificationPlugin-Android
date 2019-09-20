package com.applicaster.pinverification.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.applicaster.util.TextUtil;

public class CustomizationHelper {

    private static final String TAG = CustomizationHelper.class.getSimpleName();

    public static void setEditTextPropsWithBackground(EditText editText, String font, Float textSize, int color,
                                                      int backgroundColor, Float cornersRadius) {
        if (editText != null) {
            TextUtil.setTextFont(editText, font);
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            editText.setTextColor(color);
            editText.setBackground(getRectangleDrawable(backgroundColor, cornersRadius));
        }
    }

    //////////////////////////////////////  Button  //////////////////////////////////////////

    public static void setButtonPropsWithBackground(Button button, String text, String font, Float textSize, int color,
                                                    int backgroundColor, Float cornersRadius) {
        if (button != null) {

            button.setText(text);
            TextUtil.setTextFont(button, font);
            button.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            button.setTextColor(color);
            button.setBackground(getRectangleDrawable(backgroundColor, cornersRadius));
        }
    }

    //////////////////////////////////////  TextView  //////////////////////////////////////////

    public static void setTextViewProps(TextView textView, String text, String font, Float textSize, int color) {
        if (textView != null) {

            if (!TextUtils.isEmpty(text)) {
                textView.setText(text);
            }

            TextUtil.setTextFont(textView, font);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            textView.setTextColor(color);
        }
    }

    //////////////////////////////////////  Resources  ///////////////////////////////////////////

    private static Drawable getRectangleDrawable(int drawableColor, Float radius) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);

        shape.setColor(drawableColor);

        if (radius != null)
            shape.setCornerRadius(radius);

        return shape;
    }

    private static StateListDrawable getStateListDrawable(Drawable defStateDrawable, Drawable pressedStateDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        if (defStateDrawable != null) {
            stateListDrawable.addState(new int[]{}, defStateDrawable);
        }
        if (pressedStateDrawable != null) {
            stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedStateDrawable);
        }

        return stateListDrawable;
    }

}
