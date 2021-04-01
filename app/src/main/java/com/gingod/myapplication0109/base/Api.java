package com.gingod.myapplication0109.base;

/**
 * @author tsn
 */
public class Api {

    /**
     * BaseUrl,BaseUrl2,文库中资料视频,websocket
     */
//    public static String URL_BASE = "http://10.66.5.124:8080/";
//    public static String URL_BASE_8105 = "http://10.66.5.124:8105/";
//    public static String URL_BASE2 = "http://10.66.5.123:8080/";
//    public static String WS = "ws://10.66.5.124:8080/qnchat/websocket/";
//    public static String YP = "http://10.66.5.124:8054/";
//    public static String AliPay = "http://10.66.5.124:8080/";
//    public static String LIBRARY_URL = "http://10.66.5.124:8054/";
//    public static String LIBRARY_URL = "http://192.168.1.109:4300/";

    /**
     * 正式测试环境
     */
    public static String URL_BASE = "https://www.1000dpt.com:444/";
    public static String URL_BASE_8105 = "https://www.1000dpt.com:8200/qnnet/";
    public static String URL_BASE2 = "https://www.1000dpt.com:444/";
    public static String WS = "wss://qq.1000dpt.com:444/qnchat/websocket/";
    public static String YP = "https://clouddisk.1000dpt.com:444/";
    public static String AliPay = "https://www.1000dpt.com:444/";
    public static String LIBRARY_URL = "https://library.1000dpt.com:444/";

    /**
     * 生产环境
     */
//    public static String URL_BASE = "https://www.1000dpt.com/";
//    public static String URL_BASE2 = "https://www.1000dpt.com/";
//    public static String WS = "wss://qq.1000dpt.com/qnchat/websocket/";
//    public static String YP = "https://clouddisk.1000dpt.com/";
//    public static String AliPay = "https://www.1000dpt.com:444/";
//    public static String LIBRARY_URL = "https://library.1000dpt.com:444/";

    /**
     * 是否打印日志
     */
//    public static boolean loggerAble = false;
    public static boolean loggerAble = true;

    public static String USERACCOUNT = "userAccount";
    public static String PHONENUM = "phoneNum";
    public static String PASSWORD = "password";
    public static String TOKEN = "token";
    public static String TOACCOUNT = "TOACC";
    public static String PWD = "pwd";
    public static String USERID = "userId";
    public static String USERTYPE = "userType";
    public static String NICKNAME = "nickName";
    public static String TOPPIC = "toppic";
    public static String COMPANY_ID = "COMPANY_ID";
    public static String COMPANY_PASSWORD = "COMPANY_PASSWORD";
    public static String COMPANY_PHONE = "COMPANY_PHONE";
    public static String PER_INFO = "PER_INFO";
    public static String SHOW_PER_INF = "SHOW_PER_INF";
    public static String IS_BINDING_PAYMENT = "IS_BINDING_PAYMENT";

    public static String textMsgFeedback = "textMsgFeedback";
    //撤回消息回执
    public static String revokeMsgFeedback = "revokeMsgFeedback";
    public static String textMsg = "textMsg";
    public static String fileMsg = "fileMsg";
    public static String cardMsg = "cardMsg";
    public static String imgMsg = "imgMsg";
    public static String videoMsg = "videoMsg";
    public static String videoApplyMsg = "videoApplyMsg";
    public static String revokeMsg = "revokeMsg";
    public static String audioMsg = "audioMsg";
    public static String audioApplyMsg = "audioApplyMsg";
    public static String noticeMsg = "noticeMsg";
    public static String shareMsg = "shareMsg";
    public static String noticeMsgFeedback = "noticeMsgFeedback";
    public static String aitMsg = "aitMsg";
    public static String notice = "notice";
    public static String addFriend = "addFriend";
    public static String agreeAdd = "agreeAdd";
    //空信息, 即错误信息
    public static String emptyMsg = "emptyMsg";
    //心跳
    public static String heartMsg = "heartMsg";

    //通知
    public static String notification = "notification";

    /**
     * 0x001-接受消息  0x002-发送消息
     **/
    public static final int CHAT_ITEM_TYPE_LEFT = 0x001;
    public static final int CHAT_ITEM_TYPE_RIGHT = 0x002;
    /**
     * 0x003-发送中  0x004-发送失败  0x005-发送成功
     **/
    public static final int CHAT_ITEM_SENDING = 0x003;
    public static final int CHAT_ITEM_SEND_ERROR = 0x004;
    public static final int CHAT_ITEM_SEND_SUCCESS = 0x005;
    /**
     * 0x006-自己撤回的消息  0x007-对方撤回的消息
     **/
    public static final int CHAT_ITEM_TYPE_WITHDRAW_SELF = 0x006;
    public static final int CHAT_ITEM_TYPE_WITHDRAW_OTHER = 0x007;
    public static final int CHAT_ITEM_TYPE_NOTICEMSG = 0x008;
    public static final int CHAT_ITEM_TYPE_PICORVIDEO = 0x009;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT = 0x010;
    public static final int CHAT_ITEM_TYPE_SEND_PIC = 0x011;
    public static final int CHAT_ITEM_TYPE_SEND_AUDIO = 0x012;
    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT = 0x013;
    public static final int CHAT_ITEM_TYPE_ACCEPT_PIC = 0x014;
    public static final int CHAT_ITEM_TYPE_ACCEPT_AUDIO = 0x015;

    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT_10 = 0x016;
    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT_20 = 0x017;
    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT_40 = 0x018;
    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT_100 = 0x019;
    public static final int CHAT_ITEM_TYPE_ACCEPT_TEXT_500 = 0x020;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT_10 = 0x021;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT_20 = 0x022;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT_40 = 0x023;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT_100 = 0x024;
    public static final int CHAT_ITEM_TYPE_SEND_TEXT_500 = 0x025;

    public static final int CHAT_ITEM_TYPE_ACCEPT_SHARE = 0x026;
    public static final int CHAT_ITEM_TYPE_SEND_SHARE = 0x027;
    /**
     * 数据库cachedata 的 type
     */
    //首页聊天列表
    public static String listOfMsg = "listOfMsg";
    //网络返回数据
    public static String listOfMsgFromNetAll = "listOfMsgFromNetAll";
    public static String listOfMsgFromNetSingle = "listOfMsgFromNetSingle";
    //本地头像
    public static String msgListImageFilePath = "msgListImageFilePath";
    //联系人列表
    public static String mailList = "mailList";
    //下载文件成功
    public static String downFileSuccess = "downFileSuccess";
    //群成员信息
    public static String teamMember = "teamMember";
    //任务个数
    public static String countTask = "countTask";
    //任务单选择参数
    public static String taskSelect = "taskSelect";
    /**
     * 盖印人, 印章
     */
    public static String sealData = "sealData";
    /**
     * 文库大厅详情
     */
    public static String libraryData = "libraryData123";
    /**
     * 分类
     */
    public static String libraryClassifyCustom = "libraryClassifyCustom";
    public static String libraryClassifyCustomAll = "libraryClassifyCustomAll";

    public static String libraryManage = "libraryManage";
    public static String libraryCollection = "libraryCollection";

    //缓存数据状态 isUse:可用  noUse:不可用(相当于删除状态)
    public static String isUse = "1";
    public static String noUse = "0";

    public static String topping = "置顶";
    public static String notDisturb = "免打扰";
}
