/*package hu.bme.aut.vizivandor.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import hu.bme.aut.vizivandor.R;
import hu.bme.aut.vizivandor.ui.home.HomeFragment;

public class StartLoginRegisterFragment extends Fragment {

    private Button register;
    private Button login;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_start_login_register, container, false);

        register = root.findViewById(R.id.btnRegister);
        login = root.findViewById(R.id.btnLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), RegisterFragment.class));
                //getActivity().finish();
                HomeFragment nextFrag= new HomeFragment();                              //R.id.startregister
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(root.getId(), nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return root;
    }
}
*/