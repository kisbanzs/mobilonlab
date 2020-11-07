package hu.bme.aut.vizivandor.ui.post;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.FirebaseDatabase;

import hu.bme.aut.vizivandor.R;


public class NewPostActivity extends AppCompatActivity {

    private EditText text_title;
    private EditText text_description;
    private Button post;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_post_newpost);

        text_title = findViewById(R.id.textTitle);
        text_description = findViewById(R.id.textDesc);
        post = findViewById(R.id.postBtn);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtTitle = text_title.getText().toString();
                String txtDesc = text_description.getText().toString();

                if(txtTitle.isEmpty() || txtDesc.isEmpty()){
                    Toast.makeText(NewPostActivity.this, "Empty title or description", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("VizivandorFirebase").child("Title").setValue(txtTitle);
                    FirebaseDatabase.getInstance().getReference().child("VizivandorFirebase").child("Description").setValue(txtDesc);
                }
            }
        });

       /* HashMap<String, Object> map = new HashMap<>();
        map.put("Name", "Zsofi");
        map.put("Email", "zsofi@gmail.com");

        FirebaseDatabase.getInstance().getReference().child("ViziNewPost").child("MultipleValues").updateChildren(map);*/


    }
}
