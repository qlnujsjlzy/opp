/**
 * 自定义js --依赖于 jquery.min.js、layer.js、jquery.cookie.js
 * Created by guodandan on 2016/3/24.
 */
var guodandan = {};
//datatable:显示中文标题
guodandan.oLanguage =
{
    "sProcessing": "正在加载中......",
    "sLengthMenu": "每页显示 _MENU_ 条记录",
    "sZeroRecords": "正在加载中......",
    "sEmptyTable": "表中无数据存在！",
    "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
    "sInfoEmpty": "显示0到0条记录",
    "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
    "sSearch": "搜索",
    "oPaginate": {
        "sFirst": "首页",
        "sPrevious": "上一页",
        "sNext": "下一页",
        "sLast": "末页"
    }
}
/*ajax返回值统一处理方法*/
guodandan.layerMsg = function (data, msg, fn) {
    if (data.rc == 0) {
        guodandan.sucessAlert(msg, fn);
    } else {
        $("#submit_btn").removeAttr("disabled");
        guodandan.notAuth(data);
    }
};
/*未登录统一跳转方法*/
guodandan.notAuth = function (data) {
    if (data.rc == 401) {
        layer.msg('用户尚未登录或缓存失效，请您登录后重试!', {icon: 4}, function () {
            window.location.href = global_RootPath+"login.html";
        });
    }
    if (data.msg != "") {
        layer.msg(data.msg, {icon: 2, time: 2000});
    }
};
/*依赖layer.js 的alert弹框*/
//icon: 1--对号 2--叉号 3--问号 4--锁 5--伤心表情  6--开心表情 7--叹号
/*********************************************/
/*提示成功*/
guodandan.sucessAlert = function (msg, fn) {
    guodandan.alert(msg, {icon: 1, time: 1000}, fn);
};
/*提示错误*/
guodandan.failAlert = function (msg, fn) {
    guodandan.alert(msg, {icon: 7, time: 1000}, fn);
};
/*提示信息--可修改弹框样式*/
guodandan.alert = function (msg, icon, fn) {
    guodandan.alert(msg, {icon: icon, time: 1000}, fn);
};
/*提示信息--可修改弹框和持续时间的样式*/
guodandan.alert = function (msg, icon, time, fn) {
    guodandan.alert(msg, {icon: icon, time: time}, fn);
}
guodandan.alert = function (msg, style, fn) {
    layer.msg(msg, style, fn);
}


///////////////////////////////////////////////////////////////////
// 表单校验方法：
///////////////////////////////////////////////////////////////////
//校验身份证号
guodandan.isCardNo = function (card) {
    //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
    var reg = /(^\d{15}$)|(^\d{17}(\d|X)$)/;
    if (reg.test(card) === false) {
        return false;
    }
    return true;
}


/*引用jquery.cookie.js
 guodandan.cookie=function(key, value, options){
 $.cookie(key, value,options);
 }*/
/*设置cookie--默认根目录下缓存一小时*/
guodandan.cookieDate = function () {
    var dt = new Date();
    //设置cookies失效时间一小时
    dt.setTime(dt.getTime() + (60 * 60 * 1000));
    return dt;
};
/*获取用户cookie，为空跳转*/
guodandan.userCookie = function (key) {
    var _Cookie = $.cookie(key);
    var currentCookie;
    if (_Cookie == "null" || _Cookie == undefined) {
        layer.msg('用户尚未授权或缓存失效，请您登录后重试!', {icon: 4}, function () {
            window.location.href = global_RootPath+"login.html";
        });
        return;
    } else {
        currentCookie = eval("(" + _Cookie + ")");
    }
    return currentCookie;
};
var getRootPath = function () {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);

    return localhostPaht + projectName;
}
var getlogout = function () {
    var curWwwPath = window.document.location.href;
    var logout = curWwwPath.substring(curWwwPath.indexOf('?') + 1, curWwwPath.length);
    return logout;
}
var getUrlAuth=function(){
    var curWwwPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    return curWwwPath.substring(pos+1).split(".")[0];
}
var getlogin = function () {
    var login = false;
    var curWwwPath = window.document.location.href;
    if (curWwwPath.indexOf("login") != -1) {
        login = true;
    }
    return login;
}
//时间倒计时
var maxValidTime = 0;

var initTimer = function (leftTime) {
    maxValidTime = leftTime;
    if (maxValidTime >= 0) {
        window.timer = setInterval('countDown()', 1000);
    }
}

var countDown = function () {
    if (maxValidTime >= 0) {
        var hours = Math.floor(maxValidTime / 3600);
        var minutes = Math.floor((maxValidTime - hours * 3600) / 60);
        var seconds = Math.floor(maxValidTime % 60);
        var show_hm=hours*60+minutes;
        if (seconds < 10) {
            $('#time').html(show_hm + '分0' + seconds + "秒");
        } else {
            $('#time').html(show_hm + '分' + seconds + "秒");
        }
        --maxValidTime;
    } else {
        clearInterval(window.timer);
    }
}
//获取倒计时
var getInitTime = function (examid) {
    var init_timer = 0;
    var url_time = global_RootPath+"/records/initTimer/"+examid;
    $.ajax({
        type: "GET",
        url: url_time,
        async: false,
        success: function (data) {
            init_timer = data;
        }
    })
    return init_timer;
}
//******************************页面公用**********************************//
$(function () {
    if (!getlogin()) {//过滤登录页面
        var cookieUse = guodandan.userCookie('sessionUser');
        console.info(cookieUse)
        var urlArr=["setting","index_admin","single_choice","multiple_choice","judge_choice","make_test","examinee_batch","organization_exam","score_exam","score_examinee","analytics_exam","analytics_examinee"]
        if(cookieUse.type=="examinee"){
            if($.inArray(getUrlAuth(), urlArr)!=-1){
                layer.msg('用户没有该权限!', {icon: 4,time:50}, function () {
                    window.location.href =global_RootPath+ "index_examinee.html";
                });
            }
        }
        $("#currentuser").text(cookieUse.userName);
    }

    global_RootPath = getRootPath();
    $("#index_admin_url").attr("href", "index_admin.html");
    if ($('.form_date').length > 0) {
        $('.form_date').datetimepicker({
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
            forceParse: 0
        });
    }
});