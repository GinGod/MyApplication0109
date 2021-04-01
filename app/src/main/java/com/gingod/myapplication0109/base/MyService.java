package com.gingod.myapplication0109.base;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author tsn
 */
public interface MyService {

    /**
     * 表单编码字段 登录 1  type：个人用户1 企业用户2 getRequest(4),个人，getRequest(5)企业
     *
     * @param account
     * @param password
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/login/login")
    Call<Object> login(@Field("account") String account, @Field("password") String password, @Field("type") String type);

    /**
     * 获取验证码 verifyingSMS_Type 1：注册, getRequest(15) 2：重置密码,getRequest(8)
     * 3：实名认证，4：登录，getRequest(6) 5：绑定手机， 6：支付验证，7：绑定支付宝
     *
     * @param phoneNum
     * @param verifyingSmsType
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/common/getCode")
    Call<Object> getVeriCode(@Field("phoneNum") String phoneNum, @Field("verifyingSMS_Type") String verifyingSmsType);

    /**
     * 验证码验证 登录：getRequest(7)   重置密码：getRequest(9) 注册：getRequest(16)
     *
     * @param phoneNum
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/common/codeVerification")
    Call<Object> codeVerification(@Field("phoneNum") String phoneNum, @Field("code") String code);

    /**
     * 个人注册手机验证接口 getRequest(10) getRequest(14)
     *
     * @param phoneNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalUser/isPersonal")
    Call<Object> isPersonal(@Field("phoneNum") String phoneNum);

    /**
     * 企业注册手机号/账号验证接口 getRequest(11) getRequest(20)
     *
     * @param account
     * @param phoneNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/enterpriseUser/isEnterprise")
    Call<Object> isEnterprise(@Field("account") String account, @Field("phoneNum") String phoneNum);

    /**
     * 密码验证接口 getRequest(12) getRequest(17)
     *
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/login/passwordVerify")
    Call<Object> passwordVerify(@Field("password") String password);

    /**
     * 验证两次密码是否一致接口 getRequest(13) getRequest(18)
     *
     * @param password
     * @param confirmPassword
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/login/confirmPasswordVerify")
    Call<Object> confirmPasswordVerify(@Field("password") String password, @Field("confirmPassword") String confirmPassword);

    /**
     * 修改密码接口 getRequest(14)
     *
     * @param phoneNum
     * @param password
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/login/resetPassword")
    Call<Object> resetPassword(@Field("phoneNum") String phoneNum, @Field("password") String password, @Field("type") String type);

    /**
     * 个人注册接口 getRequest(19)
     *
     * @param phoneNum
     * @param code
     * @param password
     * @param repassword
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalUser/reg")
    Call<Object> personalUserReg(@Field("phoneNum") String phoneNum, @Field("code") String code, @Field("password") String password, @Field("repassword") String repassword);

    /**
     * 企业注册接口 getRequest(21)
     *
     * @param account
     * @param phoneNum
     * @param code
     * @param password
     * @param repassword
     * @param name
     * @param contact
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/enterpriseUser/reg")
    Call<Object> enterpriseUserReg(@Field("account") String account, @Field("phoneNum") String phoneNum, @Field("code") String code,
                                   @Field("password") String password, @Field("repassword") String repassword, @Field("name") String name, @Field("contact") String contact);

    /**
     * 显示通讯列表分组信息接口 22
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/mailList/showMailList")
    Call<Object> showMailList(@Header("Authorization") String authorization);

    /**
     * 添加好友接口 34
     *
     * @param authorization
     * @param friendType
     * @param friend99Id
     * @param message
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/addfriend")
    Call<Object> addfriend(@Header("Authorization") String authorization, @Field("friendType") int friendType,
                           @Field("friendId") long friend99Id, @Field("message") String message);

    /**
     * 查询用户创建的所有项目组接口 团队 23
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/teamNotice/selTeams")
    Call<Object> selTeams(@Header("Authorization") String authorization);

    /**
     * 查询我的团队接口 23
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/teamNotice/selMyInitTeams")
    Call<Object> selMyTeams(@Header("Authorization") String authorization);

    /**
     * 显示各分组成员接口 24
     *
     * @param authorization
     * @param groupId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/showGroupUser")
    Call<Object> showGroupUser(@Header("Authorization") String authorization, @Field("groupId") int groupId);

    /**
     * 显示添加好友请求列表接口（显示最近十天请求内容）25
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/mailList/showAddFriendList")
    Call<Object> showAddFriendList(@Header("Authorization") String authorization);

    /**
     * 好友申请验证通过/删除好友接口 28
     * operation: 操作类型（agree：同意，delete：删除）
     *
     * @param authorization
     * @param id
     * @param operation
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/updateUserFriend")
    Call<Object> updateUserFriend(@Header("Authorization") String authorization, @Field("id") long id, @Field("operation") String operation);

    /**
     * 创建分组接口 35
     *
     * @param authorization
     * @param groupName
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/createGroup")
    Call<Object> createGroup(@Header("Authorization") String authorization, @Field("groupName") String groupName);

    /**
     * 修改分组接口 36
     *
     * @param authorization
     * @param groupId
     * @param groupName
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/updateGroup")
    Call<Object> updateGroup(@Header("Authorization") String authorization, @Field("groupId") int groupId, @Field("groupName") String groupName);

    /**
     * 删除分组接口 37
     *
     * @param authorization
     * @param groupId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/deleteGroup")
    Call<Object> deleteGroup(@Header("Authorization") String authorization, @Field("groupId") int groupId);

    /**
     * 模糊搜索通讯录好友接口 38
     *
     * @param authorization
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/searchUserFriends")
    Call<Object> searchUserFriends(@Header("Authorization") String authorization, @Field("content") String content);

    /**
     * 模糊搜索全平台成员接口
     *
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/common/searchAllNet")
    Call<Object> searchAllNet(@Field("content") String content);

    /**
     * 修改备注接口 33
     *
     * @param authorization
     * @param id
     * @param remarkName
     * @param remarkerType
     * @param remarkerId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/operationRemark")
    Call<Object> operationRemark(@Header("Authorization") String authorization, @Field("id") long id, @Field("remarkName") String remarkName,
                                 @Field("remarkerType") long remarkerType, @Field("id") long remarkerId);

    /**
     * 添加备注接口 32
     *
     * @param authorization
     * @param remarkName
     * @param remarkerType
     * @param remarkerId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/operationRemark")
    Call<Object> operationRemark(@Header("Authorization") String authorization, @Field("remarkName") String remarkName,
                                 @Field("remarkerType") int remarkerType, @Field("remarkerId") long remarkerId);

    /**
     * 查询备注信息接口 31
     *
     * @param authorization
     * @param remarkerType
     * @param remarkerId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/selRemark")
    Call<Object> selRemark(@Header("Authorization") String authorization, @Field("remarkerType") int remarkerType, @Field("remarkerId") long remarkerId);

    /**
     * 显示用户的个人资料接口 27
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalInf/showPerInf")
    Call<Object> showPerInf(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取个人用户资料信息 26
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/perInfo")
    Call<Object> perInfo(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 获取企业用户公司介绍列表 29
     *
     * @param authorization
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyIntrList")
    Call<Object> companyIntrList(@Header("Authorization") String authorization, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 模糊搜索全平台成员接口 30
     *
     * @param authorization
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/common/searchAllNet")
    Call<Object> searchAllNet(@Header("Authorization") String authorization, @Field("content") String content);

    /**
     * 显示所有分组接口 41
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/mailList/showAllGroup")
    Call<Object> showAllGroup(@Header("Authorization") String authorization);

    /**
     * 重新选择分组接口 42
     *
     * @param authorization
     * @param groupId
     * @param chooserType
     * @param chooserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/choiceGroup")
    Call<Object> choiceGroup(@Header("Authorization") String authorization, @Field("groupId") long groupId,
                             @Field("chooserType") int chooserType, @Field("chooserId") long chooserId);

    /**
     * 显示所有分组接口 43
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/delAddFriendList")
    Call<Object> delAddFriendList(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 查询所有好友接口 44
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/mailList/showAllFriends")
    Call<Object> showAllFriends(@Header("Authorization") String authorization);

    /**
     * 管理项目组和添加组成员接口 45
     *
     * @param authorization
     * @param teamNum
     * @param teamName
     * @param datas
     * @return
     */
    @POST("qnchat/teamNotice/manageProTeam")
    Call<Object> manageProTeam(@Header("Authorization") String authorization, @Query("teamNum") String teamNum, @Query("teamName") String teamName, @Query("datas") String datas);

    /**
     * 获取企业用户团队介绍列表 46
     *
     * @param authorization
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/teamIntrList")
    Call<Object> teamIntrList(@Header("Authorization") String authorization, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 获取企业用户公司案例列表 47
     *
     * @param authorization
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyCaseList")
    Call<Object> companyCaseList(@Header("Authorization") String authorization, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 删除好友接口 48
     *
     * @param authorization
     * @param friendType
     * @param friendId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/delUserFriend")
    Call<Object> delUserFriend(@Header("Authorization") String authorization, @Field("friendType") int friendType, @Field("friendId") long friendId);

    /**
     * 显示企业基本资料接口 49
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/enterpriseInf/showenterpriseInf")
    Call<Object> showenterpriseInf(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 上传文件接口 50
     *
     * @param authorization
     * @param files
     * @param type
     * @return
     */
    @Multipart
    @POST("qnchat/message/upFile")
    Call<Object> upFile(@Header("Authorization") String authorization, @Part MultipartBody.Part files, @Query("type") String type);

    /**
     * 根据项目组编号查询所有成员信息接口 51
     *
     * @param authorization
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/noticeUser")
    Call<Object> noticeUser(@Header("Authorization") String authorization, @Field("projectId") long projectId);

    /**
     * 设置消息置顶和免打扰接口 52 53 228
     *
     * @param authorization
     * @param settingId
     * @param settingType
     * @param primacy
     * @param disturb
     * @param showName      设置是否显示团队成员昵称（1显示，0不显示）
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/message/updateSettingStatus")
    Call<Object> updateSettingStatus(@Header("Authorization") String authorization, @Field("settingId") long settingId, @Field("settingType") int settingType,
                                     @Field("primacy") String primacy, @Field("disturb") String disturb, @Field("showName") String showName);

    /**
     * 查询消息列表用户关系及状态接口 56
     *
     * @param authorization
     * @param userId
     * @param userType
     * @param friendList
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/message/getUserInfo")
    Call<Object> getUserInfo(@Header("Authorization") String authorization, @Field("userId") long userId, @Field("userType") int userType,
                             @Field("friendList") String friendList);

    /**
     * 上传会议文件接口 57 type：1 共享文件，2会议纪要，3消息文件
     *
     * @param authorization
     * @param files         每一个part代表一个文件
     * @param meetingId
     * @param type
     * @return
     */
    @Multipart
    @POST("qnchat/meeting/upFile")
    Call<Object> upMeetFile(@Header("Authorization") String authorization, @Part() List<MultipartBody.Part> files, @Query("meetingId") String meetingId,
                            @Query("type") String type);

