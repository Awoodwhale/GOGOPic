package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.Keywords;

public interface IKeywordsCallback extends IBaseCallback {
    void onKeywordsLoaded(Keywords keywords);
}
