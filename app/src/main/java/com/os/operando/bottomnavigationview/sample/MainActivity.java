package com.os.operando.bottomnavigationview.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.os.operando.bottomnavigationview.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.pager.setAdapter(new BottomNavigationPagerAdapter(getSupportFragmentManager()));
        binding.pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                MenuItem menuItem = binding.navigation.getMenu().findItem(BottomNavigationPage.forPosition(position).menuId);
                if (menuItem != null) {
                    menuItem.setChecked(true);// ダメだなー...選択されたって扱いではないので...
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.pager.setCurrentItem(BottomNavigationPage.forPager(item).pageIndex);
                return false;
            }
        });
    }

    enum BottomNavigationPage {
        EMAIL(R.id.nav_email, 0),
        CAMERA(R.id.nav_camera, 1),
        MAP(R.id.nav_map, 2);

        @IdRes
        private final int menuId;

        private final int pageIndex;

        BottomNavigationPage(int menuId, int pageIndex) {
            this.menuId = menuId;
            this.pageIndex = pageIndex;
        }

        public static BottomNavigationPage forPager(MenuItem menuItem) {
            for (BottomNavigationPage page : values()) {
                if (page.menuId == menuItem.getItemId()) {
                    return page;
                }
            }
            throw new AssertionError("no menu enum found for the position. you forgot to implement?");
        }

        public static BottomNavigationPage forPosition(int position) {
            for (BottomNavigationPage page : values()) {
                if (page.pageIndex == position) {
                    return page;
                }
            }
            throw new AssertionError("no menu enum found for the position. you forgot to implement?");
        }
    }
}