package top.woodwhale.gogopic.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.NonNull;

import com.github.nukc.stateview.AnimatorProvider;

/**
 * @author Nukc.
 */

public class FadeScaleAnimatorProvider implements AnimatorProvider {

    @Override
    public Animator showAnimation(@NonNull View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f)
        );
        return set;
    }

    @Override
    public Animator hideAnimation(@NonNull View view) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.1f)
        );
        return set;
    }
}
