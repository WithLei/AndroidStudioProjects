package com.example.ecode.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ecode.R;
import com.example.ecode.fragment.CheckonworkFragment;
import com.example.ecode.fragment.MeFragment;
import com.example.ecode.fragment.MsgFragment;
import com.example.ecode.fragment.WaibaoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.iv_ckeck_on_work)
    ImageView ivCkeckOnWork;
    @BindView(R.id.tv_ckeck_on_work)
    TextView tvCkeckOnWork;
    @BindView(R.id.ll_ckeck_on_work)
    LinearLayout llCkeckOnWork;
    @BindView(R.id.iv_waibao)
    ImageView ivWaibao;
    @BindView(R.id.tv_waibao)
    TextView tvWaibao;
    @BindView(R.id.ll_waibao)
    LinearLayout llWaibao;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.ll_msg)
    LinearLayout llMsg;
    @BindView(R.id.iv_me)
    ImageView ivMe;
    @BindView(R.id.tv_me)
    TextView tvMe;
    @BindView(R.id.ll_me)
    LinearLayout llMe;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();

    }

    private void initData() {

        setSelected(0);
    }

    private CheckonworkFragment checkonworkFragment;
    private WaibaoFragment waibaoFragment;
    private MsgFragment msgFragment;
    private MeFragment meFragment;
    private void setSelected(int i) {

        transaction=getSupportFragmentManager().beginTransaction();

        hideFragment();
        resetTab();

        switch (i){
            case 0:
                if(checkonworkFragment==null){
                    checkonworkFragment=new CheckonworkFragment();
                    transaction.add(R.id.fl_main,checkonworkFragment);
                }
                transaction.show(checkonworkFragment);
                ivCkeckOnWork.setImageResource(R.drawable.checkonwork_selected);
                tvCkeckOnWork.setTextColor(R.color.buttonMainSelected);
                break;

            case 1:
                if(waibaoFragment==null){
                    waibaoFragment=new WaibaoFragment();
                    transaction.add(R.id.fl_main,waibaoFragment);
                }
                transaction.show(waibaoFragment);
                ivWaibao.setImageResource(R.drawable.waibao_selected);
                tvWaibao.setTextColor(R.color.buttonMainSelected);
                break;

            case 2:
                if(msgFragment==null){
                    msgFragment=new MsgFragment();
                    transaction.add(R.id.fl_main,msgFragment);
                }
                transaction.show(msgFragment);
                ivMsg.setImageResource(R.drawable.msg_selected);
                tvMsg.setTextColor(R.color.buttonMainSelected);
                break;

            case 3:
                if(meFragment==null){
                    meFragment=new MeFragment();
                    transaction.add(R.id.fl_main,meFragment);
                }
                transaction.show(meFragment);
                ivMe.setImageResource(R.drawable.me_selected);
                tvMe.setTextColor(R.color.buttonMainSelected);
                break;
        }

        transaction.commit();
    }

    private void resetTab() {

        ivCkeckOnWork.setImageResource(R.drawable.checkonwork_unselect);
        tvCkeckOnWork.setTextColor(R.color.buttonMainUnselected);

        ivWaibao.setImageResource(R.drawable.waibao_unselect);
        tvWaibao.setTextColor(R.color.buttonMainUnselected);

        ivMsg.setImageResource(R.drawable.msg_unselect);
        tvMsg.setTextColor(R.color.buttonMainUnselected);

        ivMe.setImageResource(R.drawable.me_unselect);
        tvMe.setTextColor(R.color.buttonMainUnselected);


    }

    private void hideFragment() {

        if(checkonworkFragment!=null){
            transaction.hide(checkonworkFragment);
        }
        if(waibaoFragment!=null){
            transaction.hide(waibaoFragment);
        }
        if(msgFragment!=null){
            transaction.hide(msgFragment);
        }
        if(meFragment!=null){
            transaction.hide(meFragment);
        }
    }

    @OnClick({R.id.ll_ckeck_on_work, R.id.ll_waibao, R.id.ll_msg, R.id.ll_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_ckeck_on_work:
                setSelected(0);
                break;
            case R.id.ll_waibao:
                setSelected(1);
                break;
            case R.id.ll_msg:
                setSelected(2);
                break;
            case R.id.ll_me:
                setSelected(3);
                break;
        }
    }
}
