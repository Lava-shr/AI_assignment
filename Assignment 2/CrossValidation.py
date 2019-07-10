'''
Takes the csv file as an input
Does 1NN, 5NN and NB with 10 fold cross validation
Prints the average accuracy of each classifiers

Can also be used for finding the confidence interval of those classifiers
'''
import sys
import math
import operator
import statistics 
import numpy  
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

    fold1 = []
    fold2 = []
    fold3 = []
    fold4 = []
    fold5 = []
    fold6 = []
    fold7 = []
    fold8 = []
    fold9 = []
    fold10 = []
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

    my_1NN = []
    my_5NN = []
    my_NB = []
    # fold1 as testing and rest as training data
    test = remove_class(fold1)
    train = []

    populate_train(train,fold2,fold3,fold4,fold5,fold6,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    cols = len(fold1[0]) - 1
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold1:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1

    my_1NN.append(right1/(len(fold1)))
    my_5NN.append(right2/(len(fold1)))
    my_NB.append(right3/(len(fold1)))

    # fold2 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold2)
    populate_train(train,fold1,fold3,fold4,fold5,fold6,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold2:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1

    my_1NN.append(right1/(len(fold2)))
    my_5NN.append(right2/(len(fold2)))
    my_NB.append(right3/(len(fold2)))

    # fold3 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold3)
    populate_train(train,fold1,fold2,fold4,fold5,fold6,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold3:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold3)))
    my_5NN.append(right2/(len(fold3)))
    my_NB.append(right3/(len(fold3)))


    # fold4 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold4)
    populate_train(train,fold1,fold2,fold3,fold5,fold6,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold4:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold4)))
    my_5NN.append(right2/(len(fold4)))
    my_NB.append(right3/(len(fold4)))

    # fold5 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold5)
    populate_train(train,fold1,fold2,fold3,fold4,fold6,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold5:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold5)))
    my_5NN.append(right2/(len(fold5)))
    my_NB.append(right3/(len(fold5)))

    # fold6 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold6)
    populate_train(train,fold1,fold2,fold3,fold4,fold5,fold7,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold6:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold6)))
    my_5NN.append(right2/(len(fold6)))
    my_NB.append(right3/(len(fold6)))

    # fold7 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold7)
    populate_train(train,fold1,fold2,fold3,fold4,fold5,fold6,fold8,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold7:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold7)))
    my_5NN.append(right2/(len(fold7)))
    my_NB.append(right3/(len(fold7)))

    # fold8 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold8)
    populate_train(train,fold1,fold2,fold3,fold4,fold5,fold6,fold7,fold9,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold8:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold8)))
    my_5NN.append(right2/(len(fold8)))
    my_NB.append(right3/(len(fold8)))

    # fold9 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold9)
    populate_train(train,fold1,fold2,fold3,fold4,fold5,fold6,fold7,fold8,fold10)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold9:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold9)))
    my_5NN.append(right2/(len(fold9)))
    my_NB.append(right3/(len(fold9)))

    # fold10 as testing and rest as training
    test.clear()
    train.clear()
    test = remove_class(fold10)
    populate_train(train,fold1,fold2,fold3,fold4,fold5,fold6,fold7,fold8,fold9)
    result = KNN(1,train,test)
    result2 = KNN(5,train,test)
    result3 = NB(train,test)
    right1 = 0
    right2 = 0
    right3 = 0
    i = 0
    for data in fold10:
        if result[i] == data[cols]:
            right1 += 1
        if result2[i] == data[cols]:
            right2 += 1
        if result3[i] == data[cols]:
            right3 += 1
        i += 1
    
    my_1NN.append(right1/(len(fold10)))
    my_5NN.append(right2/(len(fold10)))
    my_NB.append(right3/(len(fold10)))

    print('My_1NN accuracy: ' + str(statistics.mean(my_1NN) * 100))
    print('My_5NN accuracy: ' + str(statistics.mean(my_5NN) * 100))
    print('My_NB accuracy: ' + str(statistics.mean(my_NB) * 100))

    ''' For finding the confidence interval'''

    d_1NN_5NN = []
    d_1NN_NB = []
    d_5NN_NB = []

    for x in range(10):
        d_1NN_5NN.append(abs(my_1NN[x] - my_5NN[x]))
        d_1NN_NB.append(abs(my_1NN[x] - my_NB[x]))
        d_5NN_NB.append(abs(my_5NN[x] - my_NB[x]))

    print()
    print('d_mean: ' + str(statistics.mean(d_5NN_NB)))
    print(statistics.mean(d_5NN_NB) / statistics.stdev(d_5NN_NB))




# removing class from the fold
def remove_class(fold):
    cols = len(fold[0]) - 1
    rows = len(fold)
    test = []

    for data in fold:
        temp = data[:]
        del temp[cols]
        test.append(temp)

    return test

