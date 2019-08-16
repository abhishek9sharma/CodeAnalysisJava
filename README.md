# Java Code Analyzer (Generating AST For java files/projects)

## Requirments
*Java, Eclipse, Git, Maven and Python 2.7.12 + associated pip* should be installed on the system


## Steps to run
### Preferably, run the below commands with sudo/admin privileges


1. Navigate to a folder on your machine say *yourdir* and then clone the Repo to a folder say using the below command

            test@testmachine:~cd yourdir
            test@testmachine:~/yourdir$ git clone https://github.com/abhishek9sharma/CodeAnalysisJava 


2. Run the following commands to download the github projects whose AST you want to get 

    - Commands for Linux (verfied on Ubuntu 16.04.6 LTS):   
            
            test@testmachine:~/yourdir$ cd CodeAnalysisJava
            test@testmachine:~/yourdir/CodeAnalysisJava$ cd ProjectsToParse
            test@testmachine:~/yourdir/CodeAnalysisJava/ProjectsToParse$ python CloneProjects.py projectstobecloned clonedprojects/
        
    - Following are the default settings/locations :

        - [projectstobecloned](CodeAnalysisJava/ProjectsToParse/projectstobecloned) : text file containing the list of projects to be stored
        - [clonedprojects](CodeAnalysisJava/ProjectsToParse/clonedprojects) : folder which will  contian the cloned projects

    

3. Run the following commands to build the github projects whose AST you want to get 

    - Commands for Linux (verfied on Ubuntu 16.04.6 LTS):   
            
            test@testmachine:~/yourdir/CodeAnalysisJava/ProjectsToParse$ python BuildProjects.py clonedprojects/
            test@testmachine:~/yourdir/CodeAnalysisJava/ProjectsToParse$ cd  clonedprojects
            test@testmachine:~/yourdir/CodeAnalysisJava/ProjectsToParse/clonedprojects$ ./command.sh
            
            
            
    - Following are the default settings/locations :

        - [clonedprojects](CodeAnalysisJava/ProjectsToParse/projectstobecloned) : folder which will  contian the cloned projects and Build Files
        - [alljavafiles.lst](CodeAnalysisJava/ProjectsToParse/clonedprojects/alljavafiles.lst) : list of .java files whose AST is to be passed


5. TO DO : Commands to compile/build the javacodeanalyzer project form command line
6. TO DO : Commands to generate ASTs
