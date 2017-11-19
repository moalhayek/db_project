
from random import *
from faker import Faker

fake = Faker()
outputfile_name = 'ad_purchases_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['bar_id','platform_id','start_date','end_date','total_clicks']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)


#each iteration refers to a person

ad_platform_list = range(1,11)
bar_list = range(1,201)

for bar_id in bar_list:
    ad_amt = randint(0,3) # number of ads this bar purchases
    ad_list = sample(ad_platform_list,ad_amt) #select freq_amt bars from bar id list
    
    for ad_platform in ad_list:
        date_obj = fake.date_between(start_date="-5y",end_date="-2y")
        start_date = '{0}-{1}-{2}'.format(str(date_obj.year),str(date_obj.month),str(date_obj.day))
        
        date_obj = fake.date_between(start_date="-2y",end_date="today")
        end_date = '{0}-{1}-{2}'.format(str(date_obj.year),str(date_obj.month),str(date_obj.day))

        mean = 2000
        sigma = 1200
        clicks = str(abs(int(gauss(mean,sigma))))

        csv_str = '{0},{1},{2},{3},{4}\n'.format(str(bar_id),str(ad_platform),start_date,end_date,clicks)
        outputfile.write(csv_str)



outputfile.close()

