var DoctorList = function () {
    this.url = {
        listByPage:"/doctor/listByPage",
        create:"/doctor/create",
        delete:"/doctor/delete",
        update:"/doctor/update",
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
    this.loadTableData = function () {
        var that = this;
        $('#doctor').bootstrapTable({
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
                title: '姓名'
            },{
                field: 'sex',
                align: 'center',
                title: '性别',
                formatter : function (value, row, index){
                    if(value==0){
                        return "女"
                    }else{
                        return "男"
                    }
                }
            },{
                field: 'mobile',
                align: 'center',
                title: '电话'
            },{
                field: 'age',
                align: 'center',
                title: '年龄'
            },{
                field: 'wordTime',
                align: 'center',
                title: '工作时间'
            },{
                field: 'title',
                align: 'center',
                title: '职称'
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/doctor/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#doctor').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#brief").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#doctor").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success(data.message, function() {
                            location.href='/doctor/list';
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
                        location.href='/doctor/list';
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
        var valid = $('#doctor').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#brief").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#doctor").serialize(),
            success: function (data) {
                if(data.success){
                    Messager.success("操作成功！", function() {
                        location.href='/doctor/list';
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
            selector : '#doctor',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                title : {
                    required : true,
                    maxlength : 30
                },
                age : {
                    required : true,
                    number : true
                },
                workTime : {
                    required : true
                }
            }
        });
    }
}


