/**
 * webuploader plugin
 * 
 */
				  
(function($) {
	var uploader;
	var uniqueCode = null;          //文件唯一标识符
    var md5Mark = null;				//md5签名
    
	jQuery.destroyUploadDialog = function(data, fn) {
		// 验证文件上传状态
		var flg = false;
		var files = uploader.getFiles();
		for (var i=0; i<files.length; i++) {
			var tmp = files[i];
			var st = tmp.getStatus();
			if (st == 'inited' || st == 'queued' || st == 'progress') {
				flg = true;
				break;
			}
		}
		if (flg) {
			var r = confirm('有文件处于上传中，确定要关闭？');
			if (r) {
				files = uploader.getFiles();
				for (var i=0; i<files.length; i++) {
					cancelFile(files[i]);
				}
				removeModal(data, fn);
			} else {
				return;
			}
		}
		removeModal(data, fn);
	}
	
	function removeModal(data, fn) {
		$("#uploadModal").modal('hide');
//		$("#uploadModal").remove(); // 直接这样写会报错，帮修改通过事件监听来实现
		$('#uploadModal').on('hidden.bs.modal', function () {
			$("#uploadModal").remove();
		})
		// 关闭回调
		if (fn instanceof Function) {
			data = eval('('+data+')');
			fn(data);
		}
		// 注销之前注册的组件
		WebUploader.Uploader.unRegister('customFileEvt');
	}
	
	function cancelFile(file) {
		/*inited 初始状态			//直接移除
	    queued 已经进入队列, 等待上传       //移除队列再移除
	    progress 上传中		    //停止继续上传，删除临时文件，移除
	    complete 上传完成。		    //移除
	    error 上传出错，可重试	    //停止继续上传，删除临时文件，移除
	    interrupt 上传中断，可续传。	//停止继续上传，删除临时文件，移除
	    invalid 文件不合格，不能重试上传。会自动从队列中移除。
	    cancelled 文件被移除。 */
		uploader.removeFile(file, true);
		var st = file.getStatus();
		if(st === 'progress'){
			//发送一个异步请求删除已上传的临时文件
		}
	}
	
	//生成文件唯一标识
	function generateUniqueCode(file) {
		var str = file.name+file.type+file.lastModifiedDate+file.size;
		return $.md5(str);
	}
	
	function md5Check(file, opts) {
		var task = new $.Deferred();
        var start = new Date().getTime();
        (new WebUploader.Uploader()).md5File(file).progress(function(percentage){
//            console.log(percentage);
        }).then(function(val){
//            console.log("总耗时: "+((new Date().getTime()) - start)/1000);
            md5Mark = val;
            $.ajax({type: 'POST', 
            		url: '/upload/md5Check',
            		data: {md5: val}, 
            		cache: false, 
            		timeout: 3600000, //todo 超时的话，只能认为该文件不曾上传过
            		dataType: 'json'
            }).then(function(data, textStatus, jqXHR){
            	//若存在，这返回失败给WebUploader，表明该文件不需要上传
                if (data.success && data.data.ifExist) {   
                    task.reject();
                    uploader.skipFile(file);
                    uploadComlate(file,data);
                } else {
                    task.resolve();
                    //拿到上传文件的唯一名称，用于断点续传
                    uniqueCode = generateUniqueCode(file);
                }
            }, function(jqXHR, textStatus, errorThrown){    //任何形式的验证失败，都触发重新上传
                task.resolve();
                //拿到上传文件的唯一名称，用于断点续传
                uniqueCode = generateUniqueCode(file);
            });
        });
        return $.when(task);
	}
	
	function continualTransfer(block, opts) {
		var task = new $.Deferred();
        $.ajax({type: 'POST',
            	url: '/upload/chunkCheck',
            	data: {uniqueCode: uniqueCode, name: block.name, chunkIndex: block.chunk, size: block.end - block.start, path: opts.formData.path},
            	cache: false, 
            	timeout: 3600000, //todo 超时的话，只能认为该分片未上传过
            	dataType: 'json'
        }).then(function(data, textStatus, jqXHR){
        	//看存在，返回失败给WebUploader，表明该分块不需要上传
            if (data.success && data.data.ifExist) {   
                task.reject();
            } else {
                task.resolve();
            }
        }, function(jqXHR, textStatus, errorThrown){
        	//任何形式的验证失败，都触发重新上传
            task.resolve();
        });
        return $.when(task);
	}
	
	function checkMerge(file, opts) {
		var chunksTotal = Math.ceil(file.size/$.fn.webuploader.defaults.chunkSize);
		var task = new $.Deferred();
        $.ajax({type: 'POST',
        		url: '/upload/chunksMerge',
        		data: {uniqueCode: generateUniqueCode(file), 
        			   name: file.name, chunks: chunksTotal, 
        			   ext: file.ext, md5: md5Mark, 
        			   clazz: opts.clazz,
        			   resourceId: opts.resourceId,
        			   size: file.size, path: opts.formData.path},
        		cache: false, 
        		timeout: 3600000,
        		dataType: 'json'
        }).then(function(data, textStatus, jqXHR){
            //todo 检查响应是否正常
            task.resolve();
            if (data.success) {
            	uploadComlate(file,data);
            } else {
            	uploadError(file, data.resultText);
            	return;
            }
            //调用自定义函数
    		if (opts.afterSendFileFn && opts.afterSendFileFn instanceof Function) {
    			opts.afterSendFileFn(data.data);
    		}
    		//自动关闭窗口
    		if (opts.fileNumLimit == 1 && opts.autoClose) {
    			removeModal(opts.data, opts.closeModalFn);
    		}
        }, function(jqXHR, textStatus, errorThrown){
            task.reject();
        });
        return $.when(task);
	}
	
	function uploadComlate(file,response) {

        var callback = null;
		if(this.WebUploader  != null) {
           return ;
		}
		else {
            callback = $(this)[0].options.uploadSuccess;
        }
		if(callback != undefined) {
            callback(file,response);
		}
		$( "#"+file.id+" .status" ).text("上传完成");
		$("#"+file.id+" .delete").hide();
	}
	
	function uploadError(file, msg) {
		$("#"+file.id+" .status" ).text(msg);
		$("#"+file.id+" .delete").show();
	}
	
	function initUploader(jq, opts) {
		// 重新注册
		WebUploader.Uploader.register({
			name: 'customFileEvt',
	        'before-send-file': 'beforeSendFile',
	        'before-send': 'beforeSend',
	        'after-send-file': 'afterSendFile'
	    },{
	    	//秒传验证
	    	beforeSendFile: function(file) {
	    		md5Check(file, opts);
	    	},
	    	//分片验证是否已传过，用于断点续传
	    	beforeSend: function(block) {
	    		continualTransfer(block, opts);
	    	},
	    	//合成文档
	    	afterSendFile: function(file) {
	    		checkMerge(file, opts);
	    	}
	    });
	
		uploader = WebUploader.create(opts);
		
		uploader.on("fileQueued", function(file){
			var fmtSize = WebUploader.Base.formatSize(file.size);
			var content = '<tr id="'+file.id+'"><td width="45%">'+file.name+'</td><td width="20%">'+fmtSize+'</td><td width="20%" class="status">等待中</td><td width="15%" class="operation"><a href="javascript:void(0);" class="delete">取消</a></td></tr>';
			$("#info").append(content);
			
			$("#"+file.id+" .operation .delete").click( function () {
			    var r = confirm("确定要取消上传此文件？");
				if (r) {
					cancelFile(file);
					$("#"+file.id).remove();
				}
 			});
		});	
 		uploader.on("uploadProgress",function(file, percentage){
 			var per = parseFloat(percentage) * 100;
 			var str = per.toFixed(2);
			$("#"+file.id+" .status"  ).text(str+"%");
		});
  	 
		uploader.on("uploadSuccess", uploadComlate);
	}
	
	function createDemandStr(opts) {
		var tmp = '';
		var num = 1;
		if (opts.describe) {
			tmp += num + '.' + opts.describe;
			if (!tmp.endsWith('；')) {
				tmp += '；';
			}
			num++;
		}
		if (opts.accept) {
			tmp += num + '.' + '允许上传的文件类型(' + opts.accept.extensions + ')；';
			num++;
		}
		var size = WebUploader.Base.formatSize(opts.fileSingleSizeLimit);
		tmp += num + '.' + '允许上传文件大小(' + size + ')；';
		return tmp;
	}
	
	function showUploadDialog(jq, opts) {
		var tmp = createDemandStr(opts);
		var html = '<div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">\
				      <div class="modal-dialog">\
				      	<div class="modal-content">\
				      		<div class="modal-header">\
				      			<button class="close" onclick="$.destroyUploadDialog(\''+opts.data+'\''+(opts.closeModalFn ? ',' + opts.closeModalFn : '')+');">&times;</button>\
				      			<h4 class="modal-title">'+opts.modalTitle+'</h4>\
				      		</div>\
				      		<div id="dndArea" class="modal-body">\
				      			<div style="padding-bottom: 10px;color: red;font-size: 12px;">要求：'+tmp+'</div>\
				      			<a id="webuploader" href="javascript:void(0)" title="支持选择文件上传和拖拽上传"><i class="ace-icon fa fa-plus-circle bigger-120"></i>\
					      			<span class="bigger-110">选择文件</span>\
								</a>\
				      			<div class="panel panel-default">\
					      			<div style="margin-left: 17px;margin-left: 0px;">\
						    			<table class="table table-hover" style="margin-bottom: 0px;border-left: 1px solid #dddddd; border-right: 1px solid #dddddd;">\
						    				<thead>\
						    					<tr>\
						    						<th width="45%">文件名称</th>\
						    						<th width="20%">大小</th>\
						    						<th width="20%">状态</th>\
						    						<th width="15%">操作</th>\
						    					</tr>\
						    				</thead>\
						    			</table>\
						    		</div>\
						    		<div style="min-height: 50px;max-height: 200px;overflow: auto;border: 1px solid #dddddd;border-top: none;">\
						    			<table class="table table-striped table-hover">\
						    				<tbody id="info"></tbody>\
						    			</table>\
						    		</div>\
				      	  		</div>\
				      	  	</div>\
				      		<div class="modal-footer" style="background-color: #eff3f8;">\
				      			<button id="close2" type="button" class="btn btn-sm btn-primary" onclick="$.destroyUploadDialog(\''+opts.data+'\''+(opts.closeModalFn ? ',' + opts.closeModalFn : '')+');">关闭</button>\
				      		</div>\
				      	</div>\
				      </div>\
				    </div>';
		jq.after(html);
		$("#uploadModal").modal("show");
		$("#uploadModal").on("shown.bs.modal", function () {
			initUploader(jq, opts);
		});
	}
	
	function createMimeTypes() {
		
	}
	
	function acceptTypeMap() {
		return {
			Images: {
				title: 'Images',
			    extensions: 'gif,jpg,jpeg,bmp,png'
			},
			Doc: {
				title: 'Doc',
			    extensions: 'doc,docx'
			},
			Xls: {
				title: 'Xls',
			    extensions: 'xls,xlsx'
			},
			Pdf: {
				title: 'Pdf',
			    extensions: 'pdf'
			},
			Word: {
				title: 'Word',
			    extensions: 'doc,docx,xls,xlsx,ppt,pptx'
			}
		};
	}
	
	function isCaps(ch){
		if (!ch) return false;
		var c = ch.charAt(0);
		if(c<'A' || c>'Z') {
			return false;
		}
		return true;
	} 
	
	function createAcceptType(acceptType) {
		var result = {
				title: '',
			    extensions: '',
			    mimeTypes: ''
		};
		var at = acceptTypeMap();
		var type = acceptType.split(',');
		for (var i=0; i<type.length; i++) {
			var tm = type[i];
			if (!tm) {
				continue;
			}
			var title = result.title;
			if (title) {
				title += '_';
			}
			var ext = result.extensions;
			if (ext) {
				ext += ',';
			}
			var mt = result.mimeTypes;
			if (mt) {
				mt += ',';
			}
			if (isCaps(tm)) {
				var tmAt = at[tm];
				if (!tmAt) {
					throw new Error('非法的acceptType。');
				}
				title += tmAt.title;
				ext += tmAt.extensions;
			} else {
				title += tm;
				ext += tm;
			}
			result.title = title;
			result.extensions = ext;
		}
		// 获取MimeType
		result.mimeTypes = MimeType.getMimes(result.extensions);
		return result;
	}
	
	function configOptions(opt) {
		var opts = $.extend({}, $.fn.webuploader.defaults, opt);
		if (!opts.formData) {
			opts.formData = {path: opts.path};
		}
		if (opts.clazz) {
			opts.formData.clazz = opts.clazz;
		}
		if (opts.acceptType) {
			opts.accept = createAcceptType(opts.acceptType);
		}
		return opts;
	}
	
	/**
	 * 通过$('#uploadBtn').webuploader({});初始化
	 * 说明：通过选择器初始化，当前只支持一个界面一个上传按钮的情形，如果一个界面存在多个上传操作请直接调用方法。
	 */
	$.fn.webuploader = function(opt) {
		var opts = configOptions(opt);
		if (!opts.path) {
			alert('请配置上传文件存放路径。');
			return;
		}
		return this.each(function() {
			var obj = $(this);
			if (opts.cls) {
				obj.addClass(opts.cls);
			}
			opts.id = obj[0].id;
			obj.click(function() {
				showUploadDialog(obj, opts);
			});
		});
	}
	
	/**
	 * 在按钮事件中直接调用$.webuploader({});方法进行初始化
	 */
	jQuery.webuploader = function(opt) {
		var opts = configOptions(opt);
		if (!opts.path) {
			alert('请配置上传文件存放路径。');
			return;
		}
//		var obj = $('#'+opts.id);
		var obj = $('body');
		showUploadDialog(obj, opts);
	}

	// 定义默认值
	$.fn.webuploader.defaults = {
		//以下为自定义配置
		id: 'uploadBtn',							// 上传按钮
		modalTitle: '文件上传',						// 窗口标题
		cls: 'btn btn-default btn-sm',				// 上传按钮样式
		path: '',									// 上传文件存放路径
		autoClose: false,							// 上传完成后自动关闭，仅对单文件上传有效
		acceptType: undefined,						// 上传文件类型限制，此处有两种写法：一种为指定到具体某类文件，如：[Images,Word,Doc,Xls,Pdf]；一种为直接指定文件类型，如：doc,pdf,xls等
		afterSendFileFn: undefined,					// 上传文件成功后回调
		closeModalFn: undefined,					// 关闭窗口回调
		data: '{}',									// 传递参数，回调中使用
		describe: '',								// 上传文件描述信息
		clazz: '',									// 上传实体
		resourceId: '',								// 上传到节点资源ID
		//以下为webupload配置
		auto: true, 
		duplicate: true, 
		chunked: true,
		server: '/upload/fileUpload',
    	swf: '/webuploader/Uploader.swf',
    	pick: '#webuploader',
   		resize: false,
   		disableGlobalDnd: true,
   		dnd: '#dndArea',
   		chunkSize: 5 * 1024 * 1024,
   		fileNumLimit: 10,
   		fileSingleSizeLimit: 20*1024*1024
	};
})(jQuery);
