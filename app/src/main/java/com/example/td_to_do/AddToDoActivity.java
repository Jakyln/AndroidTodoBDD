package com.example.td_to_do;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.td_to_do.dao.TodoDAO;
import com.example.td_to_do.pojos.Todo;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddToDoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnAdd;
    private Button btnCancel;
    private EditText editTextToDo;
    private Spinner spUrgency;
    private List<Todo> todos = new ArrayList<Todo>();
    private final String TAG = "ToDoActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);
        editTextToDo  = (EditText)findViewById(R.id.editTextToDo);
        spUrgency = findViewById(R.id.spUrgency);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.urgencies,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spUrgency.setAdapter(adapter);
        spUrgency.setOnItemSelectedListener(this);

        /*String[] plants = new String[]{
                "Low urgency",
                "Normal urgency",
                "High urgency"
        };

        final List<String>urgencyList = new ArrayList<>(Arrays.asList(plants));*/

        // Initializing an ArrayAdapter
        /*final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.spinner_item,urgencyList);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spUrgency.setAdapter(spinnerArrayAdapter);*/


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String textgdd = editTextToDo.getEditText().ToString();

                if(editTextToDo.getText().toString()!=""){
                    if(editTextToDo.getText().toString().length()>3){
                        //on check si user a rien ecrit
                        String testName = editTextToDo.getText().toString();
                        //todos.add(new Todo(testName, "testUrgence1"));
                        String urgencyItem = spUrgency.getSelectedItem().toString();


                        TodoDAO todoDAO = new TodoDAO(getApplicationContext()); //DAO base de donnée


                        Todo todo = new Todo (testName, urgencyItem);
                        todoDAO.add(todo); // DAO base de donnée

                        todos.add(todo);
                        //Log.d(TAG,"Todo added - Name = " + toDo1.getName() +" - Urgency = " + toDo1.getUrgency());
                        Log.d(TAG,"Todo added - Name = ");

                        //String toDo = todo.getName()+" // "+todo.getUrgency(); //Je met le nom et l'urgence dans un même string, puis je l'envoie
                        Intent intent=new Intent();
                        intent.putExtra("To Do : ",todo);

                        setResult(RESULT_OK,intent);
                        finish();
                    }
                    else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Nom trop petit", Toast.LENGTH_SHORT);
                        toast.show();
                    }


                }

                else{
                        //on ajoute le input dans name de todos et on met par défaut le urgency à low
                }

            }


        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_CANCELED, resultIntent);
                finish();


            }


        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();

        Log.d(TAG,"onStart() called");
    }

    @Override
    protected void onResume(){
        super.onResume();

        Log.d(TAG,"onResume() called");
    }

    @Override
    protected void onPause(){
        super.onPause();

        Log.d(TAG,"onPause() called");
    }
    @Override
    protected void onStop(){
        super.onStop();

        Log.d(TAG,"onStop() called");
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        Log.d(TAG,"onRestart() called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.d(TAG,"onDestroy() called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(TAG, "onSaveInstanceState() called");

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String urgency = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),urgency,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}