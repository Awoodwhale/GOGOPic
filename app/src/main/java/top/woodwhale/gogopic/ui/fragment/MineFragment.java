package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.UserInfo;
import top.woodwhale.gogopic.presenter.IMinePresent;
import top.woodwhale.gogopic.presenter.impl.MinePresentImpl;
import top.woodwhale.gogopic.ui.activity.LoginActivity;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.view.IMineCallback;

@SuppressLint("NonConstantResourceId")
public class MineFragment extends BaseFragment implements IMineCallback {

    @BindView(R.id.tv_mine_exit) TextView mExitTv;
    @BindView(R.id.tv_mine_sign) TextView mSignTv;
    @BindView(R.id.tv_mine_level) TextView mLevelTv;
    @BindView(R.id.tv_mine_username) TextView mUserNameTv;
    @BindView(R.id.iv_mine_head_photo) ImageView mHeadPhotoIv;
    @BindView(R.id.tv_search_title) TextView mSearchTitleTv;
    private IMinePresent mMinePresent;

    @OnClick({R.id.tv_mine_exit})
    protected void queryPress(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_exit:
                handleExit();
                break;
        }
    }

    /**
     * 用户退出登录，需要将文件删除
     */
    private void handleExit() {
        LogUtils.d(this,"退出登录！");
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(requireContext());
        alertdialogBuilder.setIcon(R.mipmap.exit_pic);
        alertdialogBuilder.setTitle("退出登录");
        alertdialogBuilder.setMessage("您确认要退出登录嘛？");
        alertdialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chooseExit();
            }
        });
        alertdialogBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MineFragment.this.requireActivity(), "再看看呗？", Toast.LENGTH_SHORT).show();
            }
        });
        alertdialogBuilder.show();
    }

    private void chooseExit() {
        Toast.makeText(this.requireActivity(), "欢迎下次再来~", Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor edit = requireActivity()
                .getSharedPreferences("GOGOPicLogin", Context.MODE_PRIVATE)
                .edit();
        // 清空数据就走
        edit.clear();
        edit.apply();
        requireActivity().startActivity(new Intent(requireActivity(), LoginActivity.class));
        requireActivity().finish();
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View rootView) {
        mSearchTitleTv.setText("我的");
        setupState(State.SUCCESS);
    }

    @Override
    public void onNetworkError() {
        setupState(State.ERROR);
    }

    @Override
    public void onLoading() {
        setupState(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setupState(State.EMPTY);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onUserInfoLoaded(UserInfo userInfo) {
        // 个人信息回调到这里
        setupState(State.SUCCESS);
        UserInfo.DataBean.UserBean user = userInfo.getData().getUser();
        // 并不需要适配器，直接写
        mUserNameTv.setText(user.getName());
        mSignTv.setText(user.getSlogan());
        mLevelTv.setText("LV:"+user.getLevel()+" "+user.getTitle());
        String headImgPath = user.getAvatar().getFileServer()
                +"/static/"+
                user.getAvatar().getPath();
        Glide.with(requireContext())
                .load(headImgPath)
                .thumbnail(0.5f)
                .into(mHeadPhotoIv);

    }

    @Override
    protected void initPresenter() {
        mMinePresent = new MinePresentImpl();
        mMinePresent.registerViewCallback(this);
    }

    @Override
    protected void loadData() {
        if (mMinePresent != null) {
            mMinePresent.getUserInfo();
        }
    }

    @Override
    protected void release() {
        if (mMinePresent != null) {
            mMinePresent.unregisterViewCallback(this);
        }
    }

    // 如果网络错误，点击重试，那么重新加载数据
    @Override
    protected void onNetworkRetryClick() {
        loadData();
    }
}
