package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Categories {

    @Override
    public String toString() {
        return "Categories{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 200
     * message : success
     * data : {"categories":[{"title":"援助嗶咔","thumb":{"originalName":"help.jpg","path":"help.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"active":true,"link":"https://donate.wikawika.xyz"},{"title":"嗶咔小禮物","thumb":{"originalName":"picacomic-gift.jpg","path":"picacomic-gift.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://gift-web.wikawika.xyz","active":true},{"title":"小電影","thumb":{"originalName":"av.jpg","path":"av.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://av.wikawika.xyz","active":true},{"title":"小里番","thumb":{"originalName":"h.jpg","path":"h.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://h.wikawika.xyz","active":true},{"title":"嗶咔畫廊","thumb":{"originalName":"picacomic-paint.jpg","path":"picacomic-paint.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://paint-web.wikawika.xyz","active":true},{"title":"嗶咔鍋貼","thumb":{"originalName":"picacomic-post.jpg","path":"picacomic-post.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://post-web.wikawika.xyz","active":true},{"title":"嗶咔商店","thumb":{"originalName":"picacomic-shop.jpg","path":"picacomic-shop.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"link":"https://online-shop-web.wikawika.xyz","active":true},{"title":"大家都在看","thumb":{"originalName":"every-see.jpg","path":"every-see.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":false,"active":true},{"title":"勤學苦練","thumb":{"originalName":"recommendation.jpg","path":"a8038f8c-cc4b-4bba-a62b-e8250dda67c8.jpg","fileServer":"https://storage1.picacomic.com"},"isWeb":false,"active":true},{"title":"那年今天","thumb":{"originalName":"old.jpg","path":"old.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":false,"active":true},{"title":"官方都在看","thumb":{"originalName":"promo.jpg","path":"promo.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":false,"active":true},{"title":"嗶咔運動","thumb":{"originalName":"picacomic-move-cat.jpg","path":"picacomic-move-cat.jpg","fileServer":"https://wikawika.xyz/static/"},"isWeb":true,"active":true,"link":"https://move-web.wikawika.xyz"},{"_id":"5821859b5f6b9a4f93dbf6e9","title":"嗶咔漢化","description":"未知","thumb":{"originalName":"translate.png","path":"f541d9aa-e4fd-411d-9e76-c912ffc514d1.png","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d1","title":"全彩","description":"未知","thumb":{"originalName":"全彩.jpg","path":"8cd41a55-591c-424c-8261-e1d56d8b9425.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6cd","title":"長篇","description":"未知","thumb":{"originalName":"長篇.jpg","path":"681081e7-9694-436a-97e4-898fc68a8f89.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6ca","title":"同人","description":"未知","thumb":{"originalName":"同人.jpg","path":"1a33f1be-90fa-4ac7-86d7-802da315732e.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6ce","title":"短篇","description":"未知","thumb":{"originalName":"短篇.jpg","path":"bd021022-8e19-49ff-8c62-6b29f31996f9.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"584ea1f45a44ac4f7dce3623","title":"圓神領域","description":"魔法少女小圓為主題的本子","thumb":{"originalName":"cat_cirle.jpg","path":"c7e86b6e-4d27-4d81-a083-4a774ceadf72.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"58542b601b8ef1eb33b57959","title":"碧藍幻想","description":"碧藍幻想的本子","thumb":{"originalName":"blue.jpg","path":"b8608481-6ec8-46a3-ad63-2f8dc5da4523.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e5","title":"CG雜圖","description":"未知","thumb":{"originalName":"CG雜圖.jpg","path":"b62b79b7-26af-4f81-95bf-d27ef33d60f3.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e8","title":"英語 ENG","description":"未知","thumb":{"originalName":"英語 ENG.jpg","path":"6621ae19-a792-4d0c-b480-ae3496a95de6.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e0","title":"生肉","description":"未知","thumb":{"originalName":"生肉.jpg","path":"c90a596c-4f63-4bdf-953d-392edcbb4889.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6de","title":"純愛","description":"未知","thumb":{"originalName":"純愛.jpg","path":"18fde59b-bee5-4177-bf1f-88c87c7c9d70.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d2","title":"百合花園","description":"未知","thumb":{"originalName":"百合花園.jpg","path":"de5f1ca3-840a-4ea4-b6c0-882f1d80bd2e.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e2","title":"耽美花園","description":"未知","thumb":{"originalName":"1492872524635.jpg","path":"dcfa0115-80c9-4233-97e3-1ad469c2c0df.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e4","title":"偽娘哲學","description":"未知","thumb":{"originalName":"偽娘哲學.jpg","path":"39119d6c-4808-4859-98df-4dda30b9da3b.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d3","title":"後宮閃光","description":"未知","thumb":{"originalName":"後宮閃光.jpg","path":"dec122af-84bf-4736-b8f0-d6533a2839f7.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d4","title":"扶他樂園","description":"未知","thumb":{"originalName":"扶他樂園.jpg","path":"73d8a102-1805-4b14-b258-a95c85b02b8a.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5abb3fd683111d2ad3eecfca","title":"單行本","thumb":{"originalName":"Loveland_001 (2).jpg","path":"a29c241a-2af7-47f2-aae5-b640374b12ac.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6da","title":"姐姐系","description":"未知","thumb":{"originalName":"姐姐系.jpg","path":"91e551c5-a98f-4f41-b7a0-c125bd77c523.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6db","title":"妹妹系","description":"未知","thumb":{"originalName":"妹妹系.jpg","path":"098f612c-9d16-4848-9732-0305b66ed799.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6cb","title":"SM","description":"未知","thumb":{"originalName":"SM.jpg","path":"41fc9bce-68f6-4b36-98cf-14ab3d3bd19e.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d0","title":"性轉換","description":"未知","thumb":{"originalName":"性轉換.jpg","path":"f5c70a00-538c-44b8-b692-d6c3b049e133.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6df","title":"足の恋","description":"未知","thumb":{"originalName":"足の恋.jpg","path":"ad3373c7-4974-45f5-b5d6-eb9490363314.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6cc","title":"人妻","description":"未知","thumb":{"originalName":"人妻.jpg","path":"e3359724-603b-47d8-905f-c88c5d38c983.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d8","title":"NTR","description":"未知","thumb":{"originalName":"NTR.jpg","path":"e10cf018-e214-41fa-bf1c-376a6b7a24ea.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d9","title":"強暴","description":"未知","thumb":{"originalName":"強暴.jpg","path":"4c3a9fb0-3084-4abf-bbc9-8efa33554749.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d6","title":"非人類","description":"未知","thumb":{"originalName":"非人類.jpg","path":"b09840fe-8ca9-41ac-9e73-3dd68e426865.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6cf","title":"艦隊收藏","description":"未知","thumb":{"originalName":"艦隊收藏.jpg","path":"1ed52b9e-8ac3-47ae-bafc-c31bfab9b3d5.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d7","title":"Love Live","description":"未知","thumb":{"originalName":"Love Live.jpg","path":"b2ae70d1-1c0e-415f-b3f8-0f6f17626387.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6dc","title":"SAO 刀劍神域","description":"未知","thumb":{"originalName":"SAO 刀劍神域.jpg","path":"f7c0ccc3-6baf-4823-b2b5-a7a83d426d4c.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e1","title":"Fate","description":"未知","thumb":{"originalName":"Fate.jpg","path":"44bf46b9-415e-490b-9b61-7916ef2cea53.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6dd","title":"東方","description":"未知","thumb":{"originalName":"東方.jpg","path":"c373bf9d-1003-453d-a791-f65dde937654.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"59041d54ccc747074b47dae4","title":"WEBTOON","description":"Webtoon 是一種始創於韓國的新概念網路漫畫，由「Web（網路）」及「Cartoon（漫畫、卡通）」組成，只需向上下滑動就能閱讀，不需翻頁，是一種專為電腦及行動裝置而設的漫畫。","thumb":{"originalName":"52a81f09-32a0-422b-bba3-207eb4d22520.jpg","path":"60c01af5-e9cd-4888-bf5c-89fb60a97cc7.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6e3","title":"禁書目錄","description":"未知","thumb":{"originalName":"禁書目錄.jpg","path":"c4314a3f-2644-473f-9b13-d78c8d857933.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5bd66e7e8ff47f7c46cf999d","title":"歐美","description":"歐美","thumb":{"fileServer":"https://storage1.picacomic.com","path":"0486b618-ccbb-4c77-a141-06351079eb9f.jpg","originalName":"67edd79c63e037afcd6309c25ad312a1.jpg"}},{"_id":"5821859b5f6b9a4f93dbf6e6","title":"Cosplay","description":"未知","thumb":{"originalName":"Cosplay.jpg","path":"24ee03b1-ad3d-4c6b-9f0f-83cc95365006.jpg","fileServer":"https://storage1.picacomic.com"}},{"_id":"5821859b5f6b9a4f93dbf6d5","title":"重口地帶","description":"未知","thumb":{"originalName":"重口地帶.jpg","path":"4540db04-ebbe-4834-a77a-7b7995b5f784.jpg","fileServer":"https://storage1.picacomic.com"}}]}
     */

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "categories=" + categories +
                    '}';
        }

        @SerializedName("categories")
        private List<CategoriesBean> categories;

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CategoriesBean {
            @Override
            public String toString() {
                return "CategoriesBean{" +
                        "title='" + title + '\'' +
                        ", thumb=" + thumb +
                        ", isWeb=" + isWeb +
                        ", active=" + active +
                        ", link='" + link + '\'' +
                        ", id='" + id + '\'' +
                        ", description='" + description + '\'' +
                        '}';
            }

            /**
             * title : 援助嗶咔
             * thumb : {"originalName":"help.jpg","path":"help.jpg","fileServer":"https://wikawika.xyz/static/"}
             * isWeb : true
             * active : true
             * link : https://donate.wikawika.xyz
             * _id : 5821859b5f6b9a4f93dbf6e9
             * description : 未知
             */

            @SerializedName("title")
            private String title;
            @SerializedName("thumb")
            private ThumbBean thumb;
            @SerializedName("isWeb")
            private boolean isWeb;
            @SerializedName("active")
            private boolean active;
            @SerializedName("link")
            private String link;
            @SerializedName("_id")
            private String id;
            @SerializedName("description")
            private String description;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public ThumbBean getThumb() {
                return thumb;
            }

            public void setThumb(ThumbBean thumb) {
                this.thumb = thumb;
            }

            public boolean isIsWeb() {
                return isWeb;
            }

            public void setIsWeb(boolean isWeb) {
                this.isWeb = isWeb;
            }

            public boolean isActive() {
                return active;
            }

            public void setActive(boolean active) {
                this.active = active;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public static class ThumbBean {
                /**
                 * originalName : help.jpg
                 * path : help.jpg
                 * fileServer : https://wikawika.xyz/static/
                 */

                @SerializedName("originalName")
                private String originalName;
                @SerializedName("path")
                private String path;
                @SerializedName("fileServer")
                private String fileServer;

                public String getOriginalName() {
                    return originalName;
                }

                public void setOriginalName(String originalName) {
                    this.originalName = originalName;
                }

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getFileServer() {
                    return fileServer;
                }

                public void setFileServer(String fileServer) {
                    this.fileServer = fileServer;
                }
            }
        }
    }
}
