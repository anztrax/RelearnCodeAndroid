package com.example.andrew.relearncodeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andrew.relearncodeandroid.data.DataManager;
import com.example.andrew.relearncodeandroid.data.NoteInfo;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_note_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(NoteListActivity.this,ContentNote.class));
      }
    });
    initializeDisplayContent();
  }

  private void initializeDisplayContent() {
    final ListView listNoteView = (ListView)findViewById(R.id.list_notes);

    List<NoteInfo> noteInfoList = DataManager.getInstance().getNotes();
    ArrayAdapter<NoteInfo> adapterNotes = new ArrayAdapter<NoteInfo>(this, android.R.layout.simple_list_item_1, noteInfoList);

    listNoteView.setAdapter(adapterNotes);
    listNoteView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(NoteListActivity.this,ContentNote.class);

//        NoteInfo note = (NoteInfo)listNoteView.getItemAtPosition(i);
        intent.putExtra(ContentNote.NOTE_POSITION, i);
        startActivity(intent);
      }
    });
  }
}