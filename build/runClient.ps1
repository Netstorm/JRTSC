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
$buildPaths="'$path1;$path2;$path3'";
$targetFiles="clientMain"
$cmd="java";
$flags="-classpath"
$allArgs=@($cmd,$flags,$buildPaths,$targetFiles)
invoke-expression "$allArgs"
cd ..;
cd build;
