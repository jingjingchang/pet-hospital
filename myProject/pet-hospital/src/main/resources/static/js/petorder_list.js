var PetOrderList = function () {
    this.url = {
        listByPage:"/petorder/listMyPage",
        create:"/petorder/create",
        delete:"/petorder/delete",
        update:"/petorder/update",
    };
    this.init = function () {
        this.validInit();
        $("#orderTime").datetimepicker({
            format: 'yyyy-MM-dd hh:mm',
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  true,
            autoclose: true,
            todayHighlight: true,
            startView: 2,
            minView: 1,
            forceParse: false
        });
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
                field: 'petName',
                align: 'center',
                title: '宠物'
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
                        arr.push("&nbsp;&nbsp;<a title='编辑' href='/petorder/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                        arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    }
                    return arr;
                }
            }]
        });
    };

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
                            location.href='/petorder/mylist';
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
            selector : '#petorder',
            rules : {
                petId : {
                    required : true,
                },
                typeId : {
                    required : true,
                },
                orderTime : {
                    required : true,
                }
            }
        });
    }
}

