# BottomNavigationView Sample

## BottomNavigationView + ViewPager

![BottomNavigationView + ViewPager](./arts/b_and_p.gif)

```java
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.pager.setAdapter(new BottomNavigationPagerAdapter(getSupportFragmentManager()));
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
    }
}
```