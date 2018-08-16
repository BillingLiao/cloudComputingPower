var vm = new Vue({
	el:'#rrapp',
    showList: true,
    data:{
    	goodDetail:{},
    	goodParam:[],
		goodInfo: {},
		goodImage:[],
		goodImageIds:[],
		goodPrice:{},
		goodId:T.p('goodId'),
		initialPreviewConfig:[],
		uploadExtraData:[]
	},
      created:function() {
    	   Vue.nextTick(function () {
    		   var E = window.wangEditor;
    	        var editor = new E('#editor');
    	        // 配置服务器端地址
    	        editor.customConfig.uploadImgServer = '/upload';
    	        editor.create();
    	         }); 
    	 
      }
,
	methods: {
		insertGoodParam:function (type){
   		 	let goodParam = {};
   		  var addvalue1= $("#paramName").val();
           var addvalue2= $("#paramVal").val();
          if(addvalue1!==""&&addvalue2!==""){
          	goodParam.name = addvalue1;
          	goodParam.value = addvalue2;
          	goodParam.goodId = this.goodId;
        	vm.goodParam.push(goodParam);
        	$("#paramName").val("");
        	$("#paramVal").val("");
           } else{
              alert("请输入属性值")
          }
			 

   	}
	,delParam: function (id) {
   		var arr = [];
   		arr.push(id);
 		$.ajax({
			type: "POST",
		    url: baseURL + "good/goodparameter/delete",
            contentType: "application/json",
		    data: JSON.stringify(arr),
		    success: function(r){
		    	if(r.code === 0){
					alert('操作成功', function(index){
						getGoodParam();
						//vm.reload();
					});
				}else{
					alert(r.msg);
				}
			}
		});
	}, 
		goodInfoSaveOrUpdate: function (event) {
			var url =  "good/good/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.goodInfo),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){

							 vm.Jsreload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		}, reload: function(){
	    	location.href=baseURL + "modules/good/good.html";
	    }, Jsreload: function(){
	    	location.href="modules/good/good.html";
	    },
	    saveGoodDetail: function(){
	    	vm.goodDetail.goodId = vm.goodId;
	    	$.ajax({
				type: "POST",
			    url: baseURL + "good/gooddetail/update",
                contentType: "application/json",
			    data: JSON.stringify(vm.goodDetail),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.Jsreload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
	 	    },
    	saveGoodSpecPrice: function(){
    		$.ajax({
				type: "POST",
			    url: baseURL + "good/goodspecprice/saveGoodSpecPriceEntity",
                contentType: "application/json",
			    data: JSON.stringify(vm.goodPrice),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.Jsreload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
	 
        },saveGoodPam: function(){
    		$.ajax({
				type: "POST",
			    url: baseURL + "good/goodparameter/insertOrUpdateAllBatch",
                contentType: "application/json",
			    data: JSON.stringify(vm.goodParam),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							getGoodParam();
							//vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
	 
        },
	},
   
    
	mounted: function(){


	}
	
}) 

function getInfo(){
	$.get(baseURL + "good/good/info/"+vm.goodId, function(r){
        vm.goodInfo = r.good;
        var jsonStr = '{"filePath":"'+vm.goodInfo.picImg+'","goodId":"'+vm.goodInfo.goodId+'"}';
		 $.parseJSON(jsonStr);
		 var picImg =  vm.goodInfo.picImg;
		 var initialPreview =  picImg=='' ||picImg==null ? []:picImg;
		 
		var initialPreviewConfig =  picImg=='' || picImg==null ?[]:[{url:baseURL +"good/good/deleteImage",width:'120px',extra:$.parseJSON(jsonStr),key:1}];
        // 图片上传star
 	    $("#input-701").fileinput({
	        theme: 'fa',
	        uploadUrl: baseURL + "good/good/updateImage",
	        uploadAsync: false,
	        minFileCount: 1,
	        maxFileCount: 1,
	        overwriteInitial: false,
	        showRemove:false,
	        showUpload: false, 
	        language : 'zh',
	        initialPreview: initialPreview,
	        initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
	        initialPreviewFileType: 'image', // image is the default and can be overridden in config below
	        initialPreviewConfig:initialPreviewConfig,
	        uploadExtraData: {goodId:vm.goodInfo.goodId}

	    }).on('filedeleted', function(event, data, previewId, index) {
	    	vm.goodInfo.picImg='';
	    })
        getGoodSpec();
    });
}
function getDetail(){
	$.get(baseURL + "good/gooddetail/goodDetail?goodId="+vm.goodId,function(r){
		if(r.goodDetail){
			vm.goodDetail = r.goodDetail;
		}
     });
}
function getGoodParam(){
	$.get(baseURL + "good/goodparameter/goodparameterList?goodId="+vm.goodId, function(r){
        vm.goodParam = r.data;
     });
}


function getInitialPreviewConfig(){
 	var goodImage = vm.goodImage;
	var goodImageIds = vm.goodImageIds;
	for(var index in goodImage){
		 var jsonStr = '{"filePath[]":"['+goodImage[index]+']","picImgIds":['+goodImageIds[index]+']}';
		 $.parseJSON(jsonStr);
		vm.initialPreviewConfig.push(new Object());
		vm.initialPreviewConfig[index].url=baseURL +"good/goodimage/delete";
		vm.initialPreviewConfig[index].width="120px";
		vm.initialPreviewConfig[index].extra=$.parseJSON(jsonStr);
		vm.initialPreviewConfig[index].key=goodImage[index];
	}
}
 

function getImage(){
	$.get(baseURL + "good/goodimage/goodImageList",{goodId:vm.goodId}, function(r){
		$(r.goodImage).each(function(i,n){
			 vm.goodImage.push(n.picImg);
			 vm.goodImageIds.push(n.picImgId);
	 	})
 		getInitialPreviewConfig();
	 	  // 图片上传star
	    $("#input-702").fileinput({
	        theme: 'fa',
	        uploadUrl: baseURL + "good/goodimage/saveGoodImage",
	        uploadAsync: false,
	        minFileCount: 1,
	        maxFileCount: 5,
	        overwriteInitial: false,
	        showRemove:false,
	        showUpload: false, 
	        language : 'zh',
	        initialPreview: vm.goodImage,
	        initialPreviewAsData: true, // identify if you are sending preview data only and not the raw markup
	        initialPreviewFileType: 'image', // image is the default and can be overridden in config below
	        initialPreviewConfig: vm.initialPreviewConfig,
	        uploadExtraData: {
	            goodId: vm.goodId
 	        }
	    }).on('filesorted', function(e, params) {
	        console.log('file sorted', e, params);
	    }).on('fileuploaded', function(e, params) {
	     
	        console.log('file uploaded', e, params);
	    }).on('filebatchuploadsuccess', function(event, data, previewId, index) {
	   	 
	    	  getImage();
	    	   console.log('filebatchuploadsuccess', event, data, previewId, index);
	    }).on('filesuccessremove', function(event, data, index,e) {
	    	console.log(event, data, index,e);
	    	console.log("filesuccessremove");
	    	 
	    		index = jQuery.inArray(data, vm.goodImageIds)
	    		
	     
	    		var filePath = [];
	    		filePath.push(vm.goodImage[index]);
	    		deleteFiles(filePath);
	    		vm.goodImageIds.splice(index,1); 
	    		vm.goodImage.splice(index,1); 

	    	//return false;
	    }).on('filecleared', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filecleared");
	    	deleteFiles(vm.goodImage);
	    	vm.goodImage=[];
	    	vm.goodImageIds=[];
	    }).on('filedeleted', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filedeleted");
	    }).on('filepreremove', function(event, data, previewId, index) {
	    	console.log(event, data, previewId,index);
	    	console.log("filepreremove");
	    }).on('fileclear', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("fileclear");
	    }).on('filepreupload', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("filepreupload");
	    }).on('filebatchuploadcomplete', function(event, data, previewId, index) {
	    	getImage();
	    	console.log(event, data);
	    	console.log("filebatchuploadcomplete");
	    }).on('beforeSend', function(event, data, previewId, index) {
	    	console.log(event, data);
	    	console.log("beforeSend");
	    }).on('filebatchselected', function(event, data, previewId, index) {
	    	console.log("filebatchselected");
	     
	    }).on('fileselect', function(event, data, previewId, index) {
	    	console.log($('#input-702').fileinput('getPreview'));
	    	console.log($('#input-702').fileinput('getFileStack'));
	    	console.log(event, data,previewId,index);
	    	console.log("fileselect");
	    	var test = $(".file-preview-thumbnails .kv-preview-thumb").each(function(){
	    		  
	    		   console.info(    $(this).attr("id"));
	    		});
	    }).on('fileimagesloaded', function(event) {
	        console.log("fileimagesloaded");
	    }).on('fileloaded', function(event, file, previewId, index, reader) {
	        console.log("fileloaded");
	    }).on('fileimageloaded', function(event, file, previewId, index, reader) {
	        console.log("fileimageloaded");
	    });
	    // 图片上传end

     });
}
function getGoodSpec(){
	$.ajax({
		url : baseURL +"good/goodspecprice/getGoodSpecPricelist",//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
		// 数据发送方式
		type : "get",
		// 接受数据格式
		dataType : "json",
		// 要传递的数据
		data : {goodId:vm.goodId},
		// 回调函数，接受服务器端返回给客户端的值，即result值
		success : function(data) {
			console.info(data);
			 if(!data.data||data.data.length==0){
 				alert('请先添加商品规格数据', function(index){
  					location.href=  "modules/good/standard.html?id="+vm.goodInfo.goodId+"&categoryId="+vm.goodInfo.oneCategoryId;
				});
			} 
			 vm.goodPrice = data.data;
 		},
		error : function(data) {

			alert("查询失败" + data);

		}
	})
}
$(document).on("ready", function() { 
	getInfo();
	getGoodParam();
	getImage();
	getDetail();
    $('.tabwrap>div').hide();
    $('.tabwrap>div').eq(0).show();
    $('.tab_head>div').click(function(){
        var index=$(this).index();
        $(this).addClass('active');
        $(this).siblings().removeClass('active')
        $('.tabwrap>div').hide();
        $('.tabwrap>div').eq(index).show();
    })

  
})

function putin(){
	 
	var stock= $('#stock').val();
	var price= $('#price').val();
	var brand= $('#brand').val();
	var size= $('.size').val();
	var good = vm.goodPrice;
	for(var good in good){
		if(stock){
		vm.goodPrice[good].stock= stock;
		}
		if(price){
		vm.goodPrice[good].price=price;
			 
		}
		if(brand){
			vm.goodPrice[good].salesVolume= brand;
		 
		}
	}
	 $('#stock').val('');
	 $('#price').val('');
	 $('#brand').val('');
	 $('.size').val('');
	
}