package com.sudoerz.support.preferences;

import android.content.Context;
import androidx.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;

public class SystemSettingEditTextPreference extends EditTextPreference {
    private boolean mAutoSummary = false;

    public SystemSettingEditTextPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    public SystemSettingEditTextPreference(Context context) {
        super(context);
        setPreferenceDataStore(new SystemSettingsStore(context.getContentResolver()));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        // This is what default ListPreference implementation is doing without respecting
        // real default value:
        //setText(restoreValue ? getPersistedString(mText) : (String) defaultValue);
        // Instead, we better do
        setText(restoreValue ? getPersistedString((String) defaultValue) : (String) defaultValue);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        if (mAutoSummary || TextUtils.isEmpty(getSummary())) {
            setSummary(text, true);
        }
    }

    @Override
    public void setSummary(CharSequence summary) {
        setSummary(summary, false);
    }

    private void setSummary(CharSequence summary, boolean autoSummary) {
        mAutoSummary = autoSummary;
        super.setSummary(summary);
    }
}
