var WxGraphicList = function () {
    this.url = {
        listByPage:"/admin/weChat/wxGraphic/listByPage",
        create:"/admin/weChat/wxGraphic/create",
        deleteWxGraphic:"/admin/weChat/wxGraphic/remove",
        updateWxGraphic:"/admin/weChat/wxGraphic/update",
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
            path: '/dm/graphic/',
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
    this.loadTableData = function () {
        var that = this;
        $('#wxGraphic_table').bootstrapTable({
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
                field: 'brief',
                align: 'center',
                title: '简介'
            },{
                field: 'readNum',
                align: 'center',
                title: '阅读次数'
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
                    arr.push("&nbsp;&nbsp;<a title='查看' href='/wx/news/"+row.id+"'><i class='fa fa-search text-info'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/weChat/wxGraphic/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteWxGraphic("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.initPic = function () {
        var url = $("#picUrl").val();
        var a = url.split(",");
        for(var i = 0 ;i<a.length;i++){
            var str =  DM.createResourcePicDiv(a[i]);
            $("#pic").append(str);
        }
    }
    this.save = function () {
        var valid = $('#wxGraphic').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#wxGraphic").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/weChat/wxGraphic/list';
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
    this.deleteWxGraphic = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteWxGraphic,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/weChat/wxGraphic/list';
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
    this.updateWxGraphic= function () {
        var valid = $('#wxGraphic').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.updateWxGraphic,
            data: $("#wxGraphic").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/weChat/wxGraphic/list';
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
            selector : '#wxGraphic',
            rules : {
                title : {
                    required : true,
                    maxlength : 30
                },
                brief : {
                    required : true,
                    maxlength : 200
                },
                url : {
                    required : false,
                    url:true
                }
            }
        });
    };
}


