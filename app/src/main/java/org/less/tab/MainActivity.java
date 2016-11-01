package org.less.tab;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import less.org.tab.BezierView;
import less.org.tab.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl);
        tabLayout.setType(TabLayout.TYPE_BEZIER_CIRCLE);
        tabLayout.setView(new BezierView(this));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);

    }
}
