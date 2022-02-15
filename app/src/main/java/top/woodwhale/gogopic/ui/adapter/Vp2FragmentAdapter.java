package top.woodwhale.gogopic.ui.adapter;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import top.woodwhale.gogopic.ui.fragment.ChapterFragment;
import top.woodwhale.gogopic.ui.fragment.CommentFragment;

public class Vp2FragmentAdapter extends FragmentStateAdapter {
    public Vp2FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @SuppressLint("NotifyDataSetChanged")
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new ChapterFragment();
        }
        return new CommentFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
