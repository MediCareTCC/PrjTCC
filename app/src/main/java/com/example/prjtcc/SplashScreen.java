package com.example.prjtcc;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import gr.net.maroulis.library.EasySplashScreen;
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(Login.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#258BE9"))
                .withBeforeLogoText("Bem-Vindo ao")
                .withLogo(R.drawable.logo_branco);

        config.getBeforeLogoTextView().setTextColor(Color.WHITE);
        config.getBeforeLogoTextView().setTextSize(1, 30);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}