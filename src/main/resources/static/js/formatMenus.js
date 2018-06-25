$(function () {
    var that = this;
    $.ajax({
        type: 'POST',
        url: '/admin/sys/menu/getMenusByUserId',
        data: {
        },
        success: function (data) {
            for(var i=0;i<data.length;i++){
                var str = ' <li>';
                if(data[i].children==null){
                    str+="<a class=\"J_menuItem\" id='"+data[i].id+"' href='"+data[i].url+"'><i class='"+data[i].css+"'></i> <span class='nav-label'>"+data[i].name+"</span></a>"
                }
                if(data[i].children!=null){
                    str+="<a id='"+data[i].id+"' href='"+data[i].url+"'><i class='"+data[i].css+"'></i> <span class='nav-label'>"+data[i].name+"</span> <span class='fa arrow'></span></a>"
                    str+=getChild(data[i].children);
                }
                str+='</li>'
                $("#side-menu").append(str);
            }

            $("#side-menu").metisMenu();
            $.getScript("/plugins/js/contabs.js");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.log("==============");
            Messager.alert("操作失败！");
            console.log(XMLHttpRequest);
            console.log("==============");
        }
    });
    function getChild(child) {
        if(child!=null){
            var html = "<ul class=\"nav nav-second-level\">";
            for(var i = 0;i<child.length;i++){
                html+="<li><a class=\"J_menuItem\" href="+child[i].url+">"+child[i].name+"</a>"
                html+=getChild(child[i].children);
                html+="</li>"
            }
            html+='</ul>'
            return html;
        }else{
            return "";
        }
    }
  /*  var url=document.location.href;
    var urls;
    urls = $("a[name='href']");
    $("a[name='href']").each(function(){
        urls = $("a[name='href']").attr('href');

    })
    console.log(typeof(urls));
    console.log(url);*/
})