#BusinessTek dummy autogenerate data 6/6/2013
x <- 1:20
w <- 1 + sqrt(x)/2
y= x + rnorm(x)*w
dummy <- data.frame(x=x, y= y)
dump(ls(dummy),"/data/local/bt/bt1.txt")