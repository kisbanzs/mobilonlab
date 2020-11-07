package hu.bme.aut.vizivandor.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hu.bme.aut.vizivandor.R;

public class RegisterFragment extends Fragment {

    private EditText email;
    private EditText password;
    private Button register;
    private Button login;

    private FirebaseAuth auth;


    public View onCreateView(@NonNull LayoutInflater inflater,
                ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_register, container, false);

        email = root.findViewById(R.id.emailField);
        password = root.findViewById(R.id.passwordField);
        register = root.findViewById(R.id.registerBtn);
        login = root.findViewById(R.id.login);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(getActivity(), "Empty fields", Toast.LENGTH_SHORT).show();
                } else if(txt_password.length() < 6){
                    Toast.makeText(getActivity(), "Password too short", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txt_email, txt_password);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginFragment nextFrag = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.registerfragmentid, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return root;

    }

    private void registerUser(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getActivity(), "Registering user succesfuly", Toast.LENGTH_SHORT).show();
                        LoginFragment nextFrag = new LoginFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.registerfragmentid, nextFrag, "findThisFragment")
                                .addToBackStack(null)
                                .commit();
                    }
                    else {
                        Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
}
