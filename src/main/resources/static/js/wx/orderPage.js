var OrderPage = function () {
     this.url = {
         addOrderStudent:"/wx/addOrderStudent/create",
         deleteOrderStudent:"/wx/deleteOrderStudent/delete",
     }
     this.compareTime = function(s1, s2) {
        var reDate = /\d{4}-\d{1,2}-\d{1,2} /;
        s1 = new Date((reDate.test(s1) ? s1 : '2017-1-1 ' + s1).replace(/-/g, '/'));
        s2 = new Date((reDate.test(s2) ? s2 : '2017-1-1 ' + s2).replace(/-/g, '/'));
        var ms = s2.getTime() > s1.getTime();

        return  ms?"1":0;
     }
     this.turnToTeacherPage =function(id,leftNum) {
         var date = new Date();
         var curTime = date.getHours()+":"+date.getMinutes();
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        var a = this.compareTime(startTime,curTime);
        var b = this.compareTime(curTime,endTime);
        if(a&&b){
            //window.location.href="/weChat/wxEvent/getAllTeachers/"+id;
            if(leftNum<=0){
                Messager.error("对不起，此时间断已预约满，请选择其它时间！");
                return;
            }else{
                window.location.href="/weChat/wxEvent/getAllTeachers/"+id;
            }
        }else{
            Messager.error("对不起，还未到预约时间！");
        }
    }
    
    this.addOrderStudent = function (teachId,odId,stuId,subType) {
         var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.addOrderStudent,
            data: {
                odId:odId,
                stuId:stuId,
                teachId:teachId,
                status:1,
            },
            success: function (data) {
                if(data.success){
                    Messager.success("提交成功！若安排到您明天学车您将会收到短信通知,未收到短信通知则暂未安排您学车！", function() {
                        location.href='/wx/getPartTimeJobList';
                    });
                }else{
                    Messager.confirm(data.resultText,function () {
                        that.deleteOrderStudent(data.resultId,teachId,odId,stuId,subType)
                    });
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    }

    this.deleteOrderStudent = function (id,teachId,odId,stuId,subType) {
         var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteOrderStudent,
            data: {
                id:id
            },
            success: function (data) {
                if(data.success){
                    that.addOrderStudent(teachId,odId,stuId,subType);
                }else{
                    Messager.error(data.resultText,function () {
                        location.href='/wx/getPartTimeJobList';
                    });
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    }

    this.initStarScore = function (list) {
        for(var i=0;i<list.length;i++){
            if(!list[i].aLevel){
                list[i].aLevel=5;
            }
            $('#'+list[i].id).markingSystem({
                num: 5,
                havePoint: true,
                haveGrade: false,
                grade: list[i].aLevel,
                height: 15,
                width: 15,
            })
        }
    }

}