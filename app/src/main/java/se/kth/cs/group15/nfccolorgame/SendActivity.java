package se.kth.cs.group15.nfccolorgame;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

import java.util.Locale;

public class SendActivity extends Activity {

    private ActionBar mActionBar;
    private Button mButton;
    private View mBackground;
    private int mSelectedColor;
    private boolean mIsColorSelected;
    private NfcAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        if (getActionBar() != null) {
            mActionBar = getActionBar();
            mActionBar.setTitle(R.string.sa_title_not_selected_color);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBackground = findViewById(R.id.sa_background);
        mBackground.setBackgroundColor(-13619152);

        mAdapter = NfcAdapter.getDefaultAdapter(this);

        mButton = findViewById(R.id.sa_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorPickerDialog.Builder builder =
                        new ColorPickerDialog.Builder(SendActivity.this,
                                AlertDialog.THEME_HOLO_DARK);

                builder.setTitle(mButton.getText());

                builder.setPositiveButton(
                        getString(R.string.sa_dialog_confirm), new ColorListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope colorEnvelope) {
                                selectedColor(colorEnvelope.getColor());
                            }
                        });

                builder.setNegativeButton(
                        getString(R.string.sa_dialog_cancel), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }
                );

                builder.show();
            }
        });
    }

    private void selectedColor(int color) {
        if (getActionBar() != null) {
            mActionBar.setTitle(R.string.sa_title_selected_color);
        }
        mButton.setText(R.string.sa_button_color_reselect);
        mSelectedColor = color;
        mIsColorSelected = true;

        setBackgroundColor(color);
        activateNfc();
    }

    private void setBackgroundColor(int color) {
        mBackground.setBackgroundColor(color);
    }

    private void activateNfc() {
        mAdapter.setNdefPushMessage(getBackGroundColorMessage(), this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private NdefMessage getBackGroundColorMessage() {
        if (!mIsColorSelected) {
            throw new IllegalArgumentException("did not select color yet");
        }

        NdefRecord colorRecord = NdefRecord
                .createTextRecord(
                        Locale.ENGLISH.toLanguageTag(),
                        Integer.toString(mSelectedColor)
                );

        return new NdefMessage(colorRecord);
    }
}
