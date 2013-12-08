TOCHAT - Chat application using Java UDP connection
==============

Multiple user can chat at a time through one server
--------------

**Following packages are core part of application**
- dev.dinesh.tochat.core

*steps to run*
- Generate executable jar file
- At the start of application role should be confirmed
  - for Host _ Enter port on which server will run
  - for client _ Enter IP address, Port of server and client port on which client will run
  
*dev.dinesh.tochat.core.listener - Observer Pattern. These interfaces can be used to extend GUI without modifying core*
- It contains two interfaces which are used by both CLient and Server an GUI classes.
  -   ChatBroker - implmented by Client and Server
  -   ChatListener - implemented by GUI classes like ChatFrame
