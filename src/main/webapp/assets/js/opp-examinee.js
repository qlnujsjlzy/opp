/**
 * Created by guodandan on 2016/3/25.
 */
var examinee = {};
//保存修改
$(function () {
    //测试数据
    //  $("#idcard").val("370682199007860987");

    $("#ticketnumber").val(new Date().getTime());//使用时间毫秒数-作为准考证号
    $("#idcard").on("change", function (event) {   //身份证号的change事件，
        var $card = $(this).val();
        if (guodandan.isCardNo($card)) { //校验身份证号
            var $password = $card.substring($card.length - 6, $card.length); //获取身份证号后六位作为密码
            $("#password").val($password);
        } else {
            guodandan.failAlert("请输入正确格式的身份证号", function () {
                $("#idcard").val("").focus();
                return;
            });
        }
    });
    //保存按钮--点击事件
    $("#btn_save").on("click", function () {
        //校验是否为空
        var userName_j = $.trim($("#username").val());
        var ticketNumber_j = $.trim($("#ticketnumber").val());
        var idCard_j = $.trim($("#idcard").val());
        var password_j = $.trim($("#password").val());
        if (userName_j == "") {
            guodandan.failAlert('考生姓名不能为空', function () {
                $("#username").focus();
            });
            return;
        }
        if (idCard_j == "") {
            guodandan.failAlert('身份证号不能为空', function () {
                $("#idcard").focus();
            });
            return;
        }
        if (password_j == "") {
            guodandan.failAlert('密码不能为空', function () {
                $("#password").focus();
            });
            return;
        }

        var jsonData = {
            userid: "",
            username: userName_j,
            ticketnumber: ticketNumber_j,
            idcard: idCard_j,
            password: password_j,
            actstatus: $('input[name="actstatus"]:checked').val(),
            phone: $("#phone").val(),
            email: $("#email").val(),
            address: $("#address").val(),
            sex: $('input[name="sex"]:checked').val()
        };
        $("#btn_save").attr("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examinee/add",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    examinee._btn_cancel();
                    $("#btn_save").attr("disabled", true);
                });
            }
        });
    });
    //点击新增事件
    $("#addexamineebtn").on("click", function () {
        $("#add_examinee").show();
        $("#list_examinee").hide();
        $("#edit_examinee").hide();


    })
    //点击返回按钮--返回列表需要异步刷新列表
    $("#addexamineebtnRe").on("click", function () {
        $("#add_examinee").hide();
        $("#list_examinee").show();
        $("#edit_examinee").hide();
        examinee.clearTable();
    })

    //取消按钮-点击事件
    $("#btn_cancel").on("click", function () {
        examinee._btn_cancel();
    });
});
//取消按钮方法
examinee._btn_cancel = function () {
    $("#username").val("");
    $("#idcard").val("");
    $("#password").val("");
    $("#phone").val("");
    $("#email").val("");
    $("#address").val("");
    $("#ticketnumber").val(new Date().getTime());
    $("#btn_save").removeAttr("disabled");
}
//查询
$(function () {
    //查询列表--id=examinee_datatable
    examinee._examinee_datatable();

    //保存按钮--点击事件
    $("#btn_save_e").on("click", function () {

        //校验是否为空
        var userName_j = $.trim($("#username_e").val());
        var password_j = $.trim($("#password_e").val());
        if (userName_j == "") {
            guodandan.failAlert('考生姓名不能为空', function () {
                $("#username").focus();
            });
            return;
        }

        var jsonData = {
            userid: $.trim($("#userid_e").val()),
            username: userName_j,
            password: password_j,
            phone: $("#phone_e").val(),
            email: $("#email_e").val(),
            address: $("#address_e").val(),
            sex: $('input[name="sex_e"]:checked').val()
        };
        $("#btn_save_e").attr("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examinee/add",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    $("#password_e").val("");
                    $("#btn_save_e").attr("disabled", true);
                });
            }
        });
    });
    //取消按钮-点击事件
    $("#btn_cancel_e").on("click", function () {
        $("#password_e").val("");
        $("#btn_save_e").removeAttr("disabled");
    });
})
//查询列表使用组件--dataTable--低于1.10.1版本
examinee._examinee_datatable = function () {
    $("#examinee_datatable").dataTable({
        'bStateSave': true,
        "sAjaxSource": global_RootPath+"/examinee/getAll?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"mDataProp": "userid"},
            {"mDataProp": "username"},
            {"mDataProp": "ticketnumber"},
            {"mDataProp": "idcard"},
            {"mDataProp": "phone"},
            {"mDataProp": "actstatus"},
            {"sDefaultContent": ""}
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": {
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
        },
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            var success_Str = "<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                "<li><a href=\"javascript:examinee._btn_update_examinee('" + aData.userid + "')\" ><i class='icon-edit'></i>   编辑</a></li>" +
                "<li><a href=\"javascript:examinee._btn_update_examineeactstatus('" + aData.userid + "',0)\" ><i class='icon-lock'></i>   锁定</a></li>" +
                " </ul>" +
                " </div>";
            var delete_Str = "<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                "<li><a href=\"javascript:examinee._btn_update_examinee('" + aData.userid + "')\" ><i class='icon-edit'></i>   编辑</a></li>" +
                "<li><a href=\"javascript:examinee._btn_update_examineeactstatus('" + aData.userid + "',1)\" ><i class='icon-unlock'></i>  解锁</a></li>" +
                " </ul>" +
                " </div>";

            if (aData.actstatus == 1) {
                $('td:eq(5)', nRow).html("<span class='label label-success'>正常</span>");
                $('td:eq(6)', nRow).html(success_Str);
            }
            else if (aData.actstatus == -1) {
                $('td:eq(5)', nRow).html("<span class='label label-important'>已删除</span>");
                $('td:eq(6)', nRow).html(success_Str);
            }
            else if (aData.actstatus == 0) {
                $('td:eq(5)', nRow).html("<span class='label label-warning'>已锁定</span>");
                $('td:eq(6)', nRow).html(delete_Str);
            }
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            return nRow;
        }
    });
};
examinee.clearTable = function () {
    if ($('#examinee_datatable').hasClass('dataTable')) {
        dttable = $('#examinee_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    examinee._examinee_datatable();
}
/**
 * 删除考生信息
 * @param userid
 * @private
 */
examinee._btn_del_examinee = function (userid) {
    $.ajax({
        type: "DELETE",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/examinee/delete/" + userid,
        dataType: "json",
        success: function (data) {
            guodandan.layerMsg(data, "锁定成功", function () {
                examinee.clearTable();
            });
        }
    });
};
/**
 * 更改考生状态
 * @param userid
 * @param actstatus
 * @private
 */
examinee._btn_update_examineeactstatus = function (userid, actstatus) {
    $.ajax({
        type: "POST",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/examinee/actstatus/" + userid + "/" + actstatus,
        dataType: "json",
        success: function (data) {
            guodandan.layerMsg(data, "修改成功", function () {
                examinee.clearTable();
            });
        }
    });
};
examinee._btn_update_examinee = function (userid) {

    $.ajax({
        type: "GET",
        contentType: "applicat ion/json; charset=utf-8",
        url: global_RootPath+"/examinee/get/" + userid,
        dataType: "json",
        success: function (data) {
            if (data.rc == 0) {
                $("#edit_examinee").show();
                $("#add_examinee").hide();
                $("#list_examinee").hide();
                var $data = data.data;
                if ($data != null && $data != "") {
                    $("#userid_e").val($data.userid);
                    $("#username_e").val($data.username);
                    $("#ticketnumber_e").val($data.ticketnumber).attr("disabled", true);
                    $("#idcard_e").val($data.idcard).attr("disabled", true);
                    $("input[name='sex_e']").eq($data.sex).attr("checked", 'checked');
                    $("#phone_e").val($data.phone);
                    $("#email_e").val($data.email);
                    $("#address_e").val($data.address);
                }
            } else {
                guodandan.notAuth(data);
            }

        }
    });
};
