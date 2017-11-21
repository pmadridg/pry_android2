package com.isil.am2template.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.isil.am2template.R;
import com.isil.am2template.model.entity.NoteEntity;
import com.isil.am2template.storage.request.ApiClient;
import com.isil.am2template.storage.request.entity.NoteRaw;
import com.isil.am2template.storage.request.entity.NoteResponse;
import com.isil.am2template.view.listeners.OnNoteListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNoteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private EditText eteName;
    private EditText eteDesc;
    private EditText eteNote;
    private Button btnAddNote;

    private String name;
    private String desc;
    private String note;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnNoteListener mListener;

    // TODO: Rename and change types and number of parameters
    public static AddNoteFragment newInstance(String param1, String param2) {
        AddNoteFragment fragment = new AddNoteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnNoteListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        eteName=(EditText)getView().findViewById(R.id.eteName);
        eteDesc=(EditText)getView().findViewById(R.id.eteDesc);
        eteNote=(EditText)getView().findViewById(R.id.eteNote);
        btnAddNote=(Button)getView().findViewById(R.id.btnAddNote);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()){
                    //addNote();
                    //closeActivity();
                    addRestNote();
                }
            }
        });
    }

    private void addRestNote() {
        final NoteRaw noteRaw= new NoteRaw();
        noteRaw.setName(name);
        noteRaw.setDescription(desc);
        noteRaw.setUserId("59e0540d429d3f501d6493de");

        Call<NoteResponse> call= ApiClient.getMyApiClient().addNote(noteRaw);
        call.enqueue(new Callback<NoteResponse>() {
            @Override
            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {
                if(response!=null){
                    NoteResponse noteResponse=null;

                    if(response.isSuccessful()){
                        noteResponse= response.body();
                        if (noteResponse != null) {
                            savedNote(noteResponse.getData());
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<NoteResponse> call, Throwable t) {
            }
        });
    }

    private void savedNote(NoteEntity data) {
        closeActivity();
    }

    private boolean validateForm(){
        //ui
        clearForm();
        name= eteName.getText().toString().trim();
        desc= eteDesc.getText().toString().trim();
        note= eteNote.getText().toString().trim();

        if(name.isEmpty()){
            eteName.setError("Nombre inválido");
            return false;
        }
        if(desc.isEmpty()){
            eteDesc.setError("Descripción inválido");
            return false;
        }

        return true;
    }

    private void clearForm() {
        eteName.setError(null);
        eteDesc.setError(null);
    }

    private void addNote() {
        //bd
        NoteEntity noteEntity= new NoteEntity(name,desc,null);
        //mListener.getCrudOperations().addNote(noteEntity);
        //mListener.getCrudOperations().addNote(noteEntity, bdCallback);
    }

    /*private void bdCallback(){
        onSuccess(){

        }

        onError(){

        }
    }*/

    private void closeActivity(){
        //ui
        getActivity().finish();
    }
}
