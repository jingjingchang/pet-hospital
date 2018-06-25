declare const Messager,$;
var DM={
    logout: function() {
        Messager.confirm('您确定要退出？', function() {
            $.ajax({
                type: 'POST',
                url: '/user/logout',
                success: function (data) {
                    location.reload();
                }
            });
        })
    },
    teacherLogOut:function () {
        Messager.confirm('您确定要退出？', function() {
            $.ajax({
                type: 'POST',
                url: '/user/teacherLogout',
                success: function (data) {
                    location.reload();
                }
            });
        })
    },
    initModal:function (title,html,fn) {
            let str = `<div class="modal fade" id="alertModal" tabindex="-1" role="dialog">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">`+title+`</h4>
                  </div>
                  <div class="modal-body">
                    <p>`+html+`</p>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary">保存</button>
                  </div>
                </div>
              </div>
            </div>`
            if($('#alertModal').length<=0){
                $("body").append(str);
            }
            $('#alertModal').modal('show');
    },
    back:function () {
        history.go(-1);
    },
   createResourcePicDiv : function (url) {
        if (!url) return '';
        var html = '';
        html += '<div id="thumb-'+url+'" class="thumbnail">';
        html += '	<a href="/'+url+'" data-gallery="">';
        html += '		<img src="'+url+'"/>';
        html += '	</a>';
        html += '	<div class="remove-btn"><a href="javascript:void(0)"><i class="fa fa-times"></i></a></div>';
        html += '</div>';
        return html;
    },
    showUploadModal:function () {
        var str = `<div class="ibox-content">
                                <div class="page-container">
                                    <p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮，来体验此demo.</p>
                                    <div id="uploader" class="wu-example">
                                        <div class="queueList">
                                            <div id="dndArea" class="placeholder">
                                                <div id="filePicker"></div>
                                                <p>或将照片拖到这里，单次最多可选300张</p>
                                            </div>
                                        </div>
                                        <div class="statusBar" style="display:none;">
                                            <div class="progress">
                                                <span class="text">0%</span>
                                                <span class="percentage"></span>
                                            </div>
                                            <div class="info"></div>
                                            <div class="btns">
                                                <div id="filePicker2"></div>
                                                <div class="uploadBtn">开始上传</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>`
        this.initModal("文件上传",str,'');
    },
    getCurrentUser(){
        $.ajax({
            type: 'POST',
            url: '/admin/sys/user/getCurrentUserInfo',
            success: function (data) {
                $("#name").html(data.resultText.name);
                $("#role").html(data.resultText.rname+"<b class=\"caret\">");
                if(data.resultText.portrait){
                    $("#portrait").attr('src',data.resultText.portrait)
                }
            }
        });
    },
    getCurrentTeacher(){
    $.ajax({
        type: 'POST',
        url: '/wx/teacher/getCurrentTeacherInfo',
        success: function (data) {
            $("#name").html(data.resultText.name+"师傅");
            $("#gender").html((data.resultText.gender=='1'?'男':'女'));
            $("#mobile").html(data.resultText.mobile);
        }
    });
}
}

/*

WX.index={
    logout: function() {
        Messager.confirm('您确定要退出？', function() {
            $.ajax({
                type: 'POST',
                url: '/user/logout',
                success: function (data) {
                    location.reload();
                }
            });
        })
    }
}*/
