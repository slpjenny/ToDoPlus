package com.mytest.todoplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Calendar_Fragment calendar_fragment;
    MainFragment main_fragment;
    Routine_Fragment routine_fragment;

    ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.viewpager);
        //캐싱을 해놓을 프래그먼트 개수
        pager.setOffscreenPageLimit(3);

        //getSupportFragmentManager로 프래그먼트 참조가능
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());

        //뷰 페이저의 1번째 페이지='달력'
        calendar_fragment= new Calendar_Fragment();
        adapter.addItem(calendar_fragment);

        //뷰 페이저의 2번째 페이지='투두'(메인)
        main_fragment = new MainFragment();
        adapter.addItem(main_fragment);

        //뷰 페이저의 3번째 페이지='루틴'
        routine_fragment = new Routine_Fragment();
        adapter.addItem(routine_fragment);

        pager.setAdapter(adapter);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab1:
                        pager.setCurrentItem(0);
                        return true;

                    case R.id.tab2:
                        pager.setCurrentItem(1);
                        return true;

                    case R.id.tab3:
                        pager.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        });

//        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                bottomNavigationView.getMenu().getItem(position).isChecked();
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    //어댑터 안에서 각각의 아이템(프라그먼트 페이지들)을 데이터로서 관리한다
    class MainPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }


}