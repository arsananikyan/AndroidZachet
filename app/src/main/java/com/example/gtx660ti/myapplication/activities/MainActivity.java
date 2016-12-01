package com.example.gtx660ti.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gtx660ti.myapplication.R;
import com.example.gtx660ti.myapplication.fragments.AlarmFragment;
import com.example.gtx660ti.myapplication.fragments.ContactsFragment;
import com.example.gtx660ti.myapplication.fragments.SongsFragment;

public class MainActivity extends AppCompatActivity {

    private View contactsTab;
    private View playerTab;
    private View alarmTab;

    private void setSelectedTab(View v) {
        contactsTab.setSelected(false);
        playerTab.setSelected(false);
        alarmTab.setSelected(false);
        v.setSelected(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactsTab = findViewById(R.id.contacts_tab);
        playerTab = findViewById(R.id.player_tab);
        alarmTab = findViewById(R.id.alarm_tab);
        contactsTab.setSelected(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new ContactsFragment()).commit();

        View.OnClickListener tabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedId = v.getId();
                if (!v.isSelected()) {
                    switch (clickedId) {
                        case R.id.contacts_tab:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new ContactsFragment()).commit();
                            break;
                        case R.id.player_tab:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new SongsFragment()).commit();
                            break;
                        case R.id.alarm_tab:
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragments_container, new AlarmFragment()).commit();
                            break;
                    }
                    setSelectedTab(v);
                }
            }
        };
        contactsTab.setOnClickListener(tabClickListener);
        playerTab.setOnClickListener(tabClickListener);
        alarmTab.setOnClickListener(tabClickListener);
    }
}
