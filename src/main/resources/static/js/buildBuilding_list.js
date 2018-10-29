var BuildBuildingList = function () {
    this.url = {
        listByPage:"/admin/sys/buildBuilding/listByPage",
        create:"/admin/sys/buildBuilding/create",
        delete:"/admin/sys/buildBuilding/remove",
        update:"/admin/sys/buildBuilding/update",
    };
    this.init = function () {
        this.validInit();
        $("#startTime,#endTime").datetimepicker({
            format: 'yyyy-MM-dd hh:mm',
            language:  'zh-CN',
            weekStart: 1,
            todayBtn:  true,
            autoclose: true,
            todayHighlight: true,
            startView: 2,
            minView: 0,
            forceParse: false
        });

    };
    this.loadTableData = function () {
        var that = this;

        $('#buildBuilding_table').bootstrapTable({
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
                title: '活动主题'
            },{
                field: 'startTime',
                align: 'center',
                title: '开始时间',
            },{
                field: 'endTime',
                align: 'center',
                title: '结束时间'
            },{
                field: 'keyword',
                align: 'center',
                title: '口令'
            },{
                field: 'maxTimes',
                align: 'center',
                title: '每人最多中奖次数'
            },{
                field: 'type',
                align: 'center',
                title: '中奖规则',
                formatter:function (value,row,index) {
                    switch (value){
                        case 0:
                            return "相等"
                            break;
                        case 1:
                            return "包含"
                            break;
                        case 2:
                            return "尾数相等"
                            break;
                    }
                }
            },{
                field: 'luckNumber',
                align: 'center',
                title: '中奖号码'
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
                    arr.push("&nbsp;&nbsp;<a title='查看堆楼信息' href='/admin/sys/buildBuilding/showBuildInfoPageByBuildingId/"+row.id+"'><i class='fa fa-search text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/buildBuilding/editById/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteBuildBuilding("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.save = function () {
        var valid = $('#buildBuilding_from').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#buildBuilding_from").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                            location.href='/admin/sys/buildBuilding/list';
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
    this.deleteBuildBuilding = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deletePartTimeJob,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/buildBuilding/list';
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
    this.updatBuildBuilding = function () {
        var valid = $('#buildBuilding_from').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.update,
            data: $("#buildBuilding_from").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/buildBuilding/list';
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
            selector : '#buildBuilding_from',
            rules : {
                title : {
                    required : true,
                    maxlength : 30
                },
                startTime: {
                    required : false,
                    maxlength : 15,
                },
                endTime : {
                    required : true,
                    maxlength : 30,
                },
                startTime : {
                    required : true,
                    maxlength : 30
                },
                keyword : {
                    required : true,
                    maxlength : 20
                },
                luckNumber : {
                    required : true,
                }
            }
        });
    };
}


