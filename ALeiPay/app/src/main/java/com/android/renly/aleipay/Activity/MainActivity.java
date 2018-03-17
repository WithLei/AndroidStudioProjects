package com.android.renly.aleipay.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.renly.aleipay.Fragment.HomeFragment;
import com.android.renly.aleipay.Fragment.InvestFragment;
import com.android.renly.aleipay.Fragment.MeFragment;
import com.android.renly.aleipay.Fragment.MoreFragment;
import com.android.renly.aleipay.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.iv_main_bottom_mainpage)
    ImageView ivMainBottomMainpage;
    @Bind(R.id.tv_main_bottom_mainpage)
    TextView tvMainBottomMainpage;
    @Bind(R.id.iv_main_bottom_invest)
    ImageView ivMainBottomInvest;
    @Bind(R.id.tv_main_bottom_invest)
    TextView tvMainBottomInvest;
    @Bind(R.id.iv_main_bottom_me)
    ImageView ivMainBottomMe;
    @Bind(R.id.tv_main_bottom_me)
    TextView tvMainBottomMe;
    @Bind(R.id.iv_main_bottom_more)
    ImageView ivMainBottomMore;
    @Bind(R.id.tv_main_bottom_more)
    TextView tvMainBottomMore;
    @Bind(R.id.ll_main_bottom_mainpage)
    LinearLayout llMainBottomMainpage;
    @Bind(R.id.ll_main_bottom_invest)
    LinearLayout llMainBottomInvest;
    @Bind(R.id.ll_main_bottom_me)
    LinearLayout llMainBottomMe;
    @Bind(R.id.ll_main_bottom_more)
    LinearLayout llMainBottomMore;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //默认显示首页
        setSelect(0);
    }

    @OnClick({R.id.ll_main_bottom_mainpage,R.id.ll_main_bottom_invest,R.id.ll_main_bottom_me,R.id.ll_main_bottom_more})
    public void showTab(View view){
//        Toast.makeText(MainActivity.this,"响应",Toast.LENGTH_SHORT).show();
        switch (view.getId()){
            case R.id.ll_main_bottom_mainpage:
                setSelect(0);
                break;
            case R.id.ll_main_bottom_invest:
                setSelect(1);
                break;
            case R.id.ll_main_bottom_me:
                setSelect(2);
                break;
            case R.id.ll_main_bottom_more:
                setSelect(3);
                break;
        }
    }
    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private MeFragment meFragment;
    private MoreFragment moreFragment;

    public void setSelect(int select) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //隐藏所有fragment的显示
        hideFragments();
        switch (select){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();//commit()后调用生命周期方法
                    transaction.add(R.id.fl_main,homeFragment);
                }
                transaction.show(homeFragment);//显示当前的Fragment

                //改变图片和文字颜色
                ivMainBottomMainpage.setImageResource(R.drawable.logoblue);
                tvMainBottomMainpage.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 1:
                if(investFragment == null){
                    investFragment = new InvestFragment();//commit()后调用生命周期方法
                    transaction.add(R.id.fl_main,investFragment);
                }
                transaction.show(investFragment);//显示当前的Fragment


                //改变图片和文字颜色
                ivMainBottomInvest.setImageResource(R.drawable.financialfillblue);
                tvMainBottomInvest.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 2:
                if(meFragment == null){
                    meFragment = new MeFragment();//commit()后调用生命周期方法
                    transaction.add(R.id.fl_main,meFragment);
                }
                transaction.show(meFragment);//显示当前的Fragment

                //改变图片和文字颜色
                ivMainBottomMe.setImageResource(R.drawable.mine_fill);
                tvMainBottomMe.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
            case 3:
                if(moreFragment == null){
                    moreFragment = new MoreFragment();//commit()后调用生命周期方法
                    transaction.add(R.id.fl_main,moreFragment);
                }
                transaction.show(moreFragment);//显示当前的Fragment

                //改变图片和文字颜色
                ivMainBottomMore.setImageResource(R.drawable.setup_fill);
                tvMainBottomMore.setTextColor(getResources().getColor(R.color.home_back_selected));
                break;
        }
        transaction.commit();//提交事务
    }

    private void hideFragments() {
        ivMainBottomMainpage.setImageResource(R.drawable.logo);
        ivMainBottomInvest.setImageResource(R.drawable.financialfill);
        ivMainBottomMe.setImageResource(R.drawable.mine);
        ivMainBottomMore.setImageResource(R.drawable.setup);
        tvMainBottomMainpage.setTextColor(getResources().getColor(R.color.bottom_unselect));
        tvMainBottomInvest.setTextColor(getResources().getColor(R.color.bottom_unselect));
        tvMainBottomMe.setTextColor(getResources().getColor(R.color.bottom_unselect));
        tvMainBottomMore.setTextColor(getResources().getColor(R.color.bottom_unselect));
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(investFragment != null){
            transaction.hide(investFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
        if(moreFragment != null){
            transaction.hide(moreFragment);
        }

    }

    //重写onkeyup()

    private static final int WHAT_RESET_BACK = 1;
    private boolean flag = true;
    Handler handler = new Handler(){
        public void handlerMessage(Message msg){
            switch (msg.what){
                case WHAT_RESET_BACK:
                    flag = true;//复原
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除所有的未被执行的消息
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && flag){
            Toast.makeText(this,"再点击一次退出当前应用",Toast.LENGTH_SHORT).show();
            flag = false;
            //发送延迟消息
            handler.sendEmptyMessageDelayed(WHAT_RESET_BACK,2000);
            return true;
        }
        //为了避免出现内存的泄露，需要在onDestroy()中，移除所有未被执行的消息

        return super.onKeyUp(keyCode, event);
    }

}
