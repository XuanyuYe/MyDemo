package cn.llwy.com.mydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.widget.Toast;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class sendemail extends MainActivity {
    private Button send;
    private TextView pre_txt;
    private EditText email_address;
    private String msg1;
    private Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email);
        send = findViewById(R.id.send_button);
        pre_txt = findViewById(R.id.pre_txt);
        email_address = findViewById(R.id.email_address);

        Intent intent = getIntent();
        //把传送进来的String类型的Message的值赋给新的变量message
        String message = intent.getStringExtra("EXTRA_MESSAGE");
        //把布局文件中的文本框和textview链接起来
        TextView textView = (TextView) findViewById(R.id.pre_txt);
        //在textview中显示出来message
        textView.setText(message);
        msg1 = textView.getText().toString();
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sendMessage(msg1);
            }
        });
    }
    private void sendMessage(final String msg) {

        /*****************************************************/
        Log.i("准备", "开始发送邮件");
        // 这个类主要是设置邮件
        new Thread(new Runnable() {

            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //获取String类型的时间
                String createdate = sdf.format(date);

                // TODO Auto-generated method stub
                MailSenderInfo mailInfo = new MailSenderInfo();
                mailInfo.setMailServerHost("smtp.qq.com");
                mailInfo.setMailServerPort("25");
                mailInfo.setValidate(true);
                mailInfo.setUserName("834909967@qq.com");
                mailInfo.setPassword("ipbqwssvgtoqbcad");// 您的邮箱密码
                mailInfo.setFromAddress("834909967@qq.com");
                mailInfo.setToAddress(email_address.getText().toString());
                if( email_address.getText().toString().isEmpty()) {
                    Looper.prepare();
                    Toast toastCenter = Toast.makeText(getApplicationContext(),"please enter an email address",Toast.LENGTH_LONG);
                    //setGravity决定Toast显示位置
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    //调用show使得toast得以显示
                    toastCenter.show();
                    Looper.loop();
                }

                if( !isEmail(email_address.getText().toString())) {
                    Looper.prepare();
                    Toast toastCenter = Toast.makeText(getApplicationContext(),"illegal email address",Toast.LENGTH_LONG);
                    //setGravity决定Toast显示位置
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    //调用show使得toast得以显示
                    toastCenter.show();
                    Looper.loop();
                }

                mailInfo.setSubject(createdate+" from 834909967@qq.com");
                mailInfo.setContent(msg);
                // 这个类主要来发送邮件
                SimpleMailSender sms = new SimpleMailSender();
                boolean isSuccess = sms.sendTextMail(mailInfo);// 发送文体格式
                // sms.sendHtmlMail(mailInfo);//发送html格式
                if (isSuccess) {
                    Log.i("准备", "发送成功");
                    Looper.prepare();
                    Toast toastCenter = Toast.makeText(getApplicationContext(),"you have send the email successfully",Toast.LENGTH_LONG);
                    //setGravity决定Toast显示位置
                    toastCenter.setGravity(Gravity.CENTER,0,0);
                    //调用show使得toast得以显示
                    toastCenter.show();
                    Looper.loop();

                } else {
                    Log.i("准备", "发送失败");
                }
            }
        }).start();}

    /**
     * 描述：是否是邮箱.
     *
     * @param str 指定的字符串
     * @return 是否是邮箱:是为true，否则false
     */
    public static Boolean isEmail(String str) {
        Boolean isEmail = false;
        String expr = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$";

        if (str.matches(expr)) {
            isEmail = true;
        }
        return isEmail;
    }
}







