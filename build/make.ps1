set-executionpolicy unrestricted
cd ..;
cd ..;
$path3="$pwd\JRTSC\src" -replace "\\","/";
cd JRTSC;
cd src;
cd ..;
$dir=pwd;
cd src;
$path1= "$dir\lib\JNativeHook.jar" -replace "\\","/";
$path2="$dir" -replace "\\","/"
$buildPaths="$path1;$path2;$path3";
$targetFiles="*.java"
$cmd="javac";
$flags="-cp"
& $cmd $flags $buildPaths $targetFiles
cd server;
& $cmd $flags $buildPaths $targetFiles
cd..;
cd client;
& $cmd $flags $buildPaths $targetFiles
cd ..;
cd ..;
cd build;