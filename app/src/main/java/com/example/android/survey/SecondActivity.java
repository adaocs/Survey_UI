package com.example.android.survey;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentOne_HealthStatus_1and2 fragmentOne_healthStatus_1and2 = new FragmentOne_HealthStatus_1and2();

        fragmentTransaction.add(R.id.fragment_container, fragmentOne_healthStatus_1and2);
        fragmentTransaction.commit();
    }

}
