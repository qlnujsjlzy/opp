/**
 * Created by 朝阳 on 2016/4/15.
 */
var score={}
//查询
$(function () {
    $("#rebtn").on("click" ,function(){
        score.show_list_exam();
    });

    score.exam_datatable();
})
score.show_list_score=function(){
    $("#list_score").show();
    $("#list_exam").hide();
}
score.show_list_exam=function(){
    $("#list_score").hide();
    $("#list_exam").show();
}
//清理考试列表
score.clearTable_exam_datatable = function () {
    if ($('#exam_datatable').hasClass('dataTable')) {
        dttable = $('#exam_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    score.exam_datatable();
}
//查询列表使用组件--dataTable--低于1.10.1版本
score.exam_datatable = function () {
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
            var btn="<li><a href=\"javascript:;\" ><i class='icon-edit'></i>考试未结束</a></li>";
            if(new Date().getTime()>aData.endtime){
                btn="<li><a href=\"javascript:score._btn_show_score('" + aData.id + "')\" ><i class='icon-edit'></i>查看成绩</a></li>";
            }
            if(new Date().getTime()<aData.begintime){
                btn="<li><a href=\"javascript:;\" ><i class='icon-edit'></i>考试未开始</a></li>";
            }
            $('td:eq(6)', nRow).html("<div class='btn-group'>" +
                "<a class='btn btn-small dropdown-toggle' data-toggle='dropdown' href='#'>" +
                " 操作" +
                " <span class='caret'></span>" +
                " </a>" +
                "<ul class='dropdown-menu pull-right'>" +btn+
                " </ul>" +
                " </div>");
            return nRow;
        }
    });
};
//查看成绩
score._btn_show_score = function (examid) {
    score.show_list_score();
    score.clearTable_score_datatable(examid);
}
//清理考试列表
score.clearTable_score_datatable = function (examid) {
    if ($('#score_datatable').hasClass('dataTable')) {
        dttable = $('#score_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    score.score_datatable(examid);
}
//查询列表使用组件--dataTable--低于1.10.1版本
score.score_datatable = function (examid) {
    $("#score_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/scores/examid/"+examid+"?now=" + new Date().getTime(),
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
        "oLanguage": guodandan.oLanguage ,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            $('td:eq(4)', nRow).html(aData.score==-1? 0:aData.score);
            return nRow;
        }
    });
};