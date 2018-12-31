//: enumerated/E09_PostOffice2.java
/****************** Exercise 9 *****************
 * Modify class PostOffice so that it uses an
 * EnumMap.
 ***********************************************/
package enumerated;

import java.util.EnumMap;

import static net.mindview.util.Print.print;

interface Command1 {
    boolean handle(Mail m);
}

public class E09_PostOffice2 {
    static EnumMap<MailHandler, Command1> em =
            new EnumMap<MailHandler, Command1>(MailHandler.class);

    static {
        em.put(MailHandler.GENERAL_DELIVERY, new Command1() {
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        });
        em.put(MailHandler.MACHINE_SCAN, new Command1() {
            public boolean handle(Mail m) {
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
        });
        em.put(MailHandler.VISUAL_INSPECTION, new Command1() {
            public boolean handle(Mail m) {
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
        });
        em.put(MailHandler.RETURN_TO_SENDER, new Command1() {
            public boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        });
    }

    enum MailHandler {
        GENERAL_DELIVERY, MACHINE_SCAN, VISUAL_INSPECTION,
        RETURN_TO_SENDER;
    }

    static void handle(Mail m) {
        for (Command1 cmd : em.values())
            if (cmd.handle(m))
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