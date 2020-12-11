package entities;

public class Room {

    private long price;
    private int number;
    private int bedsNumber;
    private RoomTypes roomType;

    public Room(){}

    public Room(long price, int number, int bedsNumber, RoomTypes roomType) {
        this.price = price;
        this.number = number;
        this.bedsNumber = bedsNumber;
        this.roomType = roomType;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomTypes getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypes roomType) {
        this.roomType = roomType;
    }

    public int getBedsNumber() {
        return bedsNumber;
    }

    public void setBedsNumber(int bedsNumber) {
        this.bedsNumber = bedsNumber;
    }



}
