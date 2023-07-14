package com.saimone.todolist_android_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoList: MutableList<Todo>
    private lateinit var rvTodoItems: RecyclerView
    private lateinit var etTodoTitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTodoItems = findViewById(R.id.rvTodoItems)
        etTodoTitle = findViewById(R.id.etTodoTitle)
        val btnAddTodo = findViewById<View>(R.id.btnAddTodo)
        val btnDeleteDoneTodos = findViewById<View>(R.id.btnDeleteDoneTodos)

        todoList = mutableListOf()
        todoAdapter = TodoAdapter(todoList)
        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val title = etTodoTitle.text.toString()
            if (title.isNotEmpty()) {
                val todoItem = Todo(title)
                todoList.add(todoItem)
                todoAdapter.notifyItemInserted(todoList.size - 1)
                etTodoTitle.text.clear()
            }
        }

        btnDeleteDoneTodos.setOnClickListener {
            val iterator = todoList.iterator()
            while (iterator.hasNext()) {
                val todoItem = iterator.next()
                if (todoItem.isChecked) {
                    val position = todoList.indexOf(todoItem)
                    iterator.remove()
                    todoAdapter.notifyItemRemoved(position)
                }
            }
        }
    }
}