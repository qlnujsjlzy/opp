/**
 * Created by 朝阳 on 2016/4/5.
 */
$(function(){
    $.ajax({
        type: "GET",
        url: global_RootPath+"/examinee/get",
        dataType: "json",
        success: function(data){
            if(data.rc==0){
                var $data= data.data;
                if($data!=null&&$data!=""){
                    $("#userid").val($data.userid);
                    $("#username").val($data.username);
                    $("#ticketnumber").val($data.ticketnumber).attr("disabled",true);
                    $("#idcard").val($data.idcard).attr("disabled",true);
                    $("input[name='sex']").eq($data.sex).attr("checked",'checked');
                    $("#phone").val($data.phone);
                    $("#email").val($data.email);
                    $("#address").val($data.address);
                }
            }else{
                guodandan.notAuth(data);
            }
        }
    });

    $("#password_n2").on("change",function(){
        check_password_n();
    });
    //校验新密码
    var check_password_n=function(){
        var password_n1 = $.trim($("#password_n1").val());
        if(password_n1==""){
            guodandan.failAlert('新密码不能为空', function () {
                $("#password_n1").focus();
            });
            return;
        }
        var password_n2 = $.trim($("#password_n2").val());
        if(password_n1!=password_n2){
            guodandan.failAlert('两次输入的密码不一致，请重新输入', function () {
                $("#password_n2").focus();
            });
            return;
        }
    }
    //保存按钮
    $("#btn_save").on("click", function () {
        //校验是否为空
        var userName_j = $.trim($("#username").val());
        var password_j = $.trim($("#password").val());
        var password_n1= $.trim($("#password_n1").val());
        if (userName_j == "") {
            guodandan.failAlert('考生姓名不能为空', function () {
                $("#username").focus();
            });
            return;
        }
        if (password_j == "") {
            guodandan.failAlert('原密码不能为空', function () {
                $("#password").focus();
            });
            return;
        }
        if(password_n1!=""){//新密码为空--不校验
            check_password_n();
        }

        var jsonData = {
            userid: $("#userid").val(),
            username: userName_j,
            password: password_j,
            newpassword:password_n1,
            phone: $("#phone").val(),
            email: $("#email").val(),
            address: $("#address").val(),
            sex: $('input[name="sex"]:checked').val()
        };
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examinee/update/examinee",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    clearPassword();
                    $("#btn_save").attr("disabled", true);
                });
            }
        });
    });
    //取消按钮-点击事件
    $("#btn_cancel").on("click", function () {
        clearPassword();
        $("#btn_save").removeAttr("disabled");
    });
    var clearPassword=function(){
        $("#password").val("");
        $("#password_n1").val("");
        $("#password_n2").val("");
    }
});