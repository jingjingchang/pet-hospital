var TeacherOrderStudentList = function () {
    this.url = {
        newListByPage:"/wx/orderStudent/newListByPage",
        deleteOrderStudent:"/admin/sys/car/orderStudent/remove",
        sendSMS:"/wx/sendSMS",
        sendToNoOrderStudent:"/wx/sendToNoOrderStudent"
    };
    this.params = {
        dateTime:DM.format.getNow(),
        subType:$("#subType").val(),
        unitId:$("#unitId").val(),
        orderDetail:$("#orderDetail").val(),
    };
    this.init = function () {
        this.validInit();
    };
    this.selectAftre =function() {
        this.params.subType=$("#subType").val();
        this.params.unitId=$("#unitId").val();
        this.params.orderDetail=$("#orderDetail").val();
        $("#orderStudentList").bootstrapTable('refresh');
    };
    this.loadTableData = function () {
        var that = this;
        $('#orderStudentList').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 25, //每页的记录行数
            url: that.url.newListByPage,
            queryParams:function (param) {
                return $.extend(that.params, param);
            },
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
                field: 'stuName',
                align: 'center',
                title: '学员名称'
            },{
                field: 'smobile',
                align: 'center',
                title: '学员电话'
            },{
                field: 'uname',
                align: 'center',
                title: '所属区域',
                formatter: function (value,row,index) {
                    if(value){
                        return value;
                    }else{
                        return "未设置";
                    }
                }
            },{
                field: 'pickup',
                align: 'center',
                title: '接送点',
                formatter: function (value,row,index) {
                    if(value){
                        return value;
                    }else{
                        return "未设置";
                    }
                }
            },{
                field: 'orderNum',
                align: 'center',
                title: '预约次数'
            },{
                field: 'successNum',
                align: 'center',
                title: '预约成功次数'
            },{
                field: 'teachName',
                align: 'center',
                title: '预约教练'
            },{
                field: 'oTime',
                align: 'center',
                title: '预约学车时间'
            },{
                field: 'subType',
                align: 'center',
                title: '预约科目',
                formatter:function (value,row,index) {
                    return value=='2'?'<span style="color: #00ee00">科目二</span>':'<span style="color:#1E90FF ">科目三</span>';
                }
            },{
                field: 'finalTime',
                align: 'center',
                title: '实际安排时间'
            },{
                field: 'finalPickup',
                align: 'center',
                title: '实际接送点'
            },{
                field: 'pickupTime',
                align: 'center',
                title: '学员达到时间'
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    var d = new Date();
                    var t = DM.format.formatDate(row.created);
                    var now = DM.format.formatDate(d.toDateString());
                    if(t==now&&row.sms!=1){
                        arr.push("&nbsp;&nbsp;<a title='发送短信通知' href='javascript:void(0)' onclick=\"show("+row.stuId+",'"+row.oTime+"','"+row.pickup+"')\"><i class='fa fa-envelope-o text-info'></i></a>");
                    }else if(row.sms==1){
                        arr.push("&nbsp;&nbsp;<a title='短信已发送' href='javascript:void(0)'<i class='fa fa-check-square-o text-success'></i></a>");
                    }
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteOrderStudent("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
        $("#dateTime").val(DM.format.getNow());
    };
    this.deleteOrderStudent = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteOrderStudent,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/orderStudent/list';
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
    this.sendSMS = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.sendSMS,
            data: {
                id:$("#osId").val(),
                pickupTime:$("#pickupTime").val(),
                finalPickup:$("#finalPickup").val(),
                finalTime:$("#finalTime").val()
            },
            success: function (data) {
                if(data.success){
                    Messager.success(data.resultText, function() {
                        $('#modalForm').modal('hide');
                       that.selectAftre();
                    });
                }else{
                    $('#modalForm').modal('hide');
                    Messager.error(data.resultText);
                    that.selectAftre();
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

    this.sendToNoOrderStudent = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.sendToNoOrderStudent,
            data: {},
            success: function (data) {
                if(data.success){
                    Messager.success(data.resultText, function() {
                        that.selectAftre();
                    });
                }else{
                    Messager.alert("操作失败！");
                    that.selectAftre();
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest.responseText);
            }
        });
    }
}


