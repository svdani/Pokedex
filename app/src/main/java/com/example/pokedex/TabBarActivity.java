package com.example.pokedex;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabBarActivity extends AppCompatActivity {

    private static final int[] TAB_ICONS = {
            R.drawable.ic_tab_pokedex,
            R.drawable.ic_tab_trainer,
            R.drawable.ic_tab_shop
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tab_bar);


        // Obtener TabLayout y ViewPager desde el layout
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);

        // Configurar el adaptador de fragments y el ViewPager
        viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        // Conectar el TabLayout con el ViewPager y configurar los íconos
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setIcon(TAB_ICONS[position])
        ).attach();
    }

    private static class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(AppCompatActivity activity) {
            super(activity);
        }

        @Override
        public Fragment createFragment(int position) {
            // Devuelve el fragment correspondiente a la posición
            switch (position) {
                case 0:
                    return new PokedexFragment();
                case 1:
                    return new TrainerFragment();
                case 2:
                    return new ShopFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            // Devuelve el número total de fragments
            return 3;
        }
    }

    public TrainerFragment getTrainerFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return (TrainerFragment) fragmentManager.findFragmentByTag("f1");
    }
}
