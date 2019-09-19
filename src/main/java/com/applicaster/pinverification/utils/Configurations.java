package com.applicaster.pinverification.utils;

import android.graphics.Color;

import java.util.Map;

public class Configurations {

    private Map<String, String> configMap;

    // Pin code
    private static final String PIN_CODE_LENGTH_KEY = "pin_verification_pin_code_length";
    private static final String PIN_CODE_FONT_KEY = "pin_verification_pin_code_font";
    private static final String PIN_CODE_SIZE_KEY = "pin_verification_pin_code_size";
    private static final String PIN_CODE_COLOR_KEY = "pin_verification_pin_code_color";
    private static final String PIN_CODE_BG_COLOR_KEY = "pin_verification_pin_code_bg_color";

    // General
    private static final String LOGO_IMAGE_KEY = "pin_verification_logo_image";
    private static final String CLOSE_BUTTON_IMAGE_KEY = "pin_verification_close_button_image";

    // Screen
    private static final String SCREEN_BG_COLOR_KEY = "pin_verification_screen_bg_color";
    private static final String SCREEN_BG_IMAGE_KEY = "pin_verification_screen_bg_image";

    // 'Title' view
    private static final String TITLE_TEXT_KEY = "pin_verification_title_text";
    private static final String TITLE_FONT_KEY = "pin_verification_title_font";
    private static final String TITLE_SIZE_KEY = "pin_verification_title_size";
    private static final String TITLE_COLOR_KEY = "pin_verification_title_color";

    // 'Incorrect pin'
    private static final String INCORRECT_PIN_TEXT_KEY = "pin_verification_incorrect_pin_text";
    private static final String INCORRECT_PIN_FONT_KEY = "pin_verification_incorrect_pin_font";
    private static final String INCORRECT_PIN_SIZE_KEY = "pin_verification_incorrect_pin_size";
    private static final String INCORRECT_PIN_COLOR_KEY = "pin_verification_incorrect_pin_color";

    // 'Resend passcode'
    private static final String RESEND_PIN_TEXT_KEY = "pin_verification_resend_pin_text";
    private static final String RESEND_PIN_FONT_KEY = "pin_verification_resend_pin_font";
    private static final String RESEND_PIN_SIZE_KEY = "pin_verification_resend_pin_size";
    private static final String RESEND_PIN_COLOR_KEY = "pin_verification_resend_pin_color";

    // 'Proceed' button
    private static final String PROCEED_BUTTON_TEXT_KEY = "pin_verification_proceed_button_text";
    private static final String PROCEED_BUTTON_FONT_KEY = "pin_verification_proceed_button_font";
    private static final String PROCEED_BUTTON_SIZE_KEY = "pin_verification_proceed_button_size";
    private static final String PROCEED_BUTTON_COLOR_KEY = "pin_verification_proceed_button_color";
    private static final String PROCEED_BUTTON_BG_COLOR_KEY = "pin_verification_proceed_button_bg_color";
    private static final String PROCEED_BUTTON_CORNERS_RADIUS_KEY = "pin_verification_proceed_button_corners_radius";

    // Notice dialog
    private static final String DIALOG_BG_COLOR_KEY = "pin_verification_dialog_bg_color";
    private static final String DIALOG_MESSAGE_FONT_KEY = "pin_verification_dialog_message_font";
    private static final String DIALOG_MESSAGE_SIZE_KEY = "pin_verification_dialog_message_size";
    private static final String DIALOG_MESSAGE_COLOR_KEY = "pin_verification_dialog_message_color";

    private static final String DIALOG_CONFIRM_BUTTON_TEXT_KEY = "pin_verification_dialog_confirm_button_text";
    private static final String DIALOG_CONFIRM_BUTTON_FONT_KEY = "pin_verification_dialog_confirm_button_font";
    private static final String DIALOG_CONFIRM_BUTTON_SIZE_KEY = "pin_verification_dialog_confirm_button_size";
    private static final String DIALOG_CONFIRM_BUTTON_COLOR_KEY = "pin_verification_dialog_confirm_button_color";
    private static final String DIALOG_CONFIRM_BUTTON_BG_COLOR_KEY = "pin_verification_dialog_confirm_button_bg_color";
    private static final String DIALOG_CONFIRM_BUTTON_CORNERS_RADIUS_KEY = "pin_verification_dialog_confirm_button_corners_radius";

    //////////////////////////////// PVMainActivity ////////////////////////////////

    public Configurations(Map<String, String> configMap) {
        this.configMap = configMap;
    }

    public int getPinCodeLength() {
        return Integer.parseInt(configMap.get(PIN_CODE_LENGTH_KEY));
    }

    public String getPinCodeFont() {
        return configMap.get(PIN_CODE_FONT_KEY);
    }

    public Float getPinCodeTextSize() {
        return Float.parseFloat(configMap.get(PIN_CODE_SIZE_KEY));
    }

