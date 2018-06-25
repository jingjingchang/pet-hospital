var UnitOrderList = function () {
    this.url = {
        listByPage:"/admin/sys/car/order/getOrderListByUnitId/"+$("#unitId").val(),
        create:"/admin/sys/car/order/create",
        deleteUnitOrder:"/admin/sys/car/order/remove",
        updateUnitOrder:"/admin/sys/car/order/update",
        getTimeListByOrderId:"/admin/sys/car/orderDetail/getTimeListByOrderId",
        removeTimeById:"/admin/sys/car/orderDetail/remove",
        addTime:"/admin/sys/car/orderDetail/create",
        updateOrderTime:"/admin/sys/car/orderTime/update",
        updateOrderDetail:"/admin/sys/car/orderDetail/update"
    };
    this.init = function () {
        this.validInit();
    };
    this.loadTableData = function () {
        var that = this;
        $('#unitOrder').bootstrapTable({
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/car/order/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteUnitOrder("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#unitOrder').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#unitOrder").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/car/order/newList';
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
    this.deleteUnitOrder = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteUnitOrder,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        history.go(0);
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("此数据因有数据与之关联所以无法删除，只能修改！！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }
    this.updateOrder = function () {
        var valid = $('#orderUnit').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateOrder,
            data: $("#orderUnit").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/order/newList';
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
            selector : '#orderUnit',
            rules : {
                name : {
                    required : true,
                    maxlength : 30
                },
                money : {
                    required : true,
                    maxlength : 15,
                    number:true
                },
                madeTime : {
                    required : true,
                },
                brief : {
                    required : false,
                    maxlength : 200
                }
            }
        });
    }
    this.updateOrderTime = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateOrderTime,
            data: {
                id:$("#orderTimeId").val(),
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val()
            },
            success: function (data) {
                Messager.alert("时间更新成功！");
            },
            error:function () {
                Messager.alert("保存失败！");
            }
        })
    }
    this.getTimeListByOrderId = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getTimeListByOrderId,
            data: {
                pId:$("#id").val()
            },
            success: function (data) {
                $("#time_list").empty();
                if(data.length<1){
                    var text = "<tr ><th>暂未添加预约时间信息</th></tr>"
                    $("#time_list").append(text);
                }else{
                    for(var i =0;i<data.length;i++){
                        data[i].status = data[i].status?'启用':'禁用';

                        var text = "<tr><th>"+data[i].startTime+"</th><th>"+data[i].endTime+"</th><th>"+data[i].maxNum+"</th><th>"+data[i].status+"</th>\n" +
                            "<th>\n" +
                            "<a title='修改' href='javascript:void(0)' onclick=\"order.editTimeById("+data[i].id+",'"+data[i].startTime+"','"+data[i].endTime+"',"+data[i].maxNum+")\" ><i class='fa fa-edit text-primary'></i></a>\n" +
                            "<a title='删除' href='javascript:void(0)' onclick=\"removeTimeById("+data[i].id+")\" ><i class='fa fa-trash-o text-warning'></i></a>\n" +
                            "</th></tr>";
                        $("#time_list").append(text);
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
    this.addOrUpdateTime = function () {
        var that = this;
        var oId = $("#oId").val();
        console.log(oId);
        var url;
        if(!oId){
            url = that.url.addTime;
        }else{
            url = that.url.updateOrderDetail;
        }
        $.ajax({
            type: 'POST',
            url: url,
            data: {
                id:$("#oId").val(),
                pId:$("#id").val(),
                startTime:$("#startTime").val(),
                endTime:$("#endTime").val(),
                maxNum:$("#maxNum").val(),
                status:$("#status").val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        that.getTimeListByOrderId();
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
    this.removeTimeById = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.removeTimeById,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        order.getTimeListByOrderId();
                    });
                }else{
                    Messager.alert("操作失败！");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("此数据因有学员与之关联所以无法删除，只能修改！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }
    this.showTime = function () {
        $("#oId").val("");
        $("#startTime").val("");
        $("#endTime").val("");
        $("#maxNum").val("");
        $('#modalForm').modal('show');
    };
    this.editTimeById = function (id,startTime,endTime,maxNum) {
        $("#oId").val(id);
        $("#startTime").val(startTime);
        $("#endTime").val(endTime);
        $("#maxNum").val(maxNum);
        $('#modalForm').modal('show');
    }
}


