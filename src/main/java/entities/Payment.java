package entities;

import java.time.LocalDate;

public class Payment {
    private String cardNumber;
    private String cardHolder;
    private LocalDate expDate;

    public Payment(String cardNumber, String cardHolder,  LocalDate expDate,) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expDate = expDate;
    }
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

}
