package com.k21d.concurrency.legacy;

import java.beans.PropertyChangeSupport;
import java.util.EventListener;
import java.util.EventObject;

/**
 * 事件监听者模式
 * {@link EventObject} 标准的事件对象<br/>
 * {@link EventListener} 标准的时间监听对象<br/>
 *
 * @See EventListener
 */
public class EventListenerDemo {

    public static void main(String[] args) {
        Person person = new Person();
        // Java Beans 规范-> 内省
        // PropertyChangeEvent 广播源
        // PropertyChangeListener 注册器
        PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(person);
        //1.注册
        propertyChangeSupport.addPropertyChangeListener("name", (event) -> {
            Person bean = (Person) event.getSource();
            System.out.printf("Persion[%s] 的 name属性，老值：%s, 新值：%s /n",bean,event.getOldValue(),event.getNewValue());
        });
        //2.触发事件
        propertyChangeSupport.firePropertyChange("name",null,"k21d");
    }

    /**
     * POJO
     */
    private static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;

        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
