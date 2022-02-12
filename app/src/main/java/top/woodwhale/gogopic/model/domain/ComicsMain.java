package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsMain {

    /**
     * code : 200
     * message : success
     * data : {"comic":{"_id":"6203d92fa4bc14602717b75a","_creator":{"_id":"593019d53f532059f297efa7","gender":"m","name":"黎欧","verified":false,"exp":2833954,"level":168,"characters":["knight"],"role":"knight","title":"萌新","avatar":{"originalName":"avatar.jpg","path":"3872737e-e52c-4398-9ecd-654cbb486d5d.jpg","fileServer":"https://storage1.picacomic.com"},"slogan":"二八七六八七八三九二（QQ代传邮箱，请标注来意不然我只能无视了 。来私自要本的还请歇歇吧，我不会提供转售服务。）代传传的，如果急着要上的说一声。","character":"https://pica-web.wakamoment.tk/images/halloween_m.png"},"title":"(C99)  サイ眠原神まとめ本 (原神)","description":"剧情向","thumb":{"originalName":"001.jpg","path":"tobeimg/ks_3FyRFkXm-j7cC5CCaJH90vyZdfk-7AzuKfcZ_Xlc/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80MDJhYzliMi0wMWY1LTRlMzUtOWY2ZC0zMDc2ZGUzMDdiMDguanBn.jpg","fileServer":"https://storage1.picacomic.com"},"author":"銀河系PRIDE (B-銀河)","chineseTeam":"无","categories":["長篇","全彩","同人","CG雜圖","生肉"],"tags":["原神:甘雨","原神","巨乳","中出","橫切面","蹦壞臉","洗腦 / 催眠","原神:琴","髮交","亂交","阿嘿顏","顏射","連褲絲襪","原神:珊瑚宮心海","原神:雷電將軍","乳交"],"pagesCount":78,"epsCount":1,"finished":true,"updated_at":"2022-02-09T15:09:35.003Z","created_at":"2022-02-09T12:28:38.590Z","allowDownload":true,"allowComment":true,"totalLikes":1686,"totalViews":214375,"viewsCount":214375,"likesCount":1686,"isFavourite":false,"isLiked":false,"commentsCount":66}}
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
        /**
         * comic : {"_id":"6203d92fa4bc14602717b75a","_creator":{"_id":"593019d53f532059f297efa7","gender":"m","name":"黎欧","verified":false,"exp":2833954,"level":168,"characters":["knight"],"role":"knight","title":"萌新","avatar":{"originalName":"avatar.jpg","path":"3872737e-e52c-4398-9ecd-654cbb486d5d.jpg","fileServer":"https://storage1.picacomic.com"},"slogan":"二八七六八七八三九二（QQ代传邮箱，请标注来意不然我只能无视了 。来私自要本的还请歇歇吧，我不会提供转售服务。）代传传的，如果急着要上的说一声。","character":"https://pica-web.wakamoment.tk/images/halloween_m.png"},"title":"(C99)  サイ眠原神まとめ本 (原神)","description":"剧情向","thumb":{"originalName":"001.jpg","path":"tobeimg/ks_3FyRFkXm-j7cC5CCaJH90vyZdfk-7AzuKfcZ_Xlc/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80MDJhYzliMi0wMWY1LTRlMzUtOWY2ZC0zMDc2ZGUzMDdiMDguanBn.jpg","fileServer":"https://storage1.picacomic.com"},"author":"銀河系PRIDE (B-銀河)","chineseTeam":"无","categories":["長篇","全彩","同人","CG雜圖","生肉"],"tags":["原神:甘雨","原神","巨乳","中出","橫切面","蹦壞臉","洗腦 / 催眠","原神:琴","髮交","亂交","阿嘿顏","顏射","連褲絲襪","原神:珊瑚宮心海","原神:雷電將軍","乳交"],"pagesCount":78,"epsCount":1,"finished":true,"updated_at":"2022-02-09T15:09:35.003Z","created_at":"2022-02-09T12:28:38.590Z","allowDownload":true,"allowComment":true,"totalLikes":1686,"totalViews":214375,"viewsCount":214375,"likesCount":1686,"isFavourite":false,"isLiked":false,"commentsCount":66}
         */

        @SerializedName("comic")
        private ComicBean comic;

        public ComicBean getComic() {
            return comic;
        }

        public void setComic(ComicBean comic) {
            this.comic = comic;
        }

        public static class ComicBean {
            /**
             * _id : 6203d92fa4bc14602717b75a
             * _creator : {"_id":"593019d53f532059f297efa7","gender":"m","name":"黎欧","verified":false,"exp":2833954,"level":168,"characters":["knight"],"role":"knight","title":"萌新","avatar":{"originalName":"avatar.jpg","path":"3872737e-e52c-4398-9ecd-654cbb486d5d.jpg","fileServer":"https://storage1.picacomic.com"},"slogan":"二八七六八七八三九二（QQ代传邮箱，请标注来意不然我只能无视了 。来私自要本的还请歇歇吧，我不会提供转售服务。）代传传的，如果急着要上的说一声。","character":"https://pica-web.wakamoment.tk/images/halloween_m.png"}
             * title : (C99)  サイ眠原神まとめ本 (原神)
             * description : 剧情向
             * thumb : {"originalName":"001.jpg","path":"tobeimg/ks_3FyRFkXm-j7cC5CCaJH90vyZdfk-7AzuKfcZ_Xlc/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80MDJhYzliMi0wMWY1LTRlMzUtOWY2ZC0zMDc2ZGUzMDdiMDguanBn.jpg","fileServer":"https://storage1.picacomic.com"}
             * author : 銀河系PRIDE (B-銀河)
             * chineseTeam : 无
             * categories : ["長篇","全彩","同人","CG雜圖","生肉"]
             * tags : ["原神:甘雨","原神","巨乳","中出","橫切面","蹦壞臉","洗腦 / 催眠","原神:琴","髮交","亂交","阿嘿顏","顏射","連褲絲襪","原神:珊瑚宮心海","原神:雷電將軍","乳交"]
             * pagesCount : 78
             * epsCount : 1
             * finished : true
             * updated_at : 2022-02-09T15:09:35.003Z
             * created_at : 2022-02-09T12:28:38.590Z
             * allowDownload : true
             * allowComment : true
             * totalLikes : 1686
             * totalViews : 214375
             * viewsCount : 214375
             * likesCount : 1686
             * isFavourite : false
             * isLiked : false
             * commentsCount : 66
             */

            @SerializedName("_id")
            private String id;
            @SerializedName("_creator")
            private CreatorBean creator;
            @SerializedName("title")
            private String title;
            @SerializedName("description")
            private String description;
            @SerializedName("thumb")
            private ThumbBean thumb;
            @SerializedName("author")
            private String author;
            @SerializedName("chineseTeam")
            private String chineseTeam;
            @SerializedName("pagesCount")
            private int pagesCount;
            @SerializedName("epsCount")
            private int epsCount;
            @SerializedName("finished")
            private boolean finished;
            @SerializedName("updated_at")
            private String updatedAt;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("allowDownload")
            private boolean allowDownload;
            @SerializedName("allowComment")
            private boolean allowComment;
            @SerializedName("totalLikes")
            private int totalLikes;
            @SerializedName("totalViews")
            private int totalViews;
            @SerializedName("viewsCount")
            private int viewsCount;
            @SerializedName("likesCount")
            private int likesCount;
            @SerializedName("isFavourite")
            private boolean isFavourite;
            @SerializedName("isLiked")
            private boolean isLiked;
            @SerializedName("commentsCount")
            private int commentsCount;
            @SerializedName("categories")
            private List<String> categories;
            @SerializedName("tags")
            private List<String> tags;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public CreatorBean getCreator() {
                return creator;
            }

            public void setCreator(CreatorBean creator) {
                this.creator = creator;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public ThumbBean getThumb() {
                return thumb;
            }

            public void setThumb(ThumbBean thumb) {
                this.thumb = thumb;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getChineseTeam() {
                return chineseTeam;
            }

            public void setChineseTeam(String chineseTeam) {
                this.chineseTeam = chineseTeam;
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

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public boolean isAllowDownload() {
                return allowDownload;
            }

            public void setAllowDownload(boolean allowDownload) {
                this.allowDownload = allowDownload;
            }

            public boolean isAllowComment() {
                return allowComment;
            }

            public void setAllowComment(boolean allowComment) {
                this.allowComment = allowComment;
            }

            public int getTotalLikes() {
                return totalLikes;
            }

            public void setTotalLikes(int totalLikes) {
                this.totalLikes = totalLikes;
            }

            public int getTotalViews() {
                return totalViews;
            }

            public void setTotalViews(int totalViews) {
                this.totalViews = totalViews;
            }

            public int getViewsCount() {
                return viewsCount;
            }

            public void setViewsCount(int viewsCount) {
                this.viewsCount = viewsCount;
            }

            public int getLikesCount() {
                return likesCount;
            }

            public void setLikesCount(int likesCount) {
                this.likesCount = likesCount;
            }

            public boolean isIsFavourite() {
                return isFavourite;
            }

            public void setIsFavourite(boolean isFavourite) {
                this.isFavourite = isFavourite;
            }

            public boolean isIsLiked() {
                return isLiked;
            }

            public void setIsLiked(boolean isLiked) {
                this.isLiked = isLiked;
            }

            public int getCommentsCount() {
                return commentsCount;
            }

            public void setCommentsCount(int commentsCount) {
                this.commentsCount = commentsCount;
            }

            public List<String> getCategories() {
                return categories;
            }

            public void setCategories(List<String> categories) {
                this.categories = categories;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public static class CreatorBean {
                /**
                 * _id : 593019d53f532059f297efa7
                 * gender : m
                 * name : 黎欧
                 * verified : false
                 * exp : 2833954
                 * level : 168
                 * characters : ["knight"]
                 * role : knight
                 * title : 萌新
                 * avatar : {"originalName":"avatar.jpg","path":"3872737e-e52c-4398-9ecd-654cbb486d5d.jpg","fileServer":"https://storage1.picacomic.com"}
                 * slogan : 二八七六八七八三九二（QQ代传邮箱，请标注来意不然我只能无视了 。来私自要本的还请歇歇吧，我不会提供转售服务。）代传传的，如果急着要上的说一声。
                 * character : https://pica-web.wakamoment.tk/images/halloween_m.png
                 */

                @SerializedName("_id")
                private String id;
                @SerializedName("gender")
                private String gender;
                @SerializedName("name")
                private String name;
                @SerializedName("verified")
                private boolean verified;
                @SerializedName("exp")
                private int exp;
                @SerializedName("level")
                private int level;
                @SerializedName("role")
                private String role;
                @SerializedName("title")
                private String title;
                @SerializedName("avatar")
                private AvatarBean avatar;
                @SerializedName("slogan")
                private String slogan;
                @SerializedName("character")
                private String character;
                @SerializedName("characters")
                private List<String> characters;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public boolean isVerified() {
                    return verified;
                }

                public void setVerified(boolean verified) {
                    this.verified = verified;
                }

                public int getExp() {
                    return exp;
                }

                public void setExp(int exp) {
                    this.exp = exp;
                }

                public int getLevel() {
                    return level;
                }

                public void setLevel(int level) {
                    this.level = level;
                }

                public String getRole() {
                    return role;
                }

                public void setRole(String role) {
                    this.role = role;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public AvatarBean getAvatar() {
                    return avatar;
                }

                public void setAvatar(AvatarBean avatar) {
                    this.avatar = avatar;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public String getCharacter() {
                    return character;
                }

                public void setCharacter(String character) {
                    this.character = character;
                }

                public List<String> getCharacters() {
                    return characters;
                }

                public void setCharacters(List<String> characters) {
                    this.characters = characters;
                }

                public static class AvatarBean {
                    /**
                     * originalName : avatar.jpg
                     * path : 3872737e-e52c-4398-9ecd-654cbb486d5d.jpg
                     * fileServer : https://storage1.picacomic.com
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

            public static class ThumbBean {
                /**
                 * originalName : 001.jpg
                 * path : tobeimg/ks_3FyRFkXm-j7cC5CCaJH90vyZdfk-7AzuKfcZ_Xlc/fill/300/400/sm/0/aHR0cHM6Ly9zdG9yYWdlMS5waWNhY29taWMuY29tL3N0YXRpYy80MDJhYzliMi0wMWY1LTRlMzUtOWY2ZC0zMDc2ZGUzMDdiMDguanBn.jpg
                 * fileServer : https://storage1.picacomic.com
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
