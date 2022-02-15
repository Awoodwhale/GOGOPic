package top.woodwhale.gogopic.view;

import top.woodwhale.gogopic.base.IBaseCallback;
import top.woodwhale.gogopic.model.domain.ComicsChapter;

public interface IEpsChapterCallback extends IBaseCallback {
    void onEpsChapterLoaded(ComicsChapter body);
    void onEpsChapterLoadedMore(ComicsChapter moreBody);
}
