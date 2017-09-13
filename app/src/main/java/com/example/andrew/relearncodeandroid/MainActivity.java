package com.example.andrew.relearncodeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final TextView textView = (TextView)findViewById(R.id.text_value);
    Button button = (Button)findViewById(R.id.another_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String stringValue = textView.getText().toString();
        int newValue = MyWorker.doubleTheValue(Integer.parseInt(stringValue));
        textView.setText(Integer.toString(newValue));
      }
    });
  }
}
