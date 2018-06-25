var InOutList = function () {
    this.url = {
        listByPage:"/admin/sys/car/inout/listByPage",
        create:"/admin/sys/car/inout/create",
        deleteInOut:"/admin/sys/car/inout/remove",
        updateInOut:"/admin/sys/car/inout/update",
    };
    this.init = function () {
        this.validInit();
        $("#madeTime").datetimepicker({
            format: 'yyyy-MM-dd',
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  true,
            autoclose: true,
            todayHighlight: true,
            startView: 2,
            minView: 2,
            forceParse: false
        });
    };
    this.loadTableData = function () {
        var that = this;

        $('#inout').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            pageList:[10,25,50,100,'All'],
            showExport: true,
            url: that.url.listByPage,
            responseHandler:function(res) {
                var income=0,outcome=0;
                for(var i=0;i<res.rows.length;i++){
                    if(res.rows[i].type=='income'){
                         income=income+ res.rows[i].money;
                        $
                    }else{
                         outcome=outcome+ res.rows[i].money;

                    }
                }

                $("#totalOutcome").text(outcome);
                $("#totalIncome").text(income);
                $("#leftCome").text(income-outcome);
                return res;
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
                field: 'name',
                align: 'center',
                title: '名称'
            },{
                field: 'type',
                align: 'center',
                title: '分类',
                formatter:function (value, row, index) {
                    if(value=='income'){
                        return "收入"
                    }else{
                        return "支出"
                    }
                }
            },{
                field: 'madeTime',
                align: 'center',
                title: '日期'
            },{
                field: 'money',
                align: 'center',
                title: '金额'
            },{
                field: 'brief',
                align: 'center',
                title: '备注'
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/car/inout/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteInOut("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#inout').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#inout").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/car/inout/list';
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
    this.deleteInOut = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteInOut,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/inout/list';
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
    this.updateInOut = function () {
        var valid = $('#inout').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateInOut,
            data: $("#inout").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/car/inout/list';
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
            selector : '#inout',
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
}


