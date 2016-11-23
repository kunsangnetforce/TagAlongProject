package com.netforceinfotech.tagalong.home.findride.paymentmodes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.netforceinfotech.tagalong.R;

public class PaymentModeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView cardnoListView;
    private ArrayAdapter<String> listadapter;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_mode);
        context = this;

        setupToolbar("PAYMENT MODE");

        initialValue();
    }

    private void initialValue() {
        String[] cardList = {"4907 xxxx xxxx x975", "4567 xxxx xxxx x678", "4556 xxxx xxxx x673"};


        cardnoListView = (ListView) findViewById(R.id.cardnoListView);
        listadapter = new ArrayAdapter<>(this,R.layout.payment_card_row_list,R.id.userCardNoTextView,cardList);
        cardnoListView.setAdapter(listadapter);


    }

    private void setupToolbar(String s) {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.inflateMenu(R.menu.home_menu);
        tb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                return onOptionsItemSelected(item);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
