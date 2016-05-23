/**
 * Created by 朝阳 on 2016/4/30.
 */
var score = {}
//查询
$(function () {
    var cookieUse=guodandan.userCookie('sessionUser');
    score.clearTable_score_datatable(cookieUse.userId);
})
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