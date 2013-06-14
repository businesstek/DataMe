# BT DataMe Shell file to set up and execute R script
#
# LIB_ENV = "echo $LD_LIBRARY_PATH";
# LIB_PATH = "/data/local/gcc/lib";
# LIB_CMD = "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/gcc/lib";
# BIN_ENV = "echo $PATH";
# BIN_PATH= "/data/local/gcc/bin";
# BIN_CMD = "export PATH=$PATH:/data/local/gcc/bin";

rm /data/local/bt/bt1.txt
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/data/local/gcc/lib
/data/local/gcc/bin/R -f /data/local/bt/bt1.R
