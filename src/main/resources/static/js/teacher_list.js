var TeacherList = function () {
    this.url = {
        listByPage:"/admin/sys/car/teacher/listByPage",
        create:"/admin/sys/car/teacher/create",
        deleteTeacher:"/admin/sys/car/teacher/remove",
        updateTeacher:"/admin/sys/car/teacher/update",
        setPwd:"/admin/sys/car/teacher/setPwd",
    };
    this.init = function () {
        this.validInit();
        this.initUpload();
        this.initPic();
    };
    this.initUpload = function () {
        // 上传
        $('#uploadBtn').webuploader({
            modalTitle: '上传【图片】',
            path: '/dm/teacher/',
            fileNumLimit: 10,
            resourceId: 1,
            autoClose: true,
            afterSendFileFn: function(data) {
            },
            uploadSuccess:function (file,data) {
                /*  if( $("#picUrl").val()==""|| $("#picUrl").val()==null) {*/
                $("#picUrl").val(data.resultText);
                var str =  DM.createResourcePicDiv(data.resultText);
                $("#pic").html(str);
                /* }else{
                     $("#picUrl").val( $("#picUrl").val()+","+data.resultText);
                 }*/
            },
            fileSingleSizeLimit: 20*1024*1024,
            acceptType: 'Images'
        });

    }
    this.initPic = function () {
        var url = $("#picUrl").val();
        var a = url.split(",");
        for(var i = 0 ;i<a.length;i++){
            var str =  DM.createResourcePicDiv(a[i]);
            $("#pic").append(str);
        }
    }
    this.loadTableData = function () {
        var that = this;

        $('#teacher').bootstrapTable({
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
                field: 'gender',
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
                field: 'number',
                align: 'center',
                title: '编号'
            },{
                field: 'uname',
                align: 'center',
                title: '负责区域',
                formatter : function (value, row, index){
                    if(value){
                        return value;
                    }else{
                        return "未设置";
                    }
                }
            },{
                field: 'subTwo',
                align: 'center',
                title: '是否负责科目二',
                formatter:function (value) {
                    if(value==1){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'subThree',
                align: 'center',
                title: '是否负责科目三',
                formatter:function (value) {
                    if(value==1){
                        return "是"
                    }else{
                        return "否"
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
                    arr.push("&nbsp;&nbsp;<a title='设置登录密码' href='javascript:void(0)' onclick='show("+row.id+")'><i class='fa fa-wrench text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='查看评价' href='/admin/sys/car/evaluate/list/"+row.id+"' ><i class='fa fa-comments text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/car/teacher/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteTeacher("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#teacher').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#teacher").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/car/teacher/list';
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
    this.deleteTeacher = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteTeacher,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/teacher/list';
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
    this.setPwd = function () {
        var that = this;
        var pwd = $.trim($("#pwd").val());
        var rePwd = $.trim($("#rePwd").val());
        if(pwd==''||rePwd==''){
            Messager.error("密码不能为空！");
            return;
        }
        if(pwd!=rePwd){
            Messager.error("两次密码不一致！");
            return;
        }
        $.ajax({
            type: 'POST',
            url: that.url.setPwd,
            data: {
                tId:$("#tId").val(),
                pwd:sha256_digest(pwd)
            },
            success: function (data) {
                if(data.success){
                    Messager.success(data.resultText, function() {
                        $('#modalForm').modal('hide');
                        history.go(0);
                    });
                }else{
                    $('#modalForm').modal('hide');
                    Messager.alert(data.resultText);
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
    this.updateTeacher = function () {
        var valid = $('#teacher').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateTeacher,
            data: $("#teacher").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/teacher/list';
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
            selector : '#teacher',
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
                    required : false,
                    maxlength : 20
                },
                number : {
                    required : false,
                    maxlength : 20
                },
                title : {
                    required : false,
                    maxlength : 20
                }
            }
        });
    }
}


