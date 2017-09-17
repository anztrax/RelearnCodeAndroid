package com.example.andrew.relearncodeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.andrew.relearncodeandroid.data.DataManager;
import com.example.andrew.relearncodeandroid.data.NoteInfo;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

  private ArrayAdapter<NoteInfo> adapterNotes;

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_note_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.action_learn_style_activity){
      startLearnStyleActivity();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void startLearnStyleActivity(){
    Intent intent = new Intent(NoteListActivity.this,LearnStyleActivity.class);
    startActivity(intent);
  }

  @Override
  protected void onResume() {
    super.onResume();
    adapterNotes.notifyDataSetChanged();    //notify if data is added at ContentNote (change activity)
  }

  private void initializeDisplayContent() {
    final ListView listNoteView = (ListView)findViewById(R.id.list_notes);

    List<NoteInfo> noteInfoList = DataManager.getInstance().getNotes();
    adapterNotes = new ArrayAdapter<NoteInfo>(this, android.R.layout.simple_list_item_1, noteInfoList);

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
