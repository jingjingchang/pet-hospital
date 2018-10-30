var BuilBuilddingInfoList = function () {
    this.url = {
        listByPage:"/admin/sys/buildBuildingInfo/listQueryByPage",
        create:"/admin/sys/buildBuildingInfo/create",
        deleteBuildBuildingInfo:"/admin/sys/buildBuildingInfo/delete",
        updateBuildBuildingInfo:"/admin/sys/buildBuildingInfo/update",
        changeStatus:"/admin/sys/buildBuildingInfo/changeStatus"
    };
    this.init = function () {

    };
    this.loadTableData = function () {
        var that = this;

        $('#buildBuildingInfo').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#buildInfo_bar',
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
                field: 'memberName',
                align: 'center',
                title: '姓名'
            },{
                field: 'gender',
                align: 'center',
                title: '性别',
                formatter : function (value, row, index){
                if(value==1){
                    return "男"
                }else{
                    return "女"
                }
        }
            },{
                field: 'number',
                align: 'center',
                title: '楼层'
            },{
                field: 'success',
                align: 'center',
                title: '状态',
                formatter : function (value, row, index){
                    if(value){
                        return "<span style=\"color: green\">已中奖</span>"
                    }else{
                        return "<span style=\"color: red\">未中奖</span>"
                    }
                }
            },{
                field: 'status',
                align: 'center',
                title: '奖品派发状态',
                formatter : function (value, row, index){
                    if(value==1){
                        return  "<span style=\"color: green\">已派发</span>"
                    }else{
                        return "<span style=\"color: red\">未派发</span>"
                    }
                }
            },{
                field: 'opt',
                align: 'center',
                title: '操作',
                formatter : function (value, row, index) {
                    var arr = [];
                    if(row.success&&!row.status){
                        arr.push("&nbsp;&nbsp;<a title='确认已派发奖品' href='javascript:void(0)' onclick='showConfirm(\""+row.id+"\")'><i class='fa fa-envelope-o text-info'></i></a>");
                    }else if(!row.success){
                        arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteObj(\""+row.id+"\")'><i class='fa fa-trash-o text-warning'></i></a>");
                    }
                    return arr;
                }
            }]
        });
    };
    this.deleteBuildBuildingInfo = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.deleteBuildBuildingInfo,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        location.reload();
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

    this.changeStatus = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.changeStatus,
            data: {id:id},
            success: function (data) {
                if(data.success){
                    Messager.success("操作成功！", function() {
                        location.reload();
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

}


