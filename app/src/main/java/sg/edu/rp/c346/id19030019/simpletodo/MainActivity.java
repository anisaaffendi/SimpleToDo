package sg.edu.rp.c346.id19030019.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTask;
    Button btnAdd,btnClear,btnDelete;
    ListView lvTask;
    Spinner spn;
    ArrayList<String> alTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask=findViewById(R.id.editText);
        btnAdd=findViewById(R.id.btnAdd);
        btnClear=findViewById(R.id.btnClear);
        btnDelete=findViewById(R.id.btnDelete);
        spn=findViewById(R.id.spinner);
        lvTask=findViewById(R.id.listViewTask);

        alTask=new ArrayList<String>();

        final ArrayAdapter<String> aaTask = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task=etTask.getText().toString();
                alTask.add(task);
                aaTask.notifyDataSetChanged();
                etTask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task=etTask.getText().toString();
                alTask.clear();
                aaTask.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(alTask.size()==0){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    int pos= Integer.parseInt(etTask.getText().toString());
                    if (alTask.size()<=pos){
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    }else{
                        alTask.remove(pos);
                        aaTask.notifyDataSetChanged();
                        etTask.setText("");
                    }
                }
            }
        });

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etTask.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        etTask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
