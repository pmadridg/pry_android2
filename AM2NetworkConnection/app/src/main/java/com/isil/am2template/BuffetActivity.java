package com.isil.am2template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.isil.am2template.model.entity.Buffet;
import com.isil.am2template.storage.PreferencesHelper;
import com.isil.am2template.storage.request.ApiClient;
import com.isil.am2template.storage.request.entity.BuffetResponse;
import com.isil.am2template.view.adapters.BuffetAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Pablo Claus on 11/2/2017.
 */

public class BuffetActivity extends AppCompatActivity {


    private static final String TAG ="BuffetActivity" ;

    private ListView lstBuffet;
    private BuffetAdapter buffetAdapter;
    private List<Buffet> lsBuffetEntities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffet);
        //populate();
        init();
        retrieveBuffet();

        //loadData();
    }

    private void retrieveBuffet(){
        Call<List<Buffet>> call= ApiClient.getMyApiClient().buffet();
        call.enqueue(new Callback<List<Buffet>>() {
            @Override
            public void onResponse(Call<List<Buffet>> call, Response<List<Buffet>> response) {
                if(response!=null){
                    List<Buffet> buffetsResponse=null;
                    if(response.isSuccessful()){
                        buffetsResponse= response.body();
                        lsBuffetEntities = buffetsResponse;
                        if(buffetsResponse!=null){
                            renderBuffet(buffetsResponse);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Buffet>> call, Throwable t) {

            }
        });
    }

    private void init() {

        lstBuffet= (ListView)(findViewById(R.id.lstFood));

        lstBuffet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Buffet buffet = (Buffet) adapterView.getAdapter().getItem(i);

            }
        });





    }

    private void renderBuffet(List<Buffet> buffetEntityList) {
        Log.v("CONSOLE", "renderNotes");
        lsBuffetEntities= buffetEntityList;

        String _grabados = PreferencesHelper.getGrabados(this);
        String[] grabados = null;
        grabados = _grabados.split(",");
        buffetAdapter= new BuffetAdapter(this,lsBuffetEntities, grabados);
        lstBuffet.setAdapter(buffetAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "MainActivity onResumen - 2");
        //loadData();
        retrieveBuffet();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "MainActivity onPause - 1");
    }

    public void guardarPedidos(View view) {
        List<String> grabaditos = null;
        for( Buffet _buffet : lsBuffetEntities ){
            if( _buffet.getMarcado() ){
                grabaditos.add(_buffet.getObjectId());
            }
        }
        PreferencesHelper.setGrabados( this,  TextUtils.join(",", grabaditos) );


    }
}
