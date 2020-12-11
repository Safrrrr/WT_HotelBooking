package dao;

import entities.Payment;
import specification.Specification;

import java.util.List;

public class PaymentDao implements Dao<Payment> {
    @Override
    public boolean add(Payment entity) {
        return false;
    }

    @Override
    public boolean remove(Payment entity) {
        return false;
    }

    @Override
    public boolean update(Payment entity) {
        return false;
    }

    @Override
    public List<Payment> query(Specification specification) {
        return null;
    }

}



