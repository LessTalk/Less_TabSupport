# TabSupport

# 初始化

```java
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tl);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
```
01 默认样式 <br>
<img src="blob/master/library/src/main/res/img/DEFAULT.png" width="700" height="100" /> <br>
02 无下划线模式 <br>
```java
tabLayout.setType(TabLayout.TYPE_NO_LINE);
```
<img src="https://github.com/LessTalk/TabSupport/blob/master/library/src/main/res/img/TYPE_NO_LINE.png" width="700" height="100" /> <br>
03 根据标题宽度自适应 <br>
```java
tabLayout.setType(TabLayout.TYPE_SYSTEM_AUTO_ANIMATION_TEXT_WIDTH); 
```
<img src="https://github.com/LessTalk/TabSupport/blob/master/library/src/main/res/img/TYPE_SYSTEM_AUTO_ANIMATION_TEXT_WIDTH.png" width="700" height="100" /> <br>
04 自定义样式
```java
        tabLayout.setType(TabLayout.TYPE_BEZIER_CIRCLE);
        tabLayout.setView(new BezierView(this));
```
<img src="https://github.com/LessTalk/TabSupport/blob/master/library/src/main/res/img/TYPE_BEZIER_CIRCLE.png" width="700" height="100" /> <br>
05 当然我们支持随机组合
```java
tabLayout.setType(TabLayout.TYPE_BEZIER_CIRCLE|TabLayout.TYPE_NO_LINE);
```

