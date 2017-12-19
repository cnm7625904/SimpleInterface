package jljt.wangs.com.simpleinterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import java.text.MessageFormat;
import java.util.Timer;
public class MainActivity extends AppCompatActivity implements ITestInterface{
    private AppCompatButton btn_time;
    private int x=0;
    private Timer mTimer=null;
    private UsingInterface mUsingInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_time=findViewById(R.id.btn_time);
        mUsingInterface =new UsingInterface(this);
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_time.setClickable(false);
                initTimer();
                if(x>10)
                {
                    mUsingInterface.useEnd("当前X是"+x);
                }
                else {
                    mUsingInterface.useStart();
                    mUsingInterface.useInteger(x,x);
                }
            }
        });
    }
    @Override
    public void start() {
        /**
         * UI线程执行顺序由快到慢 runOnUiThread - Handler.post - new Thread() - [runOnUiThread] - View.post
         * 非UI线程执行由快到慢 Handler.post - runOnUiThread - new Thread() - [runOnUiThread] - View.post
         */
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btn_time.setText(MessageFormat.format("{0}秒",x));
                x++;
                if(x>10){
                    if(mTimer!=null){
                        mTimer.cancel();
                        mTimer=null;
                        btn_time.setText("计时结束,点击重新计时");
                        btn_time.setClickable(true);
                    }
                }
            }
        });
    }
    @Override
    public void end(String test) {
        Log.d("end", "end: "+test);
        x=1;
    }
    @Override
    public int getInteger(int a) {
        Log.d("getInteger", "getInteger: "+a);
        return a;
    }
    private void initTimer(){
        mTimer=new Timer();
        final TimerTasks task=new TimerTasks(this);
        mTimer.schedule(task,0,1000);//延迟0ms,每隔1000ms执行一次，反复执行
    }
}
