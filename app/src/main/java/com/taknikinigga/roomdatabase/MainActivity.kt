package com.taknikinigga.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.taknikinigga.roomdatabase.data.Notes
import com.taknikinigga.roomdatabase.data.NotesDatabase
import com.taknikinigga.roomdatabase.data.NotesViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), onClickItem {

    lateinit var viewmodel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = Adapter(this)
        mainRecyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        mainRecyclerView.addItemDecoration(divider)

        viewmodel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)
        viewmodel.allNotes.observe(this, Observer { list ->

            list?.let {
                adapter.updateList(list)
            }
        })

        btnAdd.setOnClickListener {
            var itemText = edtAddText.text.toString()
            if (!TextUtils.isEmpty(itemText)) {
                viewmodel.insert(Notes(itemText))
            } else {
                return@setOnClickListener
            }
        }
    }

    override fun onItemClick(notes: Notes) {
        viewmodel.delete(notes)
    }
}