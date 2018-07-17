var ExaminationPaperList = function () {
    this.url = {
        listByPage:"/admin/sys/examinationPaper/listByPage",
        questionListByPage:"/admin/sys/examinationQuestion/listPageByPaperId/"+$("#paperId").val(),
        create:"/admin/sys/examinationPaper/create",
        deleteExaminationPaper:"/admin/sys/examinationPaper/remove",
        deletePaperQuestion:"/admin/sys/examinationQuestion/remove",
        updateExaminationPaper:"/admin/sys/examinationPaper/update",
        getQuestionsNotInPaper:"/admin/sys/question/getQuestionsNotInPaper",
        addPaperQuestion:"/admin/sys/examinationQuestion/create",
    };
    this.init = function () {
        this.validInit();

    };

    this.loadTableData = function () {
        var that = this;

        $('#examinationPaper').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.listByPage,
            pageList:[10,25,50,100,'All'],
            showExport: true,
            responseHandler:function(res) {
                $("#total").text(res.total);
                return res;
            },
            formatSearch: function () {
                return '搜索';
            },
            columns: [{
                checkbox: true
            },{
                field: 'id',
                visible: false,
                align: 'center',
                title: 'ID'
            },{
                field: 'name',
                align: 'center',
                title: '名称'
            },{
                field: 'brief',
                align: 'center',
                title: '简介'
            },{
                field: 'status',
                align: 'center',
                title: '状态',
                formatter : function (value, row, index){
                    if(value==1){
                        return "激活"
                    }else{
                        return "停用"
                    }
                }
            },{
                field: 'created',
                align: 'center',
                title: '创建时间',
                formatter:function (value,row,index) {
                   return DM.format.formatDateTime(value)
                }
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    arr.push("&nbsp;&nbsp;<a title='查看统计' href='/admin/sys/examinationPaper/addQuestions/"+row.id+"'><i class='fa fa-plus-circle text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='添加问题' href='/admin/sys/examinationPaper/queryQuestions/"+row.id+"'><i class='fa fa-plus text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/examinationPaper/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteExaminationPaper("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.loadQuestionTableData = function () {
        var that = this;

        $('#questionList').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.questionListByPage,
            pageList:[10,25,50,100,'All'],
            showExport: true,
            responseHandler:function(res) {
                $("#total").text(res.total);
                return res;
            },
            formatSearch: function () {
                return '搜索';
            },
            columns: [{
                checkbox: true
            },{
                field: 'id',
                visible: false,
                align: 'center',
                title: 'ID'
            },{
                field: 'questionName',
                align: 'center',
                title: '名称'
            },{
                field: 'brief',
                align: 'center',
                title: '简介'
            },{
                field: 'status',
                align: 'center',
                title: '状态',
                formatter : function (value, row, index){
                    if(value==1){
                        return "激活"
                    }else{
                        return "停用"
                    }
                }
            },{
                field: 'created',
                align: 'center',
                title: '创建时间',
                formatter:function (value,row,index) {
                   return DM.format.formatDateTime(value)
                }
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deletePaperQuestion("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#examinationPaper').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#examinationPaper").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/examinationPaper/list';
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    };
    this.deleteExaminationPaper = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteExaminationPaper,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/examinationPaper/list';
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.deletePaperQuestion = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deletePaperQuestion,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/examinationPaper/list';
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    };
    this.updateExaminationPaper = function () {
        var valid = $('#examinationPaper').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateExaminationPaper,
            data: $("#examinationPaper").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/examinationPaper/list';
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    };
    this.validInit  = function () {
        WX.validate.init({
            selector : '#examinationPaper',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                }
            }
        });
    };
    this.selectAfter= function () {
        $("#questionList").bootstrapTable('refresh');
    };
    this.getQuestionsNotInPaper = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getQuestionsNotInPaper,
            data: {
                paperId:id
            },
            success: function (data) {
                console.log(data.resultText[0]);
                data.resultText.forEach(function(value,index,array){
                    var str = '<option value="'+value.id+'">'+value.name+'</option>'
                    $("#questions").append(str);
                });
                if(data.success){
                       that.selectAfter();
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    };

    this.addPaperQuestion = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.addPaperQuestion,
            data: {
                paperId:$("#paperId").val(),
                questionId: $("#questions").val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！");
                    that.selectAfter();
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    }


}

