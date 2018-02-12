# matrix-sequence-matching
A simple 2D character matrix match implementation

# Build Instructions
To build and run this project, you need to have JDK 1.8 and maven installed 
on your computer.

Simply execute: 

`mvn clean package`

in a command line to build the artifact.

After building the artifact successfully, go to target directory and
run:

`java -jar matrix-sequence-matching-0.0.1.jar {path/to/matrix/file}`

Note that if you don't specify any matrix file path, a default
matrix file will be used which is located 
in the resource directory of project files.


