package com.isil.am2template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.am2template.model.entity.Cart;
import com.isil.am2template.model.entity.Place;
import com.isil.am2template.storage.PreferencesHelper;
import com.isil.am2template.storage.db.CRUDOperations;
import com.isil.am2template.storage.db.MyDatabase;
import com.isil.am2template.utils.StringUtils;
import com.isil.am2template.view.adapters.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Pablo Claus on 11/5/2017.
 */

public class PlaceActivity extends AppCompatActivity {
    private static final String TAG ="PlaceActivity" ;
    private static final int ACTION_ADD=1;
    private static final int ACTION_DETAIL=2;
    private static final int ACTION_CART=3;

    private TextView tviLogout,tviUser;
    private ListView lstPlaces;
    private Button btnAddPlace;
    private List<Place> lsPlaces;
    private CRUDOperations crudOperations;
    private PlaceAdapter placeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        populate();
        init();
        loadData();
        }


    private void loadData() {
        crudOperations= new CRUDOperations(new MyDatabase(this));
        lsPlaces= crudOperations.getAllPlaces();
        placeAdapter= new PlaceAdapter(this,lsPlaces);
        lstPlaces.setAdapter(placeAdapter);

    }

    private void populate() {

        CRUDOperations crudOperations= new CRUDOperations(new MyDatabase(this));
        crudOperations.clearDb();
        crudOperations.addPlace(new Place("1701","La Reserva","Urb. Bello Horizonte) 15067 Chorrillos","17"));
        crudOperations.addPlace(new Place("1702","Casona de Villa","Av. Panamericana Sur, km 19.5 13620 Lima ","18"));
        crudOperations.addPlace(new Place("1703","La Arboleda","Av. Manuel Valle, S/N 019 Pachacamac ","19"));
        crudOperations.addPlace(new Place("1704","Casa Las Cascadas","Calle Las Monjas, 191, La Molina ","20"));
        crudOperations.addPlace(new Place("1705","Hacienda Lomas de Villa","Los Avicultores, s/n 15058 Chorrillos","21"));

        Log.v(TAG, "populate " + crudOperations.getAllPlaces());
    }

    private void init() {
        tviLogout= (TextView)findViewById(R.id.tviLogout);
        tviUser= (TextView)findViewById(R.id.tviUser);
        lstPlaces= (ListView)(findViewById(R.id.lstNotes));
        btnAddPlace= (Button)(findViewById(R.id.btnAddPlace));

        //user Info
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            tviUser.setText("Bienvenido "+ StringUtils.firstCapitalize(username));
        }

        //events
        btnAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gotoNote(ACTION_ADD, null);
                sendtoCart(ACTION_CART, null);
            }
        });

        /*lstPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place place = (Place) adapterView.getAdapter().getItem(i);
                gotoNote(ACTION_DETAIL, place);
            }
        });*/

        tviLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    /*private void gotoNote(int action, Place place) {
        Intent intent= new Intent(this,DetailPlaceActivity.class);


        intent.putExtra("FRAGMENT",DetailPlaceActivity.DETAIL_PLACE);
        intent.putExtra("PLACE", place);
        startActivity(intent);


    }*/

    private void sendtoCart(int action,View view){
        for (Place _place : lsPlaces){
            if(_place.isChecked()==true){
                //CRUDOperations crudOperations= new CRUDOperations(new MyDatabase(this));
                //crudOperations.clearDb();
                crudOperations.addCart(new Cart(_place.getId(),_place.getName(), _place.getSize()));
            }
        }
        List<String> fila = new ArrayList<String>();
        Log.v(TAG, "Cart" + crudOperations.getAllCart() );
        List<Cart> prueba = crudOperations.getAllCart();
        for (Cart carrito : prueba){

            fila.add(carrito.getId());


        }
        Log.v(TAG, "ListaCart" + fila );

    }

    private void logout() {
        PreferencesHelper.signOut(this);
        //startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "MainActivity onResumen - 2");
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "MainActivity onPause - 1");
    }
}