    /**
     * 即时视频会议接口 58
     *
     * @param authorization
     * @param theme
     * @param userJsonStr
     * @param fileIdStr
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/startVideo")
    Call<Object> startVideo(@Header("Authorization") String authorization, @Field("theme") String theme, @Field("userJsonStr") String userJsonStr,
                            @Field("fileIdStr") String fileIdStr);

    /**
     * 查询我的会议列表接口 59 type1：未开始会议 type2：进行中的会议 type3：结束的会议
     *
     * @param authorization
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/getMeetingList")
    Call<Object> getMeetingList(@Header("Authorization") String authorization, @Field("type") int type);

    /**
     * 视频会议结束接口 60
     *
     * @param authorization
     * @param meetingId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/exitVideo")
    Call<Object> exitVideo(@Header("Authorization") String authorization, @Field("meetingId") String meetingId);

    /**
     * 修改个人资料接口 61
     *
     * @param authorization
     * @param pic
     * @param sex
     * @param age
     * @param email
     * @param qq
     * @param wechat
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @return
     */
    @Headers("data:image/png;base64,")
    @FormUrlEncoded
    @POST("qnnet/personalInf/modifyingTheBasicInformation")
    Call<Object> modifyingTheBasicInformation(@Header("Authorization") String authorization, @Field("pic") String pic,
                                              @Field("sex") String sex, @Field("age") String age, @Field("email") String email, @Field("qq") String qq,
                                              @Field("wechat") String wechat, @Field("provinceId") String provinceId, @Field("cityId") String cityId,
                                              @Field("areaId") String areaId, @Field("address") String address);

    /**
     * 修改个人昵称接口 62
     *
     * @param authorization
     * @param userId
     * @param nickName
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalInf/modifyTheNickname")
    Call<Object> modifyTheNickname(@Header("Authorization") String authorization, @Field("userId") long userId, @Field("nickName") String nickName);

    /**
     * 生成用户二维码接口 63
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/mailList/createQRCode")
    Call<Object> createQrCode(@Header("Authorization") String authorization);

    /**
     * 判断是否是好友接口 64
     *
     * @param authorization
     * @param friendType
     * @param friendId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/isfriend")
    Call<Object> isfriend(@Header("Authorization") String authorization, @Field("friendType") String friendType, @Field("friendId") String friendId);

    /**
     * 修改个人用户资料 65
     *
     * @param authorization
     * @param id
     * @param workTime
     * @param professionalQualifications
     * @param speciality
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/perInfoModify")
    Call<Object> perInfoModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("workTime") String workTime,
                               @Field("professionalQualifications") String professionalQualifications, @Field("speciality") String speciality,
                               @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户资料 66
     *
     * @param authorization
     * @param workTime
     * @param professionalQualifications
     * @param speciality
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/perInfoAdd")
    Call<Object> perInfoAdd(@Header("Authorization") String authorization, @Field("workTime") String workTime,
                            @Field("professionalQualifications") String professionalQualifications, @Field("speciality") String speciality,
                            @Field("personalUserId") long personalUserId);

    /**
     * 查询个人工作经验 67
     *
     * @param authorization
     * @param userid
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/workExpList")
    Call<Object> workExpList(@Header("Authorization") String authorization, @Field("userid") long userid);

    /**
     * 添加个人工作经验接口 68
     *
     * @param authorization
     * @param workStartDates
     * @param workEndDates
     * @param companyName
     * @param departmentName
     * @param jobName
     * @param companyScale
     * @param companyType
     * @param speciality
     * @param jobDescription
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/workExpAdd")
    Call<Object> workExpAdd(@Header("Authorization") String authorization, @Field("workStartDates") String workStartDates,
                            @Field("workEndDates") String workEndDates, @Field("companyName") String companyName,
                            @Field("departmentName") String departmentName, @Field("jobName") String jobName,
                            @Field("companyScale") String companyScale, @Field("companyType") String companyType,
                            @Field("speciality") String speciality, @Field("jobDescription") String jobDescription,
                            @Field("personalUserId") long personalUserId);

    /**
     * 编辑个人工作经验接口 69
     *
     * @param authorization
     * @param id
     * @param workStartDates
     * @param workEndDates
     * @param companyName
     * @param departmentName
     * @param jobName
     * @param companyScale
     * @param companyType
     * @param speciality
     * @param jobDescription
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/workExpModify")
    Call<Object> workExpModify(@Header("Authorization") String authorization, @Field("id") long id,
                               @Field("workStartDates") String workStartDates, @Field("workEndDates") String workEndDates,
                               @Field("companyName") String companyName, @Field("departmentName") String departmentName,
                               @Field("jobName") String jobName, @Field("companyScale") String companyScale,
                               @Field("companyType") String companyType, @Field("speciality") String speciality,
                               @Field("jobDescription") String jobDescription, @Field("personalUserId") long personalUserId);

    /**
     * 删除个人工作经验接口 70
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/workExpDelete")
    Call<Object> workExpDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 查询个人项目经验接口 39
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proExpList")
    Call<Object> proExpList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人项目经验接口 71
     *
     * @param authorization
     * @param proStartDates
     * @param proEndDates
     * @param projectName
     * @param projectDescription
     * @param personalResponsibility
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proExpAdd")
    Call<Object> proExpAdd(@Header("Authorization") String authorization, @Field("proStartDates") String proStartDates,
                           @Field("proEndDates") String proEndDates, @Field("projectName") String projectName,
                           @Field("projectDescription") String projectDescription, @Field("personalResponsibility") String personalResponsibility,
                           @Field("personalUserId") long personalUserId);

    /**
     * 编辑个人项目经验接口 72
     *
     * @param authorization
     * @param id
     * @param proStartDates
     * @param proEndDates
     * @param projectName
     * @param projectDescription
     * @param personalResponsibility
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proExpModify")
    Call<Object> proExpModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("proStartDates") String proStartDates,
                              @Field("proEndDates") String proEndDates, @Field("projectName") String projectName,
                              @Field("projectDescription") String projectDescription, @Field("personalResponsibility") String personalResponsibility);

    /**
     * 删除个人用户项目经验 73
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proExpDelete")
    Call<Object> proExpDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取个人用户教育经历列表 74
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/eduExpList")
    Call<Object> eduExpList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户教育经历 75
     *
     * @param authorization
     * @param eduStartDates
     * @param eduEndDates
     * @param schoolName
     * @param majorName
     * @param educationBackground
     * @param majorSpecification
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/eduExpAdd")
    Call<Object> eduExpAdd(@Header("Authorization") String authorization, @Field("eduStartDates") String eduStartDates,
                           @Field("eduEndDates") String eduEndDates, @Field("schoolName") String schoolName,
                           @Field("majorName") String majorName, @Field("educationBackground") String educationBackground,
                           @Field("majorSpecification") String majorSpecification, @Field("personalUserId") long personalUserId);

    /**
     * 修改个人用户教育经历 76
     *
     * @param authorization
     * @param id
     * @param eduStartDates
     * @param eduEndDates
     * @param schoolName
     * @param majorName
     * @param educationBackground
     * @param majorSpecification
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/eduExpModify")
    Call<Object> eduExpModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("eduStartDates") String eduStartDates,
                              @Field("eduEndDates") String eduEndDates, @Field("schoolName") String schoolName,
                              @Field("majorName") String majorName, @Field("educationBackground") String educationBackground,
                              @Field("majorSpecification") String majorSpecification, @Field("personalUserId") long personalUserId);

    /**
     * 删除个人用户教育经历 77
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/eduExpDelete")
    Call<Object> eduExpDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取个人用户职称证书列表 78
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/vocCerList")
    Call<Object> vocCerList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户职称证书 79
     *
     * @param authorization
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param professionalQualifications
     * @param picture
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/vocCerAdd")
    Call<Object> vocCerAdd(@Header("Authorization") String authorization, @Query("cerStartDates") String cerStartDates,
                           @Query("cerEndDates") String cerEndDates, @Query("certificatelName") String certificatelName,
                           @Query("professionalQualifications") String professionalQualifications, @Part MultipartBody.Part picture,
                           @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户职称证书 80
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param professionalQualifications
     * @param picture
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/vocCerModify")
    Call<Object> vocCerModify(@Header("Authorization") String authorization, @Query("id") long id, @Query("cerStartDates") String cerStartDates,
                              @Query("cerEndDates") String cerEndDates, @Query("certificatelName") String certificatelName,
                              @Query("professionalQualifications") String professionalQualifications, @Part MultipartBody.Part picture,
                              @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户职称证书 80
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param professionalQualifications
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/vocCerModify")
    Call<Object> vocCerModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("cerStartDates") String cerStartDates,
                              @Field("cerEndDates") String cerEndDates, @Field("certificatelName") String certificatelName,
                              @Field("professionalQualifications") String professionalQualifications, @Field("personalUserId") long personalUserId);

    /**
     * 删除个人用户职称证书 81
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/vocCerDelete")
    Call<Object> vocCerDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取个人用户专业证书列表 82
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proCerList")
    Call<Object> proCerList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户专业证书 83
     *
     * @param authorization
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param picture
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/proCerAdd")
    Call<Object> proCerAdd(@Header("Authorization") String authorization, @Query("cerStartDates") String cerStartDates,
                           @Query("cerEndDates") String cerEndDates, @Query("certificatelName") String certificatelName,
                           @Part MultipartBody.Part picture, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户专业证书 84
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param picture
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/proCerModify")
    Call<Object> proCerModify(@Header("Authorization") String authorization, @Query("id") long id, @Query("cerStartDates") String cerStartDates,
                              @Query("cerEndDates") String cerEndDates, @Query("certificatelName") String certificatelName,
                              @Part MultipartBody.Part picture, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户专业证书 84
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param certificatelName
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proCerModify")
    Call<Object> proCerModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("cerStartDates") String cerStartDates,
                              @Field("cerEndDates") String cerEndDates, @Field("certificatelName") String certificatelName,
                              @Field("personalUserId") long personalUserId);

    /**
     * 删除个人用户专业证书 85
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/proCerDelete")
    Call<Object> proCerDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取个人用户专利证书列表 86
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/patentCerList")
    Call<Object> patentCerList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户专利证书 87
     *
     * @param authorization
     * @param cerStartDates
     * @param cerEndDates
     * @param patentName
     * @param patentType
     * @param picture
     * @param patentDescription
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/patentCerAdd")
    Call<Object> patentCerAdd(@Header("Authorization") String authorization, @Query("cerStartDates") String cerStartDates,
                              @Query("cerEndDates") String cerEndDates, @Query("patentName") String patentName,
                              @Query("patentType") String patentType, @Part MultipartBody.Part picture,
                              @Query("patentDescription") String patentDescription, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户专利证书 88
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param patentName
     * @param patentType
     * @param picture
     * @param patentDescription
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/patentCerModify")
    Call<Object> patentCerModify(@Header("Authorization") String authorization, @Query("id") long id, @Query("cerStartDates") String cerStartDates,
                                 @Query("cerEndDates") String cerEndDates, @Query("patentName") String patentName,
                                 @Query("patentType") String patentType, @Part MultipartBody.Part picture,
                                 @Query("patentDescription") String patentDescription, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户专利证书 88
     *
     * @param authorization
     * @param id
     * @param cerStartDates
     * @param cerEndDates
     * @param patentName
     * @param patentType
     * @param patentDescription
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/patentCerModify")
    Call<Object> patentCerModify(@Header("Authorization") String authorization, @Field("id") long id, @Field("cerStartDates") String cerStartDates,
                                 @Field("cerEndDates") String cerEndDates, @Field("patentName") String patentName,
                                 @Field("patentType") String patentType,
                                 @Field("patentDescription") String patentDescription, @Field("personalUserId") long personalUserId);

    /**
     * 删除个人用户专利证书 89
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/patentCerDelete")
    Call<Object> patentCerDelete(@Header("Authorization") String authorization, @Field("id") long id);


    /**
     * 获取个人用户作品列表 40
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/worksList")
    Call<Object> worksList(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 添加个人用户作品 90
     *
     * @param authorization
     * @param worksDates
     * @param worksName
     * @param picture
     * @param worksDescription
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/worksAdd")
    Call<Object> worksAdd(@Header("Authorization") String authorization, @Query("worksDates") String worksDates,
                          @Query("worksName") String worksName, @Part MultipartBody.Part picture,
                          @Query("worksDescription") String worksDescription, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户作品 91
     *
     * @param authorization
     * @param id
     * @param worksDates
     * @param worksName
     * @param picture
     * @param worksDescription
     * @param personalUserId
     * @return
     */
    @Multipart
    @POST("qnnet/perExhibition/worksModify")
    Call<Object> worksModify(@Header("Authorization") String authorization, @Query("id") long id, @Query("worksDates") String worksDates,
                             @Query("worksName") String worksName, @Part MultipartBody.Part picture,
                             @Query("worksDescription") String worksDescription, @Query("personalUserId") long personalUserId);

