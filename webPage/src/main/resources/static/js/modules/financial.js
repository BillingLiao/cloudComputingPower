if(window.localStorage.getItem('token')==null){
     window.location.href='login.html'
}

var vm = new Vue({
	el:'#page',
	data:{
		account :{},
		userFinancial :{}
	},
	created: function(){
	    var token = window.localStorage.getItem('token');
	    $.ajax({
            url: api + 'user/detailed',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                console.log(res);
                if(res.code==0){
                    console.log(res.account);
                    vm.account = res.account;
                    vm.userFinancial = res.userFinancial;
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