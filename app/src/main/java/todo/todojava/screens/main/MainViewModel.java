package todo.todojava.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import todo.todojava.App;
import todo.todojava.model.Note;

public class MainViewModel extends ViewModel {
    private LiveData<List<Note>> noteLiveData= App.getInstance().getNoteDao().getAllLiveData();
    public LiveData<List<Note>> getNoteLiveData(){
        return noteLiveData;
    }
}