    /**
     * 修改个人用户作品 91
     *
     * @param authorization
     * @param id
     * @param worksDates
     * @param worksName
     * @param worksDescription
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/worksModify")
    Call<Object> worksModify(@Header("Authorization") String authorization, @Field("id") long id,
                             @Field("worksDates") String worksDates, @Field("worksName") String worksName,
                             @Field("worksDescription") String worksDescription, @Field("personalUserId") long personalUserId);

    /**
     * 删除个人用户作品 92
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/perExhibition/worksDelete")
    Call<Object> worksDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 查询个人用户认证信息接口 93
     *
     * @param authorization
     * @param uid
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalUserIdenVer/perReview")
    Call<Object> perReview(@Header("Authorization") String authorization, @Field("uid") long uid);

    /**
     * 查询认证结果接口 94
     *
     * @param authorization
     * @param personalUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/personalUserIdenVer/personalVerRes")
    Call<Object> personalVerRes(@Header("Authorization") String authorization, @Field("personalUserId") long personalUserId);

    /**
     * 个人用户信息认证接口 95
     *
     * @param authorization
     * @param personalUserId
     * @param realName
     * @param sex
     * @param idCardNumber
     * @param expireTime
     * @param addressDetail
     * @param picture1
     * @param picture2
     * @param picture3
     * @return
     */
    @Multipart
    @POST("qnnet/personalUserIdenVer/personalUserVer")
    Call<Object> personalUserVer(@Header("Authorization") String authorization, @Query("personalUserId") long personalUserId,
                                 @Query("realName") String realName, @Query("sex") String sex,
                                 @Query("idCardNumber") String idCardNumber, @Query("expire_time") String expireTime,
                                 @Query("address_detail") String addressDetail, @Part MultipartBody.Part picture1,
                                 @Part MultipartBody.Part picture2, @Part MultipartBody.Part picture3);

    /**
     * 修改个人用户认证信息接口 96
     *
     * @param authorization
     * @param uid
     * @param realName
     * @param sex
     * @param idCardNumber
     * @param expireTime
     * @param addressDetail
     * @param picture1
     * @param picture2
     * @param picture3
     * @return
     */
    @Multipart
    @POST("qnnet/personalUserIdenVer/upPersonalUserVer")
    Call<Object> upPersonalUserVer(@Header("Authorization") String authorization, @Query("uid") long uid,
                                   @Query("realName") String realName, @Query("sex") String sex,
                                   @Query("idCardNumber") String idCardNumber, @Query("expire_time") String expireTime,
                                   @Query("address_detail") String addressDetail, @Part MultipartBody.Part picture1,
                                   @Part MultipartBody.Part picture2, @Part MultipartBody.Part picture3);

    /**
     * 退出登录接口 97
     *
     * @param account
     * @param phoneNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/login/exitLogin")
    Call<Object> exitLogin(@Field("account") String account, @Field("phoneNum") String phoneNum);


    /**
     * 修改企业基本资料接口 98
     *
     * @param authorization
     * @param userId
     * @param pic
     * @param email
     * @param fixedTel
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param address
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/enterpriseInf/modifyingTheBasicInformation")
    Call<Object> modifyingTheBasicInformation(@Header("Authorization") String authorization, @Field("userId") long userId,
                                              @Field("pic") String pic, @Field("email") String email,
                                              @Field("fixedTel") String fixedTel, @Field("provinceId") String provinceId,
                                              @Field("cityId") String cityId, @Field("areaId") String areaId,
                                              @Field("address") String address);

    /**
     * 查询综合积分及认证信息接口 99
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/perExhibition/perCenterInfo")
    Call<Object> perCenterInfo(@Header("Authorization") String authorization);

    /**
     * 查询认证状态 100
     *
     * @param authorization
     * @param entUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entUserIdenVer/entVerRes")
    Call<Object> entVerRes(@Header("Authorization") String authorization, @Field("entUserId") long entUserId);

    /**
     * 添加企业用户公司介绍 101
     *
     * @param authorization
     * @param createDates
     * @param companyScale
     * @param companyType
     * @param speciality
     * @param companyProfile
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyIntrAdd")
    Call<Object> companyIntrAdd(@Header("Authorization") String authorization, @Field("createDates") String createDates,
                                @Field("companyScale") String companyScale, @Field("companyType") String companyType,
                                @Field("speciality") String speciality, @Field("companyProfile") String companyProfile,
                                @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司介绍 102
     *
     * @param authorization
     * @param id
     * @param createDates
     * @param companyScale
     * @param companyType
     * @param speciality
     * @param companyProfile
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyIntrModify")
    Call<Object> companyIntrModify(@Header("Authorization") String authorization, @Field("id") long id,
                                   @Field("createDates") String createDates, @Field("companyScale") String companyScale,
                                   @Field("companyType") String companyType, @Field("speciality") String speciality,
                                   @Field("companyProfile") String companyProfile, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 添加企业用户团队介绍 103
     *
     * @param authorization
     * @param teamName
     * @param teamProfile
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/teamIntrAdd")
    Call<Object> teamIntrAdd(@Header("Authorization") String authorization, @Field("teamName") String teamName,
                             @Field("teamProfile") String teamProfile, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户团队介绍 104
     *
     * @param authorization
     * @param id
     * @param teamName
     * @param teamProfile
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/teamIntrModify")
    Call<Object> teamIntrModify(@Header("Authorization") String authorization, @Field("id") long id,
                                @Field("teamName") String teamName, @Field("teamProfile") String teamProfile,
                                @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户团队介绍 105
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/teamIntrDelete")
    Call<Object> teamIntrDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取企业用户公司资质列表 106
     *
     * @param authorization
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyQuaList")
    Call<Object> companyQuaList(@Header("Authorization") String authorization, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 添加企业用户公司资质 107
     *
     * @param authorization
     * @param quaStartDates
     * @param quaEndDates
     * @param qualificationName
     * @param picture
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyQuaAdd")
    Call<Object> companyQuaAdd(@Header("Authorization") String authorization, @Query("quaStartDates") String quaStartDates,
                               @Query("quaEndDates") String quaEndDates, @Query("qualificationName") String qualificationName,
                               @Part MultipartBody.Part picture, @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司资质 108
     *
     * @param authorization
     * @param id
     * @param quaStartDates
     * @param quaEndDates
     * @param qualificationName
     * @param picture
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyQuaModify")
    Call<Object> companyQuaModify(@Header("Authorization") String authorization, @Query("id") long id,
                                  @Query("quaStartDates") String quaStartDates, @Query("quaEndDates") String quaEndDates,
                                  @Query("qualificationName") String qualificationName, @Part MultipartBody.Part picture,
                                  @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司资质 108
     *
     * @param authorization
     * @param id
     * @param quaStartDates
     * @param quaEndDates
     * @param qualificationName
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyQuaModify")
    Call<Object> companyQuaModify(@Header("Authorization") String authorization, @Field("id") long id,
                                  @Field("quaStartDates") String quaStartDates, @Field("quaEndDates") String quaEndDates,
                                  @Field("qualificationName") String qualificationName,
                                  @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 删除企业用户公司资质 109
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyQuaDelete")
    Call<Object> companyQuaDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 获取企业用户公司专利列表 112
     *
     * @param authorization
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyPatentList")
    Call<Object> companyPatentList(@Header("Authorization") String authorization, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 添加企业用户公司专利  113
     *
     * @param authorization
     * @param patentStartDates
     * @param patentEndDates
     * @param patentName
     * @param patentType
     * @param picture
     * @param patentDescription
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyPatentAdd")
    Call<Object> companyPatentAdd(@Header("Authorization") String authorization, @Query("patentStartDates") String patentStartDates,
                                  @Query("patentEndDates") String patentEndDates, @Query("patentName") String patentName,
                                  @Query("patentType") String patentType, @Part MultipartBody.Part picture,
                                  @Query("patentDescription") String patentDescription, @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司专利 114
     *
     * @param authorization
     * @param id
     * @param patentStartDates
     * @param patentEndDates
     * @param patentName
     * @param patentType
     * @param picture
     * @param patentDescription
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyPatentModify")
    Call<Object> companyPatentModify(@Header("Authorization") String authorization, @Query("id") long id,
                                     @Query("patentStartDates") String patentStartDates, @Query("patentEndDates") String patentEndDates,
                                     @Query("patentName") String patentName, @Query("patentType") String patentType,
                                     @Part MultipartBody.Part picture, @Query("patentDescription") String patentDescription,
                                     @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司专利 114
     *
     * @param authorization
     * @param id
     * @param patentStartDates
     * @param patentEndDates
     * @param patentName
     * @param patentType
     * @param patentDescription
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyPatentModify")
    Call<Object> companyPatentModify(@Header("Authorization") String authorization, @Field("id") long id,
                                     @Field("patentStartDates") String patentStartDates, @Field("patentEndDates") String patentEndDates,
                                     @Field("patentName") String patentName, @Field("patentType") String patentType,
                                     @Field("patentDescription") String patentDescription, @Field("enterpriseUserId") long enterpriseUserId);


    /**
     * 删除企业用户公司专利 115
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyPatentDelete")
    Call<Object> companyPatentDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 添加企业用户公司案例 116
     *
     * @param authorization
     * @param caseDates
     * @param caseName
     * @param picture
     * @param caseDescription
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyCaseAdd")
    Call<Object> companyCaseAdd(@Header("Authorization") String authorization, @Query("caseDates") String caseDates,
                                @Query("caseName") String caseName, @Part MultipartBody.Part picture,
                                @Query("caseDescription") String caseDescription, @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司案例 117
     *
     * @param authorization
     * @param id
     * @param caseDates
     * @param caseName
     * @param picture
     * @param caseDescription
     * @param enterpriseUserId
     * @return
     */
    @Multipart
    @POST("qnnet/entExhibition/companyCaseModify")
    Call<Object> companyCaseModify(@Header("Authorization") String authorization, @Query("id") long id,
                                   @Query("caseDates") String caseDates, @Query("caseName") String caseName,
                                   @Part MultipartBody.Part picture, @Query("caseDescription") String caseDescription,
                                   @Query("enterpriseUserId") long enterpriseUserId);

