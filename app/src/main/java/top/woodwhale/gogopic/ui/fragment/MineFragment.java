package top.woodwhale.gogopic.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.vondear.rxtool.view.RxToast;

import butterknife.BindView;
import butterknife.OnClick;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.base.BaseFragment;
import top.woodwhale.gogopic.model.domain.UserInfo;
import top.woodwhale.gogopic.presenter.IMinePresenter;
import top.woodwhale.gogopic.ui.activity.HistoryAndFavoriteActivity;
import top.woodwhale.gogopic.ui.activity.LoginActivity;
import top.woodwhale.gogopic.utils.Constants;
import top.woodwhale.gogopic.utils.LogUtils;
import top.woodwhale.gogopic.utils.PresenterManager;
import top.woodwhale.gogopic.utils.UrlUtils;
import top.woodwhale.gogopic.view.IMineCallback;

@SuppressLint("NonConstantResourceId")
public class MineFragment extends BaseFragment implements IMineCallback {

    @BindView(R.id.tv_mine_exit) TextView mExitTv;
    @BindView(R.id.tv_mine_sign) TextView mSignTv;
    @BindView(R.id.tv_mine_level) TextView mLevelTv;
    @BindView(R.id.tv_mine_username) TextView mUserNameTv;
    @BindView(R.id.iv_mine_head_photo) ImageView mHeadPhotoIv;
    private IMinePresenter mMinePresent;

    @OnClick({R.id.tv_mine_exit,R.id.tv_mine_favorite,R.id.tv_mine_history})
    protected void queryPress(View v) {
        switch (v.getId()) {
            case R.id.tv_mine_exit:
                handleExit();
                break;
            case R.id.tv_mine_favorite:
                handleFavoriteActivity(true);
                break;
            case R.id.tv_mine_history:
                handleFavoriteActivity(false);
                break;
        }
    }



    // 收藏或者页面跳转
    private void handleFavoriteActivity(boolean isFavorite) {
        Intent intent = new Intent(requireContext(), HistoryAndFavoriteActivity.class);
        if (isFavorite) {
            intent.putExtra(Constants.HISTORY_OR_FAVORITE_KEY,true);
        }
        requireContext().startActivity(intent);
    }

    /**
     * 用户退出登录，需要将文件删除
     */
    private void handleExit() {
        LogUtils.d(this,"退出登录！");
        AlertDialog.Builder alertdialogBuilder = new AlertDialog.Builder(requireContext());
        alertdialogBuilder.setIcon(R.mipmap.exit_pic);
        alertdialogBuilder.setTitle("退出登录");
        alertdialogBuilder.setMessage("您确认要退出登录嘛？登录记录会被抹除噢！");
        alertdialogBuilder.setPositiveButton("确定", (dialog, which) -> chooseExit());
        alertdialogBuilder.setNeutralButton("取消", null);
        alertdialogBuilder.show();
    }

    private void chooseExit() {
        RxToast.info("下次得登录了哦~");
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

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onUserInfoLoaded(UserInfo userInfo) {
        // 个人信息回调到这里
        showSuccess();
        UserInfo.DataBean.UserBean user = userInfo.getData().getUser();
        // 并不需要适配器，直接写
        mUserNameTv.setText(user.getName());
        mSignTv.setText(user.getSlogan());
        mLevelTv.setText("LV:"+user.getLevel()+" "+user.getTitle());
        String headImgPath = UrlUtils.getAddStaticPicPathUrl(
                user.getAvatar().getFileServer(),
                user.getAvatar().getPath());
        Glide.with(requireContext())
                .load(headImgPath)
                .thumbnail(0.5f)
                .circleCrop()
                .into(mHeadPhotoIv);

    }

    @Override
    protected void initPresenter() {
        mMinePresent = PresenterManager.getInstance().getMinePresenter();
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

    @Override
    public void onNetworkError() {
        showError();
    }

    @Override
    public void onLoading() {
        showLoading();
    }

    @Override
    public void onEmpty() {
        showEmpty();
    }

    @Override
    protected void contentRetry() {
        loadData();
    }
}