package hu.bme.aut.vizivandor.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;

import hu.bme.aut.vizivandor.MainActivity;
import hu.bme.aut.vizivandor.R;

public class SinglePostActivity extends AppCompatActivity {

    //private ImageView singelImage;
    private TextView singleTitle, singleDesc, singleUser;
    //String post_key = null;
    private DatabaseReference mDatabase;
    //private Button deleteBtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_new_post_card_view);

        //singelImage = (ImageView)findViewById(R.id.singleImageview);
        singleTitle = (TextView)findViewById(R.id.post_title_txtview);
        singleDesc = (TextView)findViewById(R.id.post_desc_txtview);
        singleUser = (TextView)findViewById(R.id.post_user);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("VizivandorFirebase");
        //post_key = getIntent().getExtras().getString("PostID");
        //deleteBtn = (Button)findViewById(R.id.deleteBtn);
        mAuth = FirebaseAuth.getInstance();
       /* deleteBtn.setVisibility(View.INVISIBLE);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child(post_key).removeValue();

                Intent mainintent = new Intent(SinglePostActivity.this, MainActivity.class);
                startActivity(mainintent);
            }
        });*/


        mDatabase.child("VizivandorFirebase").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                VizivandorFirebase vf = dataSnapshot.getValue(VizivandorFirebase.class);

                String post_title = vf.getTitle(); // (String) dataSnapshot.child("title").getValue();
                String post_desc = vf.getDescription(); // (String) dataSnapshot.child("description").getValue();
                //String post_image = (String) dataSnapshot.child("imageUrl").getValue();
                String post_uid = vf.getUsername(); // (String) dataSnapshot.child("username").getValue();

                singleTitle.setText(post_title);
                singleDesc.setText(post_desc);
                singleUser.setText(post_uid);

                System.out.println("Cime: " + post_title);
                System.out.println("Leirasa: " + post_desc);
                System.out.println("User id ja: " + post_uid);
                //Picasso.with(SinglePostActivity.this).load(post_image).into(singelImage);
                /*if (mAuth.getCurrentUser().getUid().equals(post_uid)){

                    deleteBtn.setVisibility(View.VISIBLE);
                }*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}