    /**
     * 修改企业用户公司案例 117
     *
     * @param authorization
     * @param id
     * @param caseDates
     * @param caseName
     * @param caseDescription
     * @param enterpriseUserId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyCaseModify")
    Call<Object> companyCaseModify(@Header("Authorization") String authorization, @Field("id") long id,
                                   @Field("caseDates") String caseDates, @Field("caseName") String caseName,
                                   @Field("caseDescription") String caseDescription, @Field("enterpriseUserId") long enterpriseUserId);

    /**
     * 删除企业用户公司案例 118
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entExhibition/companyCaseDelete")
    Call<Object> companyCaseDelete(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 企业认证提交接口 119
     *
     * @param authorization
     * @param enterpriseUserId
     * @param enterpriseName
     * @param licenseNumber
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param enterpriseAddress
     * @param registrationYear
     * @param scope
     * @param licensePic
     * @return
     */
    @Multipart
    @POST("qnnet/entUserIdenVer/entPersonalUserVer")
    Call<Object> entPersonalUserVer(@Header("Authorization") String authorization, @Query("enterpriseUserId") long enterpriseUserId,
                                    @Query("enterprise_name") String enterpriseName, @Query("license_number") String licenseNumber,
                                    @Query("provinceId") String provinceId, @Query("cityId") String cityId,
                                    @Query("areaId") String areaId, @Query("enterprise_address") String enterpriseAddress,
                                    @Query("registration_year") String registrationYear, @Query("scope") String scope,
                                    @Part MultipartBody.Part licensePic);

    /**
     * 企业法人认证提交接口 121
     *
     * @param authorization
     * @param enterpriseUserId
     * @param name
     * @param idCardNum
     * @param sex
     * @param idCardFront
     * @param idCardBack
     * @param expireTime
     * @param personAddress
     * @return
     */
    @Multipart
    @POST("qnnet/entUserIdenVer/entLegPersonVer")
    Call<Object> entLegPersonVer(@Header("Authorization") String authorization, @Query("enterpriseUserId") long enterpriseUserId,
                                 @Query("name") String name, @Query("id_card_num") String idCardNum,
                                 @Query("sex") String sex, @Part MultipartBody.Part idCardFront,
                                 @Part MultipartBody.Part idCardBack, @Query("expire_time") String expireTime,
                                 @Query("person_address") String personAddress);

    /**
     * 查看企业认证信息接口 122
     *
     * @param authorization
     * @param eid
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entUserIdenVer/entReview")
    Call<Object> entReview(@Header("Authorization") String authorization, @Field("eid") long eid);

    /**
     * 查看企业法人认证信息接口 123
     *
     * @param authorization
     * @param eid
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/entUserIdenVer/entLegReview")
    Call<Object> entLegReview(@Header("Authorization") String authorization, @Field("eid") long eid);


    /**
     * 修改企业认证信息接口 124
     *
     * @param authorization
     * @param eid
     * @param enterpriseName
     * @param licenseNmber
     * @param provinceId
     * @param cityId
     * @param areaId
     * @param enterpriseAddress
     * @param registrationYear
     * @param scope
     * @param licensePic
     * @return
     */
    @Multipart
    @POST("qnnet/entUserIdenVer/upEntPersonalUserVer")
    Call<Object> upEntPersonalUserVer(@Header("Authorization") String authorization, @Query("eid") long eid,
                                      @Query("enterprise_name") String enterpriseName, @Query("license_number") String licenseNmber,
                                      @Query("provinceId") String provinceId, @Query("cityId") String cityId,
                                      @Query("areaId") String areaId, @Query("enterprise_address") String enterpriseAddress,
                                      @Query("registration_year") String registrationYear, @Query("scope") String scope,
                                      @Part MultipartBody.Part licensePic);

    /**
     * 修改企业法人认证信息接口 125
     *
     * @param authorization
     * @param eid
     * @param name
     * @param idCardNum
     * @param sex
     * @param idCardFront
     * @param idCardBack
     * @param expireTime
     * @param personAddress
     * @return
     */
    @Multipart
    @POST("qnnet/entUserIdenVer/upEntLegPersonVer")
    Call<Object> upEntLegPersonVer(@Header("Authorization") String authorization, @Query("eid") long eid,
                                   @Query("name") String name, @Query("id_card_num") String idCardNum,
                                   @Query("sex") String sex, @Part MultipartBody.Part idCardFront,
                                   @Part MultipartBody.Part idCardBack, @Query("expire_time") String expireTime,
                                   @Query("person_address") String personAddress);

    /**
     * 更换绑定手机号接口 126
     *
     * @param authorization
     * @param newPhoneNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/setUp/replacePhoneNum")
    Call<Object> replacePhoneNum(@Header("Authorization") String authorization, @Field("newPhoneNum") String newPhoneNum);

    /**
     * 获取当前绑定手机号接口 127
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/setUp/getOldPhoneNum")
    Call<Object> getOldPhoneNum(@Header("Authorization") String authorization);

    /**
     * 验证旧密码接口 128
     *
     * @param authorization
     * @param oldPassword
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/setUp/verificationOldPassword")
    Call<Object> verificationOldPassword(@Header("Authorization") String authorization, @Field("oldPassword") String oldPassword);

    /**
     * 注销前验证账号接口 129
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/setUp/cancellationVerification")
    Call<Object> cancellationVerification(@Header("Authorization") String authorization);

    /**
     * 注销账号接口 130
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/setUp/cancellationAccount")
    Call<Object> cancellationAccount(@Header("Authorization") String authorization);

    /**
     * 根据用户id查询用户是否已经绑定支付宝接口 131
     *
     * @param authorization
     * @param userType
     * @param userId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/isBindingPayment")
    Call<Object> isBindingPayment(@Header("Authorization") String authorization, @Field("userType") int userType, @Field("userId") long userId);

    /**
     * 绑定支付宝账号接口 132
     *
     * @param authorization
     * @param userType
     * @param userId
     * @param paymentNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/bindingPayment")
    Call<Object> bindingPayment(@Header("Authorization") String authorization, @Field("userType") int userType, @Field("userId") long userId,
                                @Field("paymentNum") String paymentNum);

    /**
     * 解除绑定支付宝账号接口 197
     *
     * @param authorization
     * @param phoneNum
     * @param code
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/untyingPayment")
    Call<Object> untyingPayment(@Header("Authorization") String authorization, @Field("phoneNum") String phoneNum, @Field("code") String code);

    /**
     * 根据用户id查询其交易记录接口 196
     *
     * @param authorization
     * @param userType
     * @param userId
     * @param inexType
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/selOrderNoRecord")
    Call<Object> selOrderNoRecord(@Header("Authorization") String authorization, @Field("userType") int userType, @Field("userId") long userId,
                                  @Field("inexType") int inexType, @Field("startTime") String startTime, @Field("endTime") String endTime,
                                  @Field("pageIndex") int pageIndex, @Field("pageSize") int pageSize, @Field("orderSource") String orderSource);

    /**
     * 根据用户id查询其交易记录接口 196
     *
     * @param authorization
     * @param userType      用户类型
     * @param userId        用户id
     * @param inexType      收支类型（0：全部，1：支出，2：收入）
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/selOrderNoRecord")
    Call<Object> selOrderNoRecord(@Header("Authorization") String authorization, @Field("userType") int userType, @Field("userId") long userId,
                                  @Field("inexType") int inexType);

    /**
     * 查询其全部收入和支出接口 198
     *
     * @param authorization
     * @param inexType      收支类型（0：全部，1：支出，2：收入）
     * @param startTime     查询开始时间（预留，不传值）
     * @param endTime       查询结束时间（预留，不传值）
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/selAllOrder")
    Call<Object> selAllOrder(@Header("Authorization") String authorization, @Field("inexType") int inexType,
                             @Field("startTime") String startTime, @Field("endTime") String endTime);

    /**
     * 根据项目组编号查询前18位成员信息接口 133
     *
     * @param authorization
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/selectUserByShow")
    Call<Object> selectUserByShow(@Header("Authorization") String authorization, @Field("projectId") long projectId);

    /**
     * 查询会议详情接口 134
     *
     * @param authorization
     * @param meetingId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/getMeetingById")
    Call<Object> getMeetingById(@Header("Authorization") String authorization, @Field("meetingId") long meetingId);

    /**
     * 取消预约会议接口 135
     *
     * @param authorization
     * @param meetingId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/cancelMeeting")
    Call<Object> cancelMeeting(@Header("Authorization") String authorization, @Field("meetingId") long meetingId);

    /**
     * 预约会议接口 136
     *
     * @param authorization
     * @param theme
     * @param expectTime
     * @param startTime
     * @param userJsonStr
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/appointmentMeeting")
    Call<Object> appointmentMeeting(@Header("Authorization") String authorization, @Field("theme") String theme,
                                    @Field("expectTime") String expectTime, @Field("startTime") String startTime,
                                    @Field("userJsonStr") String userJsonStr);

    /**
     * 查询会议共享文件列表接口 137  type 文件类别（1会议共享文件，2会议纪要，3消息文件）
     *
     * @param authorization
     * @param meetingId
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/getFileList")
    Call<Object> appointmentMeeting(@Header("Authorization") String authorization, @Field("meetingId") long meetingId,
                                    @Field("type") int type);

    /**
     * 下载会议共享文件接口 138
     *
     * @param authorization
     * @param fileId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/meeting/downFile")
    Call<ResponseBody> downFile(@Header("Authorization") String authorization, @Field("fileId") long fileId);

    /*//查询所有的省市县信息 139
    @POST("qnnet/province/byAddress")
    Call<Object> byAddress(@Header("Authorization") String authorization);*/

