package top.woodwhale.gogopic.model.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsChapter {

    /**
     * code : 200
     * message : success
     * data : {"eps":{"docs":[{"_id":"61a3a9d254701b36fd9cbaf8","title":"第38話","order":38,"updated_at":"2021-11-28T04:25:29.119Z","id":"61a3a9d254701b36fd9cbaf8"},{"_id":"602aafbcf497826efe3c537e","title":"第37話","order":37,"updated_at":"2021-02-15T05:21:31.326Z","id":"602aafbcf497826efe3c537e"},{"_id":"6005b3def48dbe75edfc3540","title":"第36話","order":36,"updated_at":"2021-01-18T12:23:40.761Z","id":"6005b3def48dbe75edfc3540"},{"_id":"5fee09d5f62e385947f09e37","title":"第35話","order":35,"updated_at":"2020-12-31T10:47:20.263Z","id":"5fee09d5f62e385947f09e37"},{"_id":"5fc66b2ca03a64100a9a4a5a","title":"第34話","order":34,"updated_at":"2020-12-01T12:06:42.985Z","id":"5fc66b2ca03a64100a9a4a5a"},{"_id":"5f075369f0a3d42e7d0ba9d8","title":"第33話","order":33,"updated_at":"2020-07-09T13:07:08.571Z","id":"5f075369f0a3d42e7d0ba9d8"},{"_id":"5edd2ecba964321c7cefea8c","title":"第32話","order":32,"updated_at":"2020-06-07T16:10:10.847Z","id":"5edd2ecba964321c7cefea8c"},{"_id":"5e4c1dedd249753c61db6181","title":"第31話","order":31,"updated_at":"2020-02-18T12:17:00.870Z","id":"5e4c1dedd249753c61db6181"},{"_id":"5dc43ffc8dfd2360dbb461f2","title":"第30話","order":30,"updated_at":"2019-11-07T12:35:05.727Z","id":"5dc43ffc8dfd2360dbb461f2"},{"_id":"5d6bfea5e3cf303992529384","title":"第29話","order":29,"updated_at":"2019-09-01T14:51:23.093Z","id":"5d6bfea5e3cf303992529384"},{"_id":"5d2627f80e47f27e38357215","title":"第28集","order":28,"updated_at":"2019-07-10T16:56:41.840Z","id":"5d2627f80e47f27e38357215"},{"_id":"5d20eda5899f634c7bda57bb","title":"第27集","order":27,"updated_at":"2019-07-06T13:01:07.005Z","id":"5d20eda5899f634c7bda57bb"},{"_id":"5cbb6ca1f0799650f700d951","title":"第26集","order":26,"updated_at":"2019-04-20T17:07:34.184Z","id":"5cbb6ca1f0799650f700d951"},{"_id":"5b9220a2e643e44afd9bc111","title":"第25集","order":25,"updated_at":"2018-09-06T18:09:52.399Z","id":"5b9220a2e643e44afd9bc111"},{"_id":"5b27d4d83e61ea729a3aac69","title":"第24集","order":24,"updated_at":"2018-06-18T13:22:24.071Z","id":"5b27d4d83e61ea729a3aac69"},{"_id":"5aed198de110b956be5d5df2","title":"第23集","order":23,"updated_at":"2018-05-04T18:03:31.656Z","id":"5aed198de110b956be5d5df2"},{"_id":"5a88e50a075a2c280aeb1b8a","title":"第22集","order":22,"updated_at":"2018-02-17T19:05:55.751Z","id":"5a88e50a075a2c280aeb1b8a"},{"_id":"5a3db8569d4b3a1f7e71ddcf","title":"第21集","order":21,"updated_at":"2017-12-22T17:22:25.623Z","id":"5a3db8569d4b3a1f7e71ddcf"},{"_id":"5a30e47826d79313494911e4","title":"第20集","order":20,"updated_at":"2017-12-13T08:10:17.475Z","id":"5a30e47826d79313494911e4"},{"_id":"59a0535e6438d9784494deb7","title":"第19集","order":19,"updated_at":"2017-08-25T16:34:48.775Z","id":"59a0535e6438d9784494deb7"},{"_id":"598ca0c3fd49db6354b26079","title":"第18集","order":18,"updated_at":"2017-08-10T13:51:40.787Z","id":"598ca0c3fd49db6354b26079"},{"_id":"59413b3e9931b030588bd706","title":"第17集","order":17,"updated_at":"2017-06-14T12:53:28.496Z","id":"59413b3e9931b030588bd706"},{"_id":"591fcaf8106a7c3a2ecd8e54","title":"第16集","order":16,"updated_at":"2017-05-19T17:11:48.543Z","id":"591fcaf8106a7c3a2ecd8e54"},{"_id":"590e9daeb21192073e8c9160","title":"第15集","order":15,"updated_at":"2017-05-06T18:20:36.359Z","id":"590e9daeb21192073e8c9160"},{"_id":"58e98f12b9517917219b0a6b","title":"第14集","order":14,"updated_at":"2017-04-08T14:22:20.025Z","id":"58e98f12b9517917219b0a6b"},{"_id":"58a7b03fc2d3345f33b9fd21","title":"第13集","order":13,"updated_at":"2017-02-17T19:52:18.250Z","id":"58a7b03fc2d3345f33b9fd21"},{"_id":"58744bf55b1a2f7e69c83a1d","title":"第12集","order":12,"updated_at":"2017-01-09T16:13:45.615Z","id":"58744bf55b1a2f7e69c83a1d"},{"_id":"5821aad75f6b9a4f93f445c1","title":"第11集","order":11,"updated_at":"2016-11-08T10:37:11.704Z","id":"5821aad75f6b9a4f93f445c1"},{"_id":"5821aad75f6b9a4f93f4458d","title":"第10集","order":10,"updated_at":"2016-11-08T10:37:11.313Z","id":"5821aad75f6b9a4f93f4458d"},{"_id":"5821aad65f6b9a4f93f44557","title":"第9集","order":9,"updated_at":"2016-11-08T10:37:10.663Z","id":"5821aad65f6b9a4f93f44557"},{"_id":"5821aad65f6b9a4f93f44525","title":"第8集","order":8,"updated_at":"2016-11-08T10:37:10.292Z","id":"5821aad65f6b9a4f93f44525"},{"_id":"5821aad55f6b9a4f93f444f1","title":"第7集","order":7,"updated_at":"2016-11-08T10:37:09.646Z","id":"5821aad55f6b9a4f93f444f1"},{"_id":"5821aad55f6b9a4f93f444bd","title":"第6集","order":6,"updated_at":"2016-11-08T10:37:09.083Z","id":"5821aad55f6b9a4f93f444bd"},{"_id":"5821aad45f6b9a4f93f44489","title":"第5集","order":5,"updated_at":"2016-11-08T10:37:08.505Z","id":"5821aad45f6b9a4f93f44489"},{"_id":"5821aad35f6b9a4f93f44455","title":"第4集","order":4,"updated_at":"2016-11-08T10:37:07.915Z","id":"5821aad35f6b9a4f93f44455"},{"_id":"5821aad35f6b9a4f93f44421","title":"第3集","order":3,"updated_at":"2016-11-08T10:37:07.308Z","id":"5821aad35f6b9a4f93f44421"},{"_id":"5821aad25f6b9a4f93f443ed","title":"第2集","order":2,"updated_at":"2016-11-08T10:37:06.856Z","id":"5821aad25f6b9a4f93f443ed"},{"_id":"5821aad15f6b9a4f93f443b7","title":"第1集","order":1,"updated_at":"2016-11-08T10:37:05.970Z","id":"5821aad15f6b9a4f93f443b7"}],"total":38,"limit":40,"page":1,"pages":1}}
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
         * eps : {"docs":[{"_id":"61a3a9d254701b36fd9cbaf8","title":"第38話","order":38,"updated_at":"2021-11-28T04:25:29.119Z","id":"61a3a9d254701b36fd9cbaf8"},{"_id":"602aafbcf497826efe3c537e","title":"第37話","order":37,"updated_at":"2021-02-15T05:21:31.326Z","id":"602aafbcf497826efe3c537e"},{"_id":"6005b3def48dbe75edfc3540","title":"第36話","order":36,"updated_at":"2021-01-18T12:23:40.761Z","id":"6005b3def48dbe75edfc3540"},{"_id":"5fee09d5f62e385947f09e37","title":"第35話","order":35,"updated_at":"2020-12-31T10:47:20.263Z","id":"5fee09d5f62e385947f09e37"},{"_id":"5fc66b2ca03a64100a9a4a5a","title":"第34話","order":34,"updated_at":"2020-12-01T12:06:42.985Z","id":"5fc66b2ca03a64100a9a4a5a"},{"_id":"5f075369f0a3d42e7d0ba9d8","title":"第33話","order":33,"updated_at":"2020-07-09T13:07:08.571Z","id":"5f075369f0a3d42e7d0ba9d8"},{"_id":"5edd2ecba964321c7cefea8c","title":"第32話","order":32,"updated_at":"2020-06-07T16:10:10.847Z","id":"5edd2ecba964321c7cefea8c"},{"_id":"5e4c1dedd249753c61db6181","title":"第31話","order":31,"updated_at":"2020-02-18T12:17:00.870Z","id":"5e4c1dedd249753c61db6181"},{"_id":"5dc43ffc8dfd2360dbb461f2","title":"第30話","order":30,"updated_at":"2019-11-07T12:35:05.727Z","id":"5dc43ffc8dfd2360dbb461f2"},{"_id":"5d6bfea5e3cf303992529384","title":"第29話","order":29,"updated_at":"2019-09-01T14:51:23.093Z","id":"5d6bfea5e3cf303992529384"},{"_id":"5d2627f80e47f27e38357215","title":"第28集","order":28,"updated_at":"2019-07-10T16:56:41.840Z","id":"5d2627f80e47f27e38357215"},{"_id":"5d20eda5899f634c7bda57bb","title":"第27集","order":27,"updated_at":"2019-07-06T13:01:07.005Z","id":"5d20eda5899f634c7bda57bb"},{"_id":"5cbb6ca1f0799650f700d951","title":"第26集","order":26,"updated_at":"2019-04-20T17:07:34.184Z","id":"5cbb6ca1f0799650f700d951"},{"_id":"5b9220a2e643e44afd9bc111","title":"第25集","order":25,"updated_at":"2018-09-06T18:09:52.399Z","id":"5b9220a2e643e44afd9bc111"},{"_id":"5b27d4d83e61ea729a3aac69","title":"第24集","order":24,"updated_at":"2018-06-18T13:22:24.071Z","id":"5b27d4d83e61ea729a3aac69"},{"_id":"5aed198de110b956be5d5df2","title":"第23集","order":23,"updated_at":"2018-05-04T18:03:31.656Z","id":"5aed198de110b956be5d5df2"},{"_id":"5a88e50a075a2c280aeb1b8a","title":"第22集","order":22,"updated_at":"2018-02-17T19:05:55.751Z","id":"5a88e50a075a2c280aeb1b8a"},{"_id":"5a3db8569d4b3a1f7e71ddcf","title":"第21集","order":21,"updated_at":"2017-12-22T17:22:25.623Z","id":"5a3db8569d4b3a1f7e71ddcf"},{"_id":"5a30e47826d79313494911e4","title":"第20集","order":20,"updated_at":"2017-12-13T08:10:17.475Z","id":"5a30e47826d79313494911e4"},{"_id":"59a0535e6438d9784494deb7","title":"第19集","order":19,"updated_at":"2017-08-25T16:34:48.775Z","id":"59a0535e6438d9784494deb7"},{"_id":"598ca0c3fd49db6354b26079","title":"第18集","order":18,"updated_at":"2017-08-10T13:51:40.787Z","id":"598ca0c3fd49db6354b26079"},{"_id":"59413b3e9931b030588bd706","title":"第17集","order":17,"updated_at":"2017-06-14T12:53:28.496Z","id":"59413b3e9931b030588bd706"},{"_id":"591fcaf8106a7c3a2ecd8e54","title":"第16集","order":16,"updated_at":"2017-05-19T17:11:48.543Z","id":"591fcaf8106a7c3a2ecd8e54"},{"_id":"590e9daeb21192073e8c9160","title":"第15集","order":15,"updated_at":"2017-05-06T18:20:36.359Z","id":"590e9daeb21192073e8c9160"},{"_id":"58e98f12b9517917219b0a6b","title":"第14集","order":14,"updated_at":"2017-04-08T14:22:20.025Z","id":"58e98f12b9517917219b0a6b"},{"_id":"58a7b03fc2d3345f33b9fd21","title":"第13集","order":13,"updated_at":"2017-02-17T19:52:18.250Z","id":"58a7b03fc2d3345f33b9fd21"},{"_id":"58744bf55b1a2f7e69c83a1d","title":"第12集","order":12,"updated_at":"2017-01-09T16:13:45.615Z","id":"58744bf55b1a2f7e69c83a1d"},{"_id":"5821aad75f6b9a4f93f445c1","title":"第11集","order":11,"updated_at":"2016-11-08T10:37:11.704Z","id":"5821aad75f6b9a4f93f445c1"},{"_id":"5821aad75f6b9a4f93f4458d","title":"第10集","order":10,"updated_at":"2016-11-08T10:37:11.313Z","id":"5821aad75f6b9a4f93f4458d"},{"_id":"5821aad65f6b9a4f93f44557","title":"第9集","order":9,"updated_at":"2016-11-08T10:37:10.663Z","id":"5821aad65f6b9a4f93f44557"},{"_id":"5821aad65f6b9a4f93f44525","title":"第8集","order":8,"updated_at":"2016-11-08T10:37:10.292Z","id":"5821aad65f6b9a4f93f44525"},{"_id":"5821aad55f6b9a4f93f444f1","title":"第7集","order":7,"updated_at":"2016-11-08T10:37:09.646Z","id":"5821aad55f6b9a4f93f444f1"},{"_id":"5821aad55f6b9a4f93f444bd","title":"第6集","order":6,"updated_at":"2016-11-08T10:37:09.083Z","id":"5821aad55f6b9a4f93f444bd"},{"_id":"5821aad45f6b9a4f93f44489","title":"第5集","order":5,"updated_at":"2016-11-08T10:37:08.505Z","id":"5821aad45f6b9a4f93f44489"},{"_id":"5821aad35f6b9a4f93f44455","title":"第4集","order":4,"updated_at":"2016-11-08T10:37:07.915Z","id":"5821aad35f6b9a4f93f44455"},{"_id":"5821aad35f6b9a4f93f44421","title":"第3集","order":3,"updated_at":"2016-11-08T10:37:07.308Z","id":"5821aad35f6b9a4f93f44421"},{"_id":"5821aad25f6b9a4f93f443ed","title":"第2集","order":2,"updated_at":"2016-11-08T10:37:06.856Z","id":"5821aad25f6b9a4f93f443ed"},{"_id":"5821aad15f6b9a4f93f443b7","title":"第1集","order":1,"updated_at":"2016-11-08T10:37:05.970Z","id":"5821aad15f6b9a4f93f443b7"}],"total":38,"limit":40,"page":1,"pages":1}
         */

        @SerializedName("eps")
        private EpsBean eps;

        public EpsBean getEps() {
            return eps;
        }

        public void setEps(EpsBean eps) {
            this.eps = eps;
        }

        public static class EpsBean {
            /**
             * docs : [{"_id":"61a3a9d254701b36fd9cbaf8","title":"第38話","order":38,"updated_at":"2021-11-28T04:25:29.119Z","id":"61a3a9d254701b36fd9cbaf8"},{"_id":"602aafbcf497826efe3c537e","title":"第37話","order":37,"updated_at":"2021-02-15T05:21:31.326Z","id":"602aafbcf497826efe3c537e"},{"_id":"6005b3def48dbe75edfc3540","title":"第36話","order":36,"updated_at":"2021-01-18T12:23:40.761Z","id":"6005b3def48dbe75edfc3540"},{"_id":"5fee09d5f62e385947f09e37","title":"第35話","order":35,"updated_at":"2020-12-31T10:47:20.263Z","id":"5fee09d5f62e385947f09e37"},{"_id":"5fc66b2ca03a64100a9a4a5a","title":"第34話","order":34,"updated_at":"2020-12-01T12:06:42.985Z","id":"5fc66b2ca03a64100a9a4a5a"},{"_id":"5f075369f0a3d42e7d0ba9d8","title":"第33話","order":33,"updated_at":"2020-07-09T13:07:08.571Z","id":"5f075369f0a3d42e7d0ba9d8"},{"_id":"5edd2ecba964321c7cefea8c","title":"第32話","order":32,"updated_at":"2020-06-07T16:10:10.847Z","id":"5edd2ecba964321c7cefea8c"},{"_id":"5e4c1dedd249753c61db6181","title":"第31話","order":31,"updated_at":"2020-02-18T12:17:00.870Z","id":"5e4c1dedd249753c61db6181"},{"_id":"5dc43ffc8dfd2360dbb461f2","title":"第30話","order":30,"updated_at":"2019-11-07T12:35:05.727Z","id":"5dc43ffc8dfd2360dbb461f2"},{"_id":"5d6bfea5e3cf303992529384","title":"第29話","order":29,"updated_at":"2019-09-01T14:51:23.093Z","id":"5d6bfea5e3cf303992529384"},{"_id":"5d2627f80e47f27e38357215","title":"第28集","order":28,"updated_at":"2019-07-10T16:56:41.840Z","id":"5d2627f80e47f27e38357215"},{"_id":"5d20eda5899f634c7bda57bb","title":"第27集","order":27,"updated_at":"2019-07-06T13:01:07.005Z","id":"5d20eda5899f634c7bda57bb"},{"_id":"5cbb6ca1f0799650f700d951","title":"第26集","order":26,"updated_at":"2019-04-20T17:07:34.184Z","id":"5cbb6ca1f0799650f700d951"},{"_id":"5b9220a2e643e44afd9bc111","title":"第25集","order":25,"updated_at":"2018-09-06T18:09:52.399Z","id":"5b9220a2e643e44afd9bc111"},{"_id":"5b27d4d83e61ea729a3aac69","title":"第24集","order":24,"updated_at":"2018-06-18T13:22:24.071Z","id":"5b27d4d83e61ea729a3aac69"},{"_id":"5aed198de110b956be5d5df2","title":"第23集","order":23,"updated_at":"2018-05-04T18:03:31.656Z","id":"5aed198de110b956be5d5df2"},{"_id":"5a88e50a075a2c280aeb1b8a","title":"第22集","order":22,"updated_at":"2018-02-17T19:05:55.751Z","id":"5a88e50a075a2c280aeb1b8a"},{"_id":"5a3db8569d4b3a1f7e71ddcf","title":"第21集","order":21,"updated_at":"2017-12-22T17:22:25.623Z","id":"5a3db8569d4b3a1f7e71ddcf"},{"_id":"5a30e47826d79313494911e4","title":"第20集","order":20,"updated_at":"2017-12-13T08:10:17.475Z","id":"5a30e47826d79313494911e4"},{"_id":"59a0535e6438d9784494deb7","title":"第19集","order":19,"updated_at":"2017-08-25T16:34:48.775Z","id":"59a0535e6438d9784494deb7"},{"_id":"598ca0c3fd49db6354b26079","title":"第18集","order":18,"updated_at":"2017-08-10T13:51:40.787Z","id":"598ca0c3fd49db6354b26079"},{"_id":"59413b3e9931b030588bd706","title":"第17集","order":17,"updated_at":"2017-06-14T12:53:28.496Z","id":"59413b3e9931b030588bd706"},{"_id":"591fcaf8106a7c3a2ecd8e54","title":"第16集","order":16,"updated_at":"2017-05-19T17:11:48.543Z","id":"591fcaf8106a7c3a2ecd8e54"},{"_id":"590e9daeb21192073e8c9160","title":"第15集","order":15,"updated_at":"2017-05-06T18:20:36.359Z","id":"590e9daeb21192073e8c9160"},{"_id":"58e98f12b9517917219b0a6b","title":"第14集","order":14,"updated_at":"2017-04-08T14:22:20.025Z","id":"58e98f12b9517917219b0a6b"},{"_id":"58a7b03fc2d3345f33b9fd21","title":"第13集","order":13,"updated_at":"2017-02-17T19:52:18.250Z","id":"58a7b03fc2d3345f33b9fd21"},{"_id":"58744bf55b1a2f7e69c83a1d","title":"第12集","order":12,"updated_at":"2017-01-09T16:13:45.615Z","id":"58744bf55b1a2f7e69c83a1d"},{"_id":"5821aad75f6b9a4f93f445c1","title":"第11集","order":11,"updated_at":"2016-11-08T10:37:11.704Z","id":"5821aad75f6b9a4f93f445c1"},{"_id":"5821aad75f6b9a4f93f4458d","title":"第10集","order":10,"updated_at":"2016-11-08T10:37:11.313Z","id":"5821aad75f6b9a4f93f4458d"},{"_id":"5821aad65f6b9a4f93f44557","title":"第9集","order":9,"updated_at":"2016-11-08T10:37:10.663Z","id":"5821aad65f6b9a4f93f44557"},{"_id":"5821aad65f6b9a4f93f44525","title":"第8集","order":8,"updated_at":"2016-11-08T10:37:10.292Z","id":"5821aad65f6b9a4f93f44525"},{"_id":"5821aad55f6b9a4f93f444f1","title":"第7集","order":7,"updated_at":"2016-11-08T10:37:09.646Z","id":"5821aad55f6b9a4f93f444f1"},{"_id":"5821aad55f6b9a4f93f444bd","title":"第6集","order":6,"updated_at":"2016-11-08T10:37:09.083Z","id":"5821aad55f6b9a4f93f444bd"},{"_id":"5821aad45f6b9a4f93f44489","title":"第5集","order":5,"updated_at":"2016-11-08T10:37:08.505Z","id":"5821aad45f6b9a4f93f44489"},{"_id":"5821aad35f6b9a4f93f44455","title":"第4集","order":4,"updated_at":"2016-11-08T10:37:07.915Z","id":"5821aad35f6b9a4f93f44455"},{"_id":"5821aad35f6b9a4f93f44421","title":"第3集","order":3,"updated_at":"2016-11-08T10:37:07.308Z","id":"5821aad35f6b9a4f93f44421"},{"_id":"5821aad25f6b9a4f93f443ed","title":"第2集","order":2,"updated_at":"2016-11-08T10:37:06.856Z","id":"5821aad25f6b9a4f93f443ed"},{"_id":"5821aad15f6b9a4f93f443b7","title":"第1集","order":1,"updated_at":"2016-11-08T10:37:05.970Z","id":"5821aad15f6b9a4f93f443b7"}]
             * total : 38
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
                 * _id : 61a3a9d254701b36fd9cbaf8
                 * title : 第38話
                 * order : 38
                 * updated_at : 2021-11-28T04:25:29.119Z
                 * id : 61a3a9d254701b36fd9cbaf8
                 */

                @SerializedName("_id")
                private String id1;
                @SerializedName("title")
                private String title;
                @SerializedName("order")
                private int order;
                @SerializedName("updated_at")
                private String updatedAt;
                @SerializedName("id")
                private String id2;

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

                public int getOrder() {
                    return order;
                }

                public void setOrder(int order) {
                    this.order = order;
                }

                public String getUpdatedAt() {
                    return updatedAt;
                }

                public void setUpdatedAt(String updatedAt) {
                    this.updatedAt = updatedAt;
                }

                public String getId2() {
                    return id1;
                }

                public void setId2(String id2) {
                    this.id1 = id2;
                }
            }
        }
    }
}
