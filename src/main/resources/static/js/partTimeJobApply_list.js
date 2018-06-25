var PartTimeJobApplyList = function () {
    this.url = {
        listByPage:"/admin/sys/partTimeJobApply/listByPage/"+$("#ptId").val(),
        getJobApplyListByJobId:"/admin/sys/partTimeJobApply/getJobApplyListByJobId/"+$("#ptId").val(),
        create:"/admin/sys/partTimeJobApply/create",
        deletePartTimeJobApply:"/admin/sys/partTimeJobApply/remove",
        updatePartTimeJobApply:"/admin/sys/partTimeJobApply/update",
        scoreStu:"/admin/sys/partTimeJobApply/scoreStu"
    };
    this.init = function () {
        this.validInit();
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

    };
    this.loadTableData = function () {
        var that = this;

        $('#partTimeJobApply').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.getJobApplyListByJobId,
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
                title: '姓名'
            },{
                field: 'gender',
                align: 'center',
                title: '性别',
                formatter:function (value,row,index) {
                    if(value==1){
                        return "男"
                    }else if(value==0){
                        return "女"
                    }else{
                        return "其它"
                    }
                }
            },{
                field: 'mobile',
                align: 'center',
                title: '电话'
            },{
                field: 'reason',
                align: 'center',
                title: '描述'
            },{
                field: 'ascore',
                align: 'center',
                title: '综合得分',
                formatter:function (value,row,index) {
                    if(value){
                        return value.toFixed(2)
                    }else{
                        return "此人暂无评分";
                    }

                }
            },{
                field: 'score',
                align: 'center',
                title: '本次兼职评分',
                formatter:function (value,row,index) {
                    return value?value:"本次兼职暂未评分"
                }
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
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='javascript:void(0)' onclick='show("+row.id+")'><i class='fa fa-hand-o-right text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deletePartTimeJobApply("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#partTimeJobApply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#partTimeJobApply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/partTimeJobApply/list';
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
    this.deletePartTimeJobApply = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deletePartTimeJobApply,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.reload();
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
    this.scoreStu = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.scoreStu,
            data: {
                id:$("#osId").val(),
                score:$("#score").val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success('"'+data.resultText+'"', function() {
                        $('#modalForm').modal('hide');
                        location.reload();
                    });
                }else{
                    $('#modalForm').modal('hide');
                    Messager.success('"'+data.resultText+'"',function () {
                        location.reload();
                    });

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
    this.updatePartTimeJob = function () {
        var valid = $('#partTimeJobApply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.updatePartTimeJob,
            data: $("#partTimeJobApply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/partTimeJobApply/list';
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
            selector : '#partTimeJobApply',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                 salary: {
                    required : false,
                    maxlength : 15,
                },
                brief : {
                    required : true,
                    maxlength : 300,
                },
                startTime : {
                    required : true,
                    maxlength : 30
                },
                endTime : {
                    required : true,
                    maxlength : 20
                }
            }
        });
    };
}


