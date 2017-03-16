package com.example.acer.mysqlapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        final EditText newTodoListText = (EditText) findViewById(R.id.Edit_text);
        final Button newTodoListButton = (Button) findViewById(R.id.btnAddNew);
        newTodoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList  todoList = new TodoList();
                todoList.setTaskname(String.valueOf(newTodoListText.getText()));
                TodoListDAO toListDAO = new TodoListDAO(getApplicationContext());
                toListDAO.open();
                toListDAO.add(todoList);
                toListDAO.close();
                finish();
            }
        });
    }
}
