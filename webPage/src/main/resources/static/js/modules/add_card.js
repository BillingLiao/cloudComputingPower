var vm = new Vue({
	el:'#page',
	data:{
		bank : {}
	},
	created: function(){
       var token = window.localStorage.getItem('token');
        $.ajax({
           url: api + 'bank/find',
           type:'POST',
           dataType:'json',
           data:{
               token:token
           },
           success:function(res){
               console.log(res);
               if(res.code==0){
                   console.log(res.bank);
                   vm.bank = res.bank;
               }else{
                   console.log(res);
               }
           },
           error: function(res) {
               console.log(res);
           }
       });
    },
    methods:{
        save: function(){
            var token = window.localStorage.getItem('token');
            var bank = vm.bank;
            $.ajax({
               url: api + 'bank/add',
               type:'POST',
               dataType:'json',
               data:{
                   token: token,
                   cardNumber: bank.cardNumber,
                   openingBank: bank.openingBank,
                   trueName: bank.trueName,
                   bankId: bank.bankId
               },
               success:function(res){
                   console.log(res);
                   if(res.code==0){
                       console.log(res.bank);
                       vm.bank = res.bank;
                       window.location.href='account.html';
                   }else{
                       console.log(res);
                   }
               },
               error: function(res) {
                   console.log(res);
               }
           });
        }
    }

});