    /**
     * 查询我的任务列表接口 139
     */
    @FormUrlEncoded
    @POST("qnchat/task/getTaskList")
    Call<Object> getTaskList(@Header("Authorization") String authorization, @Field("taskType") int taskType);

    /**
     * 统计任务分布接口 140
     *
     * @param authorization
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getStatisticsInfo")
    Call<Object> getStatisticsInfo(@Header("Authorization") String authorization, @Field("type") int type);

    /**
     * 查询部门/分公司列表接口 141
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/task/getDepartmentList")
    Call<Object> getDepartmentList(@Header("Authorization") String authorization);

    /**
     * 查询专业列表接口 142
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/task/getMajorList")
    Call<Object> getMajorList(@Header("Authorization") String authorization);

    /**
     * 查询附件专业列表接口
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getFileMajorList")
    Call<Object> getFileMajorList(@Header("Authorization") String authorization, @Field("taskId") long taskId);

    /**
     * 查询附件专业列表接口
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/addMajorName")
    Call<Object> addMajorName(@Header("Authorization") String authorization, @Field("taskId") long taskId, @Field("majorName") String majorName);

    /**
     * 自定义搜索任务列表接口 143
     *
     * @param authorization
     * @param type
     * @param departmentName
     * @param majorName
     * @param status
     * @param currPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/searchTaskList")
    Call<Object> searchTaskList(@Header("Authorization") String authorization, @Field("type") String type,
                                @Field("departmentName") String departmentName, @Field("majorName") String majorName,
                                @Field("status") String status, @Field("currPage") int currPage, @Field("pageSize") long pageSize, @Field("taskType") int taskType);

    /**
     * 自定义搜索任务列表接口 143
     *
     * @param authorization
     * @param type
     * @param taskName
     * @param departmentName
     * @param majorName
     * @param status
     * @param currPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/searchTaskList")
    Call<Object> searchTaskList(@Header("Authorization") String authorization, @Field("type") String type, @Field("taskName") String taskName,
                                @Field("departmentName") String departmentName, @Field("majorName") String majorName,
                                @Field("status") String status, @Field("currPage") int currPage, @Field("pageSize") long pageSize);

    /**
     * 删除离线消息 144
     *
     * @param authorization
     * @param msgId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/message/delOfflineMsg")
    Call<Object> delOfflineMsg(@Header("Authorization") String authorization, @Field("msgId") String msgId);

    /**
     * 管理项目组和添加组成员接口 145
     *
     * @param authorization
     * @param teamNum
     * @param teamName
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/manageProTeam")
    Call<Object> manageProTeam(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("teamName") String teamName);

    /**
     * 管理项目组和添加组成员接口 145
     *
     * @param authorization
     * @param teamNum
     * @param teamName
     * @param datas
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/manageProTeam")
    Call<Object> manageProTeam(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("teamName") String teamName, @Field("datas") String datas);

    /**
     * 删除成员或成员退出接口 146
     *
     * @param authorization
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/outTeam")
    Call<Object> outTeam(@Header("Authorization") String authorization, @Field("id") long id);

    /**
     * 在团队中模糊查询团队成员
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/searchInTeam")
    Call<Object> searchInTeam(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("content") String content);

    /**
     * 删除项目组及其所有成员接口 147
     *
     * @param authorization
     * @param teamNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/delTeam")
    Call<Object> delTeam(@Header("Authorization") String authorization, @Field("teamNum") long teamNum);

    /**
     * 群主退出项目组后自动延位下一个成员接口 148
     *
     * @param authorization
     * @param teamNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/changeOwner")
    Call<Object> changeOwner(@Header("Authorization") String authorization, @Field("teamNum") long teamNum);

    /**
     * 添加团队成员接口（非群主添加） 150
     *
     * @param authorization
     * @param teamNum
     * @param datas
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/insterTeamMember")
    Call<Object> insterTeamMember(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("datas") String datas);

    /**
     * 扫描添加团队成员接口（非群主添加） 150
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/insterTeamMember")
    Call<Object> insterTeamMember(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("datas") String datas, @Field("source") String source);

    /**
     * 发布公告信息接口 151
     *
     * @param authorization
     * @param noticeInfo
     * @param noticePartakeUser
     * @param projectId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/noticeAdd")
    Call<Object> noticeAdd(@Header("Authorization") String authorization, @Field("noticeInfo") String noticeInfo, @Field("noticePartakeUser") long noticePartakeUser,
                           @Field("projectId") long projectId);

    /**
     * 设置成员在团队中备注接口 152
     *
     * @param authorization
     * @param teamNum
     * @param teamRemark
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/teamNotice/setTeamRemark")
    Call<Object> setTeamRemark(@Header("Authorization") String authorization, @Field("teamNum") long teamNum, @Field("teamRemark") String teamRemark);

    /**
     * 全部人才列表接口 153
     *
     * @param authorization
     * @param workTime
     * @param level
     * @param speciality
     * @param cost
     * @param score
     * @param sortType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/talent/allTalentList")
    Call<Object> allTalentList(@Header("Authorization") String authorization, @Field("workTime") String workTime, @Field("level") String level,
                               @Field("speciality") String speciality, @Field("cost") String cost,
                               @Field("score") String score, @Field("sortType") String sortType,
                               @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 查询资料列表接口（找资料和文库大厅） 156
     *
     * @param authorization
     * @param classify
     * @param queryType
     * @param articleType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/selLibrary")
    Call<Object> selLibrary(@Header("Authorization") String authorization, @Field("classify") String classify, @Field("queryType") String queryType,
                            @Field("articleType") String articleType, @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 根据文章/资料编号查看详细信息接口 157
     *
     * @param authorization
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/myLibraryInfo")
    Call<Object> myLibraryInfo(@Header("Authorization") String authorization, @Field("libraryNum") long libraryNum);

    /**
     * 管理侧-根据类别专业查询所有需求信息接口（项目大厅通用）158
     *
     * @param authorization
     * @param projectClassify
     * @param projectMajor
     * @param queryType
     * @param sortType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/scrDemand")
    Call<Object> scrDemand(@Header("Authorization") String authorization, @Field("projectClassify") String projectClassify,
                           @Field("projectMajor") String projectMajor, @Field("queryType") String queryType,
                           @Field("sortType") String sortType, @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 根据项目编号查询项目的详细信息接口 159
     *
     * @param authorization
     * @param projectNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/demand/selDemandByNum")
    Call<Object> selDemandByNum(@Header("Authorization") String authorization, @Field("projectNum") long projectNum);

    /**
     * 根据用户id和项目编号查询项目是否已收藏接口 160
     *
     * @param authorization
     * @param projectNum
     * @param collectionType
     * @param collectionId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/project/isCollection")
    Call<Object> isCollection(@Header("Authorization") String authorization, @Field("projectNum") long projectNum,
                              @Field("collectionType") int collectionType, @Field("collectionId") long collectionId);

    /**
     * 显示项目是否已投标接口 161
     *
     * @param authorization
     * @param projectId
     * @param tendererType
     * @param tendererId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/project/isTender")
    Call<Object> isTender(@Header("Authorization") String authorization, @Field("projectId") long projectId,
                          @Field("tendererType") int tendererType, @Field("tendererId") long tendererId);

    /**
     * 添加收藏接口 162
     *
     * @param authorization
     * @param projectNum
     * @param collectionType
     * @param collectionId
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/project/addCollection")
    Call<Object> addCollection(@Header("Authorization") String authorization, @Field("projectNum") long projectNum,
                               @Field("collectionType") int collectionType, @Field("collectionId") long collectionId);

    /**
     * 项目投标/添加项目接口 163
     *
     * @param authorization
     * @param projectId
     * @param tendererType
     * @param tendererId
     * @param phone
     * @param tendererMessage
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/project/addMyproject")
    Call<Object> addMyproject(@Header("Authorization") String authorization, @Field("projectId") long projectId,
                              @Field("tendererType") int tendererType, @Field("tendererId") long tendererId,
                              @Field("phone") String phone, @Field("tendererMessage") String tendererMessage);

    /**
     * 年会签到接口 164
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/annualMeeting/register")
    Call<Object> register(@Header("Authorization") String authorization);

    /**
     * 记录用户咨询状况接口 165
     *
     * @param authorization
     * @param serverId
     * @param serverType
     * @param isFees
     * @param consultationFees
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/talent/consultationRecord")
    Call<Object> consultationRecord(@Header("Authorization") String authorization, @Field("serverId") long serverId,
                                    @Field("serverType") int serverType, @Field("isFees") long isFees,
                                    @Field("consultationFees") int consultationFees);

    /**
     * 创建任务 166 type 任务类型（个人任务为1，企业任务2）
     *
     * @param authorization
     * @param type
     * @param taskNum
     * @param departmentName
     * @param majorName
     * @param start
     * @param end
     * @param description
     * @param taskName
     * @param fileStr
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/addTask")
    Call<Object> addTask(@Header("Authorization") String authorization, @Field("type") int type,
                         @Field("taskNum") String taskNum, @Field("departmentName") String departmentName,
                         @Field("majorName") String majorName, @Field("start") String start, @Field("end") String end,
                         @Field("description") String description, @Field("taskName") String taskName,
                         @Field("fileStr") String fileStr);

    /**
     * 上传附件 167
     *
     * @param authorization
     * @param taskId
     * @param type
     * @param identification
     * @param files
     * @param fromType
     * @param fileName
     * @param templateFileType
     * @return
     */
    @Multipart
    @POST("qnchat/task/upFile")
    Call<Object> taskUpFile(@Header("Authorization") String authorization, @Query("taskId") long taskId,
                            @Query("type") int type, @Query("identification") String identification,
                            @Part() List<MultipartBody.Part> files,
                            @Query("fromType") int fromType, @Query("fileName") String fileName,
                            @Query("templateFileType") String templateFileType);

