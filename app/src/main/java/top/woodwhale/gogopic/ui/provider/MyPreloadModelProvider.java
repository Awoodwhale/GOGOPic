package top.woodwhale.gogopic.ui.provider;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;

import java.util.Collections;
import java.util.List;

public class MyPreloadModelProvider implements ListPreloader.PreloadModelProvider<String> {

    private Context context;
    private List<String> urls;

    public MyPreloadModelProvider(Context context, List<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    @NonNull
    @Override
    public List<String> getPreloadItems(int position) {
        String url = urls.get(position);
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList();
        }
        return Collections.singletonList(url);
    }

    @Nullable
    @Override
    public RequestBuilder<?> getPreloadRequestBuilder(@NonNull String item) {
        //返回的 RequestBuilder ，必须与你从 onBindViewHolder 里启动的请求使用完全相同的一组选项 (占位符， 变换等) 和完全相同的尺寸
        return Glide.with(context)
                .load(item)
                .centerCrop();
    }
}
