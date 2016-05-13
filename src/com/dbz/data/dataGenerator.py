from faker import Factory
from datetime import date
import random
import csv

fake = Factory.create('en_US')

def generateAddresses(n):
    with open('csv/addresses.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            street = fake.building_number()+" "+fake.street_name()
            street2 = " "
            if random.randrange(n) < i:
                street2 = fake.secondary_address()
            city = fake.city()
            state = fake.state_abbr()
            zipcode = fake.postalcode()
            writer.writerow([street,street2,city,state,zipcode])

def generateNames(n):
    with open('csv/names.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            MI = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
            firstname = fake.first_name()
            lastname = fake.last_name()
            mi = MI[random.randrange(26)]
            writer.writerow([firstname,mi,lastname,i+1])

def generateDates(n):
    with open('csv/memberships.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            start_date = date.today().replace(day=1, month=1).toordinal()
            end_date = date.today().replace(day=31, month=12, year=2017).toordinal()
            random_day = date.fromordinal(random.randint(start_date, end_date))
            writer.writerow([i+1,random_day])

def generateJobs():
    with open('csv/jobs.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        jobs = ["VETERINARIAN","VETERINARY TECHNICIAN","ANIMAL CURATOR","ZOOLOGIST","KEEPER","REGISTRAR","GENERAL CURATOR",
        "ZOO DIRECTOR","ASSISTANT DIRECTOR","DIRECTOR","CURATOR","AFFAIRS MANAGER","OFFICER","MANAGER","COORDINATOR",
        "VOLUNTEER","HEAD KEEPER","SENIOR KEEPER","JANITOR"]
        for job in jobs:
            writer.writerow([job])

def randomEmployee(n):
    with open('csv/employee.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            job = random.randrange(1,20)
            salary = random.randrange(15000,40000,2)
            writer.writerow([i+1,salary,job])

def generateLocation(n):
    locations = ["Bird Sancuatry","Reptile House","Safari Zone","Winter World","Forest Zone","Bug Zone"]
    capacity1 = [275,200,250,240,275,320]
    capacity2 = [150,170,260,170,230,290]
    with open('csv/location.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,6):
            writer.writerow([locations[i],capacity1[i]])
    with open('csv/exhibit.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,6):
            writer.writerow([i+1,capacity2[i]])
    with open('csv/employee_exhibit.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            worksin = random.randrange(6)
            writer.writerow([i+1,worksin+1])



def generateAnimals(n):
    birds = ["Sparrow","Hawk","Dove","Eagle","Falcon","Emu","Ostrich","Flamingo","Peacock","Swan"]
    reptile = ["Alligator","Komodo Dragon","Anaconda","Cameleon","Sea Turtle","Frog","Crocodile","Lizard","Iguana","Viper"]
    safari = ["Lion","Zebra","Giraffe","Elephant","Leopard","Rhino","Cheetah","Gorilla","Hippo","Warthog"]
    winter = ["Penguin","Polar Bear","Walrus","Seal","Narwhal","Wolf","Moose","Ox","Reindeer","Puffin"]
    forest = ["Bear","Owl","Badger","Bat","Deer","Fox","Rabbit","Tiger","Hedgehog","Coyote"]
    bug = ["Ant","Bee","Butterfly","Beetle","Grasshopper","Mantis","Dragonfly","Ladybug","Fly","Worm"]
    locationIds = {0:birds,1:reptile,2:safari,3:winter,4:forest,5:bug}
    with open('csv/food.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for k in locationIds:
            for a in locationIds[k]:
                amount = round(random.uniform(500,4000),2)
                food = a+" Food"
                writer.writerow([food,amount])
    animalid={}
    index = 0
    with open('csv/animal_class.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for k in locationIds:
            for a in locationIds[k]:
                animalid[a] = index
                index += 1
                writer.writerow([a])
    with open('csv/animal.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,n):
            ls = locationIds.keys()
            g = random.randrange(0,2)
            age = random.randrange(0,11)
            name = ""
            gen = ""
            exhibt = random.sample(ls,1)
            animal = random.sample(locationIds[exhibt[0]],1)
            a = animal[0]
            if g == 0:
                name = fake.first_name_male()
                gen ="M"
            else:
                name = fake.first_name_female()
                gen = "F"
            writer.writerow([name,animalid[a],exhibt[0],gen,age])
    with open('csv/eats.csv','w') as csvfile:
        writer = csv.writer(csvfile)
        for i in range(0,60):
            eats = random.randrange(1,11)
            writer.writerow([i+1,i+1,eats])


def main():
    generateNames(3000)
    generateAddresses(3000)
    generateDates(3000)
    generateJobs()
    randomEmployee(1700)
    generateLocation(1700)
    generateAnimals(1200)


main()