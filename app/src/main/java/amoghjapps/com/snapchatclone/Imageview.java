package amoghjapps.com.snapchatclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Imageview extends AppCompatActivity {

    ImageView imageview;
    EditText caption;
    TextView loginid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        imageview=findViewById(R.id.img);
        String imageLink= getIntent().getStringExtra("uri");
        String captiontext=getIntent().getStringExtra("caption");
        String loginidtext=getIntent().getStringExtra("login");
        Picasso.get().load(imageLink).into(imageview);
        caption=findViewById(R.id.caption);
        loginid=findViewById(R.id.loginid);
        loginid.setText(loginidtext);
        caption.setText(captiontext);


    }
}
