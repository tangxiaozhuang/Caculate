/**
 * Created by thz on 2017/5/11.
 */
function a2() {
    document.getElementById('repayType').innerHTML="<option value=1 >到期还本息</option><option value=5>标满付息，到期还本</option>" ;
}
function a1() {
    document.getElementById("repayType").innerHTML="<option value=0 >月还息到期还本</option> <option value=1>到期还本息</option> <option value=2>按日算息，每月付息，到期还本</option> <option value=3>按月还本息（等额本息）</option> <option value=4>月还息按季还本</option> <option value=5>标满付息，到期还本</option> <option value=6>等本等息</option> <option value=7>等额本金</option><option value=8>按季还息，到期还本</option>" ;
}

function time() {
    getTime();
    startTime();

}
function startTime()
{
    var today=new Date()  ;
    var day = today.getDate();
    var month = today.getMonth() + 1;
    var year = today.getFullYear();
    var h=today.getHours()  ;
    var m=today.getMinutes()  ;
    var s=today.getSeconds()  ;
    m=checkTime(m)  ;
    s=checkTime(s)  ;
    document.getElementById('date').innerHTML=year+"/"+month+"/"+day+" "+h+":"+m+":"+s  ;
    t=setTimeout('startTime()',500)    ;
    function checkTime(i)
    {
        if(i<10)
        {i="0"+i;}
        return i  ;
    }
}
function  getTime() {
    var today=new Date()  ;
    var day = today.getDate();
    var month = today.getMonth() + 1;
    var year = today.getFullYear();
    document.getElementById('dateBg').value=year+"-"+month+"-"+day ;
}
//      jQuery(document).ready(function(){
//          //点击提交按钮时，从服务端获取数据，然后在客户端显示
//          $("#btn").click(function(){
function operate() {

    // 序列化表单的
    var param=$("#frm").serializeArray();
    var params=this.check(param);
//              alert(params.dateBg);
    $.ajax({
        url: "packageData.action",
        // 数据发送方式
        type: "post",
        // 要传递的数据
        data : params,
        // 接受数据格式
//                  dataType : "json",
        // 回调函数，接受服务器端返回给客户端的值，即result值
        success :  function(result){
//                      alert("........................");
            //测试result是否从服务器端返回给客户端
//                      alert(result);
            //解析json对象
            var json = eval("("+result+")");
//                      alert(json[0].payDate);
            if(json!=null){
                var resultTableTop=" <tr> <td>期号</td> <td>代收(含本金)</td> <td>本金</td> <td>利息</td> <td>奖金</td> <td>日期</td> </tr>";
                var resultTable=" ";
                for(var i=0;i<json.length;i++){

                     resultTable+="<tr><td>"+json[i].issue+"</td><td>"+json[i].balance+"</td><td>"+json[i].principal+"</td><td>"+json[i].interest+"</td><td>"+json[i].reward+"</td><td>"+json[i].payDate+"</td></tr>";
                }
                document.getElementById("resultDiv").innerHTML ="<table class='table table-bordered'>"+resultTableTop+resultTable+"</table>";

            }else{
                return null;
            }
        },
        error:function (result) {
            alert("result"+result);
        }
    });
}


function check(param){
    var param2=param;

    if(param2.dateBg==null){
        param2.dateBg=new Date();

    }
    // var date=param2.dateBg;Tue May 09 00:00:00 CST 2017  Sun May 07 2017 12:01:35 GMT+0800



    if(param2.LDate==null){
        param2.LDate=0;

    }
    if(param2.LMoney==null){
        param2.LMoney=0;
    }
    if(param2.LRate==null){
        param2.LRate=0;
    }

    return param2;
}