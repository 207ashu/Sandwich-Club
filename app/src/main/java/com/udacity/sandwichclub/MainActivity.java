package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.udacity.sandwichclub.utils.JsonUtils;


import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Sandwich> mSandwichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mSandwichList=new ArrayList<>();

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_names);
        getArrayData();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sandwiches);

        // Simplification: Using a ListView instead of a RecyclerView
        ListView listView = findViewById(R.id.sandwiches_listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchDetailActivity(position);
            }
        });
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }

    private void getArrayData(){
    String[] details = getResources().getStringArray(R.array.sandwich_details);

        for(int i=0;i<10;i++){
            Sandwich sandwich=JsonUtils.parseSandwichJson(details[i]);
            mSandwichList.add(sandwich);
        }
    }

}
