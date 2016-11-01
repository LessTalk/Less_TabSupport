# TabSupport

# 基本初始化

```java
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
