package com.world.protester.ui.mail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.world.protester.R;
import com.world.protester.tools.ToastManager;

public class MailFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mail, container, false);

        Button btnSend = root.findViewById(R.id.btnMail);
        btnSend.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"dervadim@outlook.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, this.getString(R.string.fragment_mail_subject_of_email));
            try {
                startActivity(Intent.createChooser(i, this.getString(R.string.fragment_mail_title_sending)));
            } catch (android.content.ActivityNotFoundException ex) {
                ToastManager.getInstance().showToastById(R.string.fragment_mail_clien_is_not_installed,this.getContext());
            }
        });

        return root;
    }

}
