var UnitList = function () {
    this.url = {
        listByPage:"/admin/sys/unit/listByPage",
        create:"/admin/sys/unit/create",
        deleteUnit:"/admin/sys/unit/remove",
        updateUnit:"/admin/sys/unit/update",
        getTeacherListByUnitId:"/admin/sys/car/unitTeacher/getTeacherListByUnitId",
        addTeacherList:"/admin/sys/car/unitTeacher/create",
        removeTeacherById:"/admin/sys/car/unitTeacher/remove",
        getTeacherListWithOutByUnitId:"/admin/sys/car/teacher/getTeacherListWithOutByUnitId"
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;
        $('#unit').bootstrapTable({
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
                field: 'code',
                align: 'center',
                title: '编码',
            },{
                field: 'address',
                align: 'center',
                title: '地址'
            },{
                field: 'pickup',
                align: 'center',
                title: '接送点',
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
                    arr.push("&nbsp;&nbsp;<a title='预约时间设置' href='/admin/sys/car/order/getOrderListPageByUnitId/"+row.id+"'><i class='fa fa-search text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/unit/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteUnit("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#unit').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#unit").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/unit/list';
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
    this.deleteUnit = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteUnit,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/unit/list';
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
    this.updateUnit = function () {
        var valid = $('#unit').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateUnit,
            data: $("#unit").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/unit/list';
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
            selector : '#unit',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                code : {
                    required : false,
                    maxlength : 15,
                },
                address : {
                    required : true,
                    maxlength : 100,
                },
                pickup : {
                    required : true,
                    maxlength : 30
                }
            }
        });
    };

    this.getTeacherListByUnitId = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getTeacherListByUnitId,
            data: {
                unitId:$("#id").val()
            },
            success: function (data) {
                $("#teacher_list").empty();
                if(data.length<1){
                    var text = "<tr ><th>此区域暂无师傅负责</th></tr>"
                    $("#teacher_list").append(text);
                }else{
                    for(var i =0;i<data.length;i++){
                        var text = "<tr><th>"+data[i].name+"</th><th>"+data[i].mobile+"</th>" +
                            "<th>\n" +
                            "<a title='删除' href='javascript:void(0)' onclick=\"removeTeacherById("+data[i].id+")\" ><i class='fa fa-trash-o text-warning'></i></a>\n" +
                            "</th></tr>";
                        $("#teacher_list").append(text);
                    }
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
    this.addTeacher = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.addTeacherList,
            data: {
                teacherId:$("#teachers").val(),
                unitId:$("#id").val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        that.getTeacherListByUnitId();
                        $("#cancel").click();
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
    this.removeTeacherById = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.removeTeacherById,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        unit.getTeacherListByUnitId();
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
    this.getTeacherListWithOutByUnitId = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getTeacherListWithOutByUnitId,
            data: {unitId:$("#id").val()},
            success: function (data) {
                var str = '';
                $("#teachers").empty();
                for(var i=0;i<=data.length;i++){
                    console.log(data[i].id);
                    str ="<option value='"+data[i].id+"'>"+data[i].name+" "+data[i].mobile+"</option>"
                    $("#teachers").append(str);
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }
    this.showTeacherModal = function () {
        this.getTeacherListWithOutByUnitId();
        $('#modalForm').modal('show');
    };
}

