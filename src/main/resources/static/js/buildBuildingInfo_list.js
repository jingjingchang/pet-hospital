var PartTimeJobList = function () {
    this.url = {
        listByPage:"/admin/sys/buildBuildingInfo/listByPage",
        create:"/admin/sys/buildBuildingInfo/create",
        deleteBuildBuildingInfo:"/admin/sys/buildBuildingInfo/remove",
        updateBuildBuildingInfo:"/admin/sys/buildBuildingInfo/update",
    };
    this.init = function () {

    };
    this.loadTableData = function () {
        var that = this;

        $('#buildBuildingInfo').bootstrapTable({
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
                title: '姓名'
            },{
                field: 'name',
                align: 'center',
                title: '姓名'
            },{
                field: 'startTime',
                align: 'center',
                title: '性别',
            },{
                field: 'number',
                align: 'center',
                title: '楼层'
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/admin/sys/buildBuildingInfo/edit/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteBuildBuildingInfo("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
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
                        location.href='/admin/sys/buildBuildingInfo/list';
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


