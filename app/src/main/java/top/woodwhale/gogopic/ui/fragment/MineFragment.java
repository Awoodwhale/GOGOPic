package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;

@SuppressLint("NonConstantResourceId")
public class MineFragment extends BaseFragment {

    @BindView(R.id.tv_search_title) TextView mSearchTitle;

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        mSearchTitle.setText("我的");
        setupState(State.SUCCESS);
    }
}
