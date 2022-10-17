package com.example.hospital_try_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class myreports extends AppCompatActivity {

    ArrayList<String> dateOfReport=new ArrayList<String>();
    ArrayList<String> reportLink=new ArrayList<String>();

    Snackbar snackbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreports);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View layout=findViewById(R.id.layout);
        snackbar=snackbar.make(layout,"Loading ...", Snackbar.LENGTH_INDEFINITE);
        

        snackbar.show();
        FetchData();


    }

    void FetchData()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://dorian-works.000webhostapp.com/patientReport.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(getApplicationContext(),"Fetching",Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObject;
                            JSONArray jsonArray;

                            jsonObject=new JSONObject(response);
                            jsonArray=jsonObject.getJSONArray("server_response");
                            int count=0;

                            while(count<jsonArray.length())
                            {
                                JSONObject JO=jsonArray.getJSONObject(count);

                                dateOfReport.add(JO.getString("reportDate"));
                                reportLink.add(JO.getString("reportLink"));

                              count++;
                            }

                            snackbar.dismiss();
                            buildRecyclerView();

                            //Toast.makeText(getApplicationContext(),"Data Fetched",Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {

                            snackbar.dismiss();
                            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                           // e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                snackbar.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();

                params.put("phone",patientData.phone);

                return  params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



    private void buildRecyclerView() {

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);

        reports_adapter mAdapter = new reports_adapter(this,dateOfReport,reportLink);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }







    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}