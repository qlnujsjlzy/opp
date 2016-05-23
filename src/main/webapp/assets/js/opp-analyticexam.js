/**
 * Created by 朝阳 on 2016/4/30.
 */
var analytics={};
//查看成绩
score._btn_show_score = function (examid) {
    $.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: global_RootPath+"/scores/examid/" + examid + "?now=" + new Date().getTime(),
        dataType: "json",
        success: function (data) {
            if (data.rc == 0) {
                console.info(data.data)
                var rcdata=data.data;
                var mdata=[];
                for(var i=0;i<rcdata.length;i++){
                    var xydata={};
                    xydata.x=rcdata[i].username;
                    xydata.y=rcdata[i].score==-1?0:rcdata[i].score;
                    mdata.push(xydata);
                }
                console.info(mdata)
                analytics.show_chart_score();
                analytics.show_morris(mdata);
            } else {
                guodandan.notAuth(data);
            }
        }
    })
}
$("#rebtn").on("click" ,function(){
    analytics.show_list_score();
});
analytics.show_morris = function (mdata) {
    $('.analytics_item').html('');
   new Morris.Bar({
        element: 'areachart',
        data: mdata,
        xkey: 'x',
        ykeys: ['y'],
        smooth: false,
        lineColors: ['#42C1F7', '#B3E6FC'],
        labels: ['考试成绩（分）']
    });
}
analytics.show_chart_score = function () {
    $("#chart_score").show();
    $("#list_exam").hide();
}
analytics.show_list_score = function () {
    $("#chart_score").hide();
    $("#list_exam").show();
}