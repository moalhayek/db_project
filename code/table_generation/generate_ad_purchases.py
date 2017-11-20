
from random import *
from faker import Faker
import datetime

def random_date(start,end):
    delta = end-start
    int_delta = (delta.days * 24 * 60 * 60) + delta.seconds
    random_second = randrange(int_delta)
    return start+datetime.timedelta(seconds=random_second)
fake = Faker()
outputfile_name = 'ad_purchases_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['bar_id','platform_id','start_date','end_date','total_clicks']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)


#each iteration refers to a person

ad_platform_list = range(1,11)
bar_list = range(1,201)

start = datetime.date( year=2016, month =1,day=1)
middle = datetime.date(year=2016,month=6,day=1)
end = datetime.date(year = 2016,month =12, day = 31)

for bar_id in bar_list:
    ad_amt = randint(0,3) # number of ads this bar purchases
    ad_list = sample(ad_platform_list,ad_amt) #select freq_amt bars from bar id list
    
    for ad_platform in ad_list:
        ad_start_date = random_date(start,middle).isoformat()
        ad_end_date = random_date(middle,end).isoformat()        

        mean = 2000
        sigma = 1200
        clicks = str(abs(int(gauss(mean,sigma))))

        csv_str = '{0},{1},{2},{3},{4}\n'.format(str(bar_id),str(ad_platform),ad_start_date,ad_end_date,clicks)
        outputfile.write(csv_str)



outputfile.close()

