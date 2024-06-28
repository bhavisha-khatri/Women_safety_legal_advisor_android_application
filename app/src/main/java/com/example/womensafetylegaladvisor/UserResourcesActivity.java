package com.example.womensafetylegaladvisor;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ExpandableListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserResourcesActivity extends AppCompatActivity {
    ExpandableListView expandableListView;
    UserResourcesAdapter listAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_resources);

        expandableListView = findViewById(R.id.resources);
        expandableListView.setGroupIndicator(null);

        // Preparing list data
        prepareListData();

        listAdapter = new UserResourcesAdapter(this, listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        // Adding header data
        for (String[] userDetails : ResourceData.RESOURCE) {
            listDataHeader.add(userDetails[0]); // Name as header
        }

        // Adding child data
        for (String[] userDetails : ResourceData.RESOURCE) {
            List<String> child = new ArrayList<>();
            child.add(userDetails[1]); // Comment as child
            listDataChild.put(userDetails[0], child); // Mapping header and child
        }
    }
}