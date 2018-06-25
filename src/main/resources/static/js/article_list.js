var articleList = function () {
    var zNodes ="";
    this.url = {
        listByPage:"/admin/blog/article/listByPage",
        create:"/admin/blog/article/create",
        deleteArticle:"/admin/blog/article/remove",
        updateArticle:"/admin/blog/article/update",
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;

        $('#article').bootstrapTable({
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
                field: 'title',
                align: 'center',
                title: '标题'
            },{
                field: 'categoryName',
                align: 'center',
                title: '所属分类'
            },{
                field: 'readNum',
                align: 'center',
                title: '阅读次数'
            },{
                field: 'likes',
                align: 'center',
                title: '点赞次数'
            },{
                field: 'status',
                align: 'center',
                title: '状态',
                formatter : function (value, row, index){
                    if(value==0){
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/role/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteRole("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.saveRoleMenus = function(){
        var that = this;
        var ids = "";
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        var sNodes = treeObj.getCheckedNodes(true);
        for(var i = 0;i<sNodes.length;i++){
                if(!sNodes[i].isParent){
                   ids+=sNodes[i].id;
                    if(i<sNodes.length-1){
                        ids+=","
                    }
                }
        }
        $.ajax({
            type: 'POST',
            url: that.url.addRoleMenus,
            data: {
                id: $("#roleId").val(),
                ids:ids
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/role/list';
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
    this.save = function () {
        var valid = $('#role').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#role").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/role/list';
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
    this.deleteRole = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteRole,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/role/list';
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
    this.updateRole = function () {
        var valid = $('#role').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateRole,
            data: $("#role").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/role/list';
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
            selector : '#role',
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

function onCheck(e, treeId, treeNode) {
}