    /**
     * 上传附件
     *
     * @param authorization
     * @param taskId
     * @param type
     * @param identification
     * @param files
     * @param fromType
     * @param fileName
     * @param templateFileType
     * @param fileSort
     * @return
     */
    @Multipart
    @POST("qnchat/task/upFile")
    Call<Object> taskUpFile(@Header("Authorization") String authorization, @Query("taskId") long taskId,
                            @Query("type") int type, @Query("identification") String identification,
                            @Part() List<MultipartBody.Part> files,
                            @Query("fromType") int fromType, @Query("fileName") String fileName,
                            @Query("templateFileType") String templateFileType, @Query("fileSort") int fileSort, @Query("majorName") String majorName);

    /**
     * 上传本地附件
     *
     * @param authorization
     * @param type
     * @param files
     * @param fromType
     * @return
     */
    @Multipart
    @POST("qnchat/task/upFile")
    Call<Object> taskUpFile(@Header("Authorization") String authorization, @Query("type") int type,
                            @Part() List<MultipartBody.Part> files, @Query("fromType") int fromType);

    /**
     * 文件转码
     */
    @FormUrlEncoded
    @POST("qnchat/task/backTranscoding")
    Call<Object> backTranscoding(@Header("Authorization") String authorization, @Field("taskFileIds") String taskFileIds);

    /**
     * 查询我的云盘所有附件列表 168
     *
     * @param authorization
     * @param type
     * @param currPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getCloudFileList")
    Call<Object> getCloudFileList(@Header("Authorization") String authorization, @Field("type") int type, @Field("currPage") long currPage,
                                  @Field("pageSize") int pageSize);

    /**
     * 删除文件接口（批量删除）169
     *
     * @param authorization
     * @param fileIdStr
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/deleteFile")
    Call<Object> deleteFile(@Header("Authorization") String authorization, @Field("fileIdStr") long fileIdStr);

    /**
     * 查询任务详情 170
     *
     * @param authorization
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getTaskInfo")
    Call<Object> getTaskInfo(@Header("Authorization") String authorization, @Field("taskId") long taskId);

    /**
     * 查询任务团队成员列表 171
     *
     * @param authorization
     * @param taskId
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getTeamList")
    Call<Object> getTeamList(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                             @Field("teamId") long teamId);

    /**
     * 添加组成员接口 172
     *
     * @param authorization
     * @param taskId
     * @param teamNum
     * @param datas
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/insertTeamMember")
    Call<Object> insertTeamMember(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                  @Field("teamNum") long teamNum, @Field("datas") String datas);

    /**
     * 查询时间轴痕迹列表 173
     *
     * @param authorization
     * @param type
     * @param taskId
     * @param currPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getTraceList")
    Call<Object> getTraceList(@Header("Authorization") String authorization, @Field("type") String type,
                              @Field("taskId") long taskId, @Field("currPage") long currPage, @Field("pageSize") int pageSize);

    /**
     * 查询任务描述修改记录列表接口 176
     *
     * @param authorization
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getDescriptionList")
    Call<Object> getDescriptionList(@Header("Authorization") String authorization, @Field("taskId") long taskId);

    /**
     * 下载附件 177
     *
     * @param authorization
     * @param identification
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/downFile")
    Call<ResponseBody> taskDownFile(@Header("Authorization") String authorization, @Field("identification") String identification);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param taskNum
     * @param departmentName
     * @param majorName
     * @param taskName
     * @param start
     * @param end
     * @param description
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTask(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                            @Field("taskNum") String taskNum, @Field("departmentName") String departmentName,
                            @Field("majorName") String majorName, @Field("taskName") String taskName,
                            @Field("start") String start, @Field("end") String end,
                            @Field("description") String description, @Field("status") String status);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTask(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                            @Field("status") String status);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param description
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskDes(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                               @Field("description") String description);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param departmentName
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskDepart(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                  @Field("departmentName") String departmentName);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param majorName
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskMajor(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                 @Field("majorName") String majorName);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param start
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskStart(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                 @Field("start") String start);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param taskNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskNum(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                               @Field("taskNum") String taskNum);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param end
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTaskEnd(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                               @Field("end") String end);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param taskNum
     * @param departmentName
     * @param majorName
     * @param taskName
     * @param start
     * @param end
     * @param description
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTask(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                            @Field("taskNum") String taskNum, @Field("departmentName") String departmentName,
                            @Field("majorName") String majorName, @Field("taskName") String taskName,
                            @Field("start") String start, @Field("end") String end,
                            @Field("description") String description);

    /**
     * 编辑任务 178
     *
     * @param authorization
     * @param taskId
     * @param taskNum
     * @param departmentName
     * @param majorName
     * @param taskName
     * @param start
     * @param end
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/updateTask")
    Call<Object> updateTask(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                            @Field("taskNum") String taskNum, @Field("departmentName") String departmentName,
                            @Field("majorName") String majorName, @Field("taskName") String taskName,
                            @Field("start") String start, @Field("end") String end);

    /**
     * 查询任务附件列表 type:文件类型（1任务文件，2任务模板文件，3协同编辑文件，4云盘附件，5评分模板文件）179
     *
     * @param authorization
     * @param taskId
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/getFileList")
    Call<Object> getFileList(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                             @Field("type") int type);

    @FormUrlEncoded
    @POST("qnchat/task/getFileList")
    Call<Object> getFileList(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                             @Field("type") int type, @Field("fileSort") int fileSort, @Field("majorName") String majorName);

    /**
     * 删除成员或成员退出 180
     *
     * @param authorization
     * @param taskId
     * @param id
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/outTeam")
    Call<Object> outTeam(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                         @Field("id") long id);

    /**
     * 创建任务单接口 181
     *
     * @param authorization
     * @param taskSheetResult
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/createTaskSheet")
    Call<Object> createTaskSheet(@Header("Authorization") String authorization, @Field("taskSheetResult") String taskSheetResult);

    /**
     * 查询任务单专业列表 182
     *
     * @param authorization
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getMajorList")
    Call<Object> getSheetMajorList(@Header("Authorization") String authorization, @Field("teamId") String teamId);

    /**
     * 添加时间轴痕迹信息接口 183
     *
     * @param authorization
     * @param taskId
     * @param action
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/addTrace")
    Call<Object> addTrace(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                          @Field("action") String action);

    /**
     * 查询任务单列表接口 184
     *
     * @param authorization
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getTaskSheetList")
    Call<Object> getTaskSheetList(@Header("Authorization") String authorization, @Field("taskId") long taskId);

    /**
     * 查询专业人员接口 查询专业负责人 185
     *
     * @param authorization
     * @param majorName
     * @param isMajorLeader
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> isMajorLeader(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                               @Field("isMajorLeader") String isMajorLeader, @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询专业人员接口 查询设计 186
     *
     * @param authorization
     * @param majorName
     * @param isDraftsman
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> isDraftsman(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                             @Field("isDraftsman") String isDraftsman, @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询专业人员接口 查询校核 187
     *
     * @param authorization
     * @param majorName
     * @param isProofreader
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> isProofreader(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                               @Field("isProofreader") String isProofreader, @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询专业人员接口 查询审核 188
     *
     * @param authorization
     * @param majorName
     * @param isReviewer
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> isReviewer(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                            @Field("isReviewer") String isReviewer, @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询专业人员接口 查询审定 189
     *
     * @param authorization
     * @param majorName
     * @param isApprover
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> isApprover(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                            @Field("isApprover") String isApprover, @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询专业人员接口 搜索关键字（手动输入查询时传此参数） 190
     *
     * @param authorization
     * @param majorName
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> keyword(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                         @Field("keyword") String keyword, @Field("teamId") String teamId);


    /**
     * 查询专业人员接口 其他 191
     *
     * @param authorization
     * @param majorName
     * @param keyword
     * @param teamId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/selectStaff")
    Call<Object> selectOther(@Header("Authorization") String authorization, @Field("majorName") String majorName,
                             @Field("keyword") String keyword, @Field("teamId") String teamId);

    /**
     * 查询任务单信息 192
     *
     * @param authorization
     * @param taskId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getTaskSheetInfo")
    Call<Object> getTaskSheetInfo(@Header("Authorization") String authorization, @Field("taskId") long taskId);

    /**
     * 修改任务单 193
     *
     * @param authorization
     * @param taskSheetResult
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/updateTaskSheet")
    Call<Object> updateTaskSheet(@Header("Authorization") String authorization, @Field("taskSheetResult") String taskSheetResult);

    /**
     * 检测当天是否第一次进入首页接口 194
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/appVersion/tipsFirstLogin")
    Call<Object> tipsFirstLogin(@Header("Authorization") String authorization);


    /**
     * 获取最新的apk版本信息接口 195
     *
     * @param authorization
     * @param apkVersion    apk版本号
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/appVersion/getNewVersion")
    Call<Object> getNewVersion(@Header("Authorization") String authorization, @Field("apkVersion") String apkVersion);

    /***
     * 查询综合积分数据接口 199
     * @param authorization
     * @return
     */
    @POST("qnnet/integral/queryIntegratedIntegral")
    Call<Object> queryIntegratedIntegral(@Header("Authorization") String authorization);

    /**
     * 查询咨询积分数据接口 200
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/integral/queryConsultationIntegral")
    Call<Object> queryConsultationIntegral(@Header("Authorization") String authorization);

    /**
     * 查询项目积分数据接口 201
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/integral/queryProjectIntegral")
    Call<Object> queryProjectIntegral(@Header("Authorization") String authorization);

    /**
     * 查询文库积分数据接口 202
     *
     * @param authorization
     * @return
     */
    @POST("qnnet/integral/queryLibrayIntegral")
    Call<Object> queryLibrayIntegral(@Header("Authorization") String authorization);

