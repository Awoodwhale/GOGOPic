package top.woodwhale.gogopic.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;

import com.github.nukc.stateview.AnimatorProvider;

/**
 * @author Nukc.
 */

public class SlideAnimatorProvider implements AnimatorProvider {

    @Override
    public Animator showAnimation(@NonNull View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0, 1),
                ObjectAnimator.ofFloat(view, "translationX", getDistance(view), 0)
        );
        set.setInterpolator(new AccelerateInterpolator());
        return set;
    }

    @Override
    public Animator hideAnimation(@NonNull View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 1, 0),
                ObjectAnimator.ofFloat(view, "translationX", 0, getDistance(view))
        );
        set.setInterpolator(new DecelerateInterpolator());
        return set;
    }

    private float getDistance(View view) {
        ViewParent viewParent = view.getParent();
        if (viewParent == null) {
            return 0f;
        } else {
            return ((View) viewParent).getWidth();
        }
    }
}
