package se.kth.cs.group15.nfccolorgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ReceiveActivity extends Activity {

    private View mBackGround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        setTitle(R.string.ra_title);

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }


        mBackGround = findViewById(R.id.ra_background);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        ;
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
