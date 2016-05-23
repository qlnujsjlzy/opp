/**
 * Created by 朝阳 on 2016/4/26.
 */
var online = {}
//查询
window.onbeforeunload=function(){
    return "请先点击提交试卷,否则自动提交试卷0分";
}
$(function () {
    var curWwwPath = window.document.location.href;
    var wwwparm = curWwwPath.substring(curWwwPath.indexOf('?') + 1, curWwwPath.length);
    var examid=wwwparm.split("=")[1];
    online._btn_start_exam(examid);
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
})
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
                console.info(redata)
                var single=redata.single;
                var multiple=redata.multiple;
                var judge=redata.judge;
                online.RenderExam(single,multiple,judge);
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
