package com.medicalstore.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.medicalstore.dao.AdminDAO;
import com.medicalstore.dao.CustomerDAO;
import com.medicalstore.dao.MedicalStoreDAO;
import com.medicalstore.dao.MedicineDAO;
import com.medicalstore.dao.StaffDAO;
import com.medicalstore.dto.MedicineDTO;
import com.medicalstore.entity.Admin;
import com.medicalstore.entity.Customer;
import com.medicalstore.entity.MedicalStore;
import com.medicalstore.entity.Medicine;
import com.medicalstore.entity.Staff;
import com.medicalstore.exception.DuplicateEntryException;

@Component
public class CheckDuplicateEntry{
    
    @Autowired
    private AdminDAO adminDao;

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private MedicalStoreDAO medicalStoreDao;

    @Autowired
    private StaffDAO staffDao;

    @Autowired
    private MedicineDAO medicineDao;

    private Admin admin;
    private Customer customer;
    private MedicalStore medicalStore;
    private Staff staff;
    private Medicine medicine;
    private Map<Object,String> errorMap;


    public void checkDuplicateData(Object classType,String email ,String phoneNumber){
        
        errorMap = new HashMap<Object,String>();
        
        if (classType instanceof Admin){
            
            admin = adminDao.findAdminByEmail(email);
            if(admin != null && ((Admin)classType).getAdminId() != admin.getAdminId())
                errorMap.put(email,"Email already used please give another one...");

            admin = adminDao.findAdminByPhoneNumber(phoneNumber); 
            if(admin != null && ((Admin)classType).getAdminId() != admin.getAdminId())
                errorMap.put(phoneNumber,"Phone Number already used please give another one...");

        }
        else if (classType instanceof Customer) {
            
            customer = customerDao.findCustomerByEmail(email);
            if(customer != null && ((Customer)classType).getCustomerId() != customer.getCustomerId())
                errorMap.put(email,"Email already used please give another one...");
             
            customer = customerDao.findCustomerByPhoneNumber(phoneNumber);   
            if(customer != null && ((Customer)classType).getCustomerId() != customer.getCustomerId())
                errorMap.put(phoneNumber,"Phone Number already used please give another one...");

        } 
        else if (classType instanceof MedicalStore) {

            medicalStore = medicalStoreDao.findMedicalByManagerPhone(phoneNumber);
            if(medicalStore != null && ((MedicalStore)classType).getStoreId() != medicalStore.getStoreId())
                errorMap.put(phoneNumber,"Phone Number already used please give another one...");
        }
        else if(classType instanceof Staff){
            
            staff = staffDao.findStaffByEmail(email);
            if(staff != null && ((Staff)classType).getStaffId() != staff.getStaffId())
                errorMap.put(email,"Email already used another staff please give another one...");

            staff = staffDao.findStaffByPhoneNumber(phoneNumber);    
            if(staff != null && ((Staff)classType).getStaffId() != staff.getStaffId())
                errorMap.put(phoneNumber,"Phone Number already used other staff please give another one...");
        }
        
        if(! errorMap.isEmpty())
            throw new DuplicateEntryException(errorMap ,"Duplicate Entry founds.....");
    }

    public void checkDuplicateMedicine(Object classType, long medicalStoreId){

        errorMap = new HashMap<Object,String>();
        MedicalStore medical = medicalStoreDao.findMedicalStoreById(medicalStoreId);
            if(classType instanceof Set){

                Set<MedicineDTO> set = (Set<MedicineDTO>)classType;

                for(MedicineDTO eachMedicine : set){
                   
                 medicine = medicineDao.findMedicineBymedicineNameAndmanufacutre(eachMedicine.getMedicineName(),eachMedicine.getManufacutrer(),medical);
                 if(medicine != null)
                    errorMap.put(eachMedicine.getMedicineName(),"This medicine already exist. just update...");
             
                }
                
            }
            else{
                Medicine eachMedicine  = (Medicine)classType;
                medicine = medicineDao.findMedicineBymedicineNameAndmanufacutre(eachMedicine.getMedicineName(),eachMedicine.getManufacutrer(),medical);

                if(medicine != null && eachMedicine.getMedicineId() != medicine.getMedicineId())
                   errorMap.put(eachMedicine.getMedicineName(),"This medicine information already exist please verify details..");
            }

              if(! errorMap.isEmpty())
                  throw new DuplicateEntryException(errorMap ,"Duplicate Entry founds.....");
    }

}
