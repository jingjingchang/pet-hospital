var StudentEdit = function () {
    this.url = {
        listByPage:"/wx/student/listByPage",
        getPassListByStuId:"/wx/pass/getPassListByStuId",
        addPass:"/wx/pass/create",
        removePassById:"/wx/pass/remove"
    };
    this.init = function () {
        $("#testTime").datetimepicker({
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
        $('#student').bootstrapTable({
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
            responseHandler:function(res) {
                $("#total").text(res.total);
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
                field: 'identityNo',
                align: 'center',
                title: '身份证号码'
            },{
                field: 'enrollTime',
                align: 'center',
                title: '报名时间'
            },{
                field: 'tuition',
                align: 'center',
                visible:false,
                title: '学费'
            },{
                field: 'uname',
                align: 'center',
                title: '所属区域',
                formatter : function (value, row, index){
                    if(value){
                        return value
                    }else{
                        return "未设置"
                    }
                }
            },{
                field: 'status',
                align: 'center',
                title: '是否购买保险',
                formatter : function (value, row, index){
                    if(value==1){
                        return "是"
                    }else{
                        return "否"
                    }
                }
            },{
                field: 'school',
                align: 'center',
                title: '学校'
            },{
                field: 'studyStatus',
                align: 'center',
                title: '学习状态',
                formatter:function (value, row, index) {
                    if(value==''||value==null||value==1){
                        return "<span style=\"color: #00ee00\">科一学习中</span>"
                    }else if(value==2){
                        return "<span style=\"color: #ee688f\">科二学习中</span>"
                    }else if(value==3){
                        return "<span style=\"color: #eec02b\">科三学习中</span>"
                    }else if(value==4){
                        return "<span style=\"color: #b6a5ee\">科四学习中</span>"
                    }else if(value==5){
                        return "<span style=\"color: #5300ff\">已毕业</span>"
                    }
                }
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
                    arr.push("&nbsp;&nbsp;<a title='编辑' href='/wx/studentEditPage/"+row.id+"'><i class='fa fa-edit text-primary'></i></a>");
                    return arr;
                }
            }]
        });
    };
    this.getPassListByStuId = function () {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.getPassListByStuId,
            data: {
                id:$("#id").val()
            },
            success: function (data) {
                $("#pass_list").empty();
                    if(data.length<1){
                        var text = "<tr ><th>暂未添加该学员的考试信息</th></tr>"
                        $("#pass_list").append(text);
                    }else{
                        for(var i =0;i<data.length;i++){
                            if(data[i].subType==1){
                                data[i].subType="科目一";
                            }else if(data[i].subType==2){
                                data[i].subType="科目二";
                            }else if(data[i].subType==3){
                                data[i].subType="科目三";
                            }else {
                                data[i].subType="科目四";
                            }
                            data[i].isPass = data[i].isPass?'是':'否';

                            var text = "<tr><th>"+data[i].subType+"</th><th>"+data[i].isPass+"</th><th>"+data[i].testTime+"</th><th>"+data[i].spend+"</th><th>"+data[i].evaluate+"</th>\n" +
                                "<th>\n" +
                                "<a title='删除' href='javascript:void(0)' onclick=\"removePassById("+data[i].id+")\" ><i class='fa fa-trash-o text-warning'></i></a>\n" +
                                "</th></tr>";
                            $("#pass_list").append(text);
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
    this.validInitPass  = function () {
        WX.validate.init({
            selector : '#passForm',
            rules : {
                testTime : {
                    required : true,
                    maxlength : 30
                },
                spend : {
                    maxlength : 15,
                    number:true
                },
                evaluate : {
                    required : true,
                    maxlength : 200
                }
            }
        });
    };
    this.addSubjectPass = function () {
        var valid = $('#passForm').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.addPass,
            data: {
                stuId:$("#id").val(),
                subType:$("#subType").val(),
                testTime:$("#testTime").val(),
                isPass:$("#isPass").val(),
                spend:$("#spend").val(),
                evaluate:$("#evaluate").val()
            },
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        that.getPassListByStuId();
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
    this.removePassById = function (id) {
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.removePassById,
            data: {id:id},
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                        that.getPassListByStuId();
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
    this.showSubjectPass = function () {
        $("#subType").val();
        $("#testTime").val("");
        $("#isPass").val();
        $("#spend").val("");
        $("#evaluate").val("");
        $('#modalForm').modal('show');
    };
}

