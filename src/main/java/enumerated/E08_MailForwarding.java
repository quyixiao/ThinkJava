//: enumerated/E08_MailForwarding.java
/****************** Exercise 8 *****************
 * Modify PostOffice.java so it has the ability to
 * forward mail.
 ***********************************************/
package enumerated;

import net.mindview.util.Enums;

import java.util.Iterator;

import static net.mindview.util.Print.print;

class FMail {
    // The NO's lower the probability of random selection:
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}

    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}

    enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6}

    enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    enum ForwardAddress {MISSING, OK1, OK2, OK3, OK4, OK5}

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    ForwardAddress forwardAddress;
    static long counter;
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
                ", Return address: " + returnAddress +
                ", Forward address: " + forwardAddress;
    }

    // Generate test FMail:
    public static FMail randomMail() {
        FMail m = new FMail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        m.forwardAddress = Enums.random(ForwardAddress.class);
        return m;
    }

    public static Iterable<FMail> generator(final int count) {
        return new Iterable<FMail>() {
            int n = count;

            public Iterator<FMail> iterator() {
                return new Iterator<FMail>() {
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    public FMail next() {
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

public class E08_MailForwarding {
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(FMail m) {
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
            boolean handle(FMail m) {
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
            boolean handle(FMail m) {

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
        FORWARD_MAIL {
            boolean handle(FMail m) {
                switch (m.forwardAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Forwarding " + m);
                        return true;
                }
            }
        },
        RETURN_TO_SENDER {
            boolean handle(FMail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(FMail m);
    }

    static void handle(FMail m) {
        for (MailHandler handler : MailHandler.values())
            if (handler.handle(m))
                return;
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (FMail mail : FMail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }

}