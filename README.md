JRTSC
=====

## JRTSC (Java Real Time Streaming and Control)
JRTSC is a VNC replacement meant for actual desktop use. It uses [JNativeHook](https://code.google.com/p/jnativehook/ "JNativeHook") for system level control.

### How to build from source
  

<p>
<li>Simply cd into the build directory</li>
<li> Then type 
<code> .\make.ps1 </code> 
in power shell for compiling. </li>
<li> Type   <code> .\runServer.ps1 
</code>  to run the server and  <code> .\runClient.ps1 
</code> to run the client.
</li>
  Note: Start the server first
</p>

Folder structure
<ul>
<li>JRTSC </li>
<ul> 
<li>build: stores compiling and execution scripts</li> 
<li>lib: stores external libraries</li>
<li>src</li>
<ul>
<li>server: contains server code</li>
<li>client: contains client code </li>
</ul>
