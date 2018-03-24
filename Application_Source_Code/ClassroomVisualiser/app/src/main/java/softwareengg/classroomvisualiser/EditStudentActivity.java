package softwareengg.classroomvisualiser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
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

    DBImgHelper imDb;
    Button button;
    private static final int CAM_REQUEST=1313;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        myDb = new DBHelper(this);
        imDb = new DBImgHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editCourse = (EditText)findViewById(R.id.editText_Course);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnviewUpdate= (Button)findViewById(R.id.button_update);

        // Locate the button in activity_main.xml
        button = (Button) findViewById(R.id.ImgButton);

        // Capture button clicks
        UpdateImage();

        UpdateData();
    }

    public void UpdateImage() {
        button.setOnClickListener(
                new EditStudentActivity.btnTakePhotoClicker()
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAM_REQUEST){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            boolean isInserted = imDb.updateImg(Integer.parseInt( editTextId.getText().toString() ), bitmap);
            if(isInserted == true)
                Toast.makeText(EditStudentActivity.this,"Image Inserted",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(EditStudentActivity.this,"Image not Inserted",Toast.LENGTH_LONG).show();
        }
    }

    class btnTakePhotoClicker implements  Button.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAM_REQUEST);
        }
    }

    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(Integer.parseInt( editTextId.getText().toString() ),
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
