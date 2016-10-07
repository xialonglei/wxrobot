# wxrobot
  
小程序，开发微信机器人，支持向好友群发信息以及图片，目前已经完成扫码登录部分  
  
  
开发工具以及框架:spring+springmvc+eclipse+tomcat+windows7  
  
  
服务器启动后，通过访问 http://localhost:8080/wxrobot/wx/getImage 获得登录二维码，效果图如下  
![](https://github.com/xialonglei/wxrobot/blob/master/WXQR.PNG)
  
可以向好友定时发送消息，发送消息的类为SendMessage，向list里面添加要发送信息的好友微信号即可，  
设置的是每分钟向好友发送一条随机信息；也可以通过/wxrobot/wx/sendMessage路径向好友发送消息，  
要发送的好友也要自己手动添加  
  
增加日志记录slf4j，有error.log和info.log，日志在tomcat项目的WEB-INFO/logs/目录下
