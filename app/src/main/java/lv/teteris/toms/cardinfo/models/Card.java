package lv.teteris.toms.cardinfo.models;

/**
 * Created by tt007 on 03.08.2017.
 */

public class Card {
    int id = 0;
    String name = "";
    int ownerId = 0;
    String expirationDate = "";
    int cardNo = 0;

    public Card (int id, String name, int cardNo, int ownerId) {
        this.id = id;
        this.name = name;
        this.cardNo = cardNo;
        this.ownerId = ownerId;
    }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public int getOwnerId() { return this.ownerId; }

    public int getCardNo() { return this.cardNo; }
}
