var PartTimeJobList = function () {
    this.url = {
        listByPage:"/admin/sys/partTimeJob/listByPage",
        create:"/admin/sys/partTimeJob/create",
        deleteBuildBuilding:"/admin/sys/partTimeJob/remove",
        updateBuildBuilding:"/admin/sys/partTimeJob/update",
    };
    this.init = function () {
        this.validInit();
    }
    this.loadTableData = function () {
        var that = this;

        $('#buildBuildingWinners').bootstrapTable({
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
                field: 'memberName',
                align: 'center',
                title: '需求人数'
            },{
                field: 'salary',
                align: 'center',
                title: '工资'
            },{
                field: 'applyNum',
                align: 'center',
                title: '报名人数'
            },{
                field: 'status',
                align: 'center',
                title: '礼品发放状态',
                formatter : function (value, row, index){
                    if(value==1){
                        return "已发放"
                    }else{
                        return "未发放"
                    }
                }
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    arr.push("&nbsp;&nbsp;<a title='查看兼职报名人员' href='/admin/sys/buildBuildingWinners/getApplyListPage/"+row.id+"'><i class='fa fa-search text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/buildBuildingWinners/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteBuildBuildingWinner("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.deleteBuildBuildingWinner = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteBuildBuildingWinner,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/buildBuildingWinners/list';
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
    this.updateBuildBuildingWinner = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateBuildBuildingWinner,
            data: $("#buildBuilding").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.href='/admin/sys/buildBuildingWinners/list';
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
}


