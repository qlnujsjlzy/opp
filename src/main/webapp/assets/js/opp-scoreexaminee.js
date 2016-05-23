/**
 * Created by 朝阳 on 2016/4/15.
 */
var score = {}
//查询
$(function () {
    $("#rebtn").on("click", function () {
        score.show_list_examinee();
    });

    score.clearTable_examinee_datatable();
})
score.show_list_score = function () {
    $("#list_score").show();
    $("#list_examinee").hide();
}
score.show_list_examinee = function () {
    $("#list_score").hide();
    $("#list_examinee").show();
}
score.clearTable_examinee_datatable = function () {
    if ($('#examinee_datatable').hasClass('dataTable')) {
        dttable = $('#examinee_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    score._examinee_datatable();
}
//查询列表使用组件--dataTable--低于1.10.1版本
score._examinee_datatable = function () {
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
            $('td:eq(6)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +
                " <li><a href=\"javascript:score._btn_show_score('" + aData.userid + "')\" ><i class='icon-edit'></i>查看成绩</a></li>" +
                " </ul>" +
                " </div>");
            if (aData.actstatus == 1) {
                $('td:eq(5)', nRow).html("<span class='label label-success'>正常</span>");
            }
            else if (aData.actstatus == -1) {
                $('td:eq(5)', nRow).html("<span class='label label-important'>已删除</span>");
            }
            else if (aData.actstatus == 0) {
                $('td:eq(5)', nRow).html("<span class='label label-warning'>已锁定</span>");
            }
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            return nRow;
        }
    });
}
//查看成绩
score._btn_show_score = function (userid) {
    score.show_list_score();
    score.clearTable_score_datatable(userid);
}
//清理考试列表
score.clearTable_score_datatable = function (userid) {
    if ($('#score_datatable').hasClass('dataTable')) {
        dttable = $('#score_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    score.score_datatable(userid);
}
//查询列表使用组件--dataTable--低于1.10.1版本
score.score_datatable = function (userid) {
    $("#score_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/scores/userid/" + userid + "?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "examname"},
            {"mDataProp": "username"},
            {"mDataProp": "idcard"},
            {"mDataProp": "score"}
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            $('td:eq(4)', nRow).html(aData.score == -1 ? 0 : aData.score);
            return nRow;
        }
    });
};