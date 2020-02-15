package com.endpoint.ghair.activities_fragments.activity_home.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.endpoint.ghair.R;
import com.endpoint.ghair.activities_fragments.activity_addservice.AddServiceActivity;
import com.endpoint.ghair.activities_fragments.activity_home.HomeActivity;
import com.endpoint.ghair.activities_fragments.activity_market.MarketActivity;
import com.endpoint.ghair.activities_fragments.activity_profile.fragments.FragmentMyAuction;
import com.endpoint.ghair.activities_fragments.activity_profile.fragments.FragmentMyOrder;
import com.endpoint.ghair.activities_fragments.activity_profile.fragments.FragmentMyServices;
import com.endpoint.ghair.activities_fragments.activity_profile.fragments.FragmentRequired;
import com.endpoint.ghair.adapters.Category_Adapter;
import com.endpoint.ghair.adapters.Service_Adapter;
import com.endpoint.ghair.adapters.SlidingImage_Adapter;
import com.endpoint.ghair.adapters.ViewPagerAdapter;
import com.endpoint.ghair.databinding.FragmentProfileBinding;
import com.endpoint.ghair.databinding.FragmnetMainBinding;
import com.endpoint.ghair.models.Slider_Model;
import com.endpoint.ghair.preferences.Preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class Fragment_Profile extends Fragment {
    private static Dialog dialog;
    private HomeActivity activity;
    private FragmentProfileBinding binding;
    private String lang;
    private List<Fragment> fragmentList;
    private List<String> titles;
    private ViewPagerAdapter adapter;

private List<Slider_Model.Data> dataList;
    public static Fragment_Profile newInstance() {
        return new Fragment_Profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        initView();


        return binding.getRoot();
    }



    private void initView() {
activity=(HomeActivity)getActivity();
        Paper.init(activity);
        lang = Paper.book().read("lang", Locale.getDefault().getLanguage());
        binding.setLang(lang);
        fragmentList = new ArrayList<>();
        titles = new ArrayList<>();
        binding.tab.setupWithViewPager(binding.pager);
        addFragments_Titles();
        binding.pager.setOffscreenPageLimit(fragmentList.size());

        adapter = new ViewPagerAdapter(activity.getSupportFragmentManager());
        adapter.addFragments(fragmentList);
        adapter.addTitles(titles);
        binding.pager.setAdapter(adapter);







    }
    private void addFragments_Titles() {
        fragmentList.add(FragmentMyOrder.newInstance());
        fragmentList.add(FragmentMyAuction.newInstance());

        fragmentList.add(FragmentRequired.newInstance());
        fragmentList.add(FragmentMyServices.newInstance());
        titles.add(getString(R.string.my_orders));
        titles.add(getString(R.string.my_auction));
        titles.add(getString(R.string.require));
        titles.add(getString(R.string.my_services));



    }

}