    public int getPinCodeTextColor() {
        return getColorByKey(configMap.get(PIN_CODE_COLOR_KEY));
    }

    public int getPinCodeBackgroundColor() {
        return getColorByKey(configMap.get(PIN_CODE_BG_COLOR_KEY));
    }

    public String getLogoImage() {
        return configMap.get(LOGO_IMAGE_KEY);
    }

    public String getCloseButtonImage() {
        return configMap.get(CLOSE_BUTTON_IMAGE_KEY);
    }

    public int getScreenBackgroundColor() {
        return getColorByKey(configMap.get(SCREEN_BG_COLOR_KEY));
    }

    public String getScreenBackgroundImage() {
        return configMap.get(SCREEN_BG_IMAGE_KEY);
    }

    public String getTitleText() {
        return configMap.get(TITLE_TEXT_KEY);
    }

    public String getTitleFont() {
        return configMap.get(TITLE_FONT_KEY);
    }

    public Float getTitleTextSize() {
        return Float.parseFloat(configMap.get(TITLE_SIZE_KEY));
    }

    public int getTitleColor() {
        return getColorByKey(configMap.get(TITLE_COLOR_KEY));
    }

    public String getIncorrectPinText() {
        return configMap.get(INCORRECT_PIN_TEXT_KEY);
    }

    public String getIncorrectPinFont() {
        return configMap.get(INCORRECT_PIN_FONT_KEY);
    }

    public Float getIncorrectPinTextSize() {
        return Float.parseFloat(configMap.get(INCORRECT_PIN_SIZE_KEY));
    }

    public int getIncorrectPinTextColor() {
        return getColorByKey(configMap.get(INCORRECT_PIN_COLOR_KEY));
    }

    public String getResendPinText() {
        return configMap.get(RESEND_PIN_TEXT_KEY);
    }

    public String getResendPinFont() {
        return configMap.get(RESEND_PIN_FONT_KEY);
    }

    public Float getResendPinTextSize() {
        return Float.parseFloat(configMap.get(RESEND_PIN_SIZE_KEY));
    }

    public int getResendPinTextColor() {
        return getColorByKey(configMap.get(RESEND_PIN_COLOR_KEY));
    }

    public String getProceedButtonText() {
        return configMap.get(PROCEED_BUTTON_TEXT_KEY);
    }

    public String getProceedButtonFont() {
        return configMap.get(PROCEED_BUTTON_FONT_KEY);
    }

    public Float getProceedButtonTextSize() {
        return Float.parseFloat(configMap.get(PROCEED_BUTTON_SIZE_KEY));
    }

    public int getProceedButtonTextColor() {
        return getColorByKey(configMap.get(PROCEED_BUTTON_COLOR_KEY));
    }

    public int getProceedButtonBackgroundColor() {
        return getColorByKey(configMap.get(PROCEED_BUTTON_BG_COLOR_KEY));
    }

    public float getProceedButtonCornersRadius() {
        return Float.parseFloat(configMap.get(PROCEED_BUTTON_CORNERS_RADIUS_KEY));
    }

    /////////////////////////////////////// Dialog ///////////////////////////////////////

    public int getDialogBackgroundColor() {
        return getColorByKey(configMap.get(DIALOG_BG_COLOR_KEY));
    }

    public String getDialogMessageFont() {
        return configMap.get(DIALOG_MESSAGE_FONT_KEY);
    }

    public Float getDialogMessageTextSize() {
        return Float.parseFloat(configMap.get(DIALOG_MESSAGE_SIZE_KEY));
    }

    public int getDialogMessageTextColor() {
        return getColorByKey(configMap.get(DIALOG_MESSAGE_COLOR_KEY));
    }





    public String getDialogButtonText() {
        return configMap.get(DIALOG_CONFIRM_BUTTON_TEXT_KEY);
    }

    public String getDialogButtonFont() {
        return configMap.get(DIALOG_CONFIRM_BUTTON_FONT_KEY);
    }

    public float getDialogButtonTextSize() {
        return Float.parseFloat(configMap.get(DIALOG_CONFIRM_BUTTON_SIZE_KEY));
    }

    public int getDialogButtonTextColor() {
        return getColorByKey(configMap.get(DIALOG_CONFIRM_BUTTON_COLOR_KEY));
    }

    public int getDialogButtonBgColor() {
        return getColorByKey(configMap.get(DIALOG_CONFIRM_BUTTON_BG_COLOR_KEY));
    }

    public float getDialogButtonBgRadius() {
        return Float.parseFloat(configMap.get(DIALOG_CONFIRM_BUTTON_CORNERS_RADIUS_KEY));
    }

    //////////////////////////////////////////////////////////////////////////////////////


    private int getColorByKey(String color) {
        return Color.parseColor(color);
    }
}
