package com.isil.am2template;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.isil.am2template.model.entity.NoteEntity;
import com.isil.am2template.storage.db.CRUDOperations;
import com.isil.am2template.storage.db.MyDatabase;
import com.isil.am2template.view.dialogs.MyDialogFragment;
import com.isil.am2template.view.dialogs.MyDialogListener;
import com.isil.am2template.view.fragments.AddNoteFragment;
import com.isil.am2template.view.fragments.DetailsFragment;
import com.isil.am2template.view.listeners.OnNoteListener;


public class NoteActivity extends AppCompatActivity implements OnNoteListener, MyDialogListener {

    public static final  int ADD_NOTE=100;
    public static final  int DETAIL_NOTE=101;
    public static final  int UPDATE_NOTE=102;

    private static final String TAG ="NoteActivity";

    private AddNoteFragment addNoteFragment= AddNoteFragment.newInstance(null,null);
    private DetailsFragment detailsFragment= DetailsFragment.newInstance(null,null);
    private int fragmentSelected= DETAIL_NOTE;
    private NoteEntity noteEntity;
    private NoteEntity currentNote;


    private CRUDOperations crudOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        validateExtras();

        crudOperations= new CRUDOperations(new MyDatabase(this));
        Bundle bundle= new Bundle();
        bundle.putSerializable("NOTE",noteEntity);
        changeFragment(fragmentSelected, bundle);
    }

    private void validateExtras() {
        if(getIntent().getExtras()!=null)
        {
            fragmentSelected= getIntent().getExtras().getInt("FRAGMENT",DETAIL_NOTE);
            noteEntity= (NoteEntity)getIntent().getExtras().getSerializable("NOTE");
        }
    }


    private  void changeFragment(int id,Bundle bundle)
    {
        Fragment fragment= null;
        switch (id)
        {
            case ADD_NOTE:
                fragment=addNoteFragment;
                break;

            case DETAIL_NOTE:
                fragment=detailsFragment;
                break;

            case UPDATE_NOTE:
                fragment=null;
                break;
        }

        if(fragment!=null)
        {
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    public CRUDOperations getCrudOperations() {
        return crudOperations;
    }

    @Override
    public void deleteNote(NoteEntity noteEntity) {

        currentNote= noteEntity;
        MyDialogFragment myDialogFragment =new MyDialogFragment();
        Bundle bundle= new Bundle();
        bundle.putString("TITLE","¿Deseas eliminar esta nota?");
        bundle.putInt("TYPE",100);

        myDialogFragment.setArguments(bundle);
        myDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void editNote(NoteEntity noteEntity) {
        //crudOperations.updateNote(noteEntity);
    }

    @Override
    public void onPositiveListener(Object object, int type) {
        Log.v(TAG, "dialog positive");
        if(currentNote!=null) {
            //crudOperations.deleteNote(currentNote);
            currentNote=null;
            finish();
        }
    }

    @Override
    public void onNegativeListener(Object object, int type) {
        Log.v(TAG, "dialog negative");
    }
}
