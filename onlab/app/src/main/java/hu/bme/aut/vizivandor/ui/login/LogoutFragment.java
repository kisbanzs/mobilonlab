package hu.bme.aut.vizivandor.ui.login;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import hu.bme.aut.vizivandor.R;

public class LogoutFragment extends Fragment {

    private Button logout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_logout, container, false);

        logout = root.findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Logged out", Toast.LENGTH_SHORT).show();
                LoginFragment nextFrag = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.logoutid, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
                getActivity().finish();
            }
        });

        return root;

    }
}
