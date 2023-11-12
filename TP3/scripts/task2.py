# modified from: https://www.w3schools.com/python/python_ml_linear_regression.asp
# Import libraries
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from scipy import stats
 
# Create a scatter plot with data x and y and apply a linear regression model
def plot_linear_reg(x,y, title, x_label, y_label, path=None, show=False):
    slope, intercept, r, p, std_rr = stats.linregress(x,y)
    
    def line(x):
        return slope * x + intercept
    
    model = list(map(line, x))

    plt.figure(figsize=(8, 4))
    plt.scatter(x, y)
    plt.plot(x, model)
    plt.title(title)
    plt.xlabel(x_label)
    plt.ylabel(y_label)
    if path != None:
        plt.savefig(path, bbox_inches='tight')
    if show:
        plt.show()
    return (slope, intercept, r, p, std_rr)

# Creating dataset
data = pd.read_csv('TP3/jfreechart-test-stats.csv', sep=',', header=0)

# Extracting column values
tloc = np.array(data.loc[:,'TLOC'])
wmc = np.array(data.loc[:,'WMC'])
tassert = np.array(data.loc[:,'TASSERT'])

# TLOC & TASSERT regression & correlation
title = 'Regression lineaire de TASSERT par TLOC'
x_label = 'TLOC'
y_label = 'TASSERT'
path = 'TP3/figures/tloc_tassert_reg.pdf'
tloc_tassert = plot_linear_reg(tloc, tassert, title, x_label, y_label, path)

# WMC & TASSERT regression & correlation
title = 'Regression lineaire de TASSERT par WMC'
x_label = 'WMC'
y_label = 'TASSERT'
path = 'TP3/figures/wmc_tassert_reg.pdf'
tloc_tassert = plot_linear_reg(wmc, tassert, title, x_label, y_label, path, True)

