package com.example.gtx660ti.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

//    private final static String CONTACTS_TAB = "contacts_tab";
//    private final static String PLAYER_TAB = "player_tab";
//    private final static String ALARM_TAB = "alarm_tab";

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
        getFragmentManager().beginTransaction().replace(R.id.fragments_container, new ContactsFragment()).commit();

        View.OnClickListener tabClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedId = v.getId();
                if (!v.isSelected()) {
                    switch (clickedId) {
                        case R.id.contacts_tab:
                            getFragmentManager().beginTransaction().replace(R.id.fragments_container, new ContactsFragment()).commit();
                            break;
                        case R.id.player_tab:
                            getFragmentManager().beginTransaction().replace(R.id.fragments_container, new PlayerFragment()).commit();
                            break;
                        case R.id.alarm_tab:
                            getFragmentManager().beginTransaction().replace(R.id.fragments_container, new AlarmFragment()).commit();
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
