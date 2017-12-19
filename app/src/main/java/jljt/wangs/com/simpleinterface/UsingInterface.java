package jljt.wangs.com.simpleinterface;

import java.util.Timer;

/**
 * Created by Administrator on 2017/12/19.
 */

public class UsingInterface {

    private static ITestInterface mITestInterface=null;

    public UsingInterface(ITestInterface iTestInterface) {
    this.mITestInterface=iTestInterface;
    }

    public void useStart(){
       mITestInterface.start();
    }
    public void useEnd(String string){
       mITestInterface.end(string);
    }
    public void useInteger(int a,int b){
      mITestInterface.getInteger(a+b);
    }

}
