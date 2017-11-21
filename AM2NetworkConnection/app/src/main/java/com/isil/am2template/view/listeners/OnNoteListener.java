package com.isil.am2template.view.listeners;


import com.isil.am2template.model.entity.NoteEntity;
import com.isil.am2template.storage.db.CRUDOperations;

/**
 * Created by emedinaa on 15/09/15.
 */
public interface OnNoteListener {

     CRUDOperations getCrudOperations();
     void deleteNote(NoteEntity noteEntity);
     void editNote(NoteEntity noteEntity);
}
