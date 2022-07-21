package pl.umg.traincomplete.otherLabs.viewPager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import pl.umg.traincomplete.R;

public class ViewPager extends AppCompatActivity {

    private ViewPagerAdapter viewPagerAdapter;
    private androidx.viewpager.widget.ViewPager viewPager;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.viewpager);

        //setting up the adapter

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // add the fragments
        viewPagerAdapter.add(new Page1(), "Page 1");
        viewPagerAdapter.add(new Page2(), "Page 2");
        viewPagerAdapter.add(new Page3(), "Page 3");

        //Set the adapter
        viewPager.setAdapter(viewPagerAdapter);

        // The Page (fragment) titles will be displayed in the
        // tabLayout hence we need to  set the page viewer
        // we use the setupWithViewPager().

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }
}