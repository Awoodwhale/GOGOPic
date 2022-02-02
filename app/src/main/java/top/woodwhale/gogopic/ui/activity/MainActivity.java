package top.woodwhale.gogopic.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.ui.fragment.HomeFragment;
import top.woodwhale.gogopic.ui.fragment.MineFragment;
import top.woodwhale.gogopic.utils.LogUtils;

@SuppressLint("NonConstantResourceId")
public class MainActivity extends AppCompatActivity {

    private Unbinder mBind;
    private HomeFragment mHomeFragment;
    private FragmentManager mFragmentManager;
    private MineFragment mMineFragment;
    @BindView(R.id.main_navigation_bar) public BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        initFragments();
        initEvent();    // 设置监听
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBind != null) {
            mBind.unbind();
        }
    }

    // 初始化管理fragment
    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mMineFragment = new MineFragment();
        mFragmentManager = getSupportFragmentManager();
        // 默认是主页
        switchFragment(mHomeFragment);
    }

    private void initEvent() {
        mNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
            }
        });
    }

    private void switchFragment(BaseFragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container,fragment);
        fragmentTransaction.commit();
    }
}