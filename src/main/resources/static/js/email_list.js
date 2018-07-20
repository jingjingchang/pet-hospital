var emailList = /** @class */ (function () {
    function emailList() {
        this.url = {
            listByPage: "/admin/sys/email/listByPage",
            create: "/admin/sys/email/create",
            delete: "/admin/sys/email/remove"
        };
    }
    emailList.prototype.init = function () {
        this.loadTableData();
    };
    ;
    emailList.prototype.loadTableData = function () {
        var that = this;
        $('#email').bootstrapTable({
            striped: true,
            pagination: true,
            toorbar: '#news_cate_bar',
            search: true,
            showRefresh: true,
            sidePagination: "server",
            showToggle: true,
            clickToSelect: true,
            pageSize: 25,
            url: that.url.listByPage,
            queryParams: function (param) {
                return $.extend(that.params, param);
            },
            formatSearch: function () {
                return '搜索';
            },
            columns: [{
                    checkbox: true
                }, {
                    field: 'id',
                    visible: false,
                    align: 'center',
                    title: 'ID'
                }, {
                    field: 'subject',
                    align: 'center',
                    title: '主题'
                }, {
                    field: 'sender',
                    align: 'center',
                    title: '发送邮箱'
                }, {
                    field: 'senderName',
                    align: 'center',
                    title: '发送人名称'
                }, {
                    field: 'addressee',
                    align: 'center',
                    title: '收件人'
                }, {
                    field: 'content',
                    align: 'center',
                    visible: false,
                    title: '内容'
                }, {
                    field: 'status',
                    align: 'center',
                    title: '发送状态',
                    formatter: function (value, row, index) {
                        return value == 1 ? "发送成功" : "发送失败";
                    }
                }, {
                    field: 'created',
                    align: 'center',
                    title: '发送时间',
                    formatter: function (value, row, index) {
                        return DM.format.formatDateTime(row.created);
                    }
                }, {
                    field: 'opt',
                    align: 'center',
                    title: '操作',
                    formatter: function (value, row, index) {
                        var arr = [];
                        arr.push("&nbsp;&nbsp;<a title='删除' href='javascript:void(0)' onclick='emailListTs.deleteEmail(" + row.id + ")'><i class='fa fa-trash-o text-warning'></i></a>");
                        return arr;
                    }
                }]
        });
    };
    ;
    emailList.prototype.save = function () {
        var valid = $('#email').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        editor.sync();
        $("#content").val($("#editor_id").val());
        $.ajax({
            type: 'POST',
            url: that.url.create,
            data: $("#email").serialize(),
            success: function (data) {
                if (data.status) {
                    Messager.success("操作成功！", function () {
                        location.href = '/admin/sys/email/list';
                    });
                }
                else {
                    Messager.alert("操作失败！");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.alert("操作失败！");
                console.log(XMLHttpRequest);
                console.log("==============");
            }
        });
    };
    ;
    emailList.prototype.deleteEmail = function (id) {
        var that = this;
        Messager.confirm("是否删除?", function () {
            $.ajax({
                type: 'POST',
                url: that.url.delete,
                data: { id: id },
                success: function (data) {
                    if (data.status) {
                        Messager.success("操作成功！", function () {
                            location.href = '/admin/sys/email/list';
                        });
                    }
                    else {
                        Messager.alert("操作失败！");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log("==============");
                    Messager.alert("操作失败！");
                    console.log(XMLHttpRequest.responseText);
                }
            });
        });
    };
    return emailList;
}());
var emailListTs = new emailList();
//# sourceMappingURL=email_list.js.map