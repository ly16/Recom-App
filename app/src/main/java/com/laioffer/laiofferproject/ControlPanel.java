package com.laioffer.laiofferproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ControlPanel extends AppCompatActivity {

    private Button buttonYelp;
    private Button buttonBackend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        buttonYelp = (Button)findViewById(R.id.button_yelp);
        buttonBackend = (Button)findViewById(R.id.button_backend);

        buttonYelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlPanel.this, MainActivity.class);
                intent.putExtra("Service", "Yelp");
                startActivity(intent);
            }
        });

        buttonBackend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonBackend.setText("New text");

                Intent intent = new Intent(ControlPanel.this, MainActivity.class);
                intent.putExtra("Service", "Backend");
                startActivity(intent);

            }
        });
    }

}

