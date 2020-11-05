package hu.bme.aut.vizivandor.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import hu.bme.aut.vizivandor.R;

public class MainLoginRegisterActivity extends AppCompatActivity {

    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_register);

        logout = findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainLoginRegisterActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainLoginRegisterActivity.this, LoginActivity.class)); //fragmentbe vissza nem tudtam menni, de ugysem itt lesz
            }
        });

    }
}
