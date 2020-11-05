package hu.bme.aut.vizivandor.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.home.HomeFragment;

public class LoginFragment extends Fragment {

    private EditText email;
    private EditText password;
    private Button login;
    private Button signup;

    private FirebaseAuth auth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_login, container, false);

        email = root.findViewById(R.id.login_email);
        password = root.findViewById(R.id.login_password);
        login = root.findViewById(R.id.loginBtn);
        signup = root.findViewById(R.id.signup);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                loginUser(txt_email, txt_password);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment nextFrag = new RegisterFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.loginlayout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });


        return root;
    }

    private void loginUser(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getActivity(), "Login Succesful", Toast.LENGTH_SHORT).show();
                LogoutFragment nextFrag = new LogoutFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.loginlayout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }

        });
    }

}
