package com.jetpack.a4monks

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.a4monks.ui.theme._4MonksTheme
import com.jetpack.a4monks.ui.theme.work_sans

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _4MonksTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingPreview3()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {

    val context = LocalContext.current
    _4MonksTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
                Button(onClick = {
                    context.startActivity(Intent(context, MainActivity::class.java))

                }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Your Shopping Cart",
                        style = TextStyle(
                            color = Color(0xffFFFEFE),
                            fontFamily = work_sans,
                            fontSize = 12.sp
                        )
                    )

                }
                Button(onClick = {

                    context.startActivity(Intent(context, Reels::class.java))

                }) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "platform that utilizes reels",
                        style = TextStyle(
                            color = Color(0xffFFFEFE),
                            fontFamily = work_sans,
                            fontSize = 12.sp
                        )
                    )

                }
            }

        }
    }
}