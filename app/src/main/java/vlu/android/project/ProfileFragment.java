package vlu.android.project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class ProfileFragment extends Fragment {
    private LinearLayout profileInfoLayout, settingInfoLayout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileInfoLayout = view.findViewById(R.id.profile_info_layout);
        settingInfoLayout = view.findViewById(R.id.settings_info_layout);

        // Initially hide the profile info layout
        profileInfoLayout.setVisibility(View.GONE);
        settingInfoLayout.setVisibility(View.GONE);

        // Add any button click listeners to show the profile info
        Button myProfileButton = view.findViewById(R.id.tab_my_profile);
        myProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileInfoLayout.setVisibility(View.VISIBLE);
                settingInfoLayout.setVisibility(View.GONE);//hide settings
            }
        });
        Button tab_settings = view.findViewById(R.id.tab_settings);
        tab_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingInfoLayout.setVisibility(View.VISIBLE);
                profileInfoLayout.setVisibility(View.GONE);//hide profile
            }
        });
        Button logOutButton = view.findViewById(R.id.tab_log_out);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogOutConfirmationDialog();
            }
        });

        return view;
    }
    private void showLogOutConfirmationDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Log Out")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Redirect to main layout (replace this with your main layout activity)
                        getActivity().finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
