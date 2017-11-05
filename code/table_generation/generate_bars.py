from faker import Faker
from random import *
import numpy as np
import math

fake = Faker()

def generate_street_address():
    return fake.street_address()

def generate_city():
    return fake.city()

def generate_state():
    return fake.state_abbr()

def generate_liquor_license(state):
    license_str = state
    for i in range(0,5):
        license_str += str(randint(0,9))

    return license_str


bar_name_filename = '../../bar_names.csv'
bar_name_file = open(bar_name_filename,'r')

names = bar_name_file.readlines()
bar_name_file.close()

names_list = names[0].split('\r')

output_filename = 'bar_table.csv'
output_file = open(output_filename,'w')

index = 1

attr_list = ['id','name','license','state','city','street_address']
csv_string = ','.join(attr_list) + '\n'
output_file.write(csv_string)



for line in names_list[1:]:
    tuple_list = []

    state = generate_state()

    tuple_list.append(str(index))
    tuple_list.append(line.strip()) #this is the bar name
    tuple_list.append(generate_liquor_license(state))
    tuple_list.append(state)
    tuple_list.append(generate_city())
    tuple_list.append(generate_street_address())
    

    csv_string = ','.join(tuple_list) + '\n'
    output_file.write(csv_string)

    index +=1

output_file.close()

