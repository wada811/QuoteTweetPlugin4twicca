package jp.co.wada811.quotetweet.plugin4twicca;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class QuoteTweetSettings extends PreferenceActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
    }

    @Override
    protected void onStart(){
        super.onStart();
        final SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        CheckBoxPreference check = (CheckBoxPreference)getPreferenceScreen().findPreference(getString(R.string.key));
        check.setChecked(sp.getBoolean(check.getKey(), false));
        check.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue){
                if(preference.getClass() == CheckBoxPreference.class){
                    if(preference.getKey().equals(getString(R.string.key))){
                        sp.edit().putBoolean(preference.getKey(), (Boolean)newValue).commit();
                        return true;
                    }
                }
                return false;
            }
        });
    }
}
