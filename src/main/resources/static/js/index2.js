var Index2 = function () {
    this.url = {
        getLastMouthInOut:'/admin/sys/car/statistics/getLastMouthInOut',
        getTomorrowOrderStudent:'/admin/sys/car/statistics/getTomorrowOrderStudent',
        getStudentGenderStatistics:'/admin/sys/car/statistics/getStudentGenderStatistics',
        getMouthInOutByTime:'/admin/sys/car/statistics/getMouthInOutByTime',
    };

    this.init=function () {
        this.getStudentGenderStatistics();
        this.getLastMouthInOut();
        this.getTomorrowOrderStudent();
        this.getMouthInOutByTime();
    };
    this.getLastMouthInOut = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getLastMouthInOut,
            data: {},
            success: function (data) {
                if(data.success){
                    var obj = data.resultText;
                    var text ='';
                    for(var i=0;i<obj.length;i++){
                        if(obj[i].type=='income'){
                            $("#income").text(obj[i].thisMouth);
                            $("#lastIncome").text(obj[i].lastMouth);
                            var incomePercent;
                            if(obj[i].lastMouth==0){
                                incomePercent = obj[i].thisMouth;
                            }else{
                                incomePercent =((obj[i].thisMouth - obj[i].lastMouth)/obj[i].lastMouth).toFixed(2)*100;
                            }
                            if(incomePercent>0){
                                text = incomePercent+'%<i class="fa fa-level-up"></i>'
                            }else{
                                $("#incomePercent").addClass("text-danger");
                                text = incomePercent+'%<i class="fa fa-level-down"></i>'
                            }
                            $("#incomePercent").html(text);
                        }else{
                            $("#outcome").text(obj[i].thisMouth);
                            $("#lastOutcome").text(obj[i].lastMouth);
                            var outcomePercent;
                            if(obj[i].lastMouth==0){
                                outcomePercent = obj[i].thisMouth;
                            }else{
                                outcomePercent =((obj[i].thisMouth - obj[i].lastMouth)/obj[i].lastMouth).toFixed(2)*100;
                            }
                            if(outcomePercent>0){
                                $("#outcomePercent").addClass("text-danger");
                                text = outcomePercent+'%<i class="fa fa-level-up"></i>'
                            }else{
                                text = outcomePercent+'%<i class="fa fa-level-up"></i>'
                            }

                            $("#outcomePercent").html(text)
                        }
                    }

                }
                console.log(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    };
    this.getTomorrowOrderStudent =function(){
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getTomorrowOrderStudent,
            data: {},
            success: function (data) {
                if(data.success){
                    $("#odNum").text(data.resultText[0].num)
                    $("#odTime").text(data.resultText[0].odTime)
                }else{
                    Messager.error("获取数据失败！");
                }
                console.log(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.getStudentGenderStatistics =function(){
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getStudentGenderStatistics,
            data: {},
            success: function (data) {
                    $("#stuNum").text(data[0].num+data[1].num);
                    var text = "";
                    data.forEach(function (value, index, array) {
                        if(data[index].gender==1){
                            text = text + "男："+data[index].num+" ";
                        }else{
                            text = text + "女："+data[index].num+" ";
                        }
                    });
                    $("#genderText").html(text);

            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };


    this.getMouthInOutByTime =function() {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getMouthInOutByTime,
            data: {
                start:$("#startTime").val(),
                end:$("#endTime").val()
            },
            success: function (data) {
                data = data.resultText;
                var outcome='[';
                var income='[';
                var mouth='[';
                for(var i=0;i<data.length;i++){
                    income +="'"+data[i].income+"'";
                    outcome +="'"+data[i].outcome+"'";
                    mouth +="'"+data[i].mouth+"'";
                    if(i<data.length-1){
                        outcome +=',';
                        income+=',';
                        mouth+=',';
                    }
                }
                outcome +=']';
                income+=']';
                mouth+=']';
                initCharts1(eval(mouth),eval(income),eval(outcome));
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }

    function initCharts1(mouth,income,outcome) {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('charts1'));
        // 指定图表的配置项和数据
        option = {
            title : {
                text: '收支情况',
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['收入','支出']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    data : mouth
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'收入',
                    type:'bar',
                    data:income
                },
                {
                    name:'支出',
                    type:'bar',
                    data:outcome
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
}

