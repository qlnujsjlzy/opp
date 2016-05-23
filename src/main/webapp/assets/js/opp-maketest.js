/**
 * Created by 朝阳 on 2016/4/7.
 */
var topic = {};
$(function () {
    $("#btn_save").on("click", function () {
        var id = $("#topicId").val();
        var name = $.trim($("#topic_name").val());
        var singlenumber = $.trim($("#singlenumber").val());
        var multiplenumber = $.trim($("#multiplenumber").val());
        var judgenumber = $.trim($("#judgenumber").val());
        var singleweight = $.trim($("#singleweight").val());
        var multipleweight = $.trim($("#multipleweight").val());
        var judgeweight = $.trim($("#judgeweight").val());

        if (name == "") {
            guodandan.failAlert('套题名称不能为空', function () {
                $("#topic_name").val("").focus();
            });
            return;
        }
        if (singlenumber == "" || isNaN(singlenumber)) {
            guodandan.failAlert('单选题数量不能为空且只能是数字', function () {
                $("#singlenumber").val("").focus();
            });
            return;
        }
        if (singleweight == "" || isNaN(singleweight)) {
            guodandan.failAlert('单选题分值不能为空且只能是数字', function () {
                $("#singleweight").val("").focus();
            });
            return;
        }
        if (multiplenumber == "" || isNaN(multiplenumber)) {
            guodandan.failAlert('多选题数量不能为空且只能是数字', function () {
                $("#multiplenumber").val("").focus();
            });
            return;
        }

        if (multipleweight == "" || isNaN(multipleweight)) {
            guodandan.failAlert('多选题分值不能为空且只能是数字', function () {
                $("#multipleweight").val("").focus();
            });
            return;
        }
        if (judgenumber == "" || isNaN(judgenumber)) {
            guodandan.failAlert('判断题数量不能为空且只能是数字', function () {
                $("#judgenumber").val("").focus();
            });
            return;
        }
        if (judgeweight == "" || isNaN(judgeweight)) {
            guodandan.failAlert('判断题分值不能为空且只能是数字', function () {
                $("#judgeweight").val("").focus();
            });
            return;
        }
        var weight = parseInt(judgenumber) * parseInt(judgeweight) + parseInt(singlenumber) * parseInt(singleweight) + parseInt(multiplenumber) * parseInt(multipleweight);

        if (weight != 100) {
            guodandan.failAlert('题型数*分值总和必须为100', function () {
                $("#singlenumber").focus();
            });
            return;
        }
        var jsonData = {
            id: id,
            name: name,
            singlenumber: singlenumber,
            multiplenumber: multiplenumber,
            judgenumber: judgenumber,
            singleweight: singleweight,
            multipleweight: multipleweight,
            judgeweight: judgeweight
        }
        $("#btn_save").attr("disabled", true);
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/topic/addorupdate",
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
        topic._btn_cancel();
    });
    //点击新增事件
    $("#addtopicbtn").on("click", function () {
        $("#add_topic").show();
        $("#list_topic").hide();

    })
    //点击返回按钮--返回列表需要异步刷新列表
    $("#addtopicbtnRe").on("click", function () {
        $("#add_topic").hide();
        $("#list_topic").show();
        topic.clearTable();
        topic._btn_cancel();
        topic._clearDate();
    })
})

//取消按钮
topic._btn_cancel = function () {
    $("#btn_save").removeAttr("disabled");
}
//清理列表，重新加载列表
topic.clearTable = function () {
    if ($('#topic_datatable').hasClass('dataTable')) {
        dttable = $('#topic_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    topic._topic_datatable();
}
topic._clearDate = function () {
    $("#topicId").val("");
    $("#topic_name").val("");
    $("#singlenumber").val("");
    $("#multiplenumber").val("");
    $("#judgenumber").val("");
    $("#singleweight").val("");
    $("#multipleweight").val("");
    $("#judgeweight").val("");
}
//单选题列表
$(function () {
    topic._topic_datatable();
})
topic._topic_datatable = function () {
    $("#topic_datatable").dataTable({
        "sAjaxSource": global_RootPath+"/topic/getAll?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"mDataProp": "id"},
            {"mDataProp": "name"},
            {"mDataProp": "singlenumber"},
            {"mDataProp": "multiplenumber"},
            {"mDataProp": "judgenumber"},
            {"mDataProp": "singleweight"},
            {"mDataProp": "multipleweight"},
            {"mDataProp": "judgeweight"},
            {"sDefaultContent": ''}
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(8)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:topic._btn_update_topic('" + aData.id + "')\" ><i class='icon-edit'></i>   编辑</a></li>" +
                " <li><a href=\"javascript:topic._btn_del_topic('" + aData.id + "')\" ><i class='icon-trash'></i>   删除</a></li>" +
                " </ul>" +
                " </div>");
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            return nRow;
        }
    });
}
//编辑
topic._btn_update_topic = function (id) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/topic/get/" + id,
        dataType: "json",
        success: function (datarc) {
            $("#topicId").val(datarc.data.id);
            $("#topic_name").val(datarc.data.name);
            $("#singlenumber").val(datarc.data.singlenumber);
            $("#multiplenumber").val(datarc.data.multiplenumber);
            $("#judgenumber").val(datarc.data.judgenumber);
            $("#singleweight").val(datarc.data.singleweight);
            $("#multipleweight").val(datarc.data.multipleweight);
            $("#judgeweight").val(datarc.data.judgeweight);
            $("#add_topic").show();
            $("#list_topic").hide();
        }
    });
}
//    删除
topic._btn_del_topic = function (id) {
    layer.confirm('确定删除?', function () {
        $.ajax({
            type: "DELETE",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/topic/delete/" + id,
            dataType: "json",
            success: function (datarc) {
                topic.clearTable();
                layer.msg('删除成功！');
            }
        });
    });

}
