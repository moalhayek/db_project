app.controller('bartenders',function(sharedProperties){

    this.labels = ['Bartender','Mon','Tue','Wed','Thu','Fri','Sat','Sun']

    this.sortType = 'name'


    this.setBartenders = function(barID,timeType){
        //REST endpoint with whichBar as param
        var options = {
            url: 'bartenders/getBartenders',
            params: {
                barId: barID,
            },
            method: 'GET',
            destination: 'dailyAverages'
        }

        var promise = sharedProperties.httpReq(options);

        promise.then(function(res){
            sharedProperties.setProperty('bartenders',res.bartenders)
            this.setData(timeType);
        }.bind(this));
    };

    this.data =[];

    this.setData = function(timeType){
        console.log('attempting to update data')
        var allData = sharedProperties.getProperty('bartenders');
        console.log(allData)
        var tempData = []
        var sales_name= timeType+'_avgs'
        console.log(sales_name)
        console.log(allData[0])
        console.log(allData[0][sales_name])
        allData.forEach(function(elem){
            var name = elem.name;
            var sales = elem[sales_name];
            var dataSet = {};
            dataSet.name = name;
            dataSet.monSales = sales[0]
            dataSet.tueSales = sales[1]
            dataSet.wedSales = sales[2]
            dataSet.thuSales = sales[3]
            dataSet.friSales = sales[4]
            dataSet.satSales = sales[5]
            dataSet.sunSales = sales[6]

            tempData.push(dataSet);
        });
        this.data = tempData
    }

    this.sortMap = {
        'Bartender': 'name',
        'Mon': 'monSales',
        'Tue': 'tueSales',
        'Wed': 'wedSales',
        'Thu': 'thuSales',
        'Fri': 'friSales',
        'Sat': 'satSales',
        'Sun': 'sunSales'
    }

    this.sortReverse = false

    this.changeSort = function(toWhat){
        var oldSort = this.sortType
        this.sortType = this.sortMap[toWhat];
        if(oldSort ==this.sortType) {
            this.sortReverse = !this.sortReverse;
        }else{
            this.sortReverse = false;
        }
    }

    this.total = true;

    this.disableCheck = function(time){
        if(time=='early'){
            return !this.late&&!this.total
        }else if(time=='late'){
            return !this.early&&!this.total
        }else{
            return !this.early&&!this.late
        }
    }
    this.switchData = function(time,checked){

        if(!checked){
            if(time=='early'){
                this.late = false;
                this.total = false;
                this.early = true;
            }else if(time=='late'){
                this.early = false;
                this.total = false;
                this.late = true;
            }else{
                console.log(time)
                this.early = false;
                this.late = false;
                this.total = true;
            }
            this.setData(time);
        }
    }

    this.early = false;
    this.late = false;

});