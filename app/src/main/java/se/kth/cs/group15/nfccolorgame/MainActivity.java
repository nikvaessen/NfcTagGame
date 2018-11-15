package se.kth.cs.group15.nfccolorgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = findViewById(R.id.b_main_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSendMode();
            }
        });


        Button recvButton = findViewById(R.id.b_main_recv);
        recvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterRecvMode();
            }
        });
    }

    private void enterSendMode() {
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }

    private void enterRecvMode() {
        Intent intent = new Intent(this, ReceiveActivity.class);
        startActivity(intent);
    }

}