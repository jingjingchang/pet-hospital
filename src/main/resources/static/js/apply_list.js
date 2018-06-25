var ApplyList = function () {
    var zNodes ="";
    this.url = {
        listByPage:"/admin/sys/car/apply/listByPage",
        create:"/admin/sys/car/apply/create",
        deleteApply:"/admin/sys/car/apply/remove",
        updateApply:"/admin/sys/car/apply/update",
    };
    this.init = function () {
        this.validInit();
        $("#enrollTime,#testTime").datetimepicker({
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

        $('#apply').bootstrapTable({
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
                title: '姓名',
                formatter : function (value, row, index){
                    if(value){
                        return value
                    }else{
                        return "未填写"
                    }
                }
            },{
                field: 'mId',
                align: 'center',
                title: '报名方式',
                formatter : function (value, row, index){
                    if(value){
                        return "微信报名"
                    }else{
                        return "官网报名"
                    }
                }
            },{
                field: 'gender',
                align: 'center',
                title: '性别',
                formatter : function (value, row, index){
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
                title: '电话',
                formatter : function (value, row, index){
                    if(value){
                        return value
                    }else {
                        return "未填写"
                    }
                }
            },{
                field: 'address',
                align: 'center',
                title: '所属区域'
            },{
                field: 'applyAsk',
                align: 'center',
                title: '学车要求',
                formatter : function (value, row, index){
                    if(value){
                        return value
                    }else{
                        return "未填写"
                    }
                }
            },{
                field: 'applyDate',
                align: 'center',
                title: '报名时间',
                formatter: function (value,row,index) {
                    return DM.format.formatDateTime(value,row,index);
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/car/apply/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteApply("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#apply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#apply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/car/apply/list';
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
    this.deleteApply = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteApply,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/apply/list';
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
    this.updateApply = function () {
        var valid = $('#apply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateApply,
            data: $("#apply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/apply/list';
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
            selector : '#apply',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                qq : {
                    required : false,
                    maxlength : 15,
                    number:true
                },
                mobile : {
                    required : true,
                    maxlength : 30,
                    number:true
                },
                email : {
                    required : true,
                    maxlength : 30
                },
                identityNo : {
                    required : true,
                    maxlength : 20
                },
                tuition : {
                    required : true,
                    maxlength : 30
                },
                enrollTime : {
                    required : true,
                    maxlength : 30
                }
            }
        });
    };
}

function onCheck(e, treeId, treeNode) {
}


