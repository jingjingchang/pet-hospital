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
        url: '/admin/sys/menu/getAllMenus',
        data: {},
        success: function (data) {
            if(data){
                zNodes=data;
                init();
            }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.error("操作失败！");
            console.log(XMLHttpRequest.responseText);
        }
    });
}
function showArea() {
    $('#modalForm').modal('show');
    if(zTree.getSelectedNodes()[0]){
        $('#parentId').val(zTree.getSelectedNodes()[0].id);
    }
   // addTreeNode();
}

function addMenu() {
    var valid = $('#menuForm').valid();
    if (!valid) {
        return false;
    }
    $.ajax({
        type: 'POST',
        url: '/admin/sys/menu/create',
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
            Messager.error("操作失败！");
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
            code : {
                required : true,
                maxlength : 30
            },
            uri : {
                required : true,
                maxlength : 30
            },
            sequence : {
                required : true,
                maxlength : 30
            }
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
        $("#m_edit").hide();
        $("#m_check").hide();
        $("#m_unCheck").hide();
    } else {
        $("#m_del").show();
        $("#m_edit").show();
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
                url: '/admin/sys/menu/remove',
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
                    Messager.error("请先取消角色中对此菜单的授权");
                    console.log(XMLHttpRequest.responseText);
                    console.log("==============");
                }
            });
        }
    }
}


var zTree, rMenu;
function init() {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    zTree = $.fn.zTree.getZTreeObj("treeDemo");
    rMenu = $("#rMenu");
}
