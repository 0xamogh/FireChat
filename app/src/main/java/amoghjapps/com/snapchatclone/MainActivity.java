package amoghjapps.com.snapchatclone;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore database;
    FirebaseStorage storage;
    List<ModelImage> modelImageList;
    SpotsDialog dialog;
    public StorageReference mStorageRef;
    Uri downloadlink;
    public String idUpdate="";//id of item to be updated;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelImageList=new ArrayList<>();
        database=FirebaseFirestore.getInstance();
        storage=FirebaseStorage.getInstance();
       mStorageRef = storage.getReference();
        loadPicList();
    }
    public Uri uploadFile(String path){
        Uri uri=Uri.fromFile(new File(path));
        StorageReference storageReference = mStorageRef.child(path);
        storageReference.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        View view=new View(getApplicationContext());
                         downloadlink=taskSnapshot.getDownloadUrl();
                        Snackbar.make(view,"Image uploaded successfully",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        View view=new View(getApplicationContext());
                        Snackbar.make(view,"Image upload error",Snackbar.LENGTH_LONG).show();
                    }
                });
        return downloadlink;

    }
    public void loadPicList(){
        dialog.show();
        database.collection("SnapchatClone2")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot docSnap:task.getResult()){
                            ModelImage post=new ModelImage(docSnap.getString("id"),
                                                           docSnap.getString("caption"),
                                                           docSnap.getString("name"),
                                                           Uri.parse(docSnap.getString("imageLink")));
                            modelImageList.add(post);
                        }
                        //adapter= initialize
                        //set modelImageList to adapter

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(new View(getApplicationContext()),"Unable to load feed",Snackbar.LENGTH_LONG).show();
                    }
                });

        dialog.dismiss();
    }



}
