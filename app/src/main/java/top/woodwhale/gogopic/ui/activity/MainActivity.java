package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseActivity;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.ui.fragment.HomeFragment;
import top.woodwhale.gogopic.ui.fragment.MineFragment;
import top.woodwhale.gogopic.utils.LogUtils;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseActivity {

    private BaseFragment lastShowFragment = null;
    private HomeFragment mHomeFragment;
    private FragmentManager mFragmentManager;
    private MineFragment mMineFragment;
    @BindView(R.id.main_navigation_bar) BottomNavigationView mNavigationView;
    @BindView(R.id.tb_home_toolbar) TitleBar mTitleBar;

    @Override
    protected void initView() {
        initFragments();
    }

    @Override
    protected void initPresenter() {
        // TODO:处理搜索和ban书的逻辑层
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
            }

            @Override
            public void onRightClick(TitleBar titleBar) {
                // TODO：设置搜索事件
                startActivity(new Intent(MainActivity.this,SearchActivity.class));
            }
        });
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