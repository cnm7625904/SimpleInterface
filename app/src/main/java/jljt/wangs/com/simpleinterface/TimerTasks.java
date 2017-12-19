package jljt.wangs.com.simpleinterface;


public class TimerTasks extends java.util.TimerTask {

    private ITestInterface mITestInterface=null;

    public TimerTasks(ITestInterface timerListener) {
        this.mITestInterface = timerListener;
    }

    @Override
    public void run() {
        if(mITestInterface!=null){
            mITestInterface.start();//计时器运行时，自动调用start方法
        }
    }
}
