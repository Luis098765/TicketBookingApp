package com.example.ticketbookingapp.Activities.Splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ticketbookingapp.MainActivity
import com.example.ticketbookingapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen(onGetStartedClick = {
                startActivity(Intent(this, MainActivity::class.java))
            })
        }
    }
}

@Composable
fun SplashScreen(onGetStartedClick:()->Unit={}) {
    Column (modifier = Modifier.fillMaxSize()){
        ConstraintLayout() {
            val(backgroundImg, title, subtitle, startbtn) = createRefs()

            Image(
                painter = painterResource(R.drawable.splash_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .constrainAs(backgroundImg) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .fillMaxSize()
            )
            val styledText = buildAnnotatedString {
                append("Discover your\nDream")
                withStyle(style = SpanStyle(color = colorResource(R.color.orange))) {
                    append(" Flight")
                }
                append("\nEasily")
            }
            Text(
                text = styledText,
                fontSize = 53.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 32.dp)
                    .padding(horizontal = 16.dp)
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = stringResource(R.string.subtitle_splash),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(R.color.orange),
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 32.dp)
                    .constrainAs(subtitle) {
                        top.linkTo(title.bottom)
                        start.linkTo(title.start)
                    }
            )
            Box(modifier = Modifier.constrainAs(startbtn){
                bottom.linkTo(parent.bottom)
            }) {
                GradientButton(onClick = onGetStartedClick, text = "Get Started", padding = 32)
            }
        }
    }
}