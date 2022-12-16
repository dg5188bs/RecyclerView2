package com.example.recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    TextView tv1,tv2;
    RecyclerView Rv;
    EditText et1, et2;
    Double first, multiplier;
    Double[] sumArray = new Double[20];
    ToggleButton tg;
    List<item> items;
    MyAdapter myAdapter;
    int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count = 0;
        items = new ArrayList<>();
        Rv = (RecyclerView) findViewById(R.id.recyclerview1);
        et1 = (EditText) findViewById(R.id.editTextNumberSigned);
        et2 = (EditText) findViewById(R.id.editTextNumberSigned2);
        tv1 = (TextView) findViewById(R.id.textView5);
        tv2 = (TextView) findViewById(R.id.textView4);
        tg = (ToggleButton) findViewById(R.id.toggleButton);
        Rv.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(getApplicationContext(), items, new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(count>0) {
                    tv1.setText(Integer.toString(position+1));
                    double sum = sumArray[0];
                    for(int k=1;k<position+1;k++){
                        sum = sum+sumArray[k];
                    }
                    tv2.setText(Double.toString(sum));
                }

            }
        });
        Rv.setAdapter(myAdapter);
        EmptyItems();
    }

    public void EmptyItems(){
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
        items.add(new item(""));
    }

    public int e (String str){
        int s = str.indexOf(".");
        int e = str.indexOf("E");
        if(e==-1) {
            return (str.substring(s + 1)).length();
        }
        else{
            return (str.substring(s+1)).length()+Integer.parseInt(str.substring(e+1));
        }
    }

    public boolean validnumber(String str) {
        if (str.equals("") || str.equals("-") || str.equals(".") || str.equals("-.")) {
            return false;
        } else {
            return true;
        }
    }

    public void update(View view) {
        myAdapter.notifyDataSetChanged();
        String st1 = et1.getText().toString();
        boolean legalnum1 = validnumber(st1);
        String st2 = et2.getText().toString();
        boolean legalnum2 = validnumber(st2);
        if (legalnum1 && legalnum2) {
            count++;
            first = Double.parseDouble(st1);
            multiplier = Double.parseDouble(st2);
            if (tg.isChecked()) {
                Geometrical();
            }
            else {
                Mathemtical();
            }
        }
        else{
            Toast.makeText(this, "One or more of the numbers is invalid!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Mathemtical(){
        for (int i = 0; i < 20; i++) {
            sumArray[i] = first;
            int check = e(Double.toString(first));
            String str = String.format("%.02f", first);
            if(check>2) {
               items.set(i,new item(str + "E" + Integer.toString(check - 2)));
            }
            else {
               items.set(i,new item(str));
            }
            first = first+multiplier;

        }

    }

    public void Geometrical(){
        for (int i = 0; i < 20; i++) {
            sumArray[i] = first;
            int check = e(Double.toString(first));
            String str = String.format("%.02f", first);
            if(check>2) {
                items.set(i,new item(str + "E" + Integer.toString(check - 2)));
            }
            else {
                items.set(i,new item(str));
            }
            first = first*multiplier;

        }
    }

}