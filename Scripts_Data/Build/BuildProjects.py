import sys
import os
import datetime
import subprocess

#https://stackoverflow.com/questions/21377520/do-a-maven-build-in-a-python-script
class changeDir:
  def __init__(self, newPath):
    self.newPath = os.path.expanduser(newPath)

  # Change directory with the new path
  def __enter__(self):
    self.savedPath = os.getcwd()
    os.chdir(self.newPath)

  # Return back to previous directory
  def __exit__(self, etype, value, traceback):
    os.chdir(self.savedPath)


def build(*args):
    return subprocess.check_call(['mvn'] + list(args),shell=True)
    #return subprocess.check_output(['mvn'] + list(args),shell=True)

#https://coderwall.com/p/tjc9oa/for-those-who-build-multiple-maven-projects-at-once
def mvnbuild(cdir):
    cwd=os.getcwd()
    os.chdir(cdir)
    process = subprocess.Popen(
        #"mvn clean install -DskipTests=true"
        "mvn clean install",
        shell=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE
    )
    out, err = process.communicate()
    #log(out, dir)
    #print(out)
    errcode = process.returncode
    os.chdir(cwd)
    return (out, err, errcode)


try:
    clonedprojectfolder= sys.argv[1]
    #mavenargumenr=sys.argv[2]
except:
    print(" Plese provide proper arguments in the form python BuildProjects <FOLDE PATH where projects are cloned <MAVEN command to run>")


projectstobuild=[ i for i in os.listdir(clonedprojectfolder) if(os.path.isdir(os.path.join(clonedprojectfolder+i)))]
logfile=open('Build_log_'+str(datetime.datetime.now()),'a')

print("Processing projects in folder ::" + clonedprojectfolder)
projcount=len(projectstobuild)
failedprojcount=0
processedprojcount=0
print("Total projects to build "+ str(projcount))
#logfile.write("Total projects to build "+ str(projcount)+"\n")


for p in projectstobuild:
    path=p
    fullpath=os.path.join(clonedprojectfolder+path)
    #print(fullpath)
    if(os.path.isdir(fullpath)):
        currfilelog=open('path.log','a')             
        try:
            #https://stackoverflow.com/questions/21377520/do-a-maven-build-in-a-python-script
            if(not(p.startswith('.'))):
                print("Procesiing " + path)
                opofbuild=mvnbuild(fullpath)
                if("BUILD SUCCESS" in opofbuild[0]):    
                    print("Build done  for  project " + path+"\n")            
                    logfile.write("Build done  for  project " + path+"\n")
                else:
                    print("Build failed for  project " + path+"\n")
                    logfile.write("Build failed for  project " + path+"\n")
                    #logfile..write(op)
            '''
                with changeDir(fullpath):
                    # ****** NOTE ******: using shell=True is strongly discouraged since it possesses security risks
                    #subprocess.call(["mvn", "clean", "install"], shell=True)
                    #opofbuild=build("clean", "install")   
                    #opofbuild=build("Compile") 
    
                    #build("Compile")
                    #logfile.write("Compiled project " + path+"\n")
                '''
        
        except:
            print("Exception durin build or compile for project " + path+"\n")
            logfile.write("Exception durin build or compile for project " + path+"\n")
       

    

logfile.close( )