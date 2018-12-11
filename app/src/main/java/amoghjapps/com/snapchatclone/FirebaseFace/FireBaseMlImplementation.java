package amoghjapps.com.snapchatclone.FirebaseFace;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.face.FirebaseVisionFace;
import com.google.firebase.ml.vision.face.FirebaseVisionFaceDetector;

import java.util.List;

import amoghjapps.com.snapchatclone.CameraClass;

public class FireBaseMlImplementation {
   boolean smiling=false;
    FaceAnalysisHelper faceEdit;
    CameraClass camfeed;
    FirebaseVisionImage image=FirebaseVisionImage.fromBitmap(((BitmapDrawable)camfeed.camerafeed.getDrawable()).getBitmap());
    FirebaseVisionFaceDetector detector= FirebaseVision.getInstance()
            .getVisionFaceDetector();
    Task<List<FirebaseVisionFace>> result=
            detector.detectInImage(image)
            .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionFace>>() {
                @Override
                public void onSuccess(List<FirebaseVisionFace> firebaseVisionFaces) {
                    //TaskCompleted
                    for(FirebaseVisionFace face:firebaseVisionFaces){
                        Rect faceboundary=face.getBoundingBox();
                        if(face.getSmilingProbability()>.66){
                            smiling=true;
                        }
                        face.getLandmark(0);



                    }
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });

    public Bitmap addFaceBoundingBox(Bitmap bitmap,Rect boundingbox){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(17);
        Bitmap bit=bitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas=new Canvas(bit);
        canvas.drawRect(boundingbox,paint);

            return bit;

    }
    //public void drawTextonBitmap(boolean smile,)
}
