import sys
import git
import datetime

import subprocess

def git(*args):
    return subprocess.check_call(['git'] + list(args))

try:
    repourlfilename= sys.argv[1]
    folder_to_clonein=sys.argv[2]
except:
    print(" Plese provide proper arguments in the form python CloneProjecs <FileContainingRepoURLS> <FOLDE PATH in which to be cloned>")

logfile=open(repourlfilename+'_log_'+str(datetime.datetime.now()),'a')

print("Processing file with urls ::" + repourlfilename)
repofiledata=open(repourlfilename,'r')
allurls=repofiledata.readlines()
filecount=len(allurls)
failedfilecount=0
processedfilecunt=0
print("Total Files to process "+ str(filecount))
logfile.write("Total Files to process "+ str(filecount)+"\n")


for repo in allurls:
    repourl=repo.strip()
    repofolder=repo.split('/')[-1].strip()
    print(repofolder)
    print("Processing "+ repourl)
    try:
        print
        git('clone',repourl,folder_to_clonein+repofolder)
        logfile.write("Successfully processed " + repourl)
        processedfilecunt+=1
    except:
        print(" Failed to Clone " + repourl)
        logfile.write("Processein failed for  " + repourl+"\n")
        failedfilecount+=1


print("Files Suceessfuly procesed "+ str(processedfilecunt))
logfile.write("Files Suceessfuly procesed "+ str(filecount-failedfilecount)+"\n")
print("Files Failed "+ str(failedfilecount))
logfile.write("Files Failed "+ str(failedfilecount)+"\n")


logfile.close()