package com.isil.am2template;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.isil.am2template.model.entity.Cart;
import com.isil.am2template.model.entity.Place;
import com.isil.am2template.storage.PreferencesHelper;
import com.isil.am2template.storage.db.CRUDOperations;
import com.isil.am2template.storage.db.MyDatabase;
import com.isil.am2template.utils.StringUtils;
import com.isil.am2template.view.adapters.CartAdapter;
import com.isil.am2template.view.adapters.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo Claus on 11/12/2017.
 */

public class CartActivity extends AppCompatActivity {

    private static final String TAG ="CartActivity" ;
    private static final int CART_DETAIL=1;


    private TextView tviLogout,tviUser;
    private ListView lstCart;
    private Button btnCheckout;
    private List<Cart> lsCartEntitites;
    private CRUDOperations crudOperations;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //populate();
        init();
        loadData();
    }


    private void loadData() {
        crudOperations= new CRUDOperations(new MyDatabase(this));
        lsCartEntitites= crudOperations.getAllCart();
        cartAdapter= new CartAdapter(this,lsCartEntitites);
        lstCart.setAdapter(cartAdapter);

    }



    private void init() {
        tviLogout= (TextView)findViewById(R.id.tviLogout);
        tviUser= (TextView)findViewById(R.id.tviUser);
        lstCart= (ListView)(findViewById(R.id.lstCart));
        btnCheckout= (Button)(findViewById(R.id.btnCheckout));

        //user Info
        String username = PreferencesHelper.getUserSession(this);
        if(username!=null)
        {
            tviUser.setText("Bienvenido "+ StringUtils.firstCapitalize(username));
        }

        //events
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gotoNote(ACTION_ADD, null);
                //sendtoCart(ACTION_CART, null);
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
