class SendTestOrderList{
    url={
        listByPage:"/sendTestOrder/listByPage"
    };
    params:{

    };

    loadTableData() {
        var that = this;
        $('#send_test_order').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 25, //每页的记录行数
            url: that.url.listByPage,
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
                field: 'gender',
                align: 'center',
                title: '性别',
                formatter:function (value) {
                    return value==1?"男":"女";
                }
            },{
                field: 'mobile',
                align: 'center',
                title: '学员电话'
            },{
                field: 'unitName',
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
                field: 'teachName',
                align: 'center',
                title: '预约教练'
            },{
                field: 'sTime',
                align: 'center',
                title: '预约送考时间'
            },{
                field: 'subType',
                align: 'center',
                title: '考试科目',
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
                    if(t==now&&row.sms==0){
                        arr.push("&nbsp;&nbsp;<a title='发送短信通知' href='javascript:void(0)' onclick=\"show("+row.stuId+",'"+row.oTime+"','"+row.pickup+"')\"><i class='fa fa-envelope-o text-info'></i></a>");
                    }else if(row.sms==1){
                        arr.push("&nbsp;&nbsp;<a title='短信已发送' href='javascript:void(0)'<i class='fa fa-check-square-o text-success'></i></a>");
                    }else if(row.sms==2){
                        arr.push("&nbsp;&nbsp;<a title='微信已推送不学车消息' href='javascript:void(0)'<i class='fa fa-wechat text-info'></i></a>");
                    }
                    arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='deleteOrderStudent("+row.id+")'><i class='fa fa-trash-o text-warning'></i></a>");
                    return arr;
                }
            }]
        });
    };
}