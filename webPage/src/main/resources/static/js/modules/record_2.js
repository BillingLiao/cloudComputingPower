var vm = new Vue({
	el:'#page',
	data:{
		presentRecordList :{}
	},
	created: function(){
	   var token = window.localStorage.getItem('token');
       if(token == null){
            swal("请先登录", {
                 buttons: false,
                 timer: 2000,
               }).then((value) => {
                  window.location.href='login.html'
              });
              return;
       }
       $.ajax({
           url: api + 'presentRecord/my',
           type:'POST',
           dataType:'json',
           data:{
               token: token
           },
           success:function(res){
               if(res.code==0){
                   vm.presentRecordList = res.presentRecordList;
               }else{
                   swal({
                   text: res.msg,
                   icon: "error",
                   button: "返回",
                  });
               }
           },
           error: function(res) {
                   swal({
                   text: res.msg,
                   icon: "error",
                   button: "返回",
                  });
           }
       });
    }
});