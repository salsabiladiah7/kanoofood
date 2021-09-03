package com.mafaa.kanoofood.customer_ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mafaa.kanoofood.R;
import com.mafaa.kanoofood.customer_ui.adapter.CategoryAdapter;
import com.mafaa.kanoofood.customer_ui.adapter.PopularAdapter;
import com.mafaa.kanoofood.customer_ui.domain.CategoryDomain;
import com.mafaa.kanoofood.customer_ui.domain.FoodDomain;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    public static HomeFragment newInstance() {

        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cust_fragment_home, container, false);


        recyclerViewCategory(view);
        recyclerViewPopular(view);
        return view;
    }

    private void recyclerViewPopular(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeFragment.newInstance().getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = view.findViewById(R.id.recyclerView6);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodlist = new ArrayList<>();
        foodlist.add(new FoodDomain("Pizza Hut", "tunamelt", "", 100.000));
        foodlist.add(new FoodDomain("Mie Kober", "kober", "", 11.000));
        foodlist.add(new FoodDomain("Soto", "soto", "", 15.000));

        adapter2 = new PopularAdapter(foodlist);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeFragment.newInstance().getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = view.findViewById(R.id.recyclerView4);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoryDomain("FastFood", "fastfood"));
        categoryList.add(new CategoryDomain("Sweets", "sweet"));
        categoryList.add(new CategoryDomain("Drinks", "drink"));
        categoryList.add(new CategoryDomain("SeaFood", "seafood"));
        categoryList.add(new CategoryDomain("Sushi", "sushi"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }


}