package hu.bme.aut.vizivandor.ui.post;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.login.RegisterFragment;


public class NewPostActivity extends AppCompatActivity {

    private ImageButton image;
    private static final int GALLERY_REQUEST_CODE = 1;
    private Uri uri;
    private StorageReference storage;
    private EditText text_title;
    private EditText text_description;
    private Button post;

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;
    private StorageTask uploadImageTask;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_newpost);

        image = (ImageButton) findViewById(R.id.imageBtn);
        text_title = findViewById(R.id.textTitle);
        text_description = findViewById(R.id.textDesc);
        post = findViewById(R.id.postBtn);


        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference().child("VizivandorFirebase");


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("Jelenlegi felhasznalo: " + mCurrentUser);
        System.out.println(mCurrentUser.getUid());



        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent();
                galleryIntent.setType("image/*");
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    storage =  FirebaseStorage.getInstance().getReference("Images");

                    if(uploadImageTask != null && uploadImageTask.isInProgress()){
                        Toast.makeText(NewPostActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                    } else {
                        Fileuploader();
                    }

                    startActivity(new Intent(NewPostActivity.this, SeePostActivity.class));
                    NewPostActivity.this.finish();

                }


        });


    }

    private String getExtension(Uri uri){
        ContentResolver ct = getContentResolver();
        MimeTypeMap mtm = MimeTypeMap.getSingleton();
        return mtm.getExtensionFromMimeType(ct.getType(uri));
    }

    private void Fileuploader(){
        final StorageReference ref = storage.child(System.currentTimeMillis() + "." + getExtension(uri));
        uploadImageTask = ref.putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(NewPostActivity.this, "Image upploaded succesfully", Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                final String txtTitle = text_title.getText().toString();
                                final String txtDesc = text_description.getText().toString();
                                final String txtUsername = mCurrentUser.getEmail();

                                final DatabaseReference newPost = databaseRef.push();

                                newPost.child("title").setValue(txtTitle);
                                newPost.child("description").setValue(txtDesc);
                                newPost.child("username").setValue(txtUsername);
                                newPost.child("imageUrl").setValue(String.valueOf(uri));
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                    }
                });

    }

    @Override
    // image from gallery result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data!=null && data.getData()!=null){
            uri = data.getData();
            image.setImageURI(uri);
        }
    }

}
