package top.woodwhale.gogopic.ui.adapter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.ComicsBook;
import top.woodwhale.gogopic.utils.UrlUtils;

public class ComicsBookAdapter extends RecyclerView.Adapter<ComicsBookAdapter.InnerHolder> {

    public List<ComicsBook.DataBean.PagesBean.DocsBean> mData = new ArrayList<>();

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comics_book_content, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        ComicsBook.DataBean.PagesBean.DocsBean docsBean = mData.get(position);
        holder.setData(docsBean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ComicsBook.DataBean.PagesBean.DocsBean> docs) {
        mData.clear();
        mData.addAll(docs);
        notifyDataSetChanged();
    }

    public void addData(List<ComicsBook.DataBean.PagesBean.DocsBean> docs) {
        int oldSize = mData.size();
        mData.addAll(oldSize, docs);
        notifyItemRangeChanged(oldSize,docs.size());
    }


    public static class InnerHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageView;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
        }


        public void setData(ComicsBook.DataBean.PagesBean.DocsBean docsBean) {
            String imgUrl = UrlUtils.getAddStaticPicPathUrl(docsBean.getMedia().getFileServer(),
                    docsBean.getMedia().getPath());

            Glide.with(itemView.getContext())
                    .load(imgUrl)
                    // 添加一个监听器，用来适应屏幕
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            if (mImageView == null) {
                                return false;
                            }
                            if (mImageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            }
                            ViewGroup.LayoutParams params = mImageView.getLayoutParams();
                            int vw = mImageView.getWidth() - mImageView.getPaddingLeft() - mImageView.getPaddingRight();
                            float scale = (float) vw / (float) resource.getIntrinsicWidth();
                            int vh = Math.round(resource.getIntrinsicHeight() * scale);
                            params.height = vh + mImageView.getPaddingTop() + mImageView.getPaddingBottom();
                            mImageView.setLayoutParams(params);
                            return false;
                        }
                    })
                    .thumbnail(0.3f)
                    .placeholder(R.mipmap.loading)
                    .error(R.mipmap.fail)
                    .into(mImageView);
        }
    }
}
