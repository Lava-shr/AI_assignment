import sys
import math
import operator
import statistics 
import numpy  

def main():
    algo = sys.argv[3]
    training = []
    testing = []

    with open(sys.argv[1], 'r') as f:
        for line in f.readlines():
            data = line.strip().split(',')
            training.append(data)

    with open(sys.argv[2], 'r') as f:
        for line in f.readlines():
            data = line.strip().split(',')
            testing.append(data)
    if(algo == "NB"):
        NB(training, testing)
    else:
        k = int(algo[0])
        KNN(k,training,testing)

# K Nearest Neighbout Algorithm with training data, k values
# test data to identify their class
def KNN(k,train,test):
    num_rows_test = len(test)

    # Calculation the results for testing data one at a time
    for i in range(num_rows_test):
        KNN_helper(k,train, test[i])

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
    #print(class_list)
    KNN_helper_2(class_list)

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
        print('yes')
    elif no_count > yes_count:
        print('no')
    else:
        print('yes')

# Naive bayes Implementation
def NB(train,test):
    num_rows_test = len(test)

    mean_yes, mean_no, std_yes, std_no = NB_helper(train)

    # Calculation the results for testing data one at a time
    for i in range(num_rows_test):
        NB_helper2(mean_yes,std_yes,mean_no,std_no,test[i],train)

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
        try:
            std_yes.append(statistics.stdev(yes))
        except:
            std_yes.append(1.0)

        mean_no.append(statistics.mean(no))
        try:
            std_no.append(statistics.stdev(no))
        except:
            std_yes.append(1.0)

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
        print('yes')
    elif final_prob_yes < final_prob_no:
        print('no')
    else:
        print('yes')
        
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


    #print(( 1/(8 * math.sqrt(2 *math.pi)) ) * (math.exp( - ( ((60-74.6)**2) / (2 * (8 ** 2)) ))))
