if(window.localStorage.getItem('token')==null){
    window.location.href='login.html'
}

var vm = new Vue({
	el:'#page',
	data:{
		orderRecordList :{}
	},
	created: function(){
	    var token = window.localStorage.getItem('token');
	    $.ajax({
            url: api + 'orderRecord/my',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                console.log(res);
                if(res.code==0){
                    vm.orderRecordList = res.orderRecordList;
                }else{
                    console.log(res);
                }
            },
            error: function(res) {
                console.log(res);
            }
        });
    }
});