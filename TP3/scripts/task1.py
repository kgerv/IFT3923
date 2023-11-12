# Import libraries
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
 
# Creating dataset
data = pd.read_csv('TP3/jfreechart-test-stats.csv', sep=',', header=0)

# Extracting column for box plots
tloc = np.array(data.values[:, 1])
wmc = np.array(data.values[:, 2])
tassert = np.array(data.values[:, 3])

# Create figures
#fig, ax = plt.subplots(figsize=(10,7))
 
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

# show plot
plt.show()