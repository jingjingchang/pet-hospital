var setting = {
    view: {
        dblClickExpand: false
    },
    check: {
        enable: true
    },
    callback: {
        onRightClick: OnRightClick
    }
};
var zNodes ="";

function getMenus() {
    $.ajax({
        type: 'POST',
        url: '/admin/weChat/wxMenu/getAllMenus',
        data: {},
        success: function (data) {
            if(data){
                zNodes=data;
                init();
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.alert("操作失败！");
            console.log(XMLHttpRequest.responseText);
        }
    });
}
function showArea() {

    var sNodes = zTree.getSelectedNodes()[0];
    if(sNodes){
        if(sNodes.getParentNode()){
            Messager.error("微信只能添加到二级菜单！")
        }else if(sNodes.children.length==5){
            Messager.error("微信每个一级菜单下最多只能有5个二级菜单！")
        }else{
            $('#modalForm').modal('show');
            if(zTree.getSelectedNodes()[0]){
                $('#parentId').val(zTree.getSelectedNodes()[0].id);
            }
        }
    }else if(zTree.getNodes().length==3){
        Messager.error("微信最多只能有3个一级菜单！")
    }else{
        $('#modalForm').modal('show');
    }

}

function addMenu() {
    var url = '/admin/weChat/wxMenu/create';
    var valid = $('#menuForm').valid();
    if (!valid) {
        return false;
    }
    if($("#id").val()){
        url = '/admin/weChat/wxMenu/update';
    }
    $.ajax({
        type: 'POST',
        url:url,
        data: $("#menuForm").serialize(),
        success: function (data) {
            if(data.message){
                Messager.success("添加成功",function () {
                    $('#modalForm').modal('hide');
                    location.reload();
                })
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.alert("操作失败！");
            console.log(XMLHttpRequest.responseText);
            console.log("==============");
        }
    });
    
}
function validInit() {
    WX.validate.init({
        selector : '#menuForm',
        rules : {
            name : {
                required : true,
                maxlength : 30
            },
            menuValue : {
                required : true,
                maxlength : 100
            },
            sort : {
                required : true,
                number:true,
                maxlength : 2
            }
        }
    });
}
function sendMenu() {
    Messager.confirm("确定提交到微信菜单吗？",function () {
        $.ajax({
            type: 'POST',
            url: '/admin/weChat/wxMenu/sendMenu',
            data: {
            },
            success: function (data) {
                if(data.success){
                    console.log(data.resultText);
                    Messager.success("提交成功，菜单将于24小时内生效",function () {
                        location.reload();
                    })
                }else{
                    Messager.error("创建菜单失败，请检查参数是否错误",function () {
                        location.reload();
                    })
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.error("操作失败");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
    })
}
function getGraphicList() {
        $.ajax({
            type: 'POST',
            url: '/admin/weChat/wxGraphic/getAllGraphics',
            data: {},
            success: function (data) {
                var str='';
                for(var i=0;i<data.length;i++){
                    str+="<option value='"+data[i].id+"'>"+data[i].title+"</option>"
                }
                $("#graphic").html(str);
                console.log(data);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                console.log("==============");
                Messager.error("操作失败");
                console.log(XMLHttpRequest.responseText);
                console.log("==============");
            }
        });
}
function OnRightClick(event, treeId, treeNode) {
    if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
        zTree.cancelSelectedNode();
        showRMenu("root", event.clientX, event.clientY);
    } else if (treeNode && !treeNode.noR) {
        zTree.selectNode(treeNode);
        showRMenu("node", event.clientX, event.clientY);
    }
}

function showRMenu(type, x, y) {
    $("#rMenu ul").show();
    if (type=="root") {
        $("#m_del").hide();
        $("#m_check").hide();
        $("#m_unCheck").hide();
    } else {
        $("#m_del").show();
        $("#m_check").show();
        $("#m_unCheck").show();
    }

    y += document.body.scrollTop;
    x += document.body.scrollLeft;
    rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

    $("body").bind("mousedown", onBodyMouseDown);
}
function hideRMenu() {
    if (rMenu) rMenu.css({"visibility": "hidden"});
    $("body").unbind("mousedown", onBodyMouseDown);
}
function onBodyMouseDown(event){
    if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
        rMenu.css({"visibility" : "hidden"});
    }
}
var addCount = 1;
function addTreeNode() {
    hideRMenu();
    var newNode = { name:"增加" + (addCount++)};
    if (zTree.getSelectedNodes()[0]) {
        newNode.checked = zTree.getSelectedNodes()[0].checked;
        zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
    } else {
        zTree.addNodes(null, newNode);
    }
}
function removeTreeNode() {
    hideRMenu();
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        if (nodes[0].children && nodes[0].children.length > 0) {
            Messager.error("请先删除子节点！")
            /*var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
            if (confirm(msg)==true){
                zTree.removeNode(nodes[0]);
            }*/
        } else {
            zTree.removeNode(nodes[0]);
            $.ajax({
                type: 'POST',
                url: '/admin/weChat/wxMenu/remove',
                data: {
                    id:nodes[0].id
                },
                success: function (data) {
                    if(data.message){
                        Messager.success("删除成功",function () {
                            location.reload();
                        })
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("==============");
                    Messager.error("操作失败");
                    console.log(XMLHttpRequest.responseText);
                    console.log("==============");
                }
            });
        }
    }
}

function modifyNode() {
    var nodes = zTree.getSelectedNodes();
    if (nodes && nodes.length>0) {
        /*if (nodes[0].children && nodes[0].children.length > 0) {
            Messager.error("此节点信息不能修改！")
        }else{*/
            $.ajax({
                type: 'POST',
                url: '/admin/weChat/wxMenu/editMenu',
                data: {
                    id:nodes[0].id
                },
                success: function (data) {
                    var obj = data.resultText;
                    if(data.success){
                        hideRMenu();
                        $('#modalForm').modal('show');
                        $("#id").val(obj.id);
                        $("#name").val(obj.name);
                        $("#type").val(obj.type);
                        $("#graphic").val(obj.graphic);
                        $("#sort").val(obj.sort);
                        $("#menuValue").val(obj.menuValue);
                        $("#status").val(obj.status);

                    }else{
                        Messager.error("获取数据失败！");
                    }
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    console.log("==============");
                    Messager.error("操作失败");
                    console.log(XMLHttpRequest.responseText);
                    console.log("==============");
                }
            });
     /*   }*/
    }

}
var zTree, rMenu;
function init() {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    zTree = $.fn.zTree.getZTreeObj("treeDemo");
    rMenu = $("#rMenu");
}
