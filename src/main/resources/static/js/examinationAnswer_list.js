var ExaminationAnswerList = function () {
    this.url = {
        listByPage:"/admin/sys/examinationAnswer/listByPage",
        listTeacherResult:"/admin/sys/car/teacher/getTeacherExamResultList",
        loadTableDataByPaperId:"/admin/sys/examinationAnswer/listPageByPaperId/",
    };
    this.params = {
        mouth:DM.format.getNowMouth(),
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

    this.loadTeacherResultTableData = function () {
        var that = this;
        $("#mouth").val(DM.format.getNowMouth());
        $('#teacherResultTable').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar:'#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server", //分页方式：client客户端分页，server服务端分页
            showToggle: true,
            clickToSelect: true,
            pageSize: 10, //每页的记录行数
            url: that.url.listTeacherResult,
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
                field: 'name',
                align: 'center',
                title: '姓名'
            },{
                field: 'gender',
                align: 'center',
                title: '性别',
                formatter : function (value, row, index){
                    if(value==0){
                        return "女"
                    }else{
                        return "男"
                    }
                }
            },{
                field: 'mobile',
                align: 'center',
                title: '电话'
            },{
                field: 'number',
                align: 'center',
                title: '编号'
            },{
                field: 'uname',
                align: 'center',
                title: '负责区域',
                formatter : function (value, row, index){
                    if(value){
                        return value;
                    }else{
                        return "未设置";
                    }
                }
            },{
                field: 'subTwo',
                align: 'center',
                title: '是否负责科目二',
                formatter:function (value) {
                    if(value==1){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'subThree',
                align: 'center',
                title: '是否负责科目三',
                formatter:function (value) {
                    if(value==1){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'yesNum',
                align: 'center',
                title: 'YES(个数)'
            },{
                field: 'noNum',
                align: 'center',
                title: 'NO(个数)'
            }]
        });
    };


}

