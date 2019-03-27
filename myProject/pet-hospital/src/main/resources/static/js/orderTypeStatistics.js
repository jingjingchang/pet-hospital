

function initCharts(bname,mnum) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('charts'));
    // 指定图表的配置项和数据
    var option = {
        title : {
            text: '预约项目情况统计',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: bname
        },
        series : [
            {
                name: '预约次数',
                type: 'pie',
                radius: '55%',
                data:mnum
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function getOrderTypeData() {
    $.ajax({
        type: 'POST',
        url: '/ordertype/getOrderTypeStatistics',
        data: {},
        success: function (data) {
            var mnum='[';
            var bname='[';
            for(var i=0;i<data.length;i++){
                bname +="'"+data[i].name+"'";
                mnum += '{';
                mnum +="value:"+data[i].num+",name:'"+data[i].name+"'"
                mnum +='}';
                if(i<data.length-1){
                    mnum +=',';
                    bname+=',';
                }
            }
            mnum +=']';
            bname+=']';
            initCharts(eval(bname),eval(mnum));
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.alert("操作失败！");
            console.log(XMLHttpRequest.responseText);
        }
    });
}