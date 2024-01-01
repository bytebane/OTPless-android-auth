package com.bytebane.otpless

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bytebane.otpless.ui.theme.OTPlessAuthTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OTPlessAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                //  A Column with a Text and a Login Button
                {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "OTPless Authentication",
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                        )
                        Box(modifier = Modifier.height(24.dp)) {}
                        Button(onClick = {
                            Intent(applicationContext, Authenticate::class.java).also {
                                startActivity(it)
                            }
                        }) {
                            Text(text = "Get Started")

                        }

                    }
                }
            }
        }
    }
}

@Composable
fun ViewPair(key: String, value: String, modifier: Modifier = Modifier) {
    Column {
        Row {

            Text(
                text = key,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = value,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

