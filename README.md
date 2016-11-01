# TabSupport

# 初始化

```java
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
```
https://github.com/LessTalk/TabSupport/blob/master/library/src/main/res/img/DEFAULT.png <br>
