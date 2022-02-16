package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.presenter.IHomePresenter;
import top.woodwhale.gogopic.ui.fragment.HomeFragment;
import top.woodwhale.gogopic.ui.fragment.MineFragment;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.SharedPreferencesUtils;


@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseActivity {

    private BaseFragment lastShowFragment = null;
    private HomeFragment mHomeFragment;
    private FragmentManager mFragmentManager;
    private MineFragment mMineFragment;
    @BindView(R.id.main_navigation_bar) BottomNavigationView mNavigationView;
    @BindView(R.id.tb_home_toolbar) TitleBar mTitleBar;
    private IHomePresenter mHomePresenter;

    @Override
    protected void initView() {
        initFragments();
    }

    @Override
    protected void initPresenter() {
    }

    // 初始化管理fragment
    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mMineFragment = new MineFragment();
        mFragmentManager = getSupportFragmentManager();
        // 默认是主页
        switchFragment(mHomeFragment);
    }

    @Override
    protected void initEvent() {
        // 不同fragment切换
        mNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.home:
                    LogUtils.d(MainActivity.this,"前往主页！");
                    switchFragment(mHomeFragment);
                    mTitleBar.setTitle("主页");
                    break;
                case R.id.mine:
                    LogUtils.d(MainActivity.this,"前往我的！");
                    switchFragment(mMineFragment);
                    mTitleBar.setTitle("我的");
                    break;
            }
            return true;
        });
        // 设置监听
        mTitleBar.setOnTitleBarListener(new OnTitleBarListener() {
            @Override
            public void onLeftClick(TitleBar titleBar) {
                // TODO：设置ban书事件
                handleBanBooks();
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
    }



    /**
     * ban书
     */
    private void handleBanBooks() {
        if (Constants.CATEGORISE_LISTS != null) {
            List<String> banBooksList = new ArrayList<>();
            String[] multiItem = Constants.CATEGORISE_LISTS.toArray(new String[0]);
            final int length = multiItem.length;
            boolean[] checkedItem = new boolean[length];
            for (int i = 0; i < length; i++) {
                checkedItem[i] = !Constants.BANNED_CATEGORISE_LISTS.contains(multiItem[i]);
            }
            AlertDialog.Builder multiDialog = new AlertDialog.Builder(this);
            multiDialog.setTitle("禁书目录");
            multiDialog.setMultiChoiceItems(multiItem, checkedItem, (dialog, which, isChecked) -> checkedItem[which] = isChecked);
            multiDialog.setPositiveButton("确定", (dialog, which) -> {
                for (int i = 0; i < length; i++ ){
                    if (checkedItem[i]){
                        banBooksList.add(multiItem[i]);
                    }
                }
                // 将得到的ban书写入sp中
                SharedPreferencesUtils.handleBanBooks(banBooksList,MainActivity.this);
                // 写完去loadData
                mHomeFragment.loadData();
            });
            multiDialog.setNegativeButton("取消",null);
            multiDialog.show();
        }
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    private void switchFragment(BaseFragment fragment) {
        // 使用add和hide的方式来操作
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!fragment.isAdded()) {
            fragmentTransaction.add(R.id.main_page_container,fragment);
        } else {
            fragmentTransaction.show(fragment);
        }
        if (lastShowFragment != null && lastShowFragment != fragment) {
            fragmentTransaction.hide(lastShowFragment);
        }
        lastShowFragment = fragment;
        fragmentTransaction.commit();
    }

}