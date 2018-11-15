package se.kth.cs.group15.nfccolorgame;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button mSendButton;
    private Button mRecvButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendButton = findViewById(R.id.b_main_send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterSendMode();
            }
        });

        mRecvButton = findViewById(R.id.b_main_recv);
        mRecvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterRecvMode();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String toastMessage;
        if (NfcAdapter.getDefaultAdapter(this) == null) {
            toastMessage = "NFC is not available!";
            disableButtons();
        } else if (!NfcAdapter.getDefaultAdapter(this).isEnabled()) {
            toastMessage = "NFC is disabled. Enable in settings!";
            disableButtons();
        } else {
            toastMessage = "NFC is enabled!";
            enableButtons();
        }

        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show();
    }

    private void enterSendMode() {
        Intent intent = new Intent(this, SendActivity.class);
        startActivity(intent);
    }

    private void enterRecvMode() {
        Intent intent = new Intent(this, ReceiveActivity.class);
        startActivity(intent);
    }

    private void disableButtons() {
        mSendButton.setClickable(false);
        mRecvButton.setClickable(false);
    }

    private void enableButtons() {
        mSendButton.setClickable(true);
        mRecvButton.setClickable(true);
    }
}