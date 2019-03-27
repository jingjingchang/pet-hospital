var DM = {
    logout: function () {
        Messager.confirm('您确定要退出？', function () {
            $.ajax({
                type: 'POST',
                url: '/user/logout',
                success: function (data) {
                    location.reload();
                }
            });
        });
    },
    initModal: function (title, html, fn) {
        var str = "<div class=\"modal fade\" id=\"alertModal\" tabindex=\"-1\" role=\"dialog\">\n              <div class=\"modal-dialog\" role=\"document\">\n                <div class=\"modal-content\">\n                  <div class=\"modal-header\">\n                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>\n                    <h4 class=\"modal-title\">" + title + "</h4>\n                  </div>\n                  <div class=\"modal-body\">\n                    <p>" + html + "</p>\n                  </div>\n                  <div class=\"modal-footer\">\n                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">\u5173\u95ED</button>\n                    <button type=\"button\" class=\"btn btn-primary\">\u4FDD\u5B58</button>\n                  </div>\n                </div>\n              </div>\n            </div>";
        if ($('#alertModal').length <= 0) {
            $("body").append(str);
        }
        $('#alertModal').modal('show');
    },
    back: function () {
        history.go(-1);
    },
    createResourcePicDiv: function (url) {
        if (!url)
            return '';
        var html = '';
        html += '<div id="thumb-' + url + '" class="thumbnail">';
        html += '	<a href="/' + url + '" data-gallery="">';
        html += '		<img src="' + url + '"/>';
        html += '	</a>';
        html += '	<div class="remove-btn"><a href="javascript:void(0)"><i class="fa fa-times"></i></a></div>';
        html += '</div>';
        return html;
    },
    showUploadModal: function () {
        var str = "<div class=\"ibox-content\">\n                                <div class=\"page-container\">\n                                    <p>\u60A8\u53EF\u4EE5\u5C1D\u8BD5\u6587\u4EF6\u62D6\u62FD\uFF0C\u4F7F\u7528QQ\u622A\u5C4F\u5DE5\u5177\uFF0C\u7136\u540E\u6FC0\u6D3B\u7A97\u53E3\u540E\u7C98\u8D34\uFF0C\u6216\u8005\u70B9\u51FB\u6DFB\u52A0\u56FE\u7247\u6309\u94AE\uFF0C\u6765\u4F53\u9A8C\u6B64demo.</p>\n                                    <div id=\"uploader\" class=\"wu-example\">\n                                        <div class=\"queueList\">\n                                            <div id=\"dndArea\" class=\"placeholder\">\n                                                <div id=\"filePicker\"></div>\n                                                <p>\u6216\u5C06\u7167\u7247\u62D6\u5230\u8FD9\u91CC\uFF0C\u5355\u6B21\u6700\u591A\u53EF\u9009300\u5F20</p>\n                                            </div>\n                                        </div>\n                                        <div class=\"statusBar\" style=\"display:none;\">\n                                            <div class=\"progress\">\n                                                <span class=\"text\">0%</span>\n                                                <span class=\"percentage\"></span>\n                                            </div>\n                                            <div class=\"info\"></div>\n                                            <div class=\"btns\">\n                                                <div id=\"filePicker2\"></div>\n                                                <div class=\"uploadBtn\">\u5F00\u59CB\u4E0A\u4F20</div>\n                                            </div>\n                                        </div>\n                                    </div>\n                                </div>\n                            </div>";
        this.initModal("文件上传", str, '');
    },
    getCurrentUser: function () {
        $.ajax({
            type: 'POST',
            url: '/user/getCurrentUserInfo',
            success: function (data) {
                $("#name").html(data.resultText.name);
                $("#doctor").html(data.resultText.rname + "<b class=\"caret\">");
                if (data.resultText.portrait) {
                    $("#portrait").attr('src', data.resultText.portrait);
                }
            }
        });
    }
};
//# sourceMappingURL=common.js.map