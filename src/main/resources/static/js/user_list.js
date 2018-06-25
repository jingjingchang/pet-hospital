var UserList = function() {

    this.url = {
        listByPage:"/admin/sys/user/listByPage",
        create:"/admin/sys/user/addUser",
        getAllRoles:"/admin/sys/role/getAllRoles",
        saveUserRole:"/admin/sys/user/saveUserRole",
        deleteUser:"/admin/sys/user/remove",
        updateUser:"/admin/sys/user/update",
    };

    this.init = function () {
        this.loadTableData();
    };
    this.validInit  = function () {
        WX.validate.init({
            selector : '#user',
            rules : {
                loginName : {
                    required : true,
                    maxlength : 15
                },
                name : {
                    required : true,
                    maxlength : 15
                },
                password : {
                    required : true,
                    maxlength : 20
                },
                compwd:{
                    required : true,
                    equalTo :"password"
                },
                mobile:{
                    required : true,
                    maxlength : 11
                },
                email:{
                    required : true,
                    maxlength : 20,
                    email : true
                }

            }
        });
    }
    this.getRoles = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getAllRoles,
            data: {
            },
            success: function (data) {
                $("#selectId").html("");
                console.log(data);
                var str = "";
                for(var i=0;i<data.length;i++){
                    if(id==data[i].id){
                        str +="<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>";
                    }else{
                        str +="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
                    }
                }
                $("#selectId").html(str);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    }

    this.saveUserRole = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.saveUserRole,
            data: {
                uId:$("#userId").val(),
                rId:$("#selectId").find("option:selected").eq(0).val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/user/list';
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
        var valid = $('#user').valid();
        if (!valid) {
            return false;
        }
        var that = this;

        var tmpPwd = $("#compwd").val();
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#user").serialize()+"&password="+ sha256_digest(tmpPwd),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/user/list';
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
    this.updateUser = function () {
        var that = this;
        var valid = $('#user').valid();
        if (!valid) {
            return false;
        }
        $.ajax({
            type: 'POST',
            url: that.url.updateUser,
            data: $("#user").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/user/list';
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
    this.deleteUser = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteUser,
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
                Messager.alert("存在数据关联无法删除！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }
    this.loadTableData = function () {
        var that = this;

        $('#user_table').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#conf_manage_bar',
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
                field: 'loginName',
                align: 'center',
                title: '帐号'
            },{
                field: 'name',
                align: 'center',
                title: '用户名'
            },{
                field: 'mobile',
                align: 'center',
                title: '手机号'

            },{
                field: 'email',
                align: 'center',
                title: '邮箱'
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
                    arr.push("<a title='查看' href='javascript:void(0)' onclick='showArea("+row.id+","+row.roleId+")'><i class='fa fa-group text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/user/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteUser("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    }
}