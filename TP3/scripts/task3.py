# Import libraries
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy import stats

# Creating dataset
data = pd.read_csv('jfreechart-test-stats.csv', sep=',', header=0)
less_eq = data.loc[data['TASSERT'] <= 20]
more = data.loc[data['TASSERT'] > 20]

def boxplot_metrics_calculator(data):
    l = data.quantile(0.25)
    m = data.quantile(0.5)
    u = data.quantile(0.75)
    d = u - l
    s = u + 1.5*d
    i = l - 1.5*d
    min = np.min(data)
    if i < min:
        i = min

    print('Lower quartile:\t\t',l)
    print('Median:\t\t\t',m)
    print('Upper quartile:\t\t',u)
    print('Interquartile range:\t',d)
    print('Lower limit:\t\t',i)
    print('Upper limit:\t\t',s)
    return (l,m,u,d,i,s)

print('Statistics for classes with 20 assertions or less:')
print('TLOC')
boxplot_metrics_calculator(less_eq.loc[:,'TLOC'])
print('WMC')
boxplot_metrics_calculator(less_eq.loc[:,'WMC'])
print()
print('Statistics for classes with more than 20 assertions:')
print('TLOC')
boxplot_metrics_calculator(more.loc[:,'TLOC'])
print('WMC')
boxplot_metrics_calculator(more.loc[:,'WMC'])