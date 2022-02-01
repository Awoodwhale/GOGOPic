package top.woodwhale.gogopic.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.ui.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private Unbinder mBind;
    private HomeFragment mHomeFragment;
    private FragmentManager mFragmentManager;

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

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mFragmentManager = getSupportFragmentManager();
        switchFragment(mHomeFragment);
    }

    private void initEvent() {

    }

    // TODO:加上home和mine的switch，写一个BaseFragment
    private void switchFragment(HomeFragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_page_container,fragment);
        fragmentTransaction.commit();
    }
}