    /**
     * 生成团队二维码接口 203
     *
     * @param authorization
     * @param teamNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/createTeamQRCode")
    Call<Object> createTeamQrCode(@Header("Authorization") String authorization, @Field("teamNum") long teamNum);

    /**
     * 上传签章接口
     *
     * @param authorization
     * @param sealName      q签章名称
     * @param sealFile      签章文件
     * @return
     */
    @Multipart
    @POST("qnchat/signature/upSeal")
    Call<Object> upSeal(@Header("Authorization") String authorization, @Query("sealName") String sealName, @Part MultipartBody.Part sealFile);

    /**
     * 获取用户所有印章接口 205
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/signature/selectAllSeal")
    Call<Object> selectAllSeal(@Header("Authorization") String authorization);

    /**
     * 修改个人签章接口 206
     *
     * @param authorization
     * @param sealId
     * @param sealFile
     * @return
     */
    @Multipart
    @POST("qnchat/signature/updateSeal")
    Call<Object> updateSeal(@Header("Authorization") String authorization, @Query("sealId") long sealId, @Part MultipartBody.Part sealFile);

    /**
     * 生成签章接口 207
     *
     * @param authorization
     * @param sealStyle
     * @param sealName
     * @param sealPrincipal
     * @param sealHead
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/createSeal")
    Call<Object> createSeal(@Header("Authorization") String authorization, @Field("sealStyle") String sealStyle,
                            @Field("sealName") String sealName, @Field("sealPrincipal") String sealPrincipal, @Field("sealHead") String sealHead);

    /**
     * 根据id查询签章接口 208
     *
     * @param authorization
     * @param sealId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/selSealById")
    Call<Object> selSealById(@Header("Authorization") String authorization, @Field("sealId") long sealId);

    /**
     * 根据id删除签章接口 209
     *
     * @param authorization
     * @param sealId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/delSealById")
    Call<Object> delSealById(@Header("Authorization") String authorization, @Field("sealId") long sealId);

    /**
     * 查询签章/归档附件接口 210
     *
     * @param authorization
     * @param taskId
     * @param sealType      附件分类（1：预审附件，2：签章附件，3：归档附件）
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/selectFileByType")
    Call<Object> selectFileByType(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                  @Field("sealType") int sealType);

    @FormUrlEncoded
    @POST("qnchat/task/selectFileByType")
    Call<Object> selectFileByType(@Header("Authorization") String authorization, @Field("taskId") long taskId,
                                  @Field("sealType") int sealType, @Field("majorName") String majorName);

    /**
     * 批量下载附件接口
     *
     * @param authorization
     * @param taskFileIds
     * @return
     */
    @GET("qnchat/task/downloadTaskFileZip")
    Call<ResponseBody> downloadTaskFileZip(@Header("Authorization") String authorization, @Query("taskFileIds") String taskFileIds);

    /**
     * 申请签章/归档（批量）接口 211
     *
     * @param authorization
     * @param taskFileIds
     * @param fileSort
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/task/applySealFile")
    Call<Object> applySealFile(@Header("Authorization") String authorization, @Field("taskFileIds") String taskFileIds,
                               @Field("fileSort") int fileSort);

    /**
     * 签章接口 212
     *
     * @param authorization
     * @param sealFilePath  签章文件路径
     * @param sealName      签章名称
     * @param sealType      签章类型
     * @param keyword       关键字
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/electronicSignature")
    Call<Object> electronicSignature(@Header("Authorization") String authorization, @Field("sealFilePath") String sealFilePath,
                                     @Field("sealName") String sealName, @Field("sealType") String sealType, @Field("keyword") String keyword);

    /**
     * 取消盖章接口 213
     *
     * @param authorization
     * @param filePath
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/cancelSignature")
    Call<Object> cancelSignature(@Header("Authorization") String authorization, @Field("filePath") String filePath);

    /**
     * 确定签章后将数据存入数据库接口 214
     *
     * @param authorization
     * @param taskFileId
     * @param filePath
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/signature/confirmSignature")
    Call<Object> confirmSignature(@Header("Authorization") String authorization, @Field("taskFileId") long taskFileId,
                                  @Field("filePath") String filePath);

    /**
     * 自定义搜索请印单列表接口 215
     *
     * @param authorization
     * @param keyword             工程编号/工程名称/项目负责人（非必填）
     * @param start               开始时间，非必填，默认“全部”
     * @param end                 结束时间，非必填，默认“全部”
     * @param applicantDepartment 结束时间，非必填，默认“全部”
     * @param status              状态，非必填，默认“全部”
     * @param currPage            分页参数（起始页，默认1）
     * @param pageSize            分页参数（起始页，默认1）
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/searchSignList")
    Call<Object> searchSignList(@Header("Authorization") String authorization, @Field("keyword") String keyword,
                                @Field("start") String start, @Field("end") String end,
                                @Field("applicantDepartment") String applicantDepartment, @Field("status") String status,
                                @Field("currPage") int currPage, @Field("pageSize") int pageSize);

    /**
     * 签章上传本地附件
     *
     * @param authorization
     * @param files
     * @return
     */
    @Multipart
    @POST("qnchat/sign/upFile")
    Call<Object> upFile(@Header("Authorization") String authorization, @Part() List<MultipartBody.Part> files);

    /**
     * 删除附件接口 216
     *
     * @param authorization
     * @param signFileId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/deleteFile")
    Call<Object> signDeleteFile(@Header("Authorization") String authorization, @Field("signFileId") long signFileId);

    /**
     * 查询部门列表接口 217
     *
     * @param authorization
     * @return
     */
    @POST("qnchat/sign/getDepartmentList")
    Call<Object> getSignDepartmentList(@Header("Authorization") String authorization);

    /**
     * 查询请印单信息接口 218
     *
     * @param authorization
     * @param signId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/getSignInfo")
    Call<Object> getSignInfo(@Header("Authorization") String authorization, @Field("signId") long signId);

    /**
     * 查询附件列表接口 219
     *
     * @param authorization
     * @param signId        请印单ID
     * @param fileType      文件类型（1表示上传文件，2表示请印单生成文件）
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/getSignFileList")
    Call<Object> getSignFileList(@Header("Authorization") String authorization, @Field("signId") long signId, @Field("fileType") int fileType);

    /**
     * 查询请印单痕迹信息列表接口 220
     *
     * @param authorization
     * @param signId
     * @param currPage
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/getSignTraceList")
    Call<Object> getSignFileList(@Header("Authorization") String authorization, @Field("signId") long signId,
                                 @Field("currPage") int currPage, @Field("pageSize") int pageSize);

    /**
     * 签字接口 221
     *
     * @param authorization
     * @param signId
     * @param electronicSignaturePath
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/addSign")
    Call<Object> addSign(@Header("Authorization") String authorization, @Field("signId") long signId,
                         @Field("electronicSignaturePath") String electronicSignaturePath);

    /**
     * 创建请印单接口 222
     *
     * @param authorization
     * @param signInfo
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/createSign")
    Call<Object> createSign(@Header("Authorization") String authorization, @Field("signInfo") String signInfo);

    /**
     * 查询最新生成的请印单pdf文件 223
     *
     * @param authorization
     * @param signId
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/getLatestSignFile")
    Call<Object> getLatestSignFile(@Header("Authorization") String authorization, @Field("signId") long signId);

    /**
     * 留言接口 224
     *
     * @param authorization
     * @param signId
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/addSignTrace")
    Call<Object> addSignTrace(@Header("Authorization") String authorization, @Field("signId") long signId, @Field("content") String content);

    /**
     * 确认签字接口 225
     *
     * @param authorization
     * @param signId
     * @param electronicSignaturePath
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/confirmSign")
    Call<Object> confirmSign(@Header("Authorization") String authorization, @Field("signId") long signId, @Field("electronicSignaturePath") String electronicSignaturePath);

    /**
     * 取消签字接口 226
     *
     * @param authorization
     * @param sealPdfPath
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/sign/cancelSign")
    Call<Object> cancelSign(@Header("Authorization") String authorization, @Field("sealPdfPath") String sealPdfPath);

    /**
     * 判断是否在团队里 227
     *
     * @param authorization
     * @param teamNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/scanTeamQRCode")
    Call<Object> scanTeamQrCode(@Header("Authorization") String authorization, @Field("teamNum") String teamNum);

    /**
     * 模糊搜索通讯录好友和团队接口
     *
     * @param authorization
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("qnchat/mailList/searchFriendsandTeams")
    Call<Object> searchFriendsandTeams(@Header("Authorization") String authorization, @Field("content") String content);

    /**
     * 查询区域列表接口
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getRegionList")
    Call<Object> getRegionList(@Header("Authorization") String authorization, @Field("content") String content);

    /**
     * 查询阶段列表接口
     */
    @FormUrlEncoded
    @POST("qnchat/sheet/getDesignPhaseList")
    Call<Object> getDesignPhaseList(@Header("Authorization") String authorization, @Field("content") String content);

    /**
     * 查询盖印人信息列表
     */
    @POST("qnchat/seal/getSealUserList")
    Call<Object> getSealUserList(@Header("Authorization") String authorization);

    /**
     * 查询印章信息列表
     */
    @POST("qnchat/seal/getSealList")
    Call<Object> getSealList(@Header("Authorization") String authorization);

    /**
     * 搜索文库资料接口
     *
     * @param authorization
     * @param keyword
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/searLibrary/librarySource")
    Call<Object> librarySource(@Header("Authorization") String authorization, @Field("keyword") String keyword,
                               @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 上传文件接口
     *
     * @param authorization
     * @param files
     * @param account
     * @return
     */
    @Multipart
    @POST("qnnet/library/uploadFile")
    Call<Object> uploadFile(@Header("Authorization") String authorization, @Part MultipartBody.Part files, @Query("account") String account);

