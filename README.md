Product Documents The minSdkVersion of this application is 14(Android 4.0), and the targetSdkVersion is 27(Android 8.1).

Because this application needs a real camera to capture a photo and translates it into characters, so if you want to run this application, please don’t use the AVD on your computer, use the USB to connect an android-phone and then run it. To achieve the functions, the application would ask for using camera and connecting the Internet permissions.

This application has two main functions. First, at the main page, you could press the button “TAKE PHOTOS” and take a photo from the reality timely. Then the application would capture the picture and recognize the characters, finally the textView will display the text.

Secondly, press “NEXT” button to jump to the sendemail page. You could preview the contains which you are going to send. Below the page, you need to enter the email-address that you want to send to. Because this application only connect to the QQ email host(smpt.qq.com), so only QQ email address is available. If you want to try other types of email address, it is not difficult, find this code in the sendmail class:

Search from the internet to get the serverHost and serverPort ofyour desired email address. For example, I want to send to a xxx@163.com address, just change the serverHost to smpt.163.com.

As well ,this application presets the address which you send from —— my QQ email address.

We can’t achieve the login function because if you want to make your email address has “send and get email” function rely on 3rd party app, you need to configure the POP3/smpt Service of your email address before, and get an Authorization Code. Like “ipbqwssvgtoqbcad” in the picture above. Obviously, it is not my real email address password, the Authorization Code a security your email. If you want to change the default, go make the POP3/smpt Service of your email address available. And then change the setUserName and setPassword in the picture above with your email address and authorization code.
