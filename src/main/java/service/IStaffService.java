package service;

import model.Staff;

import java.util.List;

public interface IStaffService {

    Staff findById(long id);

    Staff create(Staff staff);

    List<Staff> getAllStaff();

    Staff update(Staff staff);

    void delete(long id);

    List<Staff> findByName(String name);
}
