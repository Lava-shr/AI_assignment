'''
Converts the given CSV file to 10-fold.
Cross validation and straification are applied as well

Takes input as CSV File and returns its 10 folds

'''
import sys
import csv

def main():
    train = []

    with open(sys.argv[1], 'r') as f:
        for line in f.readlines():
            data = line.strip().split(',')
            train.append(data)
    
    yes = []
    no = []

    num_cols = len(train[0]) - 1
    num_rows = len(train)

    for data in train:
        if data[num_cols] == 'yes':
            yes.append(data)
        else:
            no.append(data)
    
    # finding number of yes and no in each fold
    no_yes = len(yes)//10
    no_no = len(no)//10

    fold1 = ['fold1']
    fold2 = ['fold2']
    fold3 = ['fold3']
    fold4 = ['fold4']
    fold5 = ['fold5']
    fold6 = ['fold6']
    fold7 = ['fold7']
    fold8 = ['fold8']
    fold9 = ['fold9']
    fold10 = ['fold10']
    i = 1

    for data in yes:
        if i == 1:
            fold1.append(data)
        if i == 2:
            fold2.append(data)
        if i == 3:
            fold3.append(data)
        if i == 4:
            fold4.append(data)
        if i == 5:
            fold5.append(data)
        if i == 6:
            fold6.append(data)
        if i == 7:
            fold7.append(data)
        if i == 8:
            fold8.append(data)
        if i == 9:
            fold9.append(data)
        if i == 10:
            fold10.append(data)
        i +=1
        if i > 10:
            i = 1
    
    if i > 10:
        i = 1
    for data in no:
        if i == 1:
            fold1.append(data)
        if i == 2:
            fold2.append(data)
        if i == 3:
            fold3.append(data)
        if i == 4:
            fold4.append(data)
        if i == 5:
            fold5.append(data)
        if i == 6:
            fold6.append(data)
        if i == 7:
            fold7.append(data)
        if i == 8:
            fold8.append(data)
        if i == 9:
            fold9.append(data)
        if i == 10:
            fold10.append(data)
        i +=1
        if i > 10:
            i = 1
    
    # Need to add some new lines and delete commas in first line of each folds
    with open('pima-folds2.csv', 'w') as csvFile:
        writer = csv.writer(csvFile)
        writer.writerows(fold1)
        writer.writerows(fold2)
        writer.writerows(fold3)
        writer.writerows(fold4)
        writer.writerows(fold5)
        writer.writerows(fold6)
        writer.writerows(fold7)
        writer.writerows(fold8)
        writer.writerows(fold9)
        writer.writerows(fold10)
    
    csvFile.close()
    

if __name__ == '__main__':
    main()
