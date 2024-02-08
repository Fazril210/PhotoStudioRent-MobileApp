package com.example.thirtyonestudio;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FotoStudioFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FotoStudioFragment() {
        // Konstruktor kosong yang diperlukan
    }

    public static FotoStudioFragment newInstance(String param1, String param2) {
        FotoStudioFragment fragment = new FotoStudioFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate layout untuk fragment ini
        View view = inflater.inflate(R.layout.fragment_foto_studio, container, false);

        // PREWEDDING SESSION
        ImageView Prewed = view.findViewById(R.id.prewedding);
        Prewed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prewedIntent = new Intent(getActivity(), PreweddingSession.class);
                startActivity(prewedIntent);
            }
        });

        // MATERNITY SESSION
        ImageView Maternity = view.findViewById(R.id.maternity);
        Maternity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maternityIntent = new Intent(getActivity(), MaternitySession.class);
                startActivity(maternityIntent);
            }
        });

        // GROUP SESSION
        ImageView preweddingImageView = view.findViewById(R.id.group);
        preweddingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent groupIntent = new Intent(getActivity(), TipeSesiFoto.class);
                startActivity(groupIntent);
            }
        });

        // FAMILY SESSION
        ImageView Family = view.findViewById(R.id.family);
        Family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyIntent = new Intent(getActivity(), FamilySession.class);
                startActivity(familyIntent);
            }
        });

        // GRADUATE SESSION
        ImageView Graduate = view.findViewById(R.id.graduate);
        Graduate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graduateIntent = new Intent(getActivity(), GraduateSession.class);
                startActivity(graduateIntent);
            }
        });

        // COUPLE SESSION
        ImageView Couple = view.findViewById(R.id.couple);
        Couple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent coupleIntent = new Intent(getActivity(), CoupleSession.class);
                startActivity(coupleIntent);
            }
        });

        return view;
    }
}
