package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// 一个分类
public class ComicsCategory {
    @Override
    public String toString() {
        return "ComicsCategory{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * code : 200
     * message : success
     * data : {"comics":{"docs":[{"_id":"6009a912f48dbe75ed001ad1","title":"還有空房嗎？","author":"ERO404&NUWARU&Sigma","totalViews":10475325,"totalLikes":98823,"pagesCount":2126,"epsCount":47,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/pJx8hbZyJ4LwOGepvhiflkP_tX0z-_-DCNyvlCC7RZY/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy84NDVkMTU4Yy00ZGY0LTQ1ZDYtYjhhYS0zNzBjMDExY2UxNmQuanBn.jpg","originalName":"IMG_20210121_021559.jpg"},"id":"6009a912f48dbe75ed001ad1","likesCount":98823},{"_id":"5fc12f7e3d22d50ffddb2ae6","title":"寄宿日记","author":"嫌疑犯H&金Zetta","totalViews":7939970,"totalLikes":57508,"pagesCount":2571,"epsCount":54,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/yjRUNNk9ZX-WZRRLCWjbVC4UI7majIncxhGTNbHpKh4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9lYmMyYzM1OC04NTNkLTRkNjgtYTUwYi03YzgzMGRlYzJlYzAuanBn.jpg","originalName":"BD.jpg"},"id":"5fc12f7e3d22d50ffddb2ae6","likesCount":57508},{"_id":"5fbbd2167caa82721defa8f6","title":"室友招募中 ","author":"Serious","totalViews":3211333,"totalLikes":17722,"pagesCount":2389,"epsCount":75,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/dgUoZ7zCUvOYcqOnQEKYsOgjoP9A15GylAxEmtP1GPo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy85M2IyNTFmZi03NzFiLTRiNWQtOTlhMC05NGIyNmRkYmExYTIuanBn.jpg","originalName":"IMG_20201123_232521.jpg"},"id":"5fbbd2167caa82721defa8f6","likesCount":17722},{"_id":"61d96abe6570ce4759608069","title":"現上教學","author":"Dable&鴨鴨","totalViews":173861,"totalLikes":2762,"pagesCount":384,"epsCount":12,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/tM33iuNS1EhShWeNqj3qU4IMdEK7rFZscCAnYTRGH9E/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jOTNmODdiNC01N2VjLTRjODktOTFjZC0wMGI0NjUzMjJhM2YuanBn.jpg","originalName":"pic_quark_1641632402087.jpg"},"id":"61d96abe6570ce4759608069","likesCount":2762},{"_id":"61fea18fd2a75c5040ae427d","title":"冬フロント (アイドルマスター シャイニーカラーズ) [無修正][中国翻訳]","author":"ツリサス","totalViews":27292,"totalLikes":139,"pagesCount":12,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","CG雜圖"],"thumb":{"originalName":"1.jpg","path":"tobeimg/xYwU-i09ezUwU5XY3r-RyzyrA3vMeEbGGfLwptGFTWo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83OGNmODg1ZC1kMmJmLTQzOGItYjY1Ny04MmY3NmRhZGM5YjkuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea18fd2a75c5040ae427d","likesCount":139},{"_id":"61fea0dd8c1ff5503819a79b","title":"(秋季例大祭7)  週末恋人ゆかりん (東方Project) [中国翻訳] [DL版]","author":"しもやけ堂 (逢魔刻壱)","totalViews":11094,"totalLikes":144,"pagesCount":21,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","東方","純愛"],"thumb":{"originalName":"01_p01.jpg","path":"tobeimg/S4XJCa-6fj2XzCxPsb-z6i3wdh4IFHwVKOgcp8MvNhI/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8wOWZkZjczYy1hMTY2LTQzOWQtODExZS1kYzFlMTBiMzliMDEuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea0dd8c1ff5503819a79b","likesCount":144},{"_id":"61c807589e46c934219a762e","title":"想跟时值青春期关系变得尴尬的青梅竹马拉近距离","author":"なえなえ","totalViews":89794,"totalLikes":1263,"pagesCount":117,"epsCount":13,"finished":false,"categories":["全彩","CG雜圖","長篇"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/lMJLVRM7pkSl2mCjxyA4d4Zv9Fw5nA1OCZRqWcCziig/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80N2RhYmU1MC1jNjhiLTRjZDItYjAzNy03YTQzMGQ4NmE4MmYuanBn.jpg","originalName":"Snipaste-12-25_12-05-47.jpg"},"id":"61c807589e46c934219a762e","likesCount":1263},{"_id":"61fea03f8c1ff5503819a525","title":" 友達の母親に慰めてもらう話 [DL版][中国翻訳]","author":"妄想エンジン","totalViews":27101,"totalLikes":256,"pagesCount":51,"epsCount":1,"finished":true,"categories":["同人","全彩"],"thumb":{"originalName":"01_mo000.jpg","path":"tobeimg/s3rDohvqSXNvLa5vOd4H2o2kZmWGodeEhnwf5Vl2WLM/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8xNmFiYTFlNi1iNWQ0LTQ0NWMtOWFhZi0zOGNkMTU4YjE2MGIuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea03f8c1ff5503819a525","likesCount":256},{"_id":"61fe9e45acc213501f83cab8","title":"モラ活","author":"Lovewn Outpost (BLADE)","totalViews":15842,"totalLikes":156,"pagesCount":14,"epsCount":1,"finished":false,"categories":["全彩","短篇","同人","生肉"],"thumb":{"originalName":"001.jpg","path":"tobeimg/DgvfxWieUobhmah2pXN9GUMO--baLVt4hLg0ExyANJA/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83ZTExOTRlOS1iYWY0LTQyMzgtOGUyYS04YzU2MTZmMmEzYWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9e45acc213501f83cab8","likesCount":156},{"_id":"61fe9c088c1ff5503819a311","title":"夢宮姉妹は落ちこぼれのサキュバス","author":"もものみプラス (もものみ)","totalViews":5435,"totalLikes":58,"pagesCount":19,"epsCount":1,"finished":false,"categories":["短篇","全彩","生肉"],"thumb":{"originalName":"0_001.jpg","path":"tobeimg/rOnEJkO4WENu04Bo2VNVqThu7nQmEfOXXGt5gpF19S4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jNjhmMDRiMC00NzM3LTRiMWEtYTJiMC0wZTE4MmI2OGEwNDYuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9c088c1ff5503819a311","likesCount":58},{"_id":"5ff075f8f62e385947f34596","title":"wool图集","author":"毛糸の森 (うーる)","totalViews":114198,"totalLikes":4601,"pagesCount":511,"epsCount":7,"finished":false,"categories":["長篇","CG雜圖","耽美花園","全彩","生肉"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/gvlYCj2fhpwTUTBxwRqVkFGsMz4cqBE0YpopYqiXKTQ/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy81Y2Y4ZDk4ZC1jZTRmLTRhMTMtODZkNy1mZGM0OTIxNjE5ODAuanBn.jpg","originalName":"wool (1).jpg"},"id":"5ff075f8f62e385947f34596","likesCount":4601},{"_id":"61fcd9b0acc213501f839e53","title":"Neko Neko Note 9 体育教師に弱み握られドスケベ教育セックスする本","author":"ネコゴショ (ヤナギユウ)","totalViews":23756,"totalLikes":180,"pagesCount":36,"epsCount":1,"finished":false,"categories":["短篇","生肉","全彩"],"thumb":{"originalName":"01_Neko_Neko_Note_9_001.jpg","path":"tobeimg/G6yIzUxqbKz8R4zXs16pwrXQVE-W0wJh8fqzQ73iU8c/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9kNzVmNWRlYi05N2IyLTQyY2EtOWJjMi1jMjI1NWY3NjU5OWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fcd9b0acc213501f839e53","likesCount":180}],"total":18171,"limit":20,"page":1,"pages":909}}
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
                    "comics=" + comics +
                    '}';
        }

        /**
         * comics : {"docs":[{"_id":"6009a912f48dbe75ed001ad1","title":"還有空房嗎？","author":"ERO404&NUWARU&Sigma","totalViews":10475325,"totalLikes":98823,"pagesCount":2126,"epsCount":47,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/pJx8hbZyJ4LwOGepvhiflkP_tX0z-_-DCNyvlCC7RZY/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy84NDVkMTU4Yy00ZGY0LTQ1ZDYtYjhhYS0zNzBjMDExY2UxNmQuanBn.jpg","originalName":"IMG_20210121_021559.jpg"},"id":"6009a912f48dbe75ed001ad1","likesCount":98823},{"_id":"5fc12f7e3d22d50ffddb2ae6","title":"寄宿日记","author":"嫌疑犯H&金Zetta","totalViews":7939970,"totalLikes":57508,"pagesCount":2571,"epsCount":54,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/yjRUNNk9ZX-WZRRLCWjbVC4UI7majIncxhGTNbHpKh4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9lYmMyYzM1OC04NTNkLTRkNjgtYTUwYi03YzgzMGRlYzJlYzAuanBn.jpg","originalName":"BD.jpg"},"id":"5fc12f7e3d22d50ffddb2ae6","likesCount":57508},{"_id":"5fbbd2167caa82721defa8f6","title":"室友招募中 ","author":"Serious","totalViews":3211333,"totalLikes":17722,"pagesCount":2389,"epsCount":75,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/dgUoZ7zCUvOYcqOnQEKYsOgjoP9A15GylAxEmtP1GPo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy85M2IyNTFmZi03NzFiLTRiNWQtOTlhMC05NGIyNmRkYmExYTIuanBn.jpg","originalName":"IMG_20201123_232521.jpg"},"id":"5fbbd2167caa82721defa8f6","likesCount":17722},{"_id":"61d96abe6570ce4759608069","title":"現上教學","author":"Dable&鴨鴨","totalViews":173861,"totalLikes":2762,"pagesCount":384,"epsCount":12,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/tM33iuNS1EhShWeNqj3qU4IMdEK7rFZscCAnYTRGH9E/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jOTNmODdiNC01N2VjLTRjODktOTFjZC0wMGI0NjUzMjJhM2YuanBn.jpg","originalName":"pic_quark_1641632402087.jpg"},"id":"61d96abe6570ce4759608069","likesCount":2762},{"_id":"61fea18fd2a75c5040ae427d","title":"冬フロント (アイドルマスター シャイニーカラーズ) [無修正][中国翻訳]","author":"ツリサス","totalViews":27292,"totalLikes":139,"pagesCount":12,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","CG雜圖"],"thumb":{"originalName":"1.jpg","path":"tobeimg/xYwU-i09ezUwU5XY3r-RyzyrA3vMeEbGGfLwptGFTWo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83OGNmODg1ZC1kMmJmLTQzOGItYjY1Ny04MmY3NmRhZGM5YjkuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea18fd2a75c5040ae427d","likesCount":139},{"_id":"61fea0dd8c1ff5503819a79b","title":"(秋季例大祭7)  週末恋人ゆかりん (東方Project) [中国翻訳] [DL版]","author":"しもやけ堂 (逢魔刻壱)","totalViews":11094,"totalLikes":144,"pagesCount":21,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","東方","純愛"],"thumb":{"originalName":"01_p01.jpg","path":"tobeimg/S4XJCa-6fj2XzCxPsb-z6i3wdh4IFHwVKOgcp8MvNhI/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8wOWZkZjczYy1hMTY2LTQzOWQtODExZS1kYzFlMTBiMzliMDEuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea0dd8c1ff5503819a79b","likesCount":144},{"_id":"61c807589e46c934219a762e","title":"想跟时值青春期关系变得尴尬的青梅竹马拉近距离","author":"なえなえ","totalViews":89794,"totalLikes":1263,"pagesCount":117,"epsCount":13,"finished":false,"categories":["全彩","CG雜圖","長篇"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/lMJLVRM7pkSl2mCjxyA4d4Zv9Fw5nA1OCZRqWcCziig/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80N2RhYmU1MC1jNjhiLTRjZDItYjAzNy03YTQzMGQ4NmE4MmYuanBn.jpg","originalName":"Snipaste-12-25_12-05-47.jpg"},"id":"61c807589e46c934219a762e","likesCount":1263},{"_id":"61fea03f8c1ff5503819a525","title":" 友達の母親に慰めてもらう話 [DL版][中国翻訳]","author":"妄想エンジン","totalViews":27101,"totalLikes":256,"pagesCount":51,"epsCount":1,"finished":true,"categories":["同人","全彩"],"thumb":{"originalName":"01_mo000.jpg","path":"tobeimg/s3rDohvqSXNvLa5vOd4H2o2kZmWGodeEhnwf5Vl2WLM/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8xNmFiYTFlNi1iNWQ0LTQ0NWMtOWFhZi0zOGNkMTU4YjE2MGIuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea03f8c1ff5503819a525","likesCount":256},{"_id":"61fe9e45acc213501f83cab8","title":"モラ活","author":"Lovewn Outpost (BLADE)","totalViews":15842,"totalLikes":156,"pagesCount":14,"epsCount":1,"finished":false,"categories":["全彩","短篇","同人","生肉"],"thumb":{"originalName":"001.jpg","path":"tobeimg/DgvfxWieUobhmah2pXN9GUMO--baLVt4hLg0ExyANJA/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83ZTExOTRlOS1iYWY0LTQyMzgtOGUyYS04YzU2MTZmMmEzYWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9e45acc213501f83cab8","likesCount":156},{"_id":"61fe9c088c1ff5503819a311","title":"夢宮姉妹は落ちこぼれのサキュバス","author":"もものみプラス (もものみ)","totalViews":5435,"totalLikes":58,"pagesCount":19,"epsCount":1,"finished":false,"categories":["短篇","全彩","生肉"],"thumb":{"originalName":"0_001.jpg","path":"tobeimg/rOnEJkO4WENu04Bo2VNVqThu7nQmEfOXXGt5gpF19S4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jNjhmMDRiMC00NzM3LTRiMWEtYTJiMC0wZTE4MmI2OGEwNDYuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9c088c1ff5503819a311","likesCount":58},{"_id":"5ff075f8f62e385947f34596","title":"wool图集","author":"毛糸の森 (うーる)","totalViews":114198,"totalLikes":4601,"pagesCount":511,"epsCount":7,"finished":false,"categories":["長篇","CG雜圖","耽美花園","全彩","生肉"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/gvlYCj2fhpwTUTBxwRqVkFGsMz4cqBE0YpopYqiXKTQ/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy81Y2Y4ZDk4ZC1jZTRmLTRhMTMtODZkNy1mZGM0OTIxNjE5ODAuanBn.jpg","originalName":"wool (1).jpg"},"id":"5ff075f8f62e385947f34596","likesCount":4601},{"_id":"61fcd9b0acc213501f839e53","title":"Neko Neko Note 9 体育教師に弱み握られドスケベ教育セックスする本","author":"ネコゴショ (ヤナギユウ)","totalViews":23756,"totalLikes":180,"pagesCount":36,"epsCount":1,"finished":false,"categories":["短篇","生肉","全彩"],"thumb":{"originalName":"01_Neko_Neko_Note_9_001.jpg","path":"tobeimg/G6yIzUxqbKz8R4zXs16pwrXQVE-W0wJh8fqzQ73iU8c/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9kNzVmNWRlYi05N2IyLTQyY2EtOWJjMi1jMjI1NWY3NjU5OWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fcd9b0acc213501f839e53","likesCount":180}],"total":18171,"limit":20,"page":1,"pages":909}
         */


        @SerializedName("comics")
        private ComicsBean comics;

        public ComicsBean getComics() {
            return comics;
        }

        public void setComics(ComicsBean comics) {
            this.comics = comics;
        }

        public static class ComicsBean {
            @Override
            public String toString() {
                return "ComicsBean{" +
                        "total=" + total +
                        ", limit=" + limit +
                        ", page=" + page +
                        ", pages=" + pages +
                        ", docs=" + docs +
                        '}';
            }

            /**
             * docs : [{"_id":"6009a912f48dbe75ed001ad1","title":"還有空房嗎？","author":"ERO404&NUWARU&Sigma","totalViews":10475325,"totalLikes":98823,"pagesCount":2126,"epsCount":47,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/pJx8hbZyJ4LwOGepvhiflkP_tX0z-_-DCNyvlCC7RZY/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy84NDVkMTU4Yy00ZGY0LTQ1ZDYtYjhhYS0zNzBjMDExY2UxNmQuanBn.jpg","originalName":"IMG_20210121_021559.jpg"},"id":"6009a912f48dbe75ed001ad1","likesCount":98823},{"_id":"5fc12f7e3d22d50ffddb2ae6","title":"寄宿日记","author":"嫌疑犯H&金Zetta","totalViews":7939970,"totalLikes":57508,"pagesCount":2571,"epsCount":54,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/yjRUNNk9ZX-WZRRLCWjbVC4UI7majIncxhGTNbHpKh4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9lYmMyYzM1OC04NTNkLTRkNjgtYTUwYi03YzgzMGRlYzJlYzAuanBn.jpg","originalName":"BD.jpg"},"id":"5fc12f7e3d22d50ffddb2ae6","likesCount":57508},{"_id":"5fbbd2167caa82721defa8f6","title":"室友招募中 ","author":"Serious","totalViews":3211333,"totalLikes":17722,"pagesCount":2389,"epsCount":75,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/dgUoZ7zCUvOYcqOnQEKYsOgjoP9A15GylAxEmtP1GPo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy85M2IyNTFmZi03NzFiLTRiNWQtOTlhMC05NGIyNmRkYmExYTIuanBn.jpg","originalName":"IMG_20201123_232521.jpg"},"id":"5fbbd2167caa82721defa8f6","likesCount":17722},{"_id":"61d96abe6570ce4759608069","title":"現上教學","author":"Dable&鴨鴨","totalViews":173861,"totalLikes":2762,"pagesCount":384,"epsCount":12,"finished":false,"categories":["全彩","長篇","WEBTOON"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/tM33iuNS1EhShWeNqj3qU4IMdEK7rFZscCAnYTRGH9E/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jOTNmODdiNC01N2VjLTRjODktOTFjZC0wMGI0NjUzMjJhM2YuanBn.jpg","originalName":"pic_quark_1641632402087.jpg"},"id":"61d96abe6570ce4759608069","likesCount":2762},{"_id":"61fea18fd2a75c5040ae427d","title":"冬フロント (アイドルマスター シャイニーカラーズ) [無修正][中国翻訳]","author":"ツリサス","totalViews":27292,"totalLikes":139,"pagesCount":12,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","CG雜圖"],"thumb":{"originalName":"1.jpg","path":"tobeimg/xYwU-i09ezUwU5XY3r-RyzyrA3vMeEbGGfLwptGFTWo/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83OGNmODg1ZC1kMmJmLTQzOGItYjY1Ny04MmY3NmRhZGM5YjkuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea18fd2a75c5040ae427d","likesCount":139},{"_id":"61fea0dd8c1ff5503819a79b","title":"(秋季例大祭7)  週末恋人ゆかりん (東方Project) [中国翻訳] [DL版]","author":"しもやけ堂 (逢魔刻壱)","totalViews":11094,"totalLikes":144,"pagesCount":21,"epsCount":1,"finished":true,"categories":["全彩","短篇","同人","東方","純愛"],"thumb":{"originalName":"01_p01.jpg","path":"tobeimg/S4XJCa-6fj2XzCxPsb-z6i3wdh4IFHwVKOgcp8MvNhI/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8wOWZkZjczYy1hMTY2LTQzOWQtODExZS1kYzFlMTBiMzliMDEuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea0dd8c1ff5503819a79b","likesCount":144},{"_id":"61c807589e46c934219a762e","title":"想跟时值青春期关系变得尴尬的青梅竹马拉近距离","author":"なえなえ","totalViews":89794,"totalLikes":1263,"pagesCount":117,"epsCount":13,"finished":false,"categories":["全彩","CG雜圖","長篇"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/lMJLVRM7pkSl2mCjxyA4d4Zv9Fw5nA1OCZRqWcCziig/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80N2RhYmU1MC1jNjhiLTRjZDItYjAzNy03YTQzMGQ4NmE4MmYuanBn.jpg","originalName":"Snipaste-12-25_12-05-47.jpg"},"id":"61c807589e46c934219a762e","likesCount":1263},{"_id":"61fea03f8c1ff5503819a525","title":" 友達の母親に慰めてもらう話 [DL版][中国翻訳]","author":"妄想エンジン","totalViews":27101,"totalLikes":256,"pagesCount":51,"epsCount":1,"finished":true,"categories":["同人","全彩"],"thumb":{"originalName":"01_mo000.jpg","path":"tobeimg/s3rDohvqSXNvLa5vOd4H2o2kZmWGodeEhnwf5Vl2WLM/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy8xNmFiYTFlNi1iNWQ0LTQ0NWMtOWFhZi0zOGNkMTU4YjE2MGIuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fea03f8c1ff5503819a525","likesCount":256},{"_id":"61fe9e45acc213501f83cab8","title":"モラ活","author":"Lovewn Outpost (BLADE)","totalViews":15842,"totalLikes":156,"pagesCount":14,"epsCount":1,"finished":false,"categories":["全彩","短篇","同人","生肉"],"thumb":{"originalName":"001.jpg","path":"tobeimg/DgvfxWieUobhmah2pXN9GUMO--baLVt4hLg0ExyANJA/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy83ZTExOTRlOS1iYWY0LTQyMzgtOGUyYS04YzU2MTZmMmEzYWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9e45acc213501f83cab8","likesCount":156},{"_id":"61fe9c088c1ff5503819a311","title":"夢宮姉妹は落ちこぼれのサキュバス","author":"もものみプラス (もものみ)","totalViews":5435,"totalLikes":58,"pagesCount":19,"epsCount":1,"finished":false,"categories":["短篇","全彩","生肉"],"thumb":{"originalName":"0_001.jpg","path":"tobeimg/rOnEJkO4WENu04Bo2VNVqThu7nQmEfOXXGt5gpF19S4/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9jNjhmMDRiMC00NzM3LTRiMWEtYTJiMC0wZTE4MmI2OGEwNDYuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fe9c088c1ff5503819a311","likesCount":58},{"_id":"5ff075f8f62e385947f34596","title":"wool图集","author":"毛糸の森 (うーる)","totalViews":114198,"totalLikes":4601,"pagesCount":511,"epsCount":7,"finished":false,"categories":["長篇","CG雜圖","耽美花園","全彩","生肉"],"thumb":{"fileServer":"https://storage1.picacomic.com","path":"tobeimg/gvlYCj2fhpwTUTBxwRqVkFGsMz4cqBE0YpopYqiXKTQ/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy81Y2Y4ZDk4ZC1jZTRmLTRhMTMtODZkNy1mZGM0OTIxNjE5ODAuanBn.jpg","originalName":"wool (1).jpg"},"id":"5ff075f8f62e385947f34596","likesCount":4601},{"_id":"61fcd9b0acc213501f839e53","title":"Neko Neko Note 9 体育教師に弱み握られドスケベ教育セックスする本","author":"ネコゴショ (ヤナギユウ)","totalViews":23756,"totalLikes":180,"pagesCount":36,"epsCount":1,"finished":false,"categories":["短篇","生肉","全彩"],"thumb":{"originalName":"01_Neko_Neko_Note_9_001.jpg","path":"tobeimg/G6yIzUxqbKz8R4zXs16pwrXQVE-W0wJh8fqzQ73iU8c/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy9kNzVmNWRlYi05N2IyLTQyY2EtOWJjMi1jMjI1NWY3NjU5OWUuanBn.jpg","fileServer":"https://storage1.picacomic.com"},"id":"61fcd9b0acc213501f839e53","likesCount":180}]
             * total : 18171
             * limit : 20
             * page : 1
             * pages : 909
             */


            @SerializedName("total")
            private int total;
            @SerializedName("limit")
            private int limit;
            @SerializedName("page")
            private int page;
            @SerializedName("pages")
            private int pages;
            @SerializedName("docs")
            private List<DocsBean> docs;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public List<DocsBean> getDocs() {
                return docs;
            }

            public void setDocs(List<DocsBean> docs) {
                this.docs = docs;
            }

            public static class DocsBean {
                @Override
                public String toString() {
                    return "DocsBean{" +
                            "id1='" + id1 + '\'' +
                            ", title='" + title + '\'' +
                            ", author='" + author + '\'' +
                            ", totalViews=" + totalViews +
                            ", totalLikes=" + totalLikes +
                            ", pagesCount=" + pagesCount +
                            ", epsCount=" + epsCount +
                            ", finished=" + finished +
                            ", thumb=" + thumb +
                            ", id2='" + id2 + '\'' +
                            ", likesCount=" + likesCount +
                            ", categories=" + categories +
                            '}';
                }

                /**
                 * _id : 6009a912f48dbe75ed001ad1
                 * title : 還有空房嗎？
                 * author : ERO404&NUWARU&Sigma
                 * totalViews : 10475325
                 * totalLikes : 98823
                 * pagesCount : 2126
                 * epsCount : 47
                 * finished : false
                 * categories : ["全彩","長篇","WEBTOON"]
                 * thumb : {"fileServer":"https://storage1.picacomic.com","path":"tobeimg/pJx8hbZyJ4LwOGepvhiflkP_tX0z-_-DCNyvlCC7RZY/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy84NDVkMTU4Yy00ZGY0LTQ1ZDYtYjhhYS0zNzBjMDExY2UxNmQuanBn.jpg","originalName":"IMG_20210121_021559.jpg"}
                 * id : 6009a912f48dbe75ed001ad1
                 * likesCount : 98823
                 */


                @SerializedName("_id")
                private String id1;
                @SerializedName("title")
                private String title;
                @SerializedName("author")
                private String author;
                @SerializedName("totalViews")
                private int totalViews;
                @SerializedName("totalLikes")
                private int totalLikes;
                @SerializedName("pagesCount")
                private int pagesCount;
                @SerializedName("epsCount")
                private int epsCount;
                @SerializedName("finished")
                private boolean finished;
                @SerializedName("thumb")
                private ThumbBean thumb;
                @SerializedName("id")
                private String id2;
                @SerializedName("likesCount")
                private int likesCount;
                @SerializedName("categories")
                private List<String> categories;

                public String getId1() {
                    return id1;
                }

                public void setId1(String id1) {
                    this.id1 = id1;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public int getTotalViews() {
                    return totalViews;
                }

                public void setTotalViews(int totalViews) {
                    this.totalViews = totalViews;
                }

                public int getTotalLikes() {
                    return totalLikes;
                }

                public void setTotalLikes(int totalLikes) {
                    this.totalLikes = totalLikes;
                }

                public int getPagesCount() {
                    return pagesCount;
                }

                public void setPagesCount(int pagesCount) {
                    this.pagesCount = pagesCount;
                }

                public int getEpsCount() {
                    return epsCount;
                }

                public void setEpsCount(int epsCount) {
                    this.epsCount = epsCount;
                }

                public boolean isFinished() {
                    return finished;
                }

                public void setFinished(boolean finished) {
                    this.finished = finished;
                }

                public ThumbBean getThumb() {
                    return thumb;
                }

                public void setThumb(ThumbBean thumb) {
                    this.thumb = thumb;
                }

                public String getId2() {
                    return id1;
                }

                public void setId2(String id2) {
                    this.id1 = id2;
                }

                public int getLikesCount() {
                    return likesCount;
                }

                public void setLikesCount(int likesCount) {
                    this.likesCount = likesCount;
                }

                public List<String> getCategories() {
                    return categories;
                }

                public void setCategories(List<String> categories) {
                    this.categories = categories;
                }

                public static class ThumbBean {
                    @Override
                    public String toString() {
                        return "ThumbBean{" +
                                "fileServer='" + fileServer + '\'' +
                                ", path='" + path + '\'' +
                                ", originalName='" + originalName + '\'' +
                                '}';
                    }

                    /**
                     * fileServer : https://storage1.picacomic.com
                     * path : tobeimg/pJx8hbZyJ4LwOGepvhiflkP_tX0z-_-DCNyvlCC7RZY/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy84NDVkMTU4Yy00ZGY0LTQ1ZDYtYjhhYS0zNzBjMDExY2UxNmQuanBn.jpg
                     * originalName : IMG_20210121_021559.jpg
                     */



                    @SerializedName("fileServer")
                    private String fileServer;
                    @SerializedName("path")
                    private String path;
                    @SerializedName("originalName")
                    private String originalName;

                    public String getFileServer() {
                        return fileServer;
                    }

                    public void setFileServer(String fileServer) {
                        this.fileServer = fileServer;
                    }

                    public String getPath() {
                        return path;
                    }

                    public void setPath(String path) {
                        this.path = path;
                    }

                    public String getOriginalName() {
                        return originalName;
                    }

                    public void setOriginalName(String originalName) {
                        this.originalName = originalName;
                    }
                }
            }
        }
    }
}
