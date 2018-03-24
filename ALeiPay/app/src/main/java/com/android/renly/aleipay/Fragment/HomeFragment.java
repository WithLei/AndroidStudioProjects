package com.android.renly.aleipay.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.renly.aleipay.R;
import com.android.renly.aleipay.bean.Image;
import com.android.renly.aleipay.bean.Index;
import com.android.renly.aleipay.bean.Product;
import com.youth.banner.Banner;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Renly on 2018/3/17.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.iv_title_back)
    ImageView ivTitleBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;
    private Index index;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initTitle();
        String content;
        initData(content);
        return view;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    private void initData(String content) {
        index = new Index();
        //解析json数据：GSON / FASTJSON
        JSONObject jsonObject = JSON.parseObject(content);
        //解析json对象数据
        String proInfo = jsonObject.getString(("proInfo"));
        Product product = JSON.parseObject(proInfo,Product.class);
        //解析json数组数据
        String imageArr = jsonObject.getString("imageArr");
        List<Image> images = jsonObject.parseArray(imageArr,Image.class);
        index.product = product;
        index.images = images;

        //更新页面数据
        tvHomeProduct.setText(product.name);
        tvHomeProduct.setText(product.yearRates + "%");
        //获取数据中的进度值
        currentProcess = Integer.parseInt(index.product.progress);

        //在分线程中，实现进度的动态变化
        new Thread(runnable)
    }

    private void initTitle() {
        ivTitleBack.setVisibility(View.GONE);
        tvTitle.setText("首页");
        ivTitleSetting.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
