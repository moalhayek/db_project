var app = angular.module('db-project');

//this will be controler for general dash, might not even need this
app.controller('generalDashController',function(){

});

//this will be controller for general music modules
app.controller('musicTrendController',function(sharedProperties,$q){
    this.setTrends = function(minAge,maxAge){
        //REST endpoint
        var options = {
            url: 'music/getMusicTrends',
            params: {
                lowerAge: minAge,
                upperAge: maxAge,
            },
            method: 'GET',
            destination: 'musicTrends'//this is the global property you want to update
        }
        var promise = sharedProperties.httpReq(options); // uncomment when server is up
        //var results = {'musicData': [{'genre': 'Gospel', 'listenerCount': 25}, {'genre': 'Rock', 'listenerCount': 35}, {'genre': 'Techno', 'listenerCount': 13}, {'genre': 'Hip-hop', 'listenerCount': 40}]};
        promise.then(function(res){
            sharedProperties.setProperty('musicTrends',res.data);
            this.setData();
            this.setLabels();
        }.bind(this))
        //sharedProperties.setProperty('musicTrends',results);
    };

    this.getTrends = function(){
        return sharedProperties.getProperty('musicTrends').musicData;
    }

    this.data = [];
    this.labels =['Gospel','Rock','Techno','Hip-hop'];
    this.options = {legend: {display: true}};

    this.refreshChart = function(min,max){
                
        this.setTrends(min,max)
        //this function is now deprecated
        
        console.log(this.getTrends())
    }

    this.setData = function(){
        var tempData = [];

        var averageArr = this.getTrends();
        console.log(averageArr)

        averageArr.forEach(function(elem){
            tempData.push(elem.listenerCount);
        })

        this.data = tempData;
    };

    this.setLabels = function(){
        var tempLabels = [];

        var averageArr = this.getTrends();
        console.log(averageArr)

        averageArr.forEach(function(elem){
            tempLabels.push(elem.genre);
        })

        this.labels = tempLabels;
    }

    this.slider = {
        minValue: 21,
        maxValue: 30,
        options: {
            floor: 21,
            ceil: 60,
            step: 1,
            minRange: 1
        }
    };
});