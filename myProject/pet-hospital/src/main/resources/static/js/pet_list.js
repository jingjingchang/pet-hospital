var PetList = function () {
    this.url = {
        listByPage:"/pet/listMyPage",
        create:"/pet/create",
        delete:"/pet/delete",
        update:"/pet/update",
    };
    this.init = function () {
        this.validInit();

    };

    this.loadTableData = function () {
        var that = this;

        $('#pet').bootstrapTable({
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
                field: 'breed',
                align: 'center',
                title: '血统'
            },{
                field: 'sex',
                align: 'center',
                title: '性别',
                formatter:function (value) {
                    return value==1?"雄":"雌";
                }
            },{
                field: 'married',
                align: 'center',
                title: '是否婚配',
                formatter:function (value) {
                    return value?"是":"否";
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/pet/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };

    this.save = function () {
        var valid = $('#pet').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#pet").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success("操作成功！", function() {
                            location.href='/pet/mylist';
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
                        location.href='/pet/mylist';
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
        var valid = $('#pet').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#pet").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                        location.href='/pet/mylist';
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
                breed : {
                    required : true,
                    maxlength : 15,
                }
            }
        });
    }
}

