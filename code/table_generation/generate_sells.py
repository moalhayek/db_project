from random import *
import numpy as np

outputfile_name = 'sells_table.csv'
outputfile = open(outputfile_name,'w')

attr_list = ['bar_id','beer_id', 'is_on_tap', 'price']
csv_str = ','.join(attr_list) + '\n'
outputfile.write(csv_str)

drinker_amt = 10000
#each iteration refers to a person

beer_list = range(0,100)

for bar in range(1,201):
    beer_amt = randint(10,40) # number of beers this bar sells
    beers_list = sample(beer_list,beer_amt) #select beer_amt beers from beer id list
    
    on_tap_prob = random()	#random float for probability
    #person_str = str(person)
    for beer in beers_list:
    	is_on_tap = np.random.choice(range(0,1), p=[1-on_tap_prob, on_tap_prob])

        #csv_str = '{0},{1}\n'.format(person_str,bar)
        #outputfile.write(csv_str)

outputfile.close()