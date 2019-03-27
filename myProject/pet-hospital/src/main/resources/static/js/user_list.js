var UserList = function() {

    this.url = {
        listByPage:"/user/listByPage",
        create:"/user/create",
        delete:"/user/remove",
        update:"/user/update",
        updateMyInfo:"/user/updateMyInfo",
    };

    this.init = function () {
        this.validInit();
        this.initUpload();
        this.initPic();
    };
    this.initUpload = function () {
        // 上传
        // 初始化Web Uploader
        var uploader = WebUploader.create({
            // 选完文件后，是否自动上传。
            auto: true,
            // swf文件路径
            swf:  '/js/Uploader.swf',
            // 文件接收服务端。
            server: '/upload/fileUpload',
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#uploadBtn',
            // 只允许选择图片文件。
            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });
        uploader.on( 'uploadSuccess', function( file,data ) {
            $("#avatar").val(data.data);
            var str =  DM.createResourcePicDiv(data.data);
            $("#pic").html(str);
        });

    };
    this.initPic = function () {
        var url = $("#avatar").val();
        var a = url.split(",");
        for(var i = 0 ;i<a.length;i++){
            var str =  DM.createResourcePicDiv(a[i]);
            $("#pic").append(str);
        }
    };
    this.validInit  = function () {
        WX.validate.init({
            selector : '#user',
            rules : {
                username : {
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
                },
                address:{
                    required : true,
                }

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
    this.update = function () {
        var that = this;
        var valid = $('#user').valid();
        if (!valid) {
            return false;
        }
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#user").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success("操作成功！", function() {
                        location.reload()
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

    this.updateMyInfo = function () {
        var that = this;
        var valid = $('#user').valid();
        if (!valid) {
            return false;
        }
        $.ajax({
            type: 'POST',
            url: that.url.updateMyInfo,
            data: $("#user").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success("操作成功！", function() {
                        history.go(0)
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
    this.deleteObj = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.delete,
            data: {id:id},
            success: function (data) {
                if(data.success){
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
            toorbar:'#user_bar',
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
                field: 'username',
                align: 'center',
                title: '帐号'
            },{
                field: 'name',
                align: 'center',
                title: '昵称'
            },{
                field: 'mobile',
                align: 'center',
                title: '手机号'

            },{
                field: 'email',
                align: 'center',
                title: '邮箱'
            },{
                field: 'type',
                align: 'center',
                title: '用户类型',
                formatter : function (value, row, index){
                    if(value==1){
                        return "普通用户"
                    }else{
                        return "管理员"
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/user/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    }
}