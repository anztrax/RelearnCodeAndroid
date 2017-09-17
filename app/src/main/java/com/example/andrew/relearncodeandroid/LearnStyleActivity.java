package com.example.andrew.relearncodeandroid;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class LearnStyleActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_learn_style);

    TextView textViewGradient = (TextView)findViewById(R.id.txtViewGradient);
    LinearGradient gradient1 = new LinearGradient(0, 0, textViewGradient.getTextSize(), 0 , Color.GREEN, Color.RED, Shader.TileMode.MIRROR);
    textViewGradient.getPaint().setShader(gradient1);
  }
}
