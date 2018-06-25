var Evaluate = {
    addEvaluate:function () {
        $.ajax({
            type: 'POST',
            url: '/wx/addEvaluate',
            data:$("#evaluateForm").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.resultText,function () {
                        location.reload();
                    });
                }else{
                    Messager.error(data.resultText,function () {
                        location.reload();
                    });
                }

            }
        });
    },
    initStarScore :function (list) {
        for(var i=0;i<list.length;i++){
            if(!list[i].level){
                list[i].level=5;
            }
            $('#star_'+list[i].id).markingSystem({
                num: 5,
                havePoint: true,
                haveGrade: false,
                grade: list[i].level,
                height: 15,
                width: 15,
            })
        }
    }
}