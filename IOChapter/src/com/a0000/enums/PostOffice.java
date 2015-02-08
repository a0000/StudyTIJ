package com.a0000.enums;

import java.util.Iterator;

import static com.a0000.io.utils.Print.*;

class Mail {
    // The No's Lower the probaliblity of random selection;
    enum GeneralDelivery { YES, NO1, NO2, NO3, NO4, NO5}
    enum Scannability {UNSCANNABLE, YES1,YES2,YES3,YES4}
    enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4}
    enum Address {INCORRECT, OK1,OK2, OK3, OK4, OK5, OK6}
    enum ReturnAdress {MISSING, OK1, OK2, OK3, OK4, OK5}
    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAdress returnAdress;
    static long counter = 0;
    long id = counter++;

    @Override
    public String toString() {
        return "Mail " + id;
    }
    public String details(){
        return toString() +
                ", General Deliverty: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Adress: " + address +
                ", Return Adress: " + returnAdress;
    }
    // Generate test Mail;
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAdress = Enums.random(ReturnAdress.class);
        return m;
    }
    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;
            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

/**
 * Created by Administrator on 2015/2/8.
 * modeling a post office.
 */
public class PostOffice {
    enum MailHandler {
        GENERAL_DELIVERY {
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery fo " + m);
                        return true;
                    default: return false;
                }
            }
        },
        MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE:return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                print("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE: return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                print("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        },
        RETURN_TO_SENDER {
            @Override
            boolean handle(Mail m) {
                switch (m.returnAdress) {
                    case MISSING: return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        };
        abstract boolean handle(Mail m);
    }
    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values()) {
            if (handler.handle(m)) {
                return;
            }
        }
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("------------------------");
        }
    }
}
