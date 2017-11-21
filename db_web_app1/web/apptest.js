(function(){
    var app = angular.module('test',[]);   

    app.controller('testCtrl',function(sharedProperties,$q,$timeout){

        this.data = {'goodbye': 'cruel world'}

        this.setter = function(){
            var options = {                
                method: 'GET',
                url: 'https://api.github.com/users/peterbe/gists',
                params: {'keyword': 'test'},
                destination: 'subs'            
            }
            promise = sharedProperties.httpReq(options);
            promise.then(function(res){
                console.log(res)
                console.log('promise worked')
                this.data = res.data[0].url; 
                console.log(this.data)
            }.bind(this));
        }

        this.getter = function(){
            return sharedProperties.getProperty('subs')
        }

        this.contructor = function(){
            //var promises = [];
            console.log('binding promises')
            this.setter();
            /*promises.push(this.setter());
            $q.all(promises).then(function(res){
                for (var env in res){
                    console.log(env)
                }
                console.log(res)
                console.log('attempting to access data on this scope')
                console.log(sharedProperties.getProperty('subs'))
                this.data = res[0]
                console.log(this.data)
                console.log('this.data inside constructor')
            }.bind(this));*/
            

            
        }
    });
})();