from faker import Faker
from random import *
import math

fake = Faker()

def generate_name(prof):
  return prof['name']

def generate_age(prof):
  return str(randint(21,60))

def generate_gender(prof):
  return prof['sex']

def generate_street_address(prof):
  return fake.street_address()

def generate_city(prof):
  return fake.city()

def generate_phone_num(prof):
  return fake.phone_number()

def generate_salary(prof):
  return str(abs(gauss(95000,35000)))

def generate_spending_per_night(prof):
  return str(gauss(50,500))

def generate_crowding_pref(prof):
  crowd_types = ['Not crowded','Moderately crowded','Very crowded']
  return choice(crowd_types)

def generate_relationship_status(prof):
  relationship_types = ['Single','Married']
  return choice(relationship_types)
 
#first we establish what our attributes are
drinker_attributes = ["name","age","gender","street_address","city","phone","salary","spending_per_night","crowding_pref","relationship_status"] 

#how many?
amount = 15000

#use a dict to map the attributes to their functions  
options = {'name': generate_name,
           'age': generate_age,
           'gender': generate_gender,
           'street_address': generate_street_address,
           'city': generate_city,
           'phone': generate_phone_num,
           'salary': generate_salary,
           'spending_per_night': generate_spending_per_night,
           'crowding_pref': generate_crowding_pref,
           'relationship_status': generate_relationship_status,
}

outputfile_name = 'drinker_table.csv'
outputfile = open(outputfile_name,'w')

#iterate for as many tuples as you need
for index in range(1,amount):
    tuple_list = []
    # generate random profile
    #iterate through all desired attributes
    random_profile = fake.profile()
    for attr in drinker_attributes:      
        new_attribute =  options[attr](random_profile)    #pass in profile
        tuple_list.append(new_attribute)
  #format as comma_delimited string 
    csv_string = ','.join(tuple_list)
    csv_string += '\n'
    outputfile.write(csv_string)        