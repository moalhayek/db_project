var app = angular.module('db-project');

app.factory('sharedProperties',function($http,$timeout){
    var properties = {
        'barNames': []
        //add whatever properties we want here, JSON style
    };

    return{
        //simple getter
        getProperty: function(whichProperty){
            return properties[whichProperty];
        },
        //simple setter
        setProperty: function(whichProperty,whichVal){
            properties[whichProperty] = whichVal;
        },
        //function template for http request, just need params as JSON
        httpReq: function(options){
            var httpRequest = $http({
                method: options.method,
                url: options.url,
                params: options.params
            }).then(function mySuccess(response){
                console.log('the request went well!');
                $timeout(function(){
                    if(options.destination != null){
                        properties[options.destination] = response.data;
                    }
                });
            },function myError(response){
                console.log(response.statusText);
                console.log('the function failed horribly');
            })
        }
    }
});