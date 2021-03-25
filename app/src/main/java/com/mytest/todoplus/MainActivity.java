package com.mytest.todoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager pager = findViewById(R.id.viewpager);
        //캐싱을 해놓을 프래그먼트 개수
        pager.setOffscreenPageLimit(3);

        //getSupportFragmentManager로 프래그먼트 참조가능
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());

        Calendar_Fragment calendar_fragment= new Calendar_Fragment();
        adapter.addItem(calendar_fragment);

        MainFragment main_fragment = new MainFragment();
        adapter.addItem(main_fragment);

        Routine_Fragment routine_fragment = new Routine_Fragment();
        adapter.addItem(routine_fragment);

        pager.setAdapter(adapter);
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