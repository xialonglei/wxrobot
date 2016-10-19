# wxrobot
  
小程序，开发微信机器人，支持向好友群发信息以及图片  
  
  
开发工具以及框架:spring+springmvc+eclipse+tomcat+windows7  
  
  
服务器启动后，通过访问 http://localhost:8080/wxrobot/wx/getImage 获得登录二维码，效果图如下  
![](https://github.com/xialonglei/wxrobot/blob/master/WXQR.PNG)
  
可以向好友定时发送消息，发送消息的类为SendMessage，向list里面添加要发送信息的好友微信号即可，  
设置的是每分钟向好友发送一条随机信息；也可以通过/wxrobot/wx/sendMessage路径向好友发送消息，  
要发送的好友也要自己手动添加    

可以向好友发送图片/wxrobot/wx/sendMultiMessage?path=xxxx(图片路径)  
  
增加日志记录slf4j，有error.log和info.log，日志在tomcat项目的WEB-INFO/logs/目录下  

当好友第一次发送消息给你时，会提醒他可以尝试命令loop、all、pic、sas，他发送loop命令给你时，  
会不断的发送消息给他（每5s一条），但他可以用stop命令结束，当他发送all命令时，可以获取你的所有好友名单，  
pic命令会发一张图片给你好友，但是必须先给出你的图片目录地址，服务器启动时会将图片库下的所有图片路径读到内存中保存，  
发送图片给好友时是随机的，图片目录地址配置:Constants.PIC_ROOT_PATH，sas命令是停止向好友自动发送消息  