    /**
     * 发表文章/上传资料接口
     *
     * @param authorization
     * @param classify
     * @param title
     * @param brief
     * @param content
     * @param format
     * @param sizes
     * @param cover
     * @param cost
     * @param publisherType
     * @param publisherId
     * @param articleState
     * @param articleType
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/addLibrary")
    Call<Object> addLibrary(@Header("Authorization") String authorization,
                            @Field("classify") String classify,
                            @Field("title") String title,
                            @Field("brief") String brief,
                            @Field("content") String content,
                            @Field("format") String format,
                            @Field("sizes") String sizes,
                            @Field("cover") String cover,
                            @Field("cost") String cost,
                            @Field("publisherType") int publisherType,
                            @Field("publisherId") long publisherId,
                            @Field("articleState") int articleState,
                            @Field("articleType") int articleType
    );

    /**
     * 文库管理
     *
     * @param authorization
     * @param classify
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/myLibrary")
    Call<Object> myLibrary(@Header("Authorization") String authorization, @Field("classify") String classify, @Field("startTime") String startTime,
                           @Field("endTime") String endTime, @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 文库文章删除
     *
     * @param authorization
     * @param libraryNums
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/delLibrary")
    Call<Object> delLibrary(@Header("Authorization") String authorization, @Field("libraryNums") String libraryNums);

    /**
     * 文库资料收藏
     *
     * @param authorization
     * @param classify
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/selCollection")
    Call<Object> selCollection(@Header("Authorization") String authorization, @Field("classify") String classify, @Field("startTime") String startTime,
                               @Field("endTime") String endTime, @Field("pageIndex") int pageIndex, @Field("pageSize") long pageSize);

    /**
     * 文库文章取消收藏
     *
     * @param authorization
     * @param collectionIds
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/cancelCollection")
    Call<Object> cancelCollection(@Header("Authorization") String authorization, @Field("collectionIds") String collectionIds);

    /**
     * 文库点赞
     *
     * @param authorization
     * @param account
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("library/praise")
    Call<Object> praise(@Header("Authorization") String authorization, @Field("account") String account, @Field("libraryNum") String libraryNum);

    /**
     * 是否点赞
     *
     * @param authorization
     * @param account
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/isPraise")
    Call<Object> isPraise(@Header("Authorization") String authorization, @Field("account") String account, @Field("libraryNum") String libraryNum);

    /**
     * 是否收藏
     *
     * @param authorization
     * @param account
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/isCollection")
    Call<Object> isCollection(@Header("Authorization") String authorization, @Field("account") String account, @Field("libraryNum") String libraryNum
            , @Field("collectionType") int collectionType, @Field("collectionId") long collectionId);

    /**
     * 文库收藏
     *
     * @param authorization
     * @param account
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("library/addCollection")
    Call<Object> addCollection(@Header("Authorization") String authorization, @Field("account") String account, @Field("libraryNum") String libraryNum
            , @Field("classify") String classify, @Field("collectionType") int collectionType, @Field("collectionId") long collectionId);

    /**
     * 文库评论
     *
     * @param authorization
     * @return
     */
    @FormUrlEncoded
    @POST("library/addArticEval")
    Call<Object> addArticEval(@Header("Authorization") String authorization, @Field("libraryNum") String lid
            , @Field("content") String content, @Field("uType") int uType, @Field("eid") long eid);

    /**
     * 文库举报
     *
     * @param authorization
     * @param account
     * @param libraryNum
     * @return
     */
    @FormUrlEncoded
    @POST("library/addReport")
    Call<Object> addReport(@Header("Authorization") String authorization, @Field("account") String account, @Field("libraryNum") String libraryNum
            , @Field("reportReason") String reportReason, @Field("reasonInfo") String reasonInfo, @Field("collectionType") int collectionType, @Field("collectionId") long collectionId);


    /**
     * 我的频道 228
     *
     * @param authorization
     * @return
     */
    @GET("library/listClassifyCustom")
    Call<Object> listClassifyCustom(@Header("Authorization") String authorization);

    /**
     * 修改频道 229
     *
     * @param authorization
     * @return
     */
    @FormUrlEncoded
    @POST("library/updateClassifyCustom")
    Call<Object> updateClassifyCustom(@Header("Authorization") String authorization, @Field("classifyNumOrder") String classifyNumOrder);

    /**
     * 文库中查询所有文章 230
     *
     * @param authorization
     * @return
     */
    @GET("library/selectAllLibraryRevision")
    Call<Object> selectAllLibraryRevision(@Header("Authorization") String authorization, @Query("classify") String classify
            , @Query("queryType") String queryType
            , @Query("articleType") String articleType, @Query("pageIndex") int pageIndex, @Query("pageSize") long pageSize
            , @Query("format") String format, @Query("sort") String sort
            , @Query("startTime") String startTime, @Query("endTime") String endTime
            , @Query("isFree") String isFree);

    /**
     * 发表文章/上传资料接口 231
     *
     * @param authorization
     * @param classify
     * @param title
     * @param brief
     * @param content
     * @param format
     * @param sizes
     * @param cover
     * @param cost
     * @param publisherType
     * @param publisherId
     * @param articleState
     * @param articleType
     * @return
     */
    @FormUrlEncoded
    @POST("library/addLibrary")
    Call<Object> addLibraryNew(@Header("Authorization") String authorization, @Field("classify") String classify,
                               @Field("title") String title, @Field("brief") String brief, @Field("content") String content,
                               @Field("format") String format, @Field("sizes") String sizes, @Field("cover") String cover,
                               @Field("cost") String cost, @Field("publisherType") int publisherType, @Field("publisherId") long publisherId,
                               @Field("articleState") int articleState, @Field("articleType") int articleType
    );

    /**
     * 文库管理 232
     *
     * @param authorization
     * @param classify
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("library/myLibrary")
    Call<Object> myLibraryNew(@Header("Authorization") String authorization, @Query("classify") String classify, @Query("startTime") String startTime,
                              @Query("endTime") String endTime, @Query("pageIndex") int pageIndex, @Query("pageSize") long pageSize);

    /**
     * 文库资料收藏 233
     *
     * @param authorization
     * @param classify
     * @param startTime
     * @param endTime
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("library/selCollection")
    Call<Object> selCollectionNew(@Header("Authorization") String authorization, @Query("classify") String classify
            , @Query("queryType") String queryType
            , @Query("articleType") String articleType, @Query("pageIndex") int pageIndex, @Query("pageSize") long pageSize
            , @Query("format") String format, @Query("sort") String sort
            , @Query("startTime") String startTime, @Query("endTime") String endTime
            , @Query("isFree") String isFree);

    /**
     * 根据文章/资料编号查看详细信息接口 234
     *
     * @param authorization
     * @param libraryNum
     * @return
     */
    @GET("library/myLibraryInfo")
    Call<Object> myLibraryInfoNew(@Header("Authorization") String authorization, @Query("libraryNum") long libraryNum);

    /**
     * 文库文章取消收藏 235
     *
     * @param authorization
     * @param collectionIds
     * @return
     */
    @FormUrlEncoded
    @POST("library/cancelCollection")
    Call<Object> cancelCollectionNew(@Header("Authorization") String authorization, @Field("collectionIds") String collectionIds);

    /**
     * 支付宝订单获取 236
     *
     * @param authorization
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/alipay/appPay")
    Call<Object> appPay(@Header("Authorization") String authorization, @Field("out_trade_no") String out_trade_no
            , @Field("total_amount") String total_amount, @Field("subject") String subject);

    /**
     * 关联支付宝订单 237
     *
     * @param authorization
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/addOrder")
    Call<Object> addOrder(@Header("Authorization") String authorization, @Field("orderNo") String orderNo
            , @Field("orderName") String orderName, @Field("orderBody") String orderBody
            , @Field("orderMoney") double orderMoney, @Field("projectNum") long projectNum);

    /**
     * 文库管理 238
     *
     * @param authorization
     * @return
     */
    @GET("library/selectMyLibrary")
    Call<Object> selectMyLibrary(@Header("Authorization") String authorization, @Query("classify") String classify
            , @Query("queryType") String queryType
            , @Query("articleType") String articleType, @Query("pageIndex") int pageIndex, @Query("pageSize") long pageSize
            , @Query("format") String format, @Query("sort") String sort
            , @Query("startTime") String startTime, @Query("endTime") String endTime
            , @Query("isFree") String isFree, @Query("articleState") String articleState);

    /**
     * 后台转码接口 239
     *
     * @param authorization
     * @return
     */
    @GET("library/backTranscodingup")
    Call<Object> backTranscodingup(@Header("Authorization") String authorization, @Query("libraryNum") String libraryNum, @Query("articleState") int articleState);

    /**
     * 文库文章删除 240
     *
     * @param authorization
     * @param libraryNums
     * @return
     */
    @FormUrlEncoded
    @POST("library/delLibrary")
    Call<Object> delLibraryNew(@Header("Authorization") String authorization, @Field("libraryNums") String libraryNums);

    /**
     * 修改文章, 资料  241
     *
     * @param authorization
     * @param classify
     * @param title
     * @param brief
     * @param cost
     * @param articleState
     * @return
     */
    @FormUrlEncoded
    @POST("library/modLibrary")
    Call<Object> modLibrary(@Header("Authorization") String authorization, @Field("classify") String classify,
                            @Field("title") String title, @Field("brief") String brief,
                            @Field("cost") String cost, @Field("articleState") int articleState,
                            @Field("libraryNum") long libraryNum
    );

    /**
     * 修改文章, 资料
     *
     * @param authorization
     * @param classify
     * @param title
     * @param brief
     * @param content
     * @param format
     * @param sizes
     * @param cover
     * @param cost
     * @param publisherType
     * @param publisherId
     * @param articleState
     * @param articleType
     * @return
     */
    @FormUrlEncoded
    @POST("qnnet/library/modLibrary")
    Call<Object> modLibraryOld(@Header("Authorization") String authorization, @Field("classify") String classify,
                               @Field("title") String title, @Field("brief") String brief, @Field("content") String content,
                               @Field("format") String format, @Field("sizes") String sizes, @Field("cover") String cover,
                               @Field("cost") String cost, @Field("publisherType") int publisherType, @Field("publisherId") long publisherId,
                               @Field("articleState") int articleState, @Field("articleType") int articleType, @Field("libraryNum") long libraryNum
    );

    /**
     * GET /library/countLibrary
     * 查询我的文库数量和我收藏资料的数量 242
     *
     * @param authorization
     * @return
     */
    @GET("library/countLibrary")
    Call<Object> countLibrary(@Header("Authorization") String authorization);

    /**
     * /library/addReply
     * 添加回复评论 243
     *
     * @param authorization
     * @return
     */
    @FormUrlEncoded
    @POST("library/addReply")
    Call<Object> addReply(@Header("Authorization") String authorization, @Field("evaluationId") String evaluationId
            , @Field("recipientType") String recipientType, @Field("recipientId") String recipientId, @Field("replyContent") String replyContent
    );

    /**
     * /library/searchLibrary
     * 自定义搜索文库列表  245
     *
     * @param authorization
     * @return
     */
    @GET("library/searchLibrary")
    Call<Object> searchLibrary(@Header("Authorization") String authorization
            , @Query("keyword") String keyword, @Query("pageIndex") int pageIndex, @Query("pageSize") int pageSize
    );

    /**
     * /library/listAllClassify
     * 查询所有分类 246
     *
     * @param authorization
     * @return
     */
    @GET("library/listAllClassify")
    Call<Object> listAllClassify(@Header("Authorization") String authorization);

}
