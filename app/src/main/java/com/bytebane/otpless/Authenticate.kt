package com.bytebane.otpless

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.otpless.dto.OtplessResponse
import com.otpless.main.OtplessManager
import com.otpless.main.OtplessView

class Authenticate : ComponentActivity() {
    private lateinit var otplessView: OtplessView

    private fun onOtplessCallback(response: OtplessResponse) {
        if (response.errorMessage != null) {
            // TODO -> error handing
            Log.e("*Otpless Error Message", response.errorMessage)
        } else {
            val token = response.data.optString("token")
            // TODO -> Use the response data as per your use-case, like token verification with api among others.
            Log.i("*Otpless Auth Token", "token: $token")
            Log.i("*Otpless Auth Response", response.toString())

            finish() //  Get back to last page

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialise OtplessView
        otplessView = OtplessManager.getInstance().getOtplessView(this)
        otplessView.setCallback(::onOtplessCallback, null, true)
        // calling showOtplessLoginPage method is optional to call here, we can also call it on buttons click
        otplessView.showOtplessLoginPage()
        // very important to call here, verification is done on low memory recreate case
        otplessView.verifyIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        otplessView.verifyIntent(intent)
        super.onNewIntent(intent)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
//        Use the Following to end this activity and return back to last page
        if (otplessView.onBackPressed()) finish()
//        Use the Following to return back to this page
//        if (otplessView.onBackPressed()) return
        super.onBackPressed()
    }
}
