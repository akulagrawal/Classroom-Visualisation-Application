package softwareengg.classroomvisualiser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editName,editSurname,editCourse ,editTextId;
    Button btnAddData;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        myDb = new DBHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editCourse = (EditText)findViewById(R.id.editText_Course);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnAddData = (Button)findViewById(R.id.button_add);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.ImgButton);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(AddStudentActivity.this,
                        CameraActivity.class);
                startActivity(myIntent);
            }
        });

        AddData();
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editCourse.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(AddStudentActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AddStudentActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
