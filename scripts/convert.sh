PATH=/usr/local/bin:$PATH
abcm2ps -x -O - $1 | ps2pdf - $2
