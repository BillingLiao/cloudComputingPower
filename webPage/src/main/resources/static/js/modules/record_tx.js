var vm = new Vue({
	el:'#page',
	data:{
		account :{},
		cny: null,
		btc: null,
		btcTrue: null
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
            url: api + 'user/my',
            type:'POST',
            dataType:'json',
            data:{
                token:token
            },
            success:function(res){
                console.log(res);
                if(res.code==0){
                    vm.account = res.account;
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
    },
    methods:{
        allBtc: function(res){
            this.btc = this.account.btcBalance;
            this.btcTrue = this.btc-(this.btc*0.005);
        },
        allCny: function(res){
            this.cny = this.account.cnyBalance;
        }
    }
});