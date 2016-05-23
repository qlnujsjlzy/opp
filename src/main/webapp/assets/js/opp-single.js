/**
 * Created by 朝阳 on 2016/4/6.
 */
var examlib = {};
$(function () {
    $("#btn_save").on("click", function () {
        var id = $("#singleId").val();
        var title = $.trim($("#title").val());
        var options = "##A." + $.trim($("#option_A").val()) + "##B." + $.trim($("#option_B").val()) + "##C." + $.trim($("#option_C").val()) + "##D." + $.trim($("#option_D").val());
        var answer = $.trim($("#answer").val());
        var type = "single";

        if (title == "") {
            guodandan.failAlert('题目不能为空', function () {
                $("#title").focus();
            });
            return;
        }
        if ($("#option_A").val() == "" || $("#option_B").val() == "" || $("#option_C").val() == "" || $("#option_D").val() == "") {
            guodandan.failAlert('题目答案内容不能为空或是格式不正确', function () {
                $("#option_A").focus();
            });
            return;
        }
        if (answer == "" || "ABCD".indexOf(answer) == -1) {
            guodandan.failAlert('正确答案不能为空或是正确答案格式不准确', function () {
                $("#answer").val("");
                $("#answer").focus();
            });
            return;
        }

        var jsonData = {id: id, title: title, options: options, answer: answer, type: type}
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examlib/addorupdate",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    $("#btn_save").attr("disabled", true);
                });
            }
        });

    })
    //取消按钮-点击事件
    $("#btn_cancel").on("click", function () {
        examlib._btn_cancel();
    });
    //点击新增事件
    $("#addexamlibbtn").on("click", function () {
        $("#show_examlib_single").hide();
        $("#add_examlib_single").show();
        $("#list_examlib_single").hide();
    })
    //点击返回按钮--返回列表需要异步刷新列表
    $("#addexamlibbtnRe").on("click", function () {
        $("#show_examlib_single").hide();
        $("#add_examlib_single").hide();
        $("#list_examlib_single").show();
        examlib._clearDate();
        examlib._btn_cancel();
        examlib.clearTable();
    })
})
//清理列表，重新加载列表
examlib.clearTable = function () {
    if ($('#examlib_datatable').hasClass('dataTable')) {
        dttable = $('#examlib_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    examlib._examlib_datatable();
}
examlib._clearDate=function(){
    $("#singleId").val("");
    $("#title").val("");
    $("#option_A").val("");
    $("#option_B").val("");
    $("#option_C").val("");
    $("#option_D").val("");
    $("#answer").val("");
}
//取消按钮
examlib._btn_cancel = function () {
    $("#btn_save").removeAttr("disabled");
}
//单选题列表
$(function () {
    examlib._examlib_datatable();
})
examlib._examlib_datatable = function () {
    $("#examlib_datatable").dataTable({
        "sAjaxSource": global_RootPath+"/examlib/type/single?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"mDataProp": "answer"},
            {"mDataProp": "title"},
            {"mDataProp": "answer"},
            {"sDefaultContent": ''}
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(3)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:examlib._btn_show_examlib('" + aData.id + "')\" ><i class='icon-edit'></i>    查看</a></li>" +
                " <li><a href=\"javascript:examlib._btn_edit_examlib('" + aData.id + "')\" ><i class='icon-edit'></i>    编辑</a></li>" +
                " <li><a href=\"javascript:examlib._btn_del_examlib('" + aData.id + "')\" ><i class='icon-trash'></i>    删除</a></li>" +
                " </ul>" +
                " </div>");
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            return nRow;
        }
    });
}
//查看
examlib._btn_show_examlib = function (id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/examlib/id/" + id,
        dataType: "json",
        success: function (datarc) {
            $("#show_title").text(datarc.data.title);
            var options = datarc.data.options.split("##")
            $("#show_a").text(options[1]);
            $("#show_b").text(options[2]);
            $("#show_c").text(options[3]);
            $("#show_d").text(options[4]);
            $("#show_answer").text(datarc.data.answer);
            $("#add_examlib_single").hide();
            $("#list_examlib_single").hide();
            $("#show_examlib_single").show();
        }
    });
}
//编辑
examlib._btn_edit_examlib = function (id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/examlib/id/" + id,
        dataType: "json",
        success: function (datarc) {
            var options = datarc.data.options.split("##")
            $("#singleId").val(datarc.data.id);
            $("#title").val(datarc.data.title);
            $("#option_A").val(options[1].substring(options[1].indexOf(".") + 1));
            $("#option_B").val(options[2].substring(options[2].indexOf(".") + 1));
            $("#option_C").val(options[3].substring(options[3].indexOf(".") + 1));
            $("#option_D").val(options[4].substring(options[4].indexOf(".") + 1));
            $("#answer").val(datarc.data.answer);
            $("#add_examlib_single").show();
            $("#list_examlib_single").hide();
            $("#show_examlib_single").hide();
        }
    });
}
//    删除
examlib._btn_del_examlib = function (id) {
        layer.confirm('确定删除?', function () {
            $.ajax({
                type: "DELETE",
                contentType: "application/json; charset=utf-8",
                url: global_RootPath+"/examlib/delete/" + id,
                dataType: "json",
                success: function (datarc) {
                    examlib.clearTable();
                    layer.msg('删除成功！');
                }
            });
        });

    }
