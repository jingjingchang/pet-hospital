var AssetsList = function () {
    this.url = {
        listByPage:"/admin/sys/car/assets/listByPage",
        create:"/admin/sys/car/assets/create",
        deleteAssets:"/admin/sys/car/assets/remove",
        updateAssets:"/admin/sys/car/assets/update",
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;

        $('#assets').bootstrapTable({
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
                field: 'pname',
                align: 'center',
                title: '所属分类',
            },{
                field: 'price',
                align: 'center',
                title: '价值'
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/car/assets/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteAssets("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#assets').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#assets").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/car/assets/list';
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
    this.deleteAssets = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteAssets,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/assets/list';
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
    this.updateAssets = function () {
        var valid = $('#assets').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateAssets,
            data: $("#assets").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/assets/list';
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
            selector : '#assets',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                price : {
                    required : true,
                    maxlength : 15,
                    number:true
                },
                brief : {
                    required : false,
                    maxlength : 200
                }
            }
        });
    }
}


