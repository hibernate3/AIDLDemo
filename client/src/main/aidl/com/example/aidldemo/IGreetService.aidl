// IPerson.aidl
package com.example.aidldemo;

//我们需要在参数上加入方向指示符in，代表参数由客户端设置，我们还需要为Person提供一个import语句(虽然说在同一个包下)
import com.example.aidldemo.Person;

// Declare any non-default types here with import statements

interface IGreetService {
    String sayHello(String someone);
    String greet(in Person person);
}
