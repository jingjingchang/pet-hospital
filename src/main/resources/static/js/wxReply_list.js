var WxReplyList = function () {
    this.url = {
        listByPage:"/admin/weChat/wxReply/listByPage",
        create:"/admin/weChat/wxReply/create",
        deleteWxReply:"/admin/weChat/wxReply/remove",
        updateWxReply:"/admin/weChat/wxReply/update",
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;

        $('#wxReply_table').bootstrapTable({
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
                field: 'keyName',
                align: 'center',
                title: '关键字'
            },{
                field: 'keyType',
                align: 'center',
                title: '回复类型',
                formatter : function (value, row, index){
                    if(value=='text'){
                        return "文本"
                    }else{
                        return "图文"
                    }
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/weChat/wxReply/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteWxReply("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#wxReply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#wxReply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/weChat/wxReply/list';
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
    this.deleteWxReply = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteWxReply,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/weChat/wxReply/list';
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
    this.updateWxReply = function () {
        var valid = $('#wxReply').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateWxReply,
            data: $("#wxReply").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/weChat/wxReply/list';
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
            selector : '#wxReply',
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


