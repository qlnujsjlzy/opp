/**
 * Created by 朝阳 on 2016/4/9.
 */
var examinee_batch = {};
$(function () {
    //全选按钮
    $("input[name='fullcheck']").on("click", function () {
        $("input[name='fullcheck']").attr("checked", $(this).is(":checked"));
        $(".sorting_1").find("input[type='checkbox']").attr("checked", $(this).is(":checked"));
    })
    //添加按钮
    $("#addbtn").on("click", function () {
        examinee_batch.show_add_group();
    });
    //返回按钮
    $("#rebtn").on("click", function () {
        $("#groupname").val("");
        $("input[name='fullcheck']").attr("checked", false);
        $(".sorting_1").find("input[type='checkbox']").attr("checked", $(this).is(":checked"));
        $("#btn_save_examinee").removeAttr("disabled");
        $("#btn_save_group").removeAttr("disabled");
        examinee_batch.clearTable_group_datatable();
        examinee_batch.show_list_group();
    });

    //分组保存
    $("#btn_save_group").on("click", function () {
        var jsonData = {id: $("#groupid").val(), groupname: $.trim($("#groupname").val())}
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/group/addorupdate",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    $("#groupid_next").val(data.data.id);
                    $("#groupname_add").text("添加考生--分组名称："+data.data.groupname);
                    $("#btn_save_group").attr("disabled", true);
                    examinee_batch.show_list_examinee();
                });
            }
        });
    });
    //返回分组
    $("#btn_cancel_group").on("click", function () {
        $("#groupname").val("");
        $("#btn_save_group").removeAttr("disabled");
    });
    //添加分组考生
    $("#btn_save_examinee").on("click", function () {
        var groupid_next = $("#groupid_next").val();
        var arr = [];
        $(".sorting_1").find("input[type='checkbox']").each(function () {
            if ($(this).is(":checked")) {
                var list = {};
                list.groupid = groupid_next;
                list.userid = $(this).val();
                arr.push(list);
            }
        });
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examineegroup/addorupdate",
            data: JSON.stringify(arr),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    $("#btn_save_examinee").attr("disabled", true);
                });
            }
        });
    });
    //返回考生
    $("#btn_cancel_examinee").on("click", function () {
        $("input[name='fullcheck']").attr("checked", false);
        $(".sorting_1").find("input[type='checkbox']").attr("checked", $(this).is(":checked"));
        $("#btn_save_examinee").removeAttr("disabled");
    });

});
//查询
$(function () {
    //查询列表
    examinee_batch.group_datatable();
    examinee_batch.examinee_batch_datatable();
})
//清理分组列表
examinee_batch.clearTable_group_datatable = function () {
    if ($('#group_datatable').hasClass('dataTable')) {
        dttable = $('#group_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    examinee_batch.group_datatable();
}
//查询列表使用组件--dataTable--低于1.10.1版本
//分组列表
examinee_batch.group_datatable = function () {
    $("#group_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/group/all?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "groupname"},
            {"sDefaultContent": ''}
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            $('td:eq(2)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:examinee_batch._btn_add_examinee('" + aData.id + "','" + aData.groupname + "')\" ><i class='icon-edit'></i> 添加考生</a></li>" +
                " <li><a href=\"javascript:examinee_batch._btn_show_examinee('" + aData.id + "','" + aData.groupname + "')\" ><i class='icon-envelope'></i> 查看考生</a></li>" +
                " <li><a href=\"javascript:examinee_batch._btn_del_group('" + aData.id + "')\" ><i class='icon-trash'></i> 删除分组</a></li>" +
                " </ul>" +
                " </div>");
            return nRow;
        }
    });
};
//查看分组中考生列表--查看按钮
examinee_batch._btn_show_examinee=function(groupid,groupname){
    $("#groupname_show").text("考生列表--分组名称："+groupname);
    examinee_batch.show_list_group_examinee();
    examinee_batch.clearTable_group_examinee_datatable(groupid);
};
//跳转到添加考生
examinee_batch._btn_add_examinee=function(groupid,groupname){
    $("#groupname_add").text("添加考生--分组名称："+groupname);
    $("#groupid_next").val(groupid);
    examinee_batch.show_list_examinee();
};
//删除分组
examinee_batch._btn_del_group = function (groupid) {
    layer.confirm('确定删除?', function () {
        $.ajax({
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/group/delete/" + groupid,
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "删除成功", function () {
                    examinee_batch.clearTable_group_datatable();//刷新列表
                });
            }
        });
    });
};
//查看分组中的考生
examinee_batch.clearTable_group_examinee_datatable = function (groupid) {
    if ($('#group_examinee_datatable').hasClass('dataTable')) {
        dttable = $('#group_examinee_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    examinee_batch.group_examinee_datatable(groupid);
}
//查看分组中考生列表
examinee_batch.group_examinee_datatable = function (groupid) {
    $("#group_examinee_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/examineegroup/get/"+groupid+"?userid=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "username"},
            {"mDataProp": "ticketnumber"},
            {"mDataProp": "idcard"},
            {"mDataProp": "phone"},
            {"mDataProp": "actstatus"},
            {"sDefaultContent": ''}
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage":guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            if (aData.actstatus == 1) {
                $('td:eq(5)', nRow).html("<span class='label label-success'>正常</span>");
            }
            else if (aData.actstatus == -1) {
                $('td:eq(5)', nRow).html("<span class='label label-important'>已删除</span>");
            } else if (aData.actstatus == 0) {
                $('td:eq(5)', nRow).html("<span class='label label-warning'>已锁定</span>");
            }
            $('td:eq(6)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:examinee_batch.btn_del_group_examinee('" + groupid + "','" + aData.userid + "')\" ><i class='icon-trash'></i>   删除</a></li>" +
                " </ul>" +
                " </div>");
            return nRow;
        }
    });
};
//删除分组中的考生
examinee_batch.btn_del_group_examinee=function(groupid,userid){
    layer.confirm('确定删除?', function () {
        $.ajax({
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/examineegroup/delete/"+groupid+"/" + userid,
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "删除成功", function () {
                    examinee_batch.clearTable_group_examinee_datatable(groupid);//刷新列表
                });
            }
        });
    });
}
//获取正常状态的考生信息
examinee_batch.examinee_batch_datatable = function () {
    $("#examinee_batch_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/examinee/getAllByActStatus/1?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "username"},
            {"mDataProp": "ticketnumber"},
            {"mDataProp": "idcard"},
            {"mDataProp": "phone"},
            {"mDataProp": "actstatus"}

        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html("<label class=\"checkbox\"> <input type=\"checkbox\" value=\"" + aData.userid + "\">选中</label>");
            if (aData.actstatus == 1) {
                $('td:eq(5)', nRow).html("<span class='label label-success'>正常</span>");
            }
            else if (aData.actstatus == -1) {
                $('td:eq(5)', nRow).html("<span class='label label-important'>已删除</span>");
            } else if (aData.actstatus == 0) {
                $('td:eq(5)', nRow).html("<span class='label label-warning'>已锁定</span>");
            }
            return nRow;
        }
    });
};
//显示分组列表
examinee_batch.show_list_group=function(){
    $("#list_group").show(); //分组列表
    $("#add_group").hide(); //添加分组
    $("#list_group_examinee").hide();//分组包含的考生列表
    $("#list_examinee").hide();//所有正常考生列表
}
//显示添加分组框
examinee_batch.show_add_group=function(){
    $("#list_group").hide(); //分组列表
    $("#add_group").show(); //添加分组
    $("#list_group_examinee").hide();//分组包含的考生列表
    $("#list_examinee").hide();//所有正常考生列表
}
//显示分组包含的考生列表
examinee_batch.show_list_group_examinee=function(){
    $("#list_group").hide(); //分组列表
    $("#add_group").hide(); //添加分组
    $("#list_group_examinee").show();//分组包含的考生列表
    $("#list_examinee").hide();//所有正常考生列表
}
//显示需添加到所有正常考生列表
examinee_batch.show_list_examinee=function(){
        $("#list_group").hide(); //分组列表
        $("#add_group").hide(); //添加分组
        $("#list_group_examinee").hide();//分组包含的考生列表
        $("#list_examinee").show();//所有正常考生列表
    }
