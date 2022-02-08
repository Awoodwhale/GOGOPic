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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.Categories;

public class HomeContentAdapter extends RecyclerView.Adapter<HomeContentAdapter.InnerHolder> {

    private List<Categories.DataBean.CategoriesBean> mCategoriesBeanList = new ArrayList<>();

    private OnListenItemClickListener mListenItemClickListener = null;

    public void unregisterOnListenItemClickListener(OnListenItemClickListener mListenItemClickListener) {
        this.mListenItemClickListener = null;
    }

    public void registerOnListenItemClickListener(OnListenItemClickListener mListenItemClickListener) {
        this.mListenItemClickListener = mListenItemClickListener;
    }
    public interface OnListenItemClickListener {
        void onItemClick(Categories.DataBean.CategoriesBean data);
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_content, parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        // 设置数据
        Categories.DataBean.CategoriesBean categoriesBean = mCategoriesBeanList.get(position);
        holder.setData(categoriesBean);
        // 给每一个imageView设置对应的点击事件
        holder.mCategoryCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListenItemClickListener != null) {
                    mListenItemClickListener.onItemClick(categoriesBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCategoriesBeanList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Categories.DataBean.CategoriesBean> categories) {
        mCategoriesBeanList.clear();
        mCategoriesBeanList.addAll(categories);
        notifyDataSetChanged();
    }

    @SuppressLint("NonConstantResourceId")
    public static class InnerHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_category_cover) ImageView mCategoryCover;
        @BindView(R.id.tv_category_title) TextView mCategoryTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(Categories.DataBean.CategoriesBean data) {
            mCategoryTitle.setText(data.getTitle());
            String imgPath = data.getThumb().getFileServer()+"/static/"+data.getThumb().getPath();
            // 设置圆角
            RoundedCorners roundedCorners = new RoundedCorners(75);
            RequestOptions options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(itemView.getContext())
                    .load(imgPath)
                    .apply(options)
                    .thumbnail(0.5f)
                    .into(mCategoryCover);
        }
    }
}
