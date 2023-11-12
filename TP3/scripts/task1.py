# Import libraries
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
 
# Creating dataset
data = pd.read_csv('TP3/jfreechart-test-stats.csv', sep=',', header=0)

# Extracting column for box plots
tloc = np.array(data.loc[:,'TLOC'])
wmc = np.array(data.loc[:,'WMC'])
tassert = np.array(data.loc[:,'TASSERT'])
 
# Creating plot: TLOC
plt.figure(figsize=(9, 3))
plt.boxplot(tloc, vert=False, manage_ticks=True)
plt.title('Box plot des TLOC du projet JFreeChart')
plt.xlabel('Values')
plt.yticks([])
plt.savefig('TP3/figures/tloc.pdf', bbox_inches='tight')

# Creating plot: WMC
plt.figure(figsize=(9, 3))
plt.boxplot(wmc, vert=False, manage_ticks=True)
plt.title('Box plot des WMC du projet JFreeChart')
plt.xlabel('Values')
plt.yticks([])
plt.savefig('TP3/figures/wmc.pdf', bbox_inches='tight')

# Creating plot: TLOC
plt.figure(figsize=(9, 3))
plt.boxplot(tassert, vert=False, manage_ticks=True)
plt.title('Box plot des TASSERT du projet JFreeChart')
plt.xlabel('Values')
plt.yticks([])
plt.savefig('TP3/figures/tassert.pdf', bbox_inches='tight')

# Show plot, muted by default
# plt.show()

# Calculate the metrics from slide 7 of chapter 7
# Print the values in command line
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

print('Box plot metrics for TLOC')
print('-------------------------')
tloc_metrics = boxplot_metrics_calculator(data.loc[:,'TLOC'])
print('-------------------------\n')
print('Box plot metrics for WMC')
print('-------------------------')
wmc_metrics = boxplot_metrics_calculator(data.loc[:,'WMC'])
print('-------------------------\n')
print('Box plot metrics for TASSERT')
print('-------------------------')
tassert_metrics = boxplot_metrics_calculator(data.loc[:,'TASSERT'])
print('-------------------------\n')