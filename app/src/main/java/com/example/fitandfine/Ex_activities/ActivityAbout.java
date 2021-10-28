package com.example.fitandfine.Ex_activities;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference.OnPreferenceClickListener;
import com.example.fitandfine.R;
import com.lb.material_preferences_library.PreferenceActivity;
import com.lb.material_preferences_library.custom_preferences.Preference;

public class ActivityAbout extends  PreferenceActivity implements OnPreferenceClickListener {
    private Preference mPrefRateReviewKey;
    private Preference mPrefShareKey;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPrefShareKey = (Preference) findPreference(getString(R.string.pref_share_key));
        this.mPrefRateReviewKey = (Preference) findPreference(getString(R.string.pref_rate_review_key));
        this.mPrefShareKey.setOnPreferenceClickListener(this);
        this.mPrefRateReviewKey.setOnPreferenceClickListener(this);
    }
    protected int getPreferencesXmlId() {
        return R.xml.pref_about;
    }
    public boolean onPreferenceClick(android.preference.Preference preference) {
        String key = preference.getKey();
        boolean z = true;
        switch (key.hashCode()) {
            case -320045692:
                if (key.equals("prefRateReviewKey")) {
                    z = true;
                    break;
                }
                break;
            case -145553085:
                if (key.equals("prefShareKey")) {
                    z = false;
                    break;
                }
                break;
        }
        if (z == false) {
            Intent shareIntent = new Intent("android.intent.action.SEND");
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.intent.extra.SUBJECT", getString(R.string.subject));
           /* shareIntent.putExtra("android.intent.extra.TEXT", getString(R.string.message) + " " +
                    getString(R.string.google_play_url)+getPackageName());
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));*/
        } else if (z == true) {
            Intent rateReviewIntent = new Intent("android.intent.action.VIEW");
          //  rateReviewIntent.setData(Uri.parse(Uri.parse(getString(R.string.google_play_url))+getPackageName()));
            startActivity(rateReviewIntent);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
