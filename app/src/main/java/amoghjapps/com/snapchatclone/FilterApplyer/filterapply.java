package amoghjapps.com.snapchatclone.FilterApplyer;

import android.graphics.Bitmap;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import amoghjapps.com.snapchatclone.R;

public class filterapply extends AppCompatActivity {
    RelativeLayout rel;
    ImageView image,filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterapply);
        image=findViewById(R.id.image);
        filter=findViewById(R.id.filter);

    }
    public static Bitmap getScreenShot(View view){
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap=Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;

    }
    public void applyFilter(String filterid){
        //filter id
        image.setImageResource(R.drawable.filter);


    }
    public void saveImage(){
        View content=findViewById(R.id.relativeLayout);
        Bitmap imagesave=getScreenShot(content);
        String currentImage="image"+ System.currentTimeMillis()+".png";
    }
}
