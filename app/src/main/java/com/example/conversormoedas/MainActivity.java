package com.example.conversormoedas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.conversormoedas.fragment.ConversorFragment;
import com.example.conversormoedas.fragment.MoedasFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicia componentes de tela
        iniciaComponentes();

        // Configura SmartTabLayout
        configTabs();

    }

    // Configura SmartTabLayout
    private void configTabs(){
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                FragmentPagerItems.with(this)
                        .add("CONVERTER", ConversorFragment.class)
                        .add("MOEDAS", MoedasFragment.class)
                        .create()
        );

        //Configurar adapter dentro do ViewPager
        viewPager.setAdapter(adapter);

        //Configurar viewPager dentro do smartTabLayout
        smartTabLayout.setViewPager(viewPager);
    }

    // Inicia componentes de tela
    private void iniciaComponentes(){
        viewPager = findViewById(R.id.viewPager);
        smartTabLayout = findViewById(R.id.viewPagerTab);
    }

}