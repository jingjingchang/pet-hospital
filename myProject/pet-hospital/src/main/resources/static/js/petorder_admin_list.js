var PetOrderAdminList = function () {
    this.url = {
        listByPage:"/petorder/listByPage",
        create:"/petorder/create",
        delete:"/petorder/delete",
        update:"/petorder/update",
    };
    this.init = function () {
        this.validInit();
    };

    this.loadTableData = function () {
        var that = this;

        $('#petorder').bootstrapTable({
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
                field: 'userName',
                align: 'center',
                title: '用户名'
            },{
                field: 'mobile',
                align: 'center',
                title: '联系电话'
            },{
                field: 'petName',
                align: 'center',
                title: '宠物名'
            },{
                field: 'orderTime',
                align: 'center',
                title: '预约时间',
                formatter:function (value) {
                    return value;
                }
            },{
                field: 'typeName',
                align: 'center',
                title: '预约类型'
            },{
                field: 'remark',
                align: 'center',
                title: '备注'
            },{
                field: 'status',
                align: 'center',
                title: '状态',
                formatter : function (value, row, index){
                    if(value==1){
                        return "已确认"
                    }else{
                        return "待确认"
                    }
                }
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    if(row.status==0){
                        arr.push("&nbsp;&nbsp;<a title='确认' href='javascript:void(0)' onclick='confirmOrder("+row.id+")'><i class='fa fa-check-square-o text-success'></i></a>");
                        arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    }else{
                        arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    }
                    return arr;
                }
            }]
        });
    };

    this.confirmOrder =function(id){
        var that = this;
            $.ajax({
                type: 'POST',
                url: that.url.update,
                data: {id:id,status:1},
                success: function (data) {
                    if(data.success){
                        Messager.success(data.message, function() {
                            location.href='/petorder/list';
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
    }

    this.save = function () {
        var valid = $('#petorder').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#petorder").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                            location.href='/petorder/list';
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
    this.deleteObj = function (id) {
        var that = this;
        $.ajax({
            type: 'GET',
            url: that.url.delete+"/"+id,
            data: null,
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                        location.href='/petorder/list';
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
    this.update = function () {
        var valid = $('#petorder').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#petorder").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                        location.href='/pet/list';
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
            selector : '#pet',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                code : {
                    required : true,
                    maxlength : 30
                }
            }
        });
    }
}

