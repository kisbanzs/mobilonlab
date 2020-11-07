package hu.bme.aut.vizivandor.ui.post;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import hu.bme.aut.vizivandor.R;


public class MainPostFragment extends Fragment {

    private ImageButton btnNew;
    private ImageButton btnMessages;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_post_homescreen, container, false);


        btnNew = root.findViewById(R.id.btnNewPost);
        btnMessages = root.findViewById(R.id.btnSeePosts);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(getActivity(), NewPostActivity.class);
                startActivity(intent);

                /*NewPostFragment nextFrag = new NewPostFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.posthomescreenlayout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();*/
            }
        });

        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(getActivity(), SeePostActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }





}
