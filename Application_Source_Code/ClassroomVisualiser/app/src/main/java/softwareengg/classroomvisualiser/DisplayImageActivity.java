package softwareengg.classroomvisualiser;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DisplayImageActivity extends AppCompatActivity {

    DBImgHelper imDb;
    EditText editTextId;
    Button button;
    ImageView imgTakenPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);
        imDb = new DBImgHelper(this);

        editTextId = (EditText)findViewById(R.id.editText_id);
        imgTakenPic = (ImageView)findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.Button);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bitmap bitmap = imDb.getImg(Integer.parseInt( editTextId.getText().toString() ));
                imgTakenPic.setImageBitmap(bitmap);
            }
        });
    }

}
