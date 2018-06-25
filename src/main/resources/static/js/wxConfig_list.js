var WxConfigList = function () {
    this.url = {
        updateWxConfig:"/admin/weChat/wxConfig/update",
    };
    this.init = function () {
        this.validInit();
    };
    this.updateWxConfig = function () {
        var valid = $('#wxConfig').valid();
        if (!valid) {
            return false;
        }
        var that = this;
        $.ajax({
            type: 'POST',
            url: that.url.updateWxConfig,
            data: $("#wxConfig").serialize(),
            success: function (data) {
                if(data.status){
                    Messager.success("操作成功！", function() {
                       history.go(0);
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

function onCheck(e, treeId, treeNode) {
}


