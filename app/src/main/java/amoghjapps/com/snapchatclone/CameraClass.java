package amoghjapps.com.snapchatclone;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.gms.vision.CameraSource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraClass extends Activity {
    private Camera camera;
    private CamPreview preview;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        camera=getCameraInstance();
        preview=new CamPreview(this,camera);
        FrameLayout layout=findViewById(R.id.camera_preview);
        layout.addView(preview);
        button=findViewById(R.id.button_capture);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.takePicture(null,null,pictureCallback);
            }
        });



    }
    private Camera.PictureCallback pictureCallback=new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera)  {
            File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if(file.exists()==false&&file.mkdirs()==false){
                Log.e("test","cant create directory");
                return;
            }


            SimpleDateFormat dateFormat=new SimpleDateFormat("yyymmddhhmmss");
            String date= dateFormat.format(new Date());
            String filename=file.getPath()+File.separator+"img"+date+".jpg";
            File pic=new File(filename);
            try {
                FileOutputStream fos=new FileOutputStream(pic);
                fos.write(data);
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }

    };

    public static android.hardware.Camera getCameraInstance(){
        Camera cam=null;
        try{
        cam= android.hardware.Camera.open();
        }catch (Exception e) {

        }
        return cam;

    }
    public class CamPreview extends SurfaceView implements SurfaceHolder.Callback{
        public SurfaceHolder surfaceHolder;
        public  Camera mycamera;

        public CamPreview(Context context, Camera mycamera) {
            super(context);
            this.mycamera = mycamera;
            surfaceHolder=getHolder();
            surfaceHolder.addCallback(this);
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                mycamera.setPreviewDisplay(surfaceHolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mycamera.startPreview();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    }


}

