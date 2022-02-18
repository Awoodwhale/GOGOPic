package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsBook {
    /**
     * code : 200
     * message : success
     * data : {"pages":{"docs":[{"_id":"620bc002e950bd601312cc14","media":{"originalName":"01_GMKJ_Le_Malin_00.jpg","path":"361e5b9f-bd06-446d-a8f7-83039ad69ba0.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc14"},{"_id":"620bc002e950bd601312cc15","media":{"originalName":"02_GMKJ_Le_Malin_0.jpg","path":"00e10ec4-1174-4115-8c3f-4059cee7aa06.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc15"},{"_id":"620bc002e950bd601312cc16","media":{"originalName":"03_GMKJ_Le_Malin_1.jpg","path":"655c1ea6-a8ee-42c6-b757-358fc19e880d.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc16"},{"_id":"620bc002e950bd601312cc17","media":{"originalName":"04_GMKJ_Le_Malin_2.jpg","path":"c8295fc5-909e-4771-87d4-c4b0ceaa5e8b.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc17"},{"_id":"620bc002e950bd601312cc18","media":{"originalName":"05_GMKJ_Le_Malin_3.jpg","path":"6ed4c4a4-3c54-463b-8f3a-749a982286b3.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc18"},{"_id":"620bc002e950bd601312cc19","media":{"originalName":"06_GMKJ_Le_Malin_4.jpg","path":"17584ab5-7ba2-4670-85b0-9072ff9f482f.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc19"},{"_id":"620bc002e950bd601312cc1a","media":{"originalName":"07_GMKJ_Le_Malin_5.jpg","path":"3e0b6707-f196-442e-8779-bc0b23d3cc18.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1a"},{"_id":"620bc002e950bd601312cc1b","media":{"originalName":"08_GMKJ_Le_Malin_6.jpg","path":"c4094abe-4378-426c-85ab-04c12d239c36.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1b"},{"_id":"620bc002e950bd601312cc1c","media":{"originalName":"09_GMKJ_Le_Malin_7.jpg","path":"ac74cccc-768f-4e4e-a57c-c2916ee6ca76.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1c"},{"_id":"620bc002e950bd601312cc1d","media":{"originalName":"10_GMKJ_Le_Malin_8.jpg","path":"9114b96e-035a-494e-b730-8153e4d79128.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1d"},{"_id":"620bc002e950bd601312cc1e","media":{"originalName":"11_GMKJ_Le_Malin_9.jpg","path":"f3f17a91-97c2-490d-b507-6971de469dc5.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1e"},{"_id":"620bc002e950bd601312cc1f","media":{"originalName":"12_GMKJ_Le_Malin_10.jpg","path":"a763333b-5fd0-4146-bbd6-a00961fe4fcc.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1f"},{"_id":"620bc002e950bd601312cc20","media":{"originalName":"13_GMKJ_Le_Malin_11.jpg","path":"caf3b623-11c1-4501-974f-e31616760597.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc20"},{"_id":"620bc002e950bd601312cc21","media":{"originalName":"14_GMKJ_Le_Malin_12.jpg","path":"de43bf63-d91e-4c7a-a567-a7012165518a.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc21"}],"total":14,"limit":40,"page":1,"pages":1},"ep":{"_id":"620bc002e950bd601312cc12","title":"第1話"}}
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
         * pages : {"docs":[{"_id":"620bc002e950bd601312cc14","media":{"originalName":"01_GMKJ_Le_Malin_00.jpg","path":"361e5b9f-bd06-446d-a8f7-83039ad69ba0.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc14"},{"_id":"620bc002e950bd601312cc15","media":{"originalName":"02_GMKJ_Le_Malin_0.jpg","path":"00e10ec4-1174-4115-8c3f-4059cee7aa06.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc15"},{"_id":"620bc002e950bd601312cc16","media":{"originalName":"03_GMKJ_Le_Malin_1.jpg","path":"655c1ea6-a8ee-42c6-b757-358fc19e880d.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc16"},{"_id":"620bc002e950bd601312cc17","media":{"originalName":"04_GMKJ_Le_Malin_2.jpg","path":"c8295fc5-909e-4771-87d4-c4b0ceaa5e8b.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc17"},{"_id":"620bc002e950bd601312cc18","media":{"originalName":"05_GMKJ_Le_Malin_3.jpg","path":"6ed4c4a4-3c54-463b-8f3a-749a982286b3.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc18"},{"_id":"620bc002e950bd601312cc19","media":{"originalName":"06_GMKJ_Le_Malin_4.jpg","path":"17584ab5-7ba2-4670-85b0-9072ff9f482f.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc19"},{"_id":"620bc002e950bd601312cc1a","media":{"originalName":"07_GMKJ_Le_Malin_5.jpg","path":"3e0b6707-f196-442e-8779-bc0b23d3cc18.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1a"},{"_id":"620bc002e950bd601312cc1b","media":{"originalName":"08_GMKJ_Le_Malin_6.jpg","path":"c4094abe-4378-426c-85ab-04c12d239c36.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1b"},{"_id":"620bc002e950bd601312cc1c","media":{"originalName":"09_GMKJ_Le_Malin_7.jpg","path":"ac74cccc-768f-4e4e-a57c-c2916ee6ca76.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1c"},{"_id":"620bc002e950bd601312cc1d","media":{"originalName":"10_GMKJ_Le_Malin_8.jpg","path":"9114b96e-035a-494e-b730-8153e4d79128.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1d"},{"_id":"620bc002e950bd601312cc1e","media":{"originalName":"11_GMKJ_Le_Malin_9.jpg","path":"f3f17a91-97c2-490d-b507-6971de469dc5.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1e"},{"_id":"620bc002e950bd601312cc1f","media":{"originalName":"12_GMKJ_Le_Malin_10.jpg","path":"a763333b-5fd0-4146-bbd6-a00961fe4fcc.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1f"},{"_id":"620bc002e950bd601312cc20","media":{"originalName":"13_GMKJ_Le_Malin_11.jpg","path":"caf3b623-11c1-4501-974f-e31616760597.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc20"},{"_id":"620bc002e950bd601312cc21","media":{"originalName":"14_GMKJ_Le_Malin_12.jpg","path":"de43bf63-d91e-4c7a-a567-a7012165518a.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc21"}],"total":14,"limit":40,"page":1,"pages":1}
         * ep : {"_id":"620bc002e950bd601312cc12","title":"第1話"}
         */

        @SerializedName("pages")
        private PagesBean pages;
        @SerializedName("ep")
        private EpBean ep;

        public PagesBean getPages() {
            return pages;
        }

        public void setPages(PagesBean pages) {
            this.pages = pages;
        }

        public EpBean getEp() {
            return ep;
        }

        public void setEp(EpBean ep) {
            this.ep = ep;
        }

        public static class PagesBean {
            /**
             * docs : [{"_id":"620bc002e950bd601312cc14","media":{"originalName":"01_GMKJ_Le_Malin_00.jpg","path":"361e5b9f-bd06-446d-a8f7-83039ad69ba0.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc14"},{"_id":"620bc002e950bd601312cc15","media":{"originalName":"02_GMKJ_Le_Malin_0.jpg","path":"00e10ec4-1174-4115-8c3f-4059cee7aa06.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc15"},{"_id":"620bc002e950bd601312cc16","media":{"originalName":"03_GMKJ_Le_Malin_1.jpg","path":"655c1ea6-a8ee-42c6-b757-358fc19e880d.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc16"},{"_id":"620bc002e950bd601312cc17","media":{"originalName":"04_GMKJ_Le_Malin_2.jpg","path":"c8295fc5-909e-4771-87d4-c4b0ceaa5e8b.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc17"},{"_id":"620bc002e950bd601312cc18","media":{"originalName":"05_GMKJ_Le_Malin_3.jpg","path":"6ed4c4a4-3c54-463b-8f3a-749a982286b3.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc18"},{"_id":"620bc002e950bd601312cc19","media":{"originalName":"06_GMKJ_Le_Malin_4.jpg","path":"17584ab5-7ba2-4670-85b0-9072ff9f482f.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc19"},{"_id":"620bc002e950bd601312cc1a","media":{"originalName":"07_GMKJ_Le_Malin_5.jpg","path":"3e0b6707-f196-442e-8779-bc0b23d3cc18.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1a"},{"_id":"620bc002e950bd601312cc1b","media":{"originalName":"08_GMKJ_Le_Malin_6.jpg","path":"c4094abe-4378-426c-85ab-04c12d239c36.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1b"},{"_id":"620bc002e950bd601312cc1c","media":{"originalName":"09_GMKJ_Le_Malin_7.jpg","path":"ac74cccc-768f-4e4e-a57c-c2916ee6ca76.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1c"},{"_id":"620bc002e950bd601312cc1d","media":{"originalName":"10_GMKJ_Le_Malin_8.jpg","path":"9114b96e-035a-494e-b730-8153e4d79128.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1d"},{"_id":"620bc002e950bd601312cc1e","media":{"originalName":"11_GMKJ_Le_Malin_9.jpg","path":"f3f17a91-97c2-490d-b507-6971de469dc5.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1e"},{"_id":"620bc002e950bd601312cc1f","media":{"originalName":"12_GMKJ_Le_Malin_10.jpg","path":"a763333b-5fd0-4146-bbd6-a00961fe4fcc.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc1f"},{"_id":"620bc002e950bd601312cc20","media":{"originalName":"13_GMKJ_Le_Malin_11.jpg","path":"caf3b623-11c1-4501-974f-e31616760597.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc20"},{"_id":"620bc002e950bd601312cc21","media":{"originalName":"14_GMKJ_Le_Malin_12.jpg","path":"de43bf63-d91e-4c7a-a567-a7012165518a.jpg","fileServer":"https://storage1.picacomic.com"},"id":"620bc002e950bd601312cc21"}]
             * total : 14
             * limit : 40
             * page : 1
             * pages : 1
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
                /**
                 * _id : 620bc002e950bd601312cc14
                 * media : {"originalName":"01_GMKJ_Le_Malin_00.jpg","path":"361e5b9f-bd06-446d-a8f7-83039ad69ba0.jpg","fileServer":"https://storage1.picacomic.com"}
                 * id : 620bc002e950bd601312cc14
                 */

                @SerializedName("_id")
                private String id1;
                @SerializedName("media")
                private MediaBean media;
                @SerializedName("id")
                private String id2;

                public String getId1() {
                    return id1;
                }

                public void setId1(String id1) {
                    this.id1 = id1;
                }

                public MediaBean getMedia() {
                    return media;
                }

                public void setMedia(MediaBean media) {
                    this.media = media;
                }

                public String getId2() {
                    return id1;
                }

                public void setId2(String id2) {
                    this.id1 = id2;
                }

                public static class MediaBean {
                    /**
                     * originalName : 01_GMKJ_Le_Malin_00.jpg
                     * path : 361e5b9f-bd06-446d-a8f7-83039ad69ba0.jpg
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

        public static class EpBean {
            /**
             * _id : 620bc002e950bd601312cc12
             * title : 第1話
             */

            @SerializedName("_id")
            private String id;
            @SerializedName("title")
            private String title;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
