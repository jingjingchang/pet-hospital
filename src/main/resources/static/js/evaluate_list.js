var EvaluateList = function () {
    this.url = {
        listByPage:"/admin/sys/car/evaluate/listByPage/"+$("#teaId").val(),
        getEvaListByTeaId:"/admin/sys/car/evaluate/getEvaListByTeaId/"+$("#teaId").val(),
        create:"/admin/sys/car/evaluate/create",
        deleteEvaluate:"/admin/sys/car/evaluate/remove",
        updateEvaluate:"/admin/sys/car/evaluate/update",
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;

        $('#evaluate').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.getEvaListByTeaId,
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
                title: '学员姓名',
                formatter:function (value,row,index) {
                    if(value){
                        return value;
                    }else{
                        return "此乃假数据"
                    }
                }
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
                        return "-"
                    }
                }
            },{
                field: 'mobile',
                align: 'center',
                title: '电话'
            },{
                field: 'level',
                align: 'center',
                title: '学员评分',
                formatter:function (value,row,index) {
                    if(value){
                        return value.toFixed(2)
                    }else{
                        return "此人暂无评分";
                    }

                }
            },{
                field: 'brief',
                align: 'center',
                title: '评价内容'
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
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteEvaluate("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.addEvaluate = function () {
        var valid = $('#evaluateForm').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#evaluateForm").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            history.go(0);
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
    this.deleteEvaluate = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteEvaluate,
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
    this.updateEvaluate = function () {
        var valid = $('#evaluate').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateEvaluate,
            data: $("#evaluate").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        history.go(0);
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
            selector : '#evaluateForm',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                level: {
                    required : false,
                    maxlength : 15,
                },
                brief : {
                    required : true,
                    maxlength : 300,
                }
            }
        });
    };
}


