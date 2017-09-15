package com.example.andrew.relearncodeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_content_note);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    Spinner spinnerCourses = (Spinner)findViewById(R.id.spinner_courses);

    //make a adapter for spinner ( populate data )
    List<CourseInfo> courseInfoList = DataManager.getInstance().getCourses();
    ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, courseInfoList);
    adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinnerCourses.setAdapter(adapterCourses);

    readDisplayStateValues();

    EditText titleNoteTitle = (EditText)findViewById(R.id.text_note_title);
    EditText titleNoteDescription = (EditText)findViewById(R.id.text_note_description);

    if(!isNewNote) {
      displayNote(spinnerCourses, titleNoteTitle, titleNoteDescription);
    }
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
