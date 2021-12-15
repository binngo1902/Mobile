package com.android.book2joy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.book2joy.Adapter.ViewPagerAdapter;
import com.android.book2joy.Fragments.HomeFrag;
import com.android.book2joy.Fragments.Recommendation;
import com.android.book2joy.model.Booking;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private FirebaseAuth firebaseAuth;
	private static Toolbar toolbar;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	public static Booking bookings=new Booking();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		toolbar = findViewById(R.id.toolBar);
		toolbar.setTitle("Hotel App");

		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayShowTitleEnabled(true);

		viewPager = findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		tabLayout =  findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

	}

	private void setupViewPager(final ViewPager viewPager) {
		final ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
		viewPagerAdapter.addFrag(new HomeFrag(),"Home");
		viewPagerAdapter.addFrag(new Recommendation(),"Recommendations");

		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				if(position==1){

					((Recommendation)viewPagerAdapter.getItem(position)).updateList();

				}
				else{
					((HomeFrag)viewPagerAdapter.getItem(position)).updateList();
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
		viewPager.setAdapter(viewPagerAdapter);
	}
	public static void updatec(int n) {
		int cur=Integer.valueOf(((TextView)toolbar.findViewById(R.id.cartCount)).getText().toString());
		((TextView)toolbar.findViewById(R.id.cartCount)).setText(String.valueOf(cur+n));
	}


}

