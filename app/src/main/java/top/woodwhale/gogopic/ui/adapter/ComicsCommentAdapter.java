package top.woodwhale.gogopic.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.ComicsComment;
import top.woodwhale.gogopic.utils.UrlUtils;

public class ComicsCommentAdapter extends RecyclerView.Adapter<ComicsCommentAdapter.InnerHolder> {
    List<ComicsComment.DataBean.CommentsBean.DocsBean> mData = new ArrayList<>();
    private int mTotal;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comics_comment, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        ComicsComment.DataBean.CommentsBean.DocsBean docsBean = mData.get(position);
        holder.setData(docsBean,mTotal);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(ComicsComment body) {
        mTotal = body.getData().getComments().getTotal();
        mData.clear();
        mData.addAll(body.getData().getComments().getDocs());
        notifyDataSetChanged();
    }

    @SuppressLint("NonConstantResourceId")
    public static class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_comics_comment_header) ImageView mCommentHeaderIv;
        @BindView(R.id.tv_comics_comment_username) TextView mCommentUsernameTv;
        @BindView(R.id.tv_comics_comment_time) TextView mCommentTimeTv;
        @BindView(R.id.tv_comics_comment_level) TextView mCommentLevelTv;
        @BindView(R.id.iv_comics_comment_content) TextView mCommentContentTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint("SetTextI18n")
        public void setData(ComicsComment.DataBean.CommentsBean.DocsBean docsBean, int total) {
            // 等级
            ComicsComment.DataBean.CommentsBean.DocsBean.UserBean user = docsBean.getUser();
            mCommentLevelTv.setText("LV:"+user.getLevel()+" "+user.getTitle());
            // name
            mCommentUsernameTv.setText(user.getName());
            // time
            String uploadTime =(docsBean.getCreatedAt().split("\\.")[0]).
                    replace("T", " ");
            mCommentTimeTv.setText(uploadTime);
            // content
            mCommentContentTv.setText(docsBean.getContent());
            // 头像
            if (user.getAvatar() != null) {
                String headUrl = UrlUtils.getAddStaticPicPathUrl(user.getAvatar().getFileServer(),
                        user.getAvatar().getPath());
                Glide.with(itemView.getContext())
                        .load(headUrl)
                        .thumbnail(0.5f)
                        .circleCrop()
                        .error(R.mipmap.no_header)
                        .into(mCommentHeaderIv);
            }
        }
    }
}
