var ExaminationAnswerList = function () {
    this.url = {
        listByPage:"/admin/sys/examinationAnswer/listByPage",
        loadTableDataByPaperId:"/admin/sys/examinationAnswer/listPageByPaperId/",
    };

    this.loadTableData = function () {
        var that = this;

        $('#examinationAnswer').bootstrapTable({
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
            pageList:[10,25,50,100,'All'],
            showExport: true,
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
                field: 'paperName',
                align: 'center',
                title: '试卷名称'
            },{
                field: 'questionName',
                align: 'center',
                title: '问题'
            },{
                field: 'teacherName',
                align: 'center',
                title: '教师'
            },{
                field: 'studentName',
                align: 'center',
                title: '学生'
            },{
                field: 'answer',
                align: 'center',
                title: '答案',
                formatter : function (value, row, index){
                    if(value=='yes'){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'created',
                align: 'center',
                title: '创建时间',
                formatter:function (value,row,index) {
                   return DM.format.formatDateTime(value)
                }
            }]
        });
    };

    this.loadTableDataByPaperId = function () {
        var that = this;

        $('#examinationAnswerByPaperId').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.loadTableDataByPaperId+$("#paperId").val(),
            pageList:[10,25,50,100,'All'],
            showExport: true,
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
                field: 'paperName',
                align: 'center',
                title: '试卷名称'
            },{
                field: 'questionName',
                align: 'center',
                title: '问题'
            },{
                field: 'teacherName',
                align: 'center',
                title: '教师'
            },{
                field: 'studentName',
                align: 'center',
                title: '学生'
            },{
                field: 'answer',
                align: 'center',
                title: '答案',
                formatter : function (value, row, index){
                    if(value=='yes'){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'created',
                align: 'center',
                title: '创建时间',
                formatter:function (value,row,index) {
                   return DM.format.formatDateTime(value)
                }
            }]
        });
    };


}

