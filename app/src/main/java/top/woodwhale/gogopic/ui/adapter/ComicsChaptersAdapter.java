package top.woodwhale.gogopic.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.woodwhale.gogopic.R;
import top.woodwhale.gogopic.model.domain.ComicsChapter;

public class ComicsChaptersAdapter extends RecyclerView.Adapter<ComicsChaptersAdapter.InnerHolder> {
    public final List<ComicsChapter.DataBean.EpsBean.DocsBean> mDocsBeans = new ArrayList<>();
    private OnListenChapterButtonClickListener listener;

    public interface OnListenChapterButtonClickListener{
        void onButtonClick(int order);
    }

    public void registerOnListenChapterButtonClickListener(OnListenChapterButtonClickListener listener) {
        this.listener = listener;
    }

    public void unregisterOnListenChapterButtonClickListener() {
        this.listener = null;
    }

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comics_chapter, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        ComicsChapter.DataBean.EpsBean.DocsBean docsBean = mDocsBeans.get(position);
        holder.setData(docsBean);
        holder.mComicsChapterBt.setOnClickListener(v -> {
            int order = docsBean.getOrder();
            if (listener != null) {
                listener.onButtonClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDocsBeans.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ComicsChapter.DataBean.EpsBean.DocsBean> docs) {
        mDocsBeans.addAll(docs);
        notifyDataSetChanged();
    }

    public void addData(List<ComicsChapter.DataBean.EpsBean.DocsBean> docs) {
        int oldSize = mDocsBeans.size();
        mDocsBeans.addAll(docs);
        notifyItemRangeChanged(oldSize,docs.size());
    }

    public static class InnerHolder extends RecyclerView.ViewHolder {

        private Button mComicsChapterBt;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            mComicsChapterBt = itemView.findViewById(R.id.bt_comics_chapter);
        }

        public void setData(ComicsChapter.DataBean.EpsBean.DocsBean docsBean) {
            mComicsChapterBt.setText(docsBean.getTitle());
        }
    }
}
