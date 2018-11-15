package se.kth.cs.group15.nfccolorgame;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private final static String RECEIVE_MODE = "Receive Mode";
    private final static String SEND_MODE    = "   Send Mode";

    private Toolbar mToolBar;

    private int mOriginalToolBarColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);

         mOriginalToolBarColor = ((ColorDrawable) mToolBar.getBackground()).getColor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.app_bar_switch);
        Switch switchItem = item.getActionView().findViewById(R.id.app_bar_switch_impl);

        final MenuItem tvMenuItem = menu.findItem(R.id.app_bar_tv_mode);
        tvMenuItem.setTitle(SEND_MODE);
        if (switchItem != null) {
            switchItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        tvMenuItem.setTitle(RECEIVE_MODE);
                        mToolBar.setBackgroundColor(Color.parseColor("#f44248"));
                    }
                    else {
                        tvMenuItem.setTitle(SEND_MODE);
                        mToolBar.setBackgroundColor(mOriginalToolBarColor);
                    }
                }
            });
        }

        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_switch:
                onSwitchStateChanged((Switch) item);
        }

        return super.onContextItemSelected(item);
    }

    private void onSwitchStateChanged(Switch menuSwitch) {
        System.out.println("received the switch!!!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
}
