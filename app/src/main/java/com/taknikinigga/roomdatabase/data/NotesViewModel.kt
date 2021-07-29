package com.taknikinigga.roomdatabase.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    var repository: NotesRepository

    val allNotes : LiveData<List<Notes>>
    init {
        val dao = NotesDatabase.getDatabase(application).getNoteDaao()
        repository = NotesRepository(dao)
        allNotes =repository.allNotes
    }

    fun delete(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }

    fun insert(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }

}