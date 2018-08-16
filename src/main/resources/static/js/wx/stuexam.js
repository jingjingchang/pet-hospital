var StuExam = function () {
     this.url = {
         addExaminationAnswer:"/wx/addExaminationAnswer",
     };
    this.submitValue = function () {
        var that = this;
        var paperId = $("#paperId").val();
        var studentId = $("#studentId").val();
        var orderId = $("#orderId").val();
        var teacherId = $("#teacherId").val();

        var array = $("#view_form").serializeArray();
        var answerList = [];
        array.forEach(function(value, index, arr){
            var obj ={
                paperId:paperId,
                answerList:studentId,
                orderId:orderId,
                teacherId:teacherId,
                questionId:null,
                answer:null
            }
            obj.questionId=value.name;
            obj.answer=value.value;
            answerList.push(obj);
        });

        console.log(answerList);
        $.ajax({
            type: 'POST',
            url: that.url.addExaminationAnswer,
            data: {
                studentId:studentId,
                answerList:JSON.stringify(answerList),
            },
            success: function (data) {
                if(data.success){
                    Messager.success(data.resultText, function() {
                        location.href='/weChat/wxEvent/getOrderPage';
                    });
                }else{
                    Messager.error(data.resultText, function() {
                        location.href='/wx/getOrderPage';
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


}