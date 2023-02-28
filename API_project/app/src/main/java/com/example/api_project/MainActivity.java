package com.example.api_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ///initialize
    private  RecyclerView recyclerView;
    private ProgressBar progressBar;
    ArrayList <DATA> dataList;
    adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///find id
        recyclerView=findViewById(R.id.recyclerView);
        dataList=new ArrayList<>();
        progressBar=findViewById(R.id.progressbar);
        
        //////Get Data on Server------------------------------------------------->
        getDataOnServer();
    }

    void ShowRecyclerView()
    {
        adapter=new adapter(dataList,getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
    void getDataOnServer()
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String url="https://pqstec.com/invoiceapps/Values/GetProductList";
        String key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOiI3OCIsIkN1cnJlbnRDb21JZCI6IjEiLCJuYmYiOjE2Nzc1NzI5ODcsImV4cCI6MTY3ODE3Nzc4NywiaWF0IjoxNjc3NTcyOTg3fQ.5w-QV47P_lZICpPK7HrYUQONrDKxuJfrr8jDp_hBLRk";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.GONE);
                if (response!=null) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("ProductList");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Log.e("test",""+i);

                            JSONObject object = jsonArray.getJSONObject(i);
                            dataList.add(new DATA(object.getString("Name"),
                                    object.getString("Type"),
                                    object.getString("Price"),
                                    object.getString("ColorName"),
                                    object.getString("Id"),
                                    object.getString("Code"),
                                    object.getString("ParentCode"),
                                    object.getString("BrandName"),
                                    object.getString("ProductBarcode")

                            ));

                        }
                        ShowRecyclerView();


                    } catch (Exception e) {
                        Log.e("erro",""+e);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Please Check Your Internet Connection", Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap header=new HashMap();
                header.put("Content-Type","application/json");
                header.put("Authorization",key);
                return header;
            }
        };
        requestQueue.add(stringRequest);
    }
}