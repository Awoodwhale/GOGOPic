package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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
    @BindView(R.id.main_navigation_bar) public BottomNavigationView mNavigationView;

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
        mNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.home:
                    LogUtils.d(MainActivity.this,"前往主页！");
                    switchFragment(mHomeFragment);
                    break;
                case R.id.mine:
                    LogUtils.d(MainActivity.this,"前往我的！");
                    switchFragment(mMineFragment);
                    break;
            }
            return true;
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