# Populate training data 
def populate_train(train,fold1,fold2,fold3,fold4,fold5,fold6,fold7,fold8,fold9):
    for data in fold1:
        train.append(data)
    for data in fold2:
        train.append(data)
    for data in fold3:
        train.append(data)
    for data in fold4:
        train.append(data)
    for data in fold5:
        train.append(data)
    for data in fold6:
        train.append(data)
    for data in fold7:
        train.append(data)
    for data in fold8:
        train.append(data)
    for data in fold9:
        train.append(data)









# K Nearest Neighbout Algorithm with training data, k values
# test data to identify their class
def KNN(k,train,test):
    num_rows_test = len(test)
    result = []

    # Calculation the results for testing data one at a time
    for i in range(num_rows_test):
        result.append(KNN_helper(k,train, test[i]))
    
    return result

# Calculation euclidean distance for the training list and test data
def cal_euclidean(train_row, test):
    num_cols = len(train_row)-1
    eucli = 0.0

    for i in range(num_cols):
        eucli += (float(train_row[i]) - float(test[i]))**2

    final_eucli = math.sqrt(eucli)
    return final_eucli

def KNN_helper(k,train, test):
    num_rows = len(train)
    num_cols = len(train[0]) - 1
    val = {}

    # Calculation distance with each rows of the training data with each testing data one at a time
    for i in range(num_rows):
        val[i] = cal_euclidean(train[i], test)
    
    # sorting dictionary by its values to get nearest neighbour
    sorted_val = sorted(val.items(), key=operator.itemgetter(1))

    class_list = []
    for i in range(k):
        class_list.append(train[sorted_val[i][0]][num_cols])
    
    return KNN_helper_2(class_list)

# prints the yes or no depending on which is repeated most
def KNN_helper_2(class_list):
    no_count = 0
    yes_count = 0
    for c in class_list:
        if c == 'no':
            no_count += 1
        elif c == 'yes':
            yes_count += 1
    
    if no_count == yes_count:
        return 'yes'
    elif no_count > yes_count:
        return 'no'
    else:
        return 'yes'


# Naive bayes Implementation
def NB(train,test):
    num_rows_test = len(test)

    mean_yes, mean_no, std_yes, std_no = NB_helper(train)

    result = []
    # Calculation the results for testing data one at a time
    for i in range(num_rows_test):
        result.append(NB_helper2(mean_yes,std_yes,mean_no,std_no,test[i],train))
    
    return result

def NB_helper(train):
    num_rows = len(train)
    num_cols = len(train[0]) - 1
    mean_yes = []
    mean_no = []
    std_yes = []
    std_no = []

    for i in range(num_cols):
        yes = []
        no = []
        for j in range(num_rows):
            if train[j][num_cols] == 'yes':
                yes.append(float(train[j][i]))
            elif train[j][num_cols] == 'no':
                no.append(float(train[j][i]))
        
        mean_yes.append(statistics.mean(yes))
        std_yes.append(statistics.stdev(yes))

        mean_no.append(statistics.mean(no))
        std_no.append(statistics.stdev(no))
    
    return mean_yes, mean_no, std_yes, std_no

def NB_helper2(mean_yes,std_yes,mean_no,std_no,test,train):
    # probability of Yes / No
    yes_prob, no_prob = cal_probality_y_n(train)

    num_cols = len(test)
    final_yes = []
    final_no = []

    for i in range(num_cols):
        x = float(test[i])

        # Probability given the class Yes
        f_yes = ( 1/(std_yes[i] * math.sqrt(2 *math.pi)) ) * (math.exp( - ( ((x - mean_yes[i])**2) / (2 * (std_yes[i] ** 2)) )))
        final_yes.append(f_yes)

        # Probability given the class No
        f_no = ( 1/(std_no[i] * math.sqrt(2 *math.pi)) ) * (math.exp( - (((x - mean_no[i])**2)/(2 * (std_no[i] ** 2))) ))
        final_no.append(f_no)
    
    # Calculation final probability
    final_prob_yes = numpy.prod(final_yes) * yes_prob
    final_prob_no = numpy.prod(final_no) *  no_prob

    if final_prob_yes > final_prob_no:
        return 'yes'
    elif final_prob_yes < final_prob_no:
        return 'no'
    else:
        return 'yes'
        
# Calculation yes and no probability
def cal_probality_y_n(train):
    num_cols = len(train[0]) - 1
    num_rows = len(train)
    yes_count = 0.0
    no_count = 0.0

    for i in range(num_rows):
        if(train[i][num_cols]) == 'yes':
            yes_count+=1
        elif (train[i][num_cols]) == 'no':
            no_count+=1
    return yes_count/num_rows , no_count/num_rows
    


if __name__ == '__main__':
    main()
