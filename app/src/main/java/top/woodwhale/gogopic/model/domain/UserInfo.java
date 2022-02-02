package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfo {

    /**
     * code : 200
     * message : success
     * data : {"user":{"_id":"59514ab73bf93c65bc241f1b","birthday":"1995-03-03T00:00:00.000Z","email":"1966890773","gender":"m","name":"荆集","slogan":"有着大雕的妹子","title":"萌新","verified":false,"exp":860,"level":3,"characters":[],"created_at":"2017-06-26T17:56:07.763Z","avatar":{"originalName":"avatar.jpg","path":"2416bbee-5a9a-4a3b-8800-54c5e1c96fd4.jpg","fileServer":"https://storage1.picacomic.com"},"isPunched":true}}
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
         * user : {"_id":"59514ab73bf93c65bc241f1b","birthday":"1995-03-03T00:00:00.000Z","email":"1966890773","gender":"m","name":"荆集","slogan":"有着大雕的妹子","title":"萌新","verified":false,"exp":860,"level":3,"characters":[],"created_at":"2017-06-26T17:56:07.763Z","avatar":{"originalName":"avatar.jpg","path":"2416bbee-5a9a-4a3b-8800-54c5e1c96fd4.jpg","fileServer":"https://storage1.picacomic.com"},"isPunched":true}
         */

        @SerializedName("user")
        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * _id : 59514ab73bf93c65bc241f1b
             * birthday : 1995-03-03T00:00:00.000Z
             * email : 1966890773
             * gender : m
             * name : 荆集
             * slogan : 有着大雕的妹子
             * title : 萌新
             * verified : false
             * exp : 860
             * level : 3
             * characters : []
             * created_at : 2017-06-26T17:56:07.763Z
             * avatar : {"originalName":"avatar.jpg","path":"2416bbee-5a9a-4a3b-8800-54c5e1c96fd4.jpg","fileServer":"https://storage1.picacomic.com"}
             * isPunched : true
             */

            @SerializedName("_id")
            private String id;
            @SerializedName("birthday")
            private String birthday;
            @SerializedName("email")
            private String email;
            @SerializedName("gender")
            private String gender;
            @SerializedName("name")
            private String name;
            @SerializedName("slogan")
            private String slogan;
            @SerializedName("title")
            private String title;
            @SerializedName("verified")
            private boolean verified;
            @SerializedName("exp")
            private int exp;
            @SerializedName("level")
            private int level;
            @SerializedName("created_at")
            private String createdAt;
            @SerializedName("avatar")
            private AvatarBean avatar;
            @SerializedName("isPunched")
            private boolean isPunched;
            @SerializedName("characters")
            private List<?> characters;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public AvatarBean getAvatar() {
                return avatar;
            }

            public void setAvatar(AvatarBean avatar) {
                this.avatar = avatar;
            }

            public boolean isIsPunched() {
                return isPunched;
            }

            public void setIsPunched(boolean isPunched) {
                this.isPunched = isPunched;
            }

            public List<?> getCharacters() {
                return characters;
            }

            public void setCharacters(List<?> characters) {
                this.characters = characters;
            }

            public static class AvatarBean {
                /**
                 * originalName : avatar.jpg
                 * path : 2416bbee-5a9a-4a3b-8800-54c5e1c96fd4.jpg
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
