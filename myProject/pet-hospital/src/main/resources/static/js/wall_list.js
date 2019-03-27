var WallList = function () {
    this.url = {
        listByPage:"/wall/listByPage",
        create:"/wall/create",
        delete:"/wall/delete",
        update:"/wall/update",
    };
    this.init = function () {
        this.validInit();
    };

    this.loadTableData = function () {
        var that = this;

        $('#wall').bootstrapTable({
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
                title: '评论者'
            },{
                field: 'content',
                align: 'center',
                title: '内容'
            },{
                field: 'created',
                align: 'center',
                title: '评论时间'
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
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };

    this.save = function () {
        var valid = $('#wall').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#wall").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/wall/list';
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
                        location.href='/wall/list';
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
    this.publish = function(){
        var that = this;
        var content = $("#content").val();
        if(!content){
            Messager.error("内容不能为空");
            return;
        }
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: {content:content,status:1},
            success: function (data) {
                if(data.success){
                    Messager.success("留言成功！", function() {
                        location.href='/wall/view';
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
    }
    this.update = function () {
        var valid = $('#wall').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#wall").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                        location.href='/wall/list';
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
            selector : '#wall',
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

