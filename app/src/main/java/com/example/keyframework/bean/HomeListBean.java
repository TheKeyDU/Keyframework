package com.example.keyframework.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class HomeListBean implements Parcelable {

    /**
     * toplist : [{"client":"6","device":"6","topplat":"6","newsid":457492,"title":"云日历iOS版1.02更新：iPad适配、第8个小组件、海量更新","postdate":"2019-11-17T14:31:31.407","orderdate":"0001-01-01T00:00:00","description":"@软媒 今天为云日历iOS版添加了第8个小组件，迄今为止，世界范围内，应该没有其他同类App支持如此众多的小组件。我们今天更适配了iPadOS，所有小组件也能在iPad设备中完美使用，今天的更新太多了，详见\u2014\u2014","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457492_240.jpg?r=1573972291407","hitcount":21909,"commentcount":468,"cid":58,"sid":0,"url":"https://www.ithome.com/0/457/492.htm"},{"client":"5","device":"5","topplat":"5","newsid":457216,"title":"云日历安卓版1.02更新：工具小部件+大量改进和修正","postdate":"2019-11-15T15:13:57","orderdate":"0001-01-01T00:00:00","description":"@软媒 今天发布云日历安卓版本的1.02，对大家反馈的问题进行了大量改进和修正，虽然是蓝图中的第一阶段产品，但目前云日历已经有着三大痛点：集成海量的功能订阅+海量的小组件/小部件、跨越所有常见设备的云端数据全同步、永久免费/纯净/无广告\u2026\u2026","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457216_240.jpg?r=1573803140083","hitcount":13869,"commentcount":369,"cid":72,"sid":0,"url":"https://www.ithome.com/0/457/216.htm"},{"client":"1,2,3,7,4","device":"1,2,3,7,4","topplat":"1,2,3,7,4","newsid":455589,"title":"云日历，1.0，全平台，软媒制造，翩然而至。","postdate":"2019-11-08T13:04:28","orderdate":"0001-01-01T00:00:00","description":"云日历，把握时间。日程、天气、星座、黄历、万年历、节假日、历史上的今天、世界时钟、运势、限行日历、福彩体彩\u2026\u2026以上目之所及，只是开始，@软媒 制造。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/455589_240.jpg?r=1573356616663","hitcount":151165,"commentcount":1621,"cid":58,"sid":0,"url":"https://www.ithome.com/0/455/589.htm"}]
     * newslist : [{"newsid":457666,"title":"语音识别大拿Daniel Povey将出任小米集团语音首席科学家","postdate":"2019-11-18T14:42:54.247","orderdate":"2019-11-18T14:42:54.247","description":"北京时间今日午间，小米创办人、董事长兼CEO雷军发微博表示，Daniel Povey将出任小米集团语音首席科学家，他将在2019小米开发者大会上和大家分享\u201cAI语音领域的未来技术趋势\u201d","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457666_240.jpg?r=1574059374247","hitcount":402,"commentcount":0,"cid":31,"sid":0,"url":"/0/457/666.htm"},{"newsid":457665,"title":"官方公布华为nova 6 5G：前置\u201c药丸\u201d双摄","postdate":"2019-11-18T14:40:27.697","orderdate":"2019-11-18T14:40:27.697","description":"今天华为官方正式公布了华为nova 6 5G心机，从宣传视频来看，该机主打自拍，前置\u201c药丸\u201d形打孔双摄，支持105度超广角","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457665_240.jpg?r=1574059227697","hitcount":1206,"commentcount":24,"cid":74,"sid":0,"url":"/0/457/665.htm"},{"imagelist":["http://img.ithome.com/newsuploadfiles/2019/11/20191118_143536_908.jpg@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/11/20191118_143706_624.jpg@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/11/20191118_143832_706.jpg@s_2,w_240,h_180"],"newsid":457664,"title":"紫光国芯将展示DDR4内存：16GB单条，频率达2666MHz","postdate":"2019-11-18T14:40:08","orderdate":"2019-11-18T14:40:08","description":"11月27日，集邦咨询旗下DRAMeXchange将主办\u201c2020存储产业趋势峰会\u201d，届时紫光国芯将会展示全系列的闪存、内存产品。现在，DRAMeXchange提前曝光了紫光国芯将要展示的DDR4内存产品","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457664_240.jpg?r=1574059822790","hitcount":1608,"commentcount":30,"cid":100,"sid":0,"url":"/0/457/664.htm"},{"newsid":457663,"title":"31岁WPS科创板上市，雷军：曾有20年不赚钱","postdate":"2019-11-18T14:34:29","orderdate":"2019-11-18T14:34:29","description":"11月18日，金山办公正式在上交所科创板挂牌交易。在随后的媒体采访中，雷军感慨WPS走过31个年头，上市就等了20年。因为等太久了，当这一切真的发生时，都不敢相信这是真","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457663_240.jpg?r=1574059376440","hitcount":402,"commentcount":0,"cid":71,"sid":0,"url":"/0/457/663.htm"},{"newsid":457662,"title":"\u201c1+1=1\u201d：山泽六类千兆网线*1+1米安卓数据线*1，1.1元包邮","postdate":"2019-11-18T14:29:48.587","orderdate":"2019-11-18T14:29:48.587","description":"家用高速网线，下载更快速，游戏不卡，追求飞一般的网速，且额外赠送1条安卓数据线，网线+数据线券后仅需1.1元！便宜划算又实用，商家吐血亏，速度囤！","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457662_240.jpg?r=1574058588587","hitcount":804,"commentcount":12,"cid":166,"sid":0,"url":"https://lapin.ithome.com/html/digi/457662.htm","aid":1},{"newsid":457661,"title":"新西兰运营商Spark：我们不仅用华为5G设备，还将其列入首选名单","postdate":"2019-11-18T14:29:46.503","orderdate":"2019-11-18T14:29:46.503","description":"据HC消息，新西兰电信运营商巨头Spark表示，它不仅要使用华为5G设备，还在其多设备供应商名单中将华为列为前三位首选供应商之一。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457661_240.jpg?r=1574058586503","hitcount":1608,"commentcount":13,"cid":31,"sid":0,"url":"/0/457/661.htm"},{"newsid":457659,"title":"三星手机新设计专利曝光：正面只有屏幕","postdate":"2019-11-18T14:27:40.683","orderdate":"2019-11-18T14:27:40.683","description":"近日Letsgodigital曝光了一项三星手机的新的设计专利；从设计草图可以看到，这款手机的正面和上下左右四边只有屏幕","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457659_240.jpg?r=1574058460683","hitcount":2613,"commentcount":25,"cid":74,"sid":0,"url":"/0/457/659.htm"},{"newsid":457657,"title":"索尼推送Aibo更新，自行训练\u201c索狗\u201d成为可能","postdate":"2019-11-18T14:23:26.14","orderdate":"2019-11-18T14:23:26.14","description":"如果不想完全靠自己来拖拽方块或是用代码编写动作，用户可以借助地图绘制功能来\u201c训练\u201d自己的\u201c索狗\u201d上厕所、教它正坐好保持安静。你甚至还可以给\u201c索狗\u201d喂食","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457657_240.jpg?r=1574058206140","hitcount":804,"commentcount":13,"cid":158,"sid":0,"url":"/0/457/657.htm"},{"newsid":457656,"title":"英特尔十代i9现身数据库：10核20线程，核显还是UHD 630","postdate":"2019-11-18T14:22:05.38","orderdate":"2019-11-18T14:22:05.38","description":"不久前，消息称英特尔将在明年初发布十代桌面处理器，i5升级为6核12线程，i7升级为8核16线程，而i9则升级为10核20线程。现在Geekbench数据库中就曝光了一款十代i9处理器，一起来看一下吧","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457656_240.jpg?r=1574058125380","hitcount":2010,"commentcount":25,"cid":100,"sid":0,"url":"/0/457/656.htm"},{"newsid":457651,"title":"专访雷军：金山有持续作战能力，骨子里不服输","postdate":"2019-11-18T14:06:51.59","orderdate":"2019-11-18T14:06:51.59","description":"雷军透露，金山办公上市前专门开了几次董事会，\u201c我发现，金山在骨子里有股不服输的精神、而且具备力争上游的心态，这都推动了金山前行\u201d！","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457651_240.jpg?r=1574057211590","hitcount":2211,"commentcount":30,"cid":31,"sid":0,"url":"/0/457/651.htm"},{"newsid":457650,"title":"《绝地求生》4AM/VC/QM晋级全球赛决赛，FaZe最后绝地翻盘","postdate":"2019-11-18T14:05:57.22","orderdate":"2019-11-18T14:05:57.22","description":"北京时间今日上午进行的《绝地求生》PGC全球总决赛半决赛第三日的比赛，A组和C组战队参加比赛，中国PCL赛区4AM战队和QM战队参赛","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457650_240.jpg?r=1574057157220","hitcount":2412,"commentcount":20,"cid":76,"sid":0,"url":"/0/457/650.htm"},{"newsid":457649,"title":"雷军笑谈金山办公上市：CEO可以说是A股最励志董秘","postdate":"2019-11-18T14:04:47.277","orderdate":"2019-11-18T14:04:47.277","description":"雷军在开盘仪式上谈到，金山办公1999年计划筹备上市时，葛珂就是董秘，没想到一等20年，现在葛珂已经成了金山办公CEO，他应该是A股市场上最励志的董秘。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457649_240.jpg?r=1574057087277","hitcount":2412,"commentcount":51,"cid":31,"sid":0,"url":"/0/457/649.htm"},{"newsid":457652,"title":"健康纯棉，马克华菲士男士内衣秋裤套装59元","postdate":"2019-11-18T14:10:52","orderdate":"2019-11-18T14:04:46","description":"正品保障，时尚百搭，舒适透气，精细做工，年轻潮流，优质面料，我们只做精品！【赠运费险】","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457652_240.jpg?r=1574058481227","hitcount":0,"commentcount":5,"cid":166,"sid":0,"url":"https://lapin.ithome.com/html/digi/457652.htm","aid":1},{"newsid":457648,"title":"被指设网站销售盗版课程视频，两人被公诉侵犯著作权","postdate":"2019-11-18T14:01:56.05","orderdate":"2019-11-18T14:01:56.05","description":"被告人范某、李某被指设立侵权网站以收取会员费的方式对外销售其它公司享有著作权的课程视频，以涉嫌侵犯著作权罪被提起公诉。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457648_240.jpg?r=1574056916050","hitcount":1608,"commentcount":10,"cid":32,"sid":0,"url":"/0/457/648.htm"},{"newsid":457646,"title":"黑暗舞者别样之美：《魔兽争霸3：重制版》赛特斯/海象人建模释出","postdate":"2019-11-18T13:58:22","orderdate":"2019-11-18T13:58:22","description":"近日外媒Wowhead再度释出《魔兽争霸3：重制版》赛特斯(Satyr)和海象人(Tuskarr)的人物建模，一起来看一下","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457646_240.jpg?r=1574058714160","hitcount":2010,"commentcount":16,"cid":76,"sid":0,"url":"/0/457/646.htm"},{"newsid":457642,"title":"与央视春晚达成独家互动合作？快手：不予回应","postdate":"2019-11-18T13:52:45","orderdate":"2019-11-18T13:52:45","description":"据晚点LatePost消息，快手已经竞标拿下2020年央视春晚独家互动合作伙伴权，对于上述该消息，快手官方表示，不予回应","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457642_240.jpg?r=1574056412550","hitcount":2413,"commentcount":33,"cid":31,"sid":0,"url":"/0/457/642.htm"},{"newsid":457641,"title":"金山办公葛珂公开信：要坚持程序员文化","postdate":"2019-11-18T13:47:37.627","orderdate":"2019-11-18T13:47:37.627","description":"金山办公董事长兼首席执行官葛珂发布公开信称，技术立业帮助金山办公把握住了属于自己的、为数不多的机会。WPS始终坚持在做一件事：搞技术，把产品做好，坚持程序员文化，坚持技术立业","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457641_240.jpg?r=1574056057627","hitcount":1809,"commentcount":17,"cid":31,"sid":0,"url":"/0/457/641.htm"},{"newsid":457640,"title":"总能玩出新花样：外国高玩用光剑游玩《星球大战绝地》","postdate":"2019-11-18T13:38:56.247","orderdate":"2019-11-18T13:38:56.247","description":"近日有一位\u201d硬核\u201c的外国玩家便在推特上展示了一段用\u201c自制光剑\u201d游玩《星球大战绝地：陨落的武士团》的短视频，在游戏中也是秀出了各类骚操作","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457640_240.jpg?r=1574055536247","hitcount":2412,"commentcount":15,"cid":76,"sid":0,"url":"/0/457/640.htm"},{"newsid":457639,"title":"全国首例网络个人大病求助案宣判：男子隐瞒财产、挪用筹款被判全额返还","postdate":"2019-11-18T13:23:00.647","orderdate":"2019-11-18T13:23:00.647","description":"据新京报我们的视频消息，11月6日，全国首例因网络个人大病求助引发的纠纷一审宣判。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457639_240.jpg?r=1574054580647","hitcount":8040,"commentcount":103,"cid":32,"sid":0,"url":"/0/457/639.htm"},{"newsid":457638,"title":"智能设备走向折叠屏时代：能折能曲，折法多样","postdate":"2019-11-18T13:05:45.827","orderdate":"2019-11-18T13:05:45.827","description":"折叠屏被认为是未来手机发展的趋势，这一点毋庸置疑。近段时间，随着三星Galaxy Fold、华为Mate X相继开卖以及摩托罗拉Razr登场亮相，消费电子产品正在整体进入\u201c折叠时代\u201d","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457638_240.jpg?r=1574053545827","hitcount":3618,"commentcount":79,"cid":74,"sid":0,"url":"/0/457/638.htm"},{"newsid":457114,"title":"【已补货】Type-C+USB 3.0：朗科64G双接口U盘39元","postdate":"2019-11-15T10:02:23","orderdate":"2019-11-18T13:05:44","description":"【正品保证】朗科Type-C手机电脑两用u盘，一款有格调，有质量的U盘，64g小身材大容量，时尚匠心打造，防尘，防水，防震，一体封装，个性便携挂环，多功能设备使用，即插即用，方便你的存储！【赠运费险】","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457114_240.jpg?r=1574054904727","hitcount":38391,"commentcount":320,"cid":166,"sid":0,"url":"https://lapin.ithome.com/html/digi/457114.htm","aid":1},{"newsid":457637,"title":"消息：快手拿下2020年央视春晚独家互动合作伙伴权","postdate":"2019-11-18T13:02:46","orderdate":"2019-11-18T13:02:46","description":"快手已经竞标拿下2020年央视春晚独家互动合作伙伴，竞标方有阿里、拼多多、字节跳动。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457637_240.jpg?r=1574059075477","hitcount":4824,"commentcount":87,"cid":31,"sid":0,"url":"/0/457/637.htm"},{"newsid":457636,"title":"中国移动：Top8终端品牌存量份额近95%，高机龄客户市场庞大","postdate":"2019-11-18T12:52:04.193","orderdate":"2019-11-18T12:52:04.193","description":"中国移动公布了国内前八名手机品牌的存量份额，其中OPPO占据22%、vivo占据21%、华为15%、苹果15%、荣耀11%、小米7%、三星2%、魅族1%，累计份额近95%。","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457636_240.jpg?r=1574052724193","hitcount":4422,"commentcount":67,"cid":74,"sid":0,"url":"/0/457/636.htm"},{"newsid":457635,"title":"性能狂暴的道路机器：福特Mustang Mach-E正式发布","postdate":"2019-11-18T12:50:32.793","orderdate":"2019-11-18T12:50:32.793","description":"今天福特正式将这款基于Mustang打造的纯电动SUV：福特Mustang Mach-E带到发布会的展台，而这也是福特品牌下的第一款纯电动SUV车型","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457635_240.jpg?r=1574052632793","hitcount":6433,"commentcount":32,"cid":160,"sid":0,"url":"/0/457/635.htm"},{"newsid":457634,"title":"《暗黑破坏神2》重制版可能凉了：因源代码和资源遭损坏","postdate":"2019-11-18T12:41:32.437","orderdate":"2019-11-18T12:41:32.437","description":"在今年的暴雪嘉年华中出现了很多关于暴雪新游的消息，除了《守望先锋2》、《暗黑4》等大作的消息公布外令人惊讶的是没有有关《暗黑2》重制版的消息释出，不过在GameSpot的采访中《暗黑2》制作人给出了相应的解释","image":"http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457634_240.jpg?r=1574052092437","hitcount":8844,"commentcount":82,"cid":76,"sid":0,"url":"/0/457/634.htm"}]
     * lapin : false
     */

    private boolean lapin;
    private List<ToplistBean> toplist;
    private List<NewslistBean> newslist;

    protected HomeListBean(Parcel in) {
        lapin = in.readByte() != 0;
    }

    public static final Creator<HomeListBean> CREATOR = new Creator<HomeListBean>() {
        @Override
        public HomeListBean createFromParcel(Parcel in) {
            return new HomeListBean(in);
        }

        @Override
        public HomeListBean[] newArray(int size) {
            return new HomeListBean[size];
        }
    };

    public boolean isLapin() {
        return lapin;
    }

    public void setLapin(boolean lapin) {
        this.lapin = lapin;
    }

    public List<ToplistBean> getToplist() {
        return toplist;
    }

    public void setToplist(List<ToplistBean> toplist) {
        this.toplist = toplist;
    }

    public List<NewslistBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistBean> newslist) {
        this.newslist = newslist;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (lapin ? 1 : 0));
    }

    public static class ToplistBean {
        /**
         * client : 6
         * device : 6
         * topplat : 6
         * newsid : 457492
         * title : 云日历iOS版1.02更新：iPad适配、第8个小组件、海量更新
         * postdate : 2019-11-17T14:31:31.407
         * orderdate : 0001-01-01T00:00:00
         * description : @软媒 今天为云日历iOS版添加了第8个小组件，迄今为止，世界范围内，应该没有其他同类App支持如此众多的小组件。我们今天更适配了iPadOS，所有小组件也能在iPad设备中完美使用，今天的更新太多了，详见——
         * image : http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457492_240.jpg?r=1573972291407
         * hitcount : 21909
         * commentcount : 468
         * cid : 58
         * sid : 0
         * url : https://www.ithome.com/0/457/492.htm
         */

        private String client;
        private String device;
        private String topplat;
        private int newsid;
        private String title;
        private String postdate;
        private String orderdate;
        private String description;
        private String image;
        private int hitcount;
        private int commentcount;
        private int cid;
        private int sid;
        private String url;

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getTopplat() {
            return topplat;
        }

        public void setTopplat(String topplat) {
            this.topplat = topplat;
        }

        public int getNewsid() {
            return newsid;
        }

        public void setNewsid(int newsid) {
            this.newsid = newsid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPostdate() {
            return postdate;
        }

        public void setPostdate(String postdate) {
            this.postdate = postdate;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getHitcount() {
            return hitcount;
        }

        public void setHitcount(int hitcount) {
            this.hitcount = hitcount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class NewslistBean implements Parcelable{
        /**
         * newsid : 457666
         * title : 语音识别大拿Daniel Povey将出任小米集团语音首席科学家
         * postdate : 2019-11-18T14:42:54.247
         * orderdate : 2019-11-18T14:42:54.247
         * description : 北京时间今日午间，小米创办人、董事长兼CEO雷军发微博表示，Daniel Povey将出任小米集团语音首席科学家，他将在2019小米开发者大会上和大家分享“AI语音领域的未来技术趋势”
         * image : http://img.ithome.com/newsuploadfiles/thumbnail/2019/11/457666_240.jpg?r=1574059374247
         * hitcount : 402
         * commentcount : 0
         * cid : 31
         * sid : 0
         * url : /0/457/666.htm
         * imagelist : ["http://img.ithome.com/newsuploadfiles/2019/11/20191118_143536_908.jpg@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/11/20191118_143706_624.jpg@s_2,w_240,h_180","http://img.ithome.com/newsuploadfiles/2019/11/20191118_143832_706.jpg@s_2,w_240,h_180"]
         * aid : 1
         */

        private int newsid;
        private String title;
        private String postdate;
        private String orderdate;
        private String description;
        private String image;
        private int hitcount;
        private int commentcount;
        private int cid;
        private int sid;
        private String url;
        private int aid;
        private List<String> imagelist;

        protected NewslistBean(Parcel in) {
            newsid = in.readInt();
            title = in.readString();
            postdate = in.readString();
            orderdate = in.readString();
            description = in.readString();
            image = in.readString();
            hitcount = in.readInt();
            commentcount = in.readInt();
            cid = in.readInt();
            sid = in.readInt();
            url = in.readString();
            aid = in.readInt();
            imagelist = in.createStringArrayList();
        }

        public static final Creator<NewslistBean> CREATOR = new Creator<NewslistBean>() {
            @Override
            public NewslistBean createFromParcel(Parcel in) {
                return new NewslistBean(in);
            }

            @Override
            public NewslistBean[] newArray(int size) {
                return new NewslistBean[size];
            }
        };

        public int getNewsid() {
            return newsid;
        }

        public void setNewsid(int newsid) {
            this.newsid = newsid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPostdate() {
            return postdate;
        }

        public void setPostdate(String postdate) {
            this.postdate = postdate;
        }

        public String getOrderdate() {
            return orderdate;
        }

        public void setOrderdate(String orderdate) {
            this.orderdate = orderdate;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getHitcount() {
            return hitcount;
        }

        public void setHitcount(int hitcount) {
            this.hitcount = hitcount;
        }

        public int getCommentcount() {
            return commentcount;
        }

        public void setCommentcount(int commentcount) {
            this.commentcount = commentcount;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getSid() {
            return sid;
        }

        public void setSid(int sid) {
            this.sid = sid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public List<String> getImagelist() {
            return imagelist;
        }

        public void setImagelist(List<String> imagelist) {
            this.imagelist = imagelist;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(newsid);
            dest.writeString(title);
            dest.writeString(postdate);
            dest.writeString(orderdate);
            dest.writeString(description);
            dest.writeString(image);
            dest.writeInt(hitcount);
            dest.writeInt(commentcount);
            dest.writeInt(cid);
            dest.writeInt(sid);
            dest.writeString(url);
            dest.writeInt(aid);
            dest.writeStringList(imagelist);
        }
    }
}
