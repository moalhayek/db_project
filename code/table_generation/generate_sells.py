from random import *
import numpy as np

#gonna import the beers table
inputfile_name = 'beers_table.csv'
inputfile = open(inputfile_name,'r')

price_dict = {}

carriage_lines  = inputfile.readlines()
lines = carriage_lines[0].split('\r')
for line in lines[1:]:
    attr_list = line.strip().split(',')   
    price_dict[attr_list[0]] = attr_list[4]  


inputfile.close()


outputfile_name = 'sells_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['bar_id','beer_id', 'is_on_tap', 'price']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

bar_list = range(1,201)
beer_list = range(0,100)

for bar in bar_list:

    beer_amt = randint(10,40) # number of beers this bar sells
    beer_sells_list = sample(beer_list,beer_amt) #select beer_amt beers from beer id list
    
    on_tap_prob = random()	#random float for probability
    #person_str = str(person)
    for beer in beer_sells_list:
        manf_price = float(price_dict[str(beer)])

        #This is kinda arbitrary, I just did it by inspection and felt this to be a reasonable method
        price_mult = uniform(2,4)
        price_additive = uniform(2,4)

        beer_price = (price_mult*manf_price) + price_additive

    	is_on_tap = np.random.choice([0,1], p=[1-on_tap_prob, on_tap_prob])

        csv_str = '{0},{1},{2},{3:0.2f}\n'.format(str(bar),str(beer),str(is_on_tap),beer_price)
        outputfile.write(csv_str)

outputfile.close()