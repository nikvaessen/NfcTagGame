package se.kth.cs.group15.nfccolorgame;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.skydoves.colorpickerpreference.ColorEnvelope;
import com.skydoves.colorpickerpreference.ColorListener;
import com.skydoves.colorpickerpreference.ColorPickerDialog;

public class SendActivity extends Activity {

    private ActionBar mActionBar;
    private Button mButton;
    private View mBackground;

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
        if(getActionBar() != null) {
            mActionBar.setTitle(R.string.sa_title_selected_color);
        }
        mButton.setText(R.string.sa_button_color_reselect);

        setBackgroundColor(color);
        activateNfc();
    }

    private void setBackgroundColor(int color) {
        mBackground.setBackgroundColor(color);
    }

    private void activateNfc() {
        System.out.println("now NFC should be enabled somehow :)");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { ;
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
