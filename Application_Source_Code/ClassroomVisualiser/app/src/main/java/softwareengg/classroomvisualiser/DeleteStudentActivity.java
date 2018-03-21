package softwareengg.classroomvisualiser;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteStudentActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editTextId;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);
        myDb = new DBHelper(this);

        editTextId = (EditText)findViewById(R.id.editText_id);
        btnDelete= (Button)findViewById(R.id.button_delete);
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DeleteStudentActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DeleteStudentActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
