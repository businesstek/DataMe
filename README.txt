/**  README  6/7/2013  BusinessTek
* This DataMe app uses R to generate and display data
*
*  PREP
*  follow instructions from "getting-started:installation:android [R Wiki]"
*  Last modified: 2012/07/16 by cielavenir
*  on first time check R exists. If not perform the following
*  will eventually be automated with in the Android App

adb shell mkdir /data/local/gcc
adb push android_gcc_r2a.tar.bz2 /data/local/gcc
adb push android_gcc_supplement.tar.bz2 /data/local/gcc
adb push android_R_r1a2.tar.bz2 /data/local/gcc
adb shell
cd /data/local/gcc
tar xjf android_gcc_r2a.tar.bz2
tar xjf android_gcc_supplement.tar.bz2
tar xjf android_R_r1a2.tar.bz2
mkdir /data/local/bt
cp ma1.R /data/local/bt


* EXECUTE R
* Now just autogenerate some data and write to text file
* Contents of R batch file "bt1.R" 
#BusinessTek dummy autogenerate data 6/6/2013
x <- 1:20
w <- 1 + sqrt(x)/2
y= x + rnorm(x)*w
dummy <- data.frame(x=x, y= y)
dump(ls(dummy),"/data/local/bt/bt1.txt")


* ANDROID SYSTEM COMMANDS
* commands to run from app
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/gcc/lib
export PATH=$PATH:/data/local/gcc/bin
R -f /data/local/bt/bt1.R




x <- 1:20

rm(var)
R -f file
