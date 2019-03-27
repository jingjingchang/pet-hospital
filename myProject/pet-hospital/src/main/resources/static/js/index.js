var Index = function () {
    this.url = {
        listByPage:"/admin/sys/car/student/listByPage",
        create:"/admin/sys/car/student/create",
        deleteStudent:"/admin/sys/car/student/remove",
        updateStudent:"/admin/sys/car/student/update",
        getPassListByStuId:"/admin/sys/car/pass/getPassListByStuId",
        getPassList:"/admin/sys/car/pass/create",
        removePassById:"/admin/sys/car/pass/remove"
    };

    this.init=function () {
        var that = this;
        $("#startTime,#endTime").datetimepicker({
            format: 'yyyy-MM-dd',
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  true,
            autoclose: true,
            todayHighlight: true,
            startView: 2,
            minView: 2,
            forceParse: false
        });
        this.getUnitStatisticsData();
        this.getGenderStatisticsData();
        this.getInOutByTime();
        this.getStudyStatusStatisticsData();
    };
    this.getInOutByTime = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: '/admin/sys/car/statistics/getInOutByTime',
            data: {
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val()
            },
            success: function (data) {
                data = eval(data.resultText);
                var str='[';
                var names='[';
                for(var i=0;i<data.length;i++){
                    if(data[i].type=='income'){
                        data[i].type='收入'
                    }else if(data[i].type=='outcome'){
                        data[i].type="支出"
                    }
                    names +="'"+data[i].type+"'";
                    str += '{';
                    str +="value:"+data[i].money+",name:'"+data[i].type+"'"
                    str +='}';
                    if(i<data.length-1){
                        str +=',';
                        names+=',';
                    }
                }
                str +=']';
                names+=']';
                that.initInOutCharts(eval(str),eval(names));
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    };
    this.getUnitStatisticsData =function(){
        var that = this;
        $.ajax({
            type: 'POST',
            url: '/admin/sys/car/student/getStudentUnitStatistics',
            data: {},
            success: function (data) {
                var str='[';
                var names='[';
                for(var i=0;i<data.length;i++){
                    names +="'"+data[i].name+"'";
                    str += '{';
                    str +="value:"+data[i].num+",name:'"+data[i].name+"'"
                    str +='}';
                    if(i<data.length-1){
                        str +=',';
                        names+=',';
                    }
                }
                str +=']';
                names+=']';
                that.initUnitCharts(eval(str),eval(names));
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.getGenderStatisticsData =function(){
        var that = this;
        $.ajax({
            type: 'POST',
            url: '/admin/sys/car/student/getStudentGenderStatistics',
            data: {},
            success: function (data) {
                var str='[';
                var names='[';
                for(var i=0;i<data.length;i++){
                    names +="'"+(data[i].gender==1?'男':'女')+"'";
                    str += '{';
                    str +="value:"+data[i].num+",name:'"+(data[i].gender==1?'男':'女')+"'"
                    str +='}';
                    if(i<data.length-1){
                        str +=',';
                        names+=',';
                    }
                }
                str +=']';
                names+=']';
                console.log(str);
                that.initGenderCharts(eval(str),eval(names));
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.getStudyStatusStatisticsData =function(){
        var that = this;
        $.ajax({
            type: 'POST',
            url: '/admin/sys/car/statistics/getStudentStudyStatusStatistics',
            data: {},
            success: function (data) {
                var str='[';
                var names='[';
                for(var i=0;i<data.length;i++){
                    var temName = '';
                    if(data[i].studyStatus==1){
                        temName = '科目一';
                        names += "'科目一'";
                    }else if(data[i].studyStatus==2){
                        temName = '科目二';
                        names +=  "'科目二'";
                    }else if(data[i].studyStatus==3){
                        temName = '科目三';
                        names +=  "'科目三'";
                    }else if(data[i].studyStatus==4){
                        temName = '科目四';
                        names +=  "'科目四'";
                    }else{
                        temName = '已毕业';
                        names +=  "'已毕业'";
                    }
                    str += '{';
                    str +="value:"+data[i].num+",name:'"+temName+"'"
                    str +='}';
                    if(i<data.length-1){
                        str +=',';
                        names+=',';
                    }
                }
                str +=']';
                names+=']';
                console.log(names);
                that.initStadyStatusCharts(eval(str),eval(names));
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.initUnitCharts = function(str,names) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main2'), 'macarons');
        // 指定图表的配置项和数据
        var option = {
            title : {
                text: '业务区域学员统计',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: names
            },
            series : [
                {
                    name: '人数/占比',
                    type: 'pie',
                    radius: '55%',
                    data:str
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
    this.initInOutCharts =function(str,names) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main1'), 'macarons');
        // 指定图表的配置项和数据
        var option = {
            title : {
                text: '收支情况统计',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: names
            },
            series : [
                {
                    name: '收支统计',
                    type: 'pie',
                    radius: '55%',
                    data:str
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    };
    this.initGenderCharts =function(str,names) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main3'), 'macarons');
        // 指定图表的配置项和数据
        var option = {
            title : {
                text: '学员性别统计',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: names
            },
            series : [
                {
                    name: '性别统计',
                    type: 'pie',
                    radius: '55%',
                    data:str
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    };
    this.initStadyStatusCharts =function(str,names) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main4'), 'macarons');
        // 指定图表的配置项和数据
        var option = {
            title : {
                text: '学员学车状态统计',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: names
            },
            series : [
                {
                    name: '状态统计',
                    type: 'pie',
                    radius: '55%',
                    data:str
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    };
}

