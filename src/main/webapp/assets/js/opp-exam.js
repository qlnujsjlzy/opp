/**
 * Created by 朝阳 on 2016/4/15.
 */
var exam = {};
$(function () {
    //考试操作
    $('#begintime_dm').on('click', function() {
        $('#begintime_dm').datetimepicker('setEndDate', $("#endtime").val());
    })
    $('#endtime_dm').on('click', function() {
        $('#endtime_dm').datetimepicker('setStartDate', $("#begintime").val());
    })
    $("#btn_save").on("click", function () {
        var examid = $.trim($("#examid").val());
        var examname = $.trim($("#examname").val());
        var groupid = $.trim($("#groupid").val());
        var topicid = $.trim($("#topicid").val());
        var begintime = $.trim($("#begintime").val());
        var endtime = $.trim($("#endtime").val());
        if (examname == "") {
            guodandan.failAlert('考试名称不能为空', function () {
                $("#examname").focus();
            });
            return;
        }
        if (groupid == "") {
            guodandan.failAlert('分组不能为空', function () {
                $("#groupname").focus();
            });
            return;
        }
        if (topicid == "") {
            guodandan.failAlert('套题不能为空', function () {
                $("#topicname").focus();
            });
            return;
        }
        if (begintime == "") {
            guodandan.failAlert('开始时间不能为空', function () {
                $("#begintime").focus();
            });
            return;
        }
        if (endtime == "") {
            guodandan.failAlert('结束时间不能为空', function () {
                $("#endtime").focus();
            });
            return;
        }
        begintime=Date.parse(begintime);
        endtime=Date.parse(endtime);
        if (endtime<begintime) {
            guodandan.failAlert('结束时间不能小于开始时间', function () {
                $("#endtime").focus();
            });
            return;
        }
        var jsonData = {id: examid, examname: examname, groupid: groupid, topicid: topicid, begintime: begintime,endtime:endtime};
        $("#btn_save").attr("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/exam/addorupdate",
            data: JSON.stringify(jsonData),
            dataType: "json",
            success: function (data) {
                guodandan.layerMsg(data, "保存成功", function () {
                    $("#btn_save").attr("disabled", true);
                    exam.clearTable_exam_datatable();
                });
            }
        });

    })
    $("#btn_cancel").on("click",function(){
        $("#btn_save").removeAttr("disabled");
    });
    $("#addbtn").on("click",function(){
        exam.show_add_exam();
        exam.clearInput();
    });
    $("#rebtn").on("click",function(){
        exam.show_list_exam();
        exam.clearInput();
    });
    //套题操作
    $("#topicname").on("click",function(){
        exam.show_list_topic();
        exam.clear_topic_datatable();

    });
    $("#topic_save").on("click",function(){
        var topic_radio= $("input[name='topic_radio']:checked").val();
        if(topic_radio==""||topic_radio==null){
            guodandan.failAlert("请选择一条套题");
            return;
        }
        var trArr= topic_radio.split("|");
        $("#topicid").val(trArr[0]);
        $("#topicname").val(trArr[1]);
        exam.show_add_exam();
    })
    $("#topic_cal").on("click",function(){
        $("input[name='topic_radio']:checked").removeAttr("checked");
        exam.show_add_exam();
    })

    //分组操作
    $("#groupname").on("click",function(){
        exam.show_list_group();
        exam.clearTable_group_datatable();

    });
    $("#group_save").on("click",function(){
        var topic_radio= $("input[name='group_radio']:checked").val();
        if(topic_radio==""||topic_radio==null){
            guodandan.failAlert("请选择一个分组");
            return;
        }
        var trArr= topic_radio.split("|");
        $("#groupid").val(trArr[0]);
        $("#groupname").val(trArr[1]);
        exam.show_add_exam();
    })
    $("#group_cal").on("click",function(){
        $("input[name='groupc_radio']:checked").removeAttr("checked");
        exam.show_add_exam();
    })
})
exam.show_add_exam=function(){
    $("#list_topic").hide();
    $("#list_group").hide();
    $("#add_exam").show();
    $("#list_exam").hide();
}
exam.show_list_topic=function(){
    $("#add_exam").hide();
    $("#list_group").hide();
    $("#list_topic").show();
    $("#list_exam").hide();
}
exam.show_list_group=function(){
    $("#add_exam").hide();
    $("#list_topic").hide();
    $("#list_group").show();
    $("#list_exam").hide();
}
exam.show_list_exam=function(){
    $("#add_exam").hide();
    $("#list_topic").hide();
    $("#list_group").hide();
    $("#list_exam").show();
}

