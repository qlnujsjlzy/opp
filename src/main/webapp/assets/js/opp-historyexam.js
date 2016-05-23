/**
 * Created by 朝阳 on 2016/4/26.
 */
var online = {}
//查询
/*  window.onbeforeunload=function(){
 return "请先提交试卷,否则自动提交试卷";
 }*/
$(function () {
    var cookieUse=guodandan.userCookie('sessionUser');
    online.clearTable_score_datatable(cookieUse.userId);
    //提交试卷
    $("#submit").on("click",function(){
        var single_total=$("#single_total").val();
        var multiple_total=$("#multiple_total").val();
        var judge_total=$("#judge_total").val();
        var records=[];
        if(single_total!=""){
            for(var n=0;n<single_total;n++){
                var record={};
                var rejr=$("#single_examlib").find("h5").eq(n).find("input").is(':checked');
                if(rejr==false){
                    guodandan.failAlert("单选题-第"+(n+1)+"题未作答");
                    record={};
                    records=[];
                    return;
                }else{
                    var jr= $("#single_examlib").find("h5").eq(n).find("input[type=radio]:checked");
                    var id= jr[0].name;
                    var val=jr[0].value;
                    record.id=id;
                    record.myanswer=val;
                    records.push(record);
                }
            }
        }
        if(multiple_total!=""){
            var id="";
            var val="";
            for(var i=0;i<multiple_total;i++){
                var record={};
                //var jranswer= $("#multiple_examlib").find("h5").eq(i).find("input");
                var jr=$("#multiple_examlib").find("h5").eq(i).find("input").is(':checked');
                if(jr==false){
                    guodandan.failAlert("多选题-第"+(i+1)+"题未作答");
                    record={};
                    records=[];
                    return;
                }else{
                    var val="";
                    var ex=$("#multiple_examlib").find("h5").eq(i).find("input[type=checkbox]:checked");
                    for(var m=0;m<ex.length;m++){
                        id=ex[m].name ;
                        id=ex[m].name
                        val+=ex[m].value;
                    }
                    record.id=id;
                    record.myanswer=val;
                    records.push(record);
                }
            }
        }

        if(judge_total!=""){
            for(var j=0;j<judge_total;j++){
                var record={};
                var rejr=$("#judge_examlib").find("h5").eq(j).find("input").is(':checked');
                if(rejr==false){
                    guodandan.failAlert("判断题-第"+(j+1)+"题未作答");
                    record={};
                    records=[];
                    return;
                }else{
                    var jr= $("#judge_examlib").find("h5").eq(j).find("input[type=radio]:checked");
                    var id= jr[0].name;
                    var val=jr[0].value;
                    record.id=id;
                    record.myanswer=val;
                    records.push(record);
                }
            }
        }
        console.info(records)
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: global_RootPath+"/records/addorupdate",
            data:JSON.stringify(records),
            dataType: "json",
            success: function (datarc) {
                if (datarc.rc == 0) {
                    $("#submit").attr("disabled",true);
                    guodandan.layerMsg("试卷保存成功")
                } else {
                    guodandan.failAlert("试卷保存失败！");
                }
            }
        })
    });
    $("#relist").on("click",function(){
        online.show_list_score();
    });
})
online.clearTable_score_datatable = function (userid) {
    if ($('#score_datatable').hasClass('dataTable')) {
        dttable = $('#score_datatable').dataTable();
        dttable.fnClearTable(); //清空一下table
        dttable.fnDestroy(); //还原初始化了的datatable
    }
    online.score_datatable(userid);
}
//查询列表使用组件--dataTable--低于1.10.1版本
online.score_datatable = function (userid) {
    $("#score_datatable").dataTable({
        "bDestory": true,
        "sAjaxSource": global_RootPath+"/scores/userid/" + userid + "?now=" + new Date().getTime(),
        "sAjaxDataProp": "data",
        "aoColumns": [
            {"sDefaultContent": ''},
            {"mDataProp": "examname"},
            {"mDataProp": "begintime"},
            {"mDataProp": "endtime"},
            {"sDefaultContent": ''}
        ],
        "aoColumnDefs": [{"bSortable": false, "aTargets": [0]}],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType": "full_numbers",
        "oLanguage": guodandan.oLanguage,
        "fnRowCallback": function (nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格
            $('td:eq(0)', nRow).html(iDisplayIndex + 1);
            var newDate = new Date();
            newDate.setTime(aData.begintime);
            $('td:eq(2)', nRow).html(newDate.toLocaleDateString());
            newDate.setTime(aData.endtime);
            $('td:eq(3)', nRow).html(newDate.toLocaleDateString());
            var btn=" <li><a href=\"javascript:online._btn_start_exam('" + aData.examid + "')\" ><i class='icon-edit'></i>开始考试</a></li>";
            if(aData.status==1){
                btn=" <li><a href=\"javascript:online._btn_history_exam('" + aData.examid + "')\" ><i class='icon-edit'></i>已参加考试</a></li>";
            }
            if(new Date().getTime()>aData.endtime){
                btn=" <li><a href=\"javascript:;\" ><i class='icon-edit'></i>考试已结束</a></li>";
            }
            if(new Date().getTime()<aData.begintime){
                btn=" <li><a href=\"javascript:;\" ><i class='icon-edit'></i>考试未开始</a></li>";
            }


            $('td:eq(4)', nRow).html("<div class='btn-group'>" +
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
//开始考试
online._btn_start_exam = function (examid) {
    initTimer(getInitTime(examid));//时间倒计时
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/records/init/" + examid,
        dataType: "json",
        success: function (datarc) {
            if (datarc.rc == 0) {
                var redata=datarc.data;
                var single=redata.single;
                var multiple=redata.multiple;
                var judge=redata.judge;
                online.RenderExam(single,multiple,judge);
            } else {
                guodandan.notAuth(data);
            }
        }
    })
    online.show_online_exam();
}
//历史数据
online._btn_history_exam = function (examid) {
    online.show_history_exam();
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/records/history/" + examid,
        dataType: "json",
        success: function (datarc) {
            if (datarc.rc == 0) {
                var redata=datarc.data;
                var single=redata.single;
                var multiple=redata.multiple;
                var judge=redata.judge;
                online.RenderhistoryExam(single,multiple,judge);
            } else {
                guodandan.notAuth(data);
            }
        }
    })

}
online.RenderExam=function(single,multiple,judge){
    $("#single_examlib").empty();
    $("#multiple_total").empty();
    $("#judge_total").empty();
    //单选题
    if(single!=null&&single!=""){
        $("#single_total").val(single.length);
        var singlestr=""
        $("#single_type").html("单选题（共"+single.length+"题，1题"+single[0].weight+"分)");
        for(var i=0;i<single.length;i++){
            var options = single[i].options.split("##")
            singlestr+='<strong> <h4>'+single[i].sort+'、<span>'+single[i].title+'</span></h4><h5> <span> <label class="radio"> <input type="radio" name="'+single[i].id+'" value="A" >'+options[1]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="B" >'+options[2]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="C" >'+options[3]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="D" >'+options[4]+'</label> </h5> </strong>';
        }
        $("#single_examlib").append(singlestr);
    }
    //多选题
    if(multiple!=null&&multiple!=""){
        $("#multiple_total").val(multiple.length);
        var multiplestr=""
        $("#multiple_type").html("多选题（共"+multiple.length+"题，1题"+multiple[0].weight+"分)");
        for(var i=0;i<multiple.length;i++){
            var options = multiple[i].options.split("##")
            multiplestr+='<strong> <h4>'+multiple[i].sort+'、<span>'+multiple[i].title+'</span></h4><h5> <span> <label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="A">'+options[1]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="B">'+options[2]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="C">'+options[3]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="D">'+options[4]+'</label> </h5> </strong>';
        }
        $("#multiple_examlib").append(multiplestr);
    }
    //判断题
    if(judge!=null&&judge!=""){
        $("#judge_total").val(judge.length);
        var judgestr=""
        $("#judge_type").html("判断题（共"+judge.length+"题，1题"+judge[0].weight+"分)");
        for(var i=0;i<judge.length;i++){
            judgestr+='<strong> <h4>'+judge[i].sort+'、<span>'+judge[i].title+'</span></h4><h5> <span> <label class="radio"> <input type="radio" name="'+judge[i].id+'" value="1">正确 </label><label class="radio"> <input type="radio" name="'+judge[i].id+'" value="0">错误 </label> </span> </h5> </strong>';
        }
        $("#judge_examlib").append(judgestr);
    }

}
online.RenderhistoryExam=function(single,multiple,judge){
    $("#single_examlib").empty();
    $("#multiple_total").empty();
    $("#judge_total").empty();
    //单选题
    if(single!=null&&single!=""){
        $("#single_total").val(single.length);
        var singlestr=""
        $("#single_type").html("单选题（共"+single.length+"题，1题"+single[0].weight+"分)");
        for(var i=0;i<single.length;i++){
            var options = single[i].options.split("##")
            singlestr+='<strong> <h4>'+single[i].sort+'、<span>'+single[i].title+'</span></h4><h5> <span> <label class="radio"> <input type="radio" name="'+single[i].id+'" value="A" >'+options[1]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="B" >'+options[2]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="C" >'+options[3]+'</label><label class="radio"> <input type="radio" name="'+single[i].id+'" value="D" >'+options[4]+'</label> <label class="radio"> 正确答案： '+single[i].answer+'</label></h5> </strong>';
        }
        $("#single_examlib").append(singlestr);
        for(var i=0;i<single.length;i++){
            var id=single[i].id;
            var myanswer=single[i].myanswer;
            $("input[name=" +id + "]").each(function () {
                if ($(this).val() ==myanswer) {
                    $(this).attr("checked", true)
                }
            })
        }
    }
    //多选题
    if(multiple!=null&&multiple!=""){
        $("#multiple_total").val(multiple.length);
        var multiplestr=""
        $("#multiple_type").html("多选题（共"+multiple.length+"题，1题"+multiple[0].weight+"分)");
        for(var i=0;i<multiple.length;i++){
            var options = multiple[i].options.split("##")
            multiplestr+='<strong> <h4>'+multiple[i].sort+'、<span>'+multiple[i].title+'</span></h4><h5> <span> <label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="A">'+options[1]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="B">'+options[2]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="C">'+options[3]+'</label><label class="checkbox"> <input type="checkbox" name="'+multiple[i].id+'" value="D">'+options[4]+'</label><label class="checkbox"> 正确答案:'+multiple[i].answer+'</label> </h5> </strong>';
        }
        $("#multiple_examlib").append(multiplestr);
        for(var i=0;i<multiple.length;i++){
            var id=multiple[i].id;
            var myanswer=multiple[i].myanswer;
            if(myanswer!=null){
                var _ma=myanswer.length;
                for(var k=1;k<=_ma;k++){
                    var m=  myanswer.substring(k-1,k);
                    $("input[name=" +id + "]").each(function () {
                        if ($(this).val() ==m) {
                            $(this).attr("checked", true)
                        }
                    })
                }
            }
        }
    }
    //判断题
    if(judge!=null&&judge!=""){
        $("#judge_total").val(judge.length);
        var judgestr=""
        $("#judge_type").html("判断题（共"+judge.length+"题，1题"+judge[0].weight+"分)");
        for(var i=0;i<judge.length;i++){
            judgestr+='<strong> <h4>'+judge[i].sort+'、<span>'+judge[i].title+'</span></h4><h5> <span> <label class="radio"> <input type="radio" name="'+judge[i].id+'" value="1">正确 </label><label class="radio"> <input type="radio" name="'+judge[i].id+'" value="0">错误 </label><label class="radio"> 正确答案： '+(judge[i].answer==1?"正确":"错误")+'</label> </span> </h5> </strong>';
        }
        $("#judge_examlib").append(judgestr);
        for(var i=0;i<judge.length;i++){
            var id=judge[i].id;
            var myanswer=judge[i].myanswer;
            $("input[name=" +id + "]").each(function () {
                if ($(this).val() ==myanswer) {
                    $(this).attr("checked", true)
                }
            })
        }
    }

}
online.show_online_exam = function () {
    $("#countdown").show();
    $("#submit").show();
    $("#relist").hide();
    $("#list_score").hide();
    $("#show_examlib_judge").show();
    $("#show_examlib_single").show();
    $("#show_examlib_multiple").show();
}
online.show_history_exam = function () {
    $("#list_score").hide();
    $("#submit").hide();
    $("#relist").show();
    $("#show_examlib_judge").show();
    $("#show_examlib_single").show();
    $("#show_examlib_multiple").show();
}
online.show_list_score = function () {
        $("#list_score").show();
        $("#submit").hide();
        $("#relist").hide();
        $("#show_examlib_judge").hide();
        $("#show_examlib_single").hide();
        $("#show_examlib_multiple").hide();
    }
