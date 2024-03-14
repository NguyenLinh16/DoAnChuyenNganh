package com.example.learnenglish.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnenglish.R;
import com.example.learnenglish.activity.CacThiTrongTAActivity;
import com.example.learnenglish.activity.LuyenNgheActivity;
import com.example.learnenglish.activity.NguPhapActivity;
import com.example.learnenglish.activity.TestDoneActivity;
import com.example.learnenglish.activity.VocabularyItemActivity;


public class HomePageFragment extends Fragment {

    CardView cardViewHocTuVung, cardViewTracNghiem, cardViewNguPhap, cardViewLuyenNghe,cardViewCacThi,cardViewXepHang;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page,container,false);
        cardViewHocTuVung =view.findViewById(R.id.cardViewHocTuVung);
        cardViewCacThi=view.findViewById(R.id.cardViewCacThi);
        cardViewTracNghiem= view.findViewById(R.id.cardViewTracNghiem);
        cardViewNguPhap = view.findViewById(R.id.cardViewSapXepCau);
        cardViewLuyenNghe = view.findViewById(R.id.cardViewLuyenNghe);
        cardViewXepHang = view.findViewById(R.id.cardViewXepHang);


        cardViewHocTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một FragmentTransaction để thêm hoặc thay thế Fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                VocabularyFragment vocabularyFragment = new VocabularyFragment();
                ((FragmentTransaction) transaction).replace(R.id.fragment_container, vocabularyFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardViewTracNghiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một FragmentTransaction để thêm hoặc thay thế Fragment
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                TestFragment testFragment = new TestFragment();
                transaction.replace(R.id.fragment_container, testFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        cardViewCacThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CacThiTrongTAActivity.class);
                startActivity(intent);
            }
        });

        cardViewLuyenNghe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LuyenNgheActivity.class);
                startActivity(intent);
            }
        });

        cardViewNguPhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NguPhapActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}