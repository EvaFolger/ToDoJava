package todo.todojava.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import todo.todojava.App;
import todo.todojava.R;
import todo.todojava.model.Note;

public class NoteDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_NOTE= "NoteDetailsActivity. EXTRA_NOTE";
    Note note;
    private EditText editText;
    private EditText editNote;
    public static void  start(Activity caller, Note note){
        Intent intent = new Intent(caller, NoteDetailsActivity.class);
        if (note!=null){
            intent.putExtra(EXTRA_NOTE,note);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.note_details_title));

        editText =findViewById(R.id.text);
        editNote=findViewById(R.id.note);
        if (getIntent().hasExtra(EXTRA_NOTE)) {
            note = getIntent().getParcelableExtra(EXTRA_NOTE);
            editText.setText(note.text);
            editNote.setText(note.noteT);
        } else {
            note = new Note();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                break;
            case R.id.action_save:
                if(editText.getText().length()>0){
                   note.text=editText.getText().toString();
                   note.done=false;
                   note.timestamp=System.currentTimeMillis();
                   note.noteT=editNote.getText().toString();
                   if(getIntent().hasExtra(EXTRA_NOTE)){
                       App.getInstance().getNoteDao().update(note);
                   }else {App.getInstance().getNoteDao().insert(note);
                }finish();}
                   break;
        }

        return super.onOptionsItemSelected(item);
    }
}
