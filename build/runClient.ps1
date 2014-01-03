set-executionpolicy remotesigned
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
$targetFiles="ClientMain"
$cmd="java";
$flags="-classpath"
& $cmd $flags $buildPaths $targetFiles
cd ..;
cd build;
