package org.jeecg.config.shiro;

import com.google.common.collect.Sets;

import java.util.Set;

public enum WhiteList {
    ;
    static final Set<String> API_SET = Sets.newHashSet("/sys/cas/client/validateLogin"//cas验证登录
            ,"/sys/randomImage/**"//登录验证码接口排除
            ,"/sys/checkCaptcha"//登录验证码接口排除
            ,"/sys/smsCheckCaptcha"//短信次数发送太多验证码排除
            ,"/sys/login"//登录接口排除
            ,"/sys/mLogin"//登录接口排除
            ,"/sys/logout"//登出接口排除
            ,"/sys/thirdLogin/**"//第三方登录
            ,"/sys/getEncryptedString"//获取加密串
            ,"/sys/sms"//短信验证码
            ,"/sys/phoneLogin"//手机登录
            ,"/sys/user/checkOnlyUser"//校验用户是否存在
            ,"/sys/user/register"//用户注册
            ,"/sys/user/phoneVerification"//用户忘记密码验证手机号
            ,"/sys/user/passwordChange"//用户更改密码
            ,"/auth/2step-code"//登录验证码
            ,"/sys/common/static/**"//图片预览 &下载文件不限制token
            ,"/sys/common/pdf/**"//pdf预览
//                ,"/sys/common/view/**"//图片预览不限制token
//                ,"/sys/common/download/**"//文件下载不限制token
            ,"/generic/**"//pdf预览需要文件
            ,"/sys/getLoginQrcode/**"//登录二维码
            ,"/sys/getQrcodeToken/**"//监听扫码
            ,"/sys/checkAuth"//授权接口排除
    );
    static final Set<String> RES_SET =  Sets.newHashSet("/"
            ,"/doc.html"
            ,"/**/*.js"
            ,"/**/*.css"
            ,"/**/*.html"
            ,"/**/*.svg"
            ,"/**/*.pdf"
            ,"/**/*.jpg"
            ,"/**/*.png"
            ,"/**/*.gif"
            ,"/**/*.ico"
            ,"/**/*.ttf"
            ,"/**/*.woff"
            ,"/**/*.woff2"
            ,"/**/*.glb"
            ,"/**/*.wasm"
    );
    static final Set<String> SWAGGER_ETC_SET = Sets.newHashSet("/druid/**"
            ,"/swagger-ui.html"
            ,"/swagger**/**"
            ,"/webjars/**"
            ,"/v2/**"
    );

    static final Set<String> REPORT_FORM_SET = Sets.newHashSet("/jmreport/**"//积木报表排除
            ,"/**/*.js.map"
            ,"/**/*.css.map"
            ,"/drag/view"   //积木BI大屏和仪表盘排除
            ,"/drag/page/queryById"
            ,"/drag/page/addVisitsNumber"
            ,"/drag/page/queryTemplateList"
            ,"/drag/share/view/**"
            ,"/drag/onlDragDatasetHead/getAllChartData"
            ,"/drag/onlDragDatasetHead/getTotalData"
            ,"/drag/mock/json/**"
            ,"/jimubi/view"
            ,"/jimubi/share/view/**"
    );


    static final Set<String> BIT_SCREEN_SET = Sets.newHashSet("/test/bigScreen/**"
            ,"/bigscreen/template1/**"//大屏模板例子
            ,"/bigscreen/template2/**"
//                ,"/test/jeecgDemo/rabbitMqClientTest/**"//MQ测试
//                ,"/test/jeecgDemo/html"//模板页面
//                ,"/test/jeecgDemo/redis/**"//redis测试
    );
    //websocket排除
    static final Set<String> WS_SET = Sets.newHashSet("/websocket/**"//系统通知和公告
            ,"/newsWebsocket/**"//CMS模块
            ,"/vxeSocket/**"//JVxeTable无痕刷新示例
    );

}
