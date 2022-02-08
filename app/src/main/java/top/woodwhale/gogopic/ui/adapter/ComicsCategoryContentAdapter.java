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
import top.woodwhale.gogopic.model.domain.ComicsCategory;

public class ComicsCategoryContentAdapter extends RecyclerView.Adapter<ComicsCategoryContentAdapter.InnerHolder> {

    private final List<ComicsCategory.DataBean.ComicsBean.DocsBean> mDocsList = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comics_category_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        ComicsCategory.DataBean.ComicsBean.DocsBean docsBean = mDocsList.get(position);
        holder.setData(docsBean);
    }

    @Override
    public int getItemCount() {
        return mDocsList.size();
    }

    // 适配器拿到content，去前端渲染
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ComicsCategory.DataBean.ComicsBean.DocsBean> body) {
        mDocsList.clear();
        mDocsList.addAll(body);
        notifyDataSetChanged();
    }

    public void addData(List<ComicsCategory.DataBean.ComicsBean.DocsBean> docs) {
        int oldSize = mDocsList.size();
        mDocsList.addAll(docs);
        notifyItemChanged(oldSize,mDocsList.size());
    }

    @SuppressLint("NonConstantResourceId")
    public static class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_comics_category_front_cover) ImageView mFrontCoverIv;
        @BindView(R.id.tv_comics_category_title) TextView mTitleTv;
        @BindView(R.id.iv_comics_category_author) TextView mAuthorTv;
        @BindView(R.id.tv_comics_category_classification) TextView mClassificationTv;
        @BindView(R.id.tv_comics_category_is_it_over) TextView mIsOverTv;
        @BindView(R.id.tv_comics_category_love_count) TextView mLoveCountTv;
        @BindView(R.id.tv_comics_category_pagination) TextView mPaginationTv;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
        public void setData(ComicsCategory.DataBean.ComicsBean.DocsBean docsBean) {
            // 喜欢人数
            int likesCount = docsBean.getLikesCount();
            mLoveCountTv.setText(likesCount+"");
            // 作者
            String author = docsBean.getAuthor();
            mAuthorTv.setText(author);
            // 标题
            String title = docsBean.getTitle();
            mTitleTv.setText(title);
            // 分类
            List<String> categories = docsBean.getCategories();
            StringBuilder classifications = new StringBuilder();
            for (String category : categories) {
                classifications.append(category).append(" ");
            }
            mClassificationTv.setText(classifications.toString());
            // 是否完结
            mIsOverTv.setText(docsBean.isFinished() ? "完结" : "连载");
            mIsOverTv.setBackground(itemView.getContext().getDrawable(R.drawable.shape_tab_bg));
            // 分页
            int epsCount = docsBean.getEpsCount();
            int pagesCount = docsBean.getPagesCount();
            mPaginationTv.setText(epsCount+" E / "+pagesCount+" P");
            // 设置封面
            ComicsCategory.DataBean.ComicsBean.DocsBean.ThumbBean thumb = docsBean.getThumb();
            String fileServer = thumb.getFileServer();
            String path = thumb.getPath();
            String url = fileServer + "/static/" + path;
            Glide.with(itemView.getContext())
                    .load(url)
                    .thumbnail(0.1f)
                    .placeholder(R.mipmap.loading)
                    .error(R.mipmap.fail)
                    .into(mFrontCoverIv);
        }
    }
}
