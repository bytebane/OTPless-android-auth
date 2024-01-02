package com.bytebane.otpless;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.otpless.dto.OtplessResponse;
import com.otpless.main.OtplessManager;
import com.otpless.main.OtplessView;

import org.json.JSONException;
import org.json.JSONObject;

public class Authenticate extends AppCompatActivity {
    OtplessView otplessView;

    private void onOtplessCallback(OtplessResponse response) {
        if (response.getErrorMessage() != null) {
            // TODO -> error handing
            Log.e("*Otpless Auth Error", response.getErrorMessage());
        } else {
            // TODO -> Use the response data as per your use-case, like token verification with api among others.
            Log.i("*Otpless Auth Response", response.toString());
            final String token = response.getData().optString("token");
            Log.i("*Otpless Auth Token", "token: " + token);

            finish(); //  Get back to last page
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        // Initialise OtplessView
        otplessView = OtplessManager.getInstance().getOtplessView(this);
        final JSONObject extras = new JSONObject();
        try {
            extras.put("method", "get");
            final JSONObject params = new JSONObject();
            // TODO -> Add your CID below from https://otpless.com/platforms/android
            params.put("cid", "YOUR_CID_HERE");
            extras.put("params", params);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        otplessView.setCallback(this::onOtplessCallback, extras);
        otplessView.showOtplessLoginPage(extras, this::onOtplessCallback);
        otplessView.verifyIntent(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (otplessView != null) {
            otplessView.verifyIntent(intent);
        }
        super.onNewIntent(intent);
    }

    @Override
    public void onBackPressed() {
//        Use the Following to end this activity and return back to last page
        if (otplessView.onBackPressed()) finish();
//        Use the Following to return back to this page
//        if (otplessView.onBackPressed()) return
        super.onBackPressed();
    }
}
