sub(){
    echo "################################################################################"
    echo "## " "$1"
    echo "################################################################################"
    java -classpath ./:args4j-2.33.jar Shell $*
}

javac -classpath args4j-2.33.jar Shell.java

sub
sub "-v"
sub "-h"
sub "sub1"
sub "sub1 -h"
sub "sub1 -i aaa -o bbb"
sub "sub2"
sub "sub2 -h"
sub "hoge"
sub "sub1 -s"

