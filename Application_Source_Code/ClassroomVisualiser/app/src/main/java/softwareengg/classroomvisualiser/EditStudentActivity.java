package softwareengg.classroomvisualiser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditStudentActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editName,editSurname,editCourse ,editTextId;
    Button btnviewUpdate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        myDb = new DBHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editCourse = (EditText)findViewById(R.id.editText_Course);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnviewUpdate= (Button)findViewById(R.id.button_update);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.ImgButton);

        // Capture button clicks
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(EditStudentActivity.this,
                        CameraActivity.class);
                startActivity(myIntent);
            }
        });

        UpdateData();
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),editCourse.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(EditStudentActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(EditStudentActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
