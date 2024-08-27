package org.codeba.scs.kafka;


import java.util.Objects;

/**
 * @author codeba
 */
public class MyMessage {
    private String foo;
    private Integer bar;

    public MyMessage(String foo, Integer bar) {
        this.foo = foo;
        this.bar = bar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMessage myMessage = (MyMessage) o;
        return Objects.equals(foo, myMessage.foo) && Objects.equals(bar, myMessage.bar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foo, bar);
    }


    @Override
    public String toString() {
        return "MyMessage{" +
                "foo='" + foo + '\'' +
                ", bar=" + bar +
                '}';
    }

    public String getFoo() {
        return foo;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public Integer getBar() {
        return bar;
    }

    public void setBar(Integer bar) {
        this.bar = bar;
    }
}
