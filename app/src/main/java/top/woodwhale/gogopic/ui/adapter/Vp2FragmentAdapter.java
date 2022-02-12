package top.woodwhale.gogopic.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import top.woodwhale.gogopic.ui.fragment.ChapterFragment;

public class Vp2FragmentAdapter extends FragmentStateAdapter {
    public Vp2FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return new ChapterFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
