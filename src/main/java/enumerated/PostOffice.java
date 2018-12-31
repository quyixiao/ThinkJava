package enumerated;//: enumerated/PostOffice.java
// Modeling a post office.

import net.mindview.util.Enums;

import java.util.Iterator;

import static net.mindview.util.Print.print;


/**
 * 606 页
 *
 * 在职责链设计模式中，程序员以多种不同的方式来解决一个问题，然后将它们的链接在一起。
 * 当一个请求到来的时，它遍历这个链，直到这个链中的某个解决方案能够处理该请求，通过常量
 * 相关的方法，我们可能容易地实现一个简单的职责链
 * 我们先来描述一下邮件，邮件的每个特征我们都可以enum来表示，程序将随机的生成Mail对象，如果
 * 要减小一封邮件的GeneraDelivery为yes的概率，那最简单的方法就是多创建g，
 *
 *
 * 我们先来描述一下邮件，邮件的每一个关键特征可以用enum来表示，程序将随机地生成Mail对象，如果
 * Mail对象，如果要减少一封邮件的GeneralDelivery的YES的概率，那么最简单的方法就是多创建几个
 * 不是YES的enum 实例，所以enum的定义看起来有点古怪。
 *
 *  我们看到Mail中有一个randomMain() 方法，它负随机地创建用于测试的邮件，而generator() 方法生成
 *  Iterable对象，该对象在你调用next()方法时，在其内部使用randomMail() 来创建Mail对象，这样的结构
 *  使程序使得程序员可以通过Mail.generator() 方法，很容易地构造出一个foreach循环
 *
 *  职责链由enum MailHandler实现，而enum定义的
 *
 *
 *  枚举类开非常适合用来创建状态机，一个状态机可双具有有限个特定的状态，它通常根据输入，从一个状态转移到下一个状态，不过
 *  也可能存在瞬时状态，而一旦任务执行结束，状态机就会，非常适合用来表现不同的状态和输入，一般而言，每个状态都有相关的
 *  输出，
 *  自动售货是一个很好的状态机的例子，首先，我们用一个enum定义各种输入
 *
 *
 *
 *
 */
class Mail {
    // The NO's lower the probability of random selection:
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    static long counter = 0;
    long id = counter++;

    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Return address: " + returnAddress;
    }

    // Generate test Mail:
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public Mail next() {
                        return randomMail();
                    }

                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class PostOffice {
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE:
                        return false;
                    default:
                        switch (m.address) {
                            case INCORRECT:
                                return false;
                            default:
                                print("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }

    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values())
            if (handler.handle(m))
                return;
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }
}








/* Output:
Mail 0, General Delivery: NO2, Address Scanability: UNSCANNABLE, Address Readability: YES3, Address Address: OK1, Return address: OK1
Delivering Mail 0 normally
*****
Mail 1, General Delivery: NO5, Address Scanability: YES3, Address Readability: ILLEGIBLE, Address Address: OK5, Return address: OK1
Delivering Mail 1 automatically
*****
Mail 2, General Delivery: YES, Address Scanability: YES3, Address Readability: YES1, Address Address: OK1, Return address: OK5
Using general delivery for Mail 2
*****
Mail 3, General Delivery: NO4, Address Scanability: YES3, Address Readability: YES1, Address Address: INCORRECT, Return address: OK4
Returning Mail 3 to sender
*****
Mail 4, General Delivery: NO4, Address Scanability: UNSCANNABLE, Address Readability: YES1, Address Address: INCORRECT, Return address: OK2
Returning Mail 4 to sender
*****
Mail 5, General Delivery: NO3, Address Scanability: YES1, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK2
Delivering Mail 5 automatically
*****
Mail 6, General Delivery: YES, Address Scanability: YES4, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK4
Using general delivery for Mail 6
*****
Mail 7, General Delivery: YES, Address Scanability: YES3, Address Readability: YES4, Address Address: OK2, Return address: MISSING
Using general delivery for Mail 7
*****
Mail 8, General Delivery: NO3, Address Scanability: YES1, Address Readability: YES3, Address Address: INCORRECT, Return address: MISSING
Mail 8 is a dead letter
*****
Mail 9, General Delivery: NO1, Address Scanability: UNSCANNABLE, Address Readability: YES2, Address Address: OK1, Return address: OK4
Delivering Mail 9 normally
*****
*///:~
