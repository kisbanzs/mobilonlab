package hu.bme.aut.vizivandor.ui.post;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.login.RegisterFragment;


public class NewPostActivity extends AppCompatActivity {

    private ImageButton image;
    private static final int GALLERY_REQUEST_CODE = 2;
    private Uri uri = null;
    private StorageReference storage;
    private EditText text_title;
    private EditText text_description;
    private Button post;

    private FirebaseDatabase database;
    private DatabaseReference databaseRef;
    private DatabaseReference mDatabaseUsers;
    private FirebaseUser mCurrentUser;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_newpost);

        image = (ImageButton) findViewById(R.id.imageBtn);
        text_title = findViewById(R.id.textTitle);
        text_description = findViewById(R.id.textDesc);
        post = findViewById(R.id.postBtn);

        storage = FirebaseStorage.getInstance().getReference();
        database = FirebaseDatabase.getInstance();
        databaseRef = database.getReference().child("VizivandorFirebase");


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        System.out.println("Jelenlegi felhasznalo: " + mCurrentUser);
        System.out.println(mCurrentUser.getUid());
        //mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child("username").child(mCurrentUser.getUid());


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });

        /*final Uri downloadUrl = taskSnapshot.getDownloadUrl();
        Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();*/

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String txtTitle = text_title.getText().toString();
                final String txtDesc = text_description.getText().toString();
                final String txtUsername = mCurrentUser.getEmail();


                    final DatabaseReference newPost = databaseRef.push();


                    String key = database.getReference("VizivandorFirebase").push().getKey();
                    newPost.child("title").setValue(txtTitle);
                    newPost.child("description").setValue(txtDesc);
                    newPost.child("username").setValue(txtUsername);

                    startActivity(new Intent(NewPostActivity.this, SeePostActivity.class));
                    NewPostActivity.this.finish();


                }

                /* if(txtTitle.isEmpty() || txtDesc.isEmpty()){
                    Toast.makeText(NewPostActivity.this, "Empty title or description", Toast.LENGTH_SHORT).show();
                } else {

                    StorageReference filepath = storage.child("imageUrl").child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            @SuppressWarnings("VisibleForTests")
                            //getting the post image download url
                            final Uri downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().getResult();
                            Toast.makeText(getApplicationContext(), "Succesfully Uploaded", Toast.LENGTH_SHORT).show();
                            final DatabaseReference newPost = databaseRef.push();


                           /* String key = database.getReference("VizivandorFirebase").push().getKey();
                            newPost.child(key).child("title").setValue(txtTitle);
                            newPost.child(key).child("description").setValue(txtDesc);
                            //newPost.child(key).child("imageUrl").setValue(downloadUrl.toString());


                        }
                    });*/


        });


    }


    @Override
    // image from gallery result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK){
            uri = data.getData();
            image.setImageURI(uri);
        }
    }

}
