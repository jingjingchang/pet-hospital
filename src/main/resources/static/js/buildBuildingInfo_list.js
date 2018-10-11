var PartTimeJobList = function () {
    this.url = {
        listByPage:"/admin/sys/partTimeJob/listByPage",
        create:"/admin/sys/partTimeJob/create",
        deletePartTimeJob:"/admin/sys/partTimeJob/remove",
        updatePartTimeJob:"/admin/sys/partTimeJob/update",
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

        $('#partTimeJob').bootstrapTable({
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
                field: 'startTime',
                align: 'center',
                title: '开始时间',
            },{
                field: 'endTime',
                align: 'center',
                title: '结束时间'
            },{
                field: 'maxNum',
                align: 'center',
                title: '需求人数'
            },{
                field: 'salary',
                align: 'center',
                title: '工资'
            },{
                field: 'applyNum',
                align: 'center',
                title: '报名人数'
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
                    arr.push("&nbsp;&nbsp;<a title='查看兼职报名人员' href='/admin/sys/partTimeJobApply/getApplyListPage/"+row.id+"'><i class='fa fa-search text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/partTimeJob/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deletePartTimeJob("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#partTimeJob').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#partTimeJob").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/partTimeJob/list';
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
    this.deletePartTimeJob = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deletePartTimeJob,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/partTimeJob/list';
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
    }
    this.updatePartTimeJob = function () {
        var valid = $('#partTimeJob').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.updatePartTimeJob,
            data: $("#partTimeJob").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/partTimeJob/list';
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
            selector : '#partTimeJob',
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


