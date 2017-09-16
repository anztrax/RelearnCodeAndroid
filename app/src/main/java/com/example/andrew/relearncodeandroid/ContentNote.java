package com.example.andrew.relearncodeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.andrew.relearncodeandroid.data.CourseInfo;
import com.example.andrew.relearncodeandroid.data.DataManager;
import com.example.andrew.relearncodeandroid.data.NoteInfo;

import java.util.List;

public class ContentNote extends AppCompatActivity {
  public static final String NOTE_POSITION = "com.example.andrew.relearncodeandroid.NOTE_POSITION";
  public static final int POSITION_NOT_SET = -1;
  private NoteInfo noteInfo;
  private boolean isNewNote;
  private Spinner spinnerCourses;
  private EditText titleNoteTitle;
  private EditText titleNoteDescription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content_note);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    spinnerCourses = (Spinner)findViewById(R.id.spinner_courses);

    //make a adapter for spinner ( populate data )
    List<CourseInfo> courseInfoList = DataManager.getInstance().getCourses();
    ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, courseInfoList);
    adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerCourses.setAdapter(adapterCourses);

    readDisplayStateValues();

    titleNoteTitle = (EditText)findViewById(R.id.text_note_title);
    titleNoteDescription = (EditText)findViewById(R.id.text_note_description);

    if(!isNewNote) {
      displayNote(spinnerCourses, titleNoteTitle, titleNoteDescription);
    }
  }

  //set the menubar
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_note, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == R.id.action_send_mail){
      sendMail();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void sendMail(){
    CourseInfo courseInfo = (CourseInfo)spinnerCourses.getSelectedItem();
    String subject = titleNoteTitle.getText().toString();
    String text = "Check what I learned : " + titleNoteDescription.getText().toString();

    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("message/rfc2822");
    intent.putExtra(Intent.EXTRA_SUBJECT, subject);
    intent.putExtra(Intent.EXTRA_TEXT, text);
    startActivity(intent);
  }


  private void displayNote(Spinner spinnerCourses, EditText titleNoteTitle, EditText titleNoteDescription) {
    List<CourseInfo> courseInfoList = DataManager.getInstance().getCourses();
    int courseIndex = courseInfoList.indexOf(noteInfo.getCourse());

    spinnerCourses.setSelection(courseIndex);
    titleNoteTitle.setText(noteInfo.getTitle());
    titleNoteDescription.setText(noteInfo.getText());
  }

  private void readDisplayStateValues() {
    Intent intent = getIntent();
    int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
    isNewNote = (position == POSITION_NOT_SET);
    if(!isNewNote){
      noteInfo = DataManager.getInstance().getNotes().get(position);
    }
  }
}
