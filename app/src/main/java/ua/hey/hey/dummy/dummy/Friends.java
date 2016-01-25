package ua.hey.hey.dummy.dummy;

import ua.hey.hey.Contacts;

/**
 * Created by Maxim on 12/21/15.
 */
public class Friends {
    public static String[] getFriends() {
        for(int b = 0; b<friends.length; b++){
            System.out.println("\ntrying to get "+b);
            System.out.println("\n" + Contacts.s.get(b));
            friends[b]=Contacts.s.get(b);
        }
        return friends;
    }

    private static String[] friends = new String [Contacts.b];
}