exam.clearInput=function(){
    $("#examid").val("");
    $("#examname").val("");
    $("#groupid").val("");
    $("#topicid").val("");
    $("#begintime").val("");
    $("#endtime").val("");
    $("#begintime_show").val("");
    $("#endtime_show").val("");
}

//查询
$(function () {
    exam.exam_datatable();
})
//清理列表，重新加载列表
exam.clear_topic_datatable = function () {
    if ($('#topic_datatable').hasClass('dataTable')) {
        dttable = $('#topic_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    exam._topic_datatable();
}
exam._topic_datatable = function () {
    $("#topic_datatable").dataTable({
        "sAjaxSource": global_RootPath+"/topic/getAll?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "name"},
            {"mDataProp": "singlenumber"},
            {"mDataProp": "multiplenumber"},
            {"mDataProp": "judgenumber"},
            {"mDataProp": "singleweight"},
            {"mDataProp": "multipleweight"},
            {"mDataProp": "judgeweight"}
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html("<label class=\"radio\"> <input type=\"radio\" name=\"topic_radio\" value=\"" + aData.id+"|"+aData.name + "\">选中</label>");
            return nRow;
        }
    });
};

//清理考试列表
exam.clearTable_exam_datatable = function () {
    if ($('#exam_datatable').hasClass('dataTable')) {
        dttable = $('#exam_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    exam.exam_datatable();
}
//查询列表使用组件--dataTable--低于1.10.1版本
exam.exam_datatable = function () {
    $("#exam_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/exam/all?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "examname"},
            {"mDataProp": "topicname"},
            {"mDataProp": "groupname"},
            {"mDataProp": "begintime"},
            {"mDataProp": "endtime"},
            {"sDefaultContent": ''}
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            var newDate = new Date();
            newDate.setTime(aData.begintime);
            $('td:eq(4)', nRow).html(newDate.toLocaleDateString());
            newDate.setTime(aData.endtime);
            $('td:eq(5)', nRow).html(newDate.toLocaleDateString());

            $('td:eq(6)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:exam._btn_edit_exam('" + aData.id + "')\" ><i class='icon-edit'></i> 编辑</a></li>" +
                " <li><a href=\"javascript:exam._btn_del_exam('" + aData.id + "')\" ><i class='icon-trash'></i> 删除</a></li>" +
                " </ul>" +
                " </div>");
            return nRow;
        }
    });
};
//编辑
exam._btn_edit_exam = function (id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/exam/id/" + id,
        dataType: "json",
        success: function (datarc) {
            $("#examid").val(datarc.data.id);
            $("#examname").val(datarc.data.examname);
            $("#groupid").val(datarc.data.groupid);
            $("#topicid").val(datarc.data.topicid);
            $("#groupname").val(datarc.data.groupname);
            $("#topicname").val(datarc.data.topicname);
            var newDate = new Date();
            newDate.setTime(datarc.data.begintime);
            $("#begintime_show").val(newDate.toLocaleDateString());
            $("#begintime").val(newDate.toLocaleDateString());
            newDate.setTime(datarc.data.endtime);
            $("#endtime_show").val(newDate.toLocaleDateString());
            $("#endtime").val(newDate.toLocaleDateString());
            exam.show_add_exam();
        }
    });
}
//    删除
exam._btn_del_exam = function (id) {
    layer.confirm('确定删除?', function () {
        $.ajax({
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/exam/delete/" + id,
            dataType: "json",
            success: function (datarc) {
                exam.clearTable_exam_datatable();
                layer.msg('删除成功！');
            }
        });
    });

}


//清理分组列表
exam.clearTable_group_datatable = function () {
    if ($('#group_datatable').hasClass('dataTable')) {
        dttable = $('#group_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    exam.group_datatable();
}
//查询列表使用组件--dataTable--低于1.10.1版本
//分组列表
exam.group_datatable = function () {
    $("#group_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/group/all?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "groupname"},
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html("<label class=\"radio\"> <input type=\"radio\" name=\"group_radio\" value=\"" + aData.id+"|"+aData.groupname + "\">选中</label>");
            return nRow;
        }
    });
};