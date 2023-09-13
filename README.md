# OnlinePharmacy
I created Rest API for online medicine booking and delivery system.

I have created API of
ADMIN
ADDRESS
MEDICAL_STORE
STAFF
MEDICINE
CUSTOMER
BOOKING


Flow of that project (if you test on POSTMAN)
- First we create the admin
- second we carete the address
- admin has reponsibility to create staff and medicalStore
- first admin will create the medical store
- and then his will create a staff and that time this satff assigned by the this medical store
- and this staff will add medicine / create medicine and that time it will assigned to that medical store 
- we create customer
- then customer book the medicine that we need to pass CustomerId , medicalStoreId and medicineId and Qty
 



If you want test this api just download POSTMAN tool and test
Endpoint / Url to test this api

1) ADMIN -


          SAVE ADMIN -          After running the program you need to hit first save ADMIN that time you will sen JSON 
                               METHOD - POST
                               URL -   localhost:8080//admin
                               JON -
                                      {
                                          "adminName" : "VALUE",
                                          "adminEmail" :"VALUE",
                                          "phoneNumber" : VALUE(only accept 10 didgit and start from 6 to 9 between them),
                                          "password" :"VALUE(password at least 8 char and combination of character,number,and speical character ex- nik@1526348) "   
                                      }

         UPDATE ADMIN -       At the time of update you need to provide existing admin id as well as JSON object
                               METHOD - PUT
                               URL - localhost:8080/admin?adminId=VALUE(ADMIN ID YOU ALREADY CARETED)
                               JSON -                                    
                                      {
                                          "adminName" : "VALUE",
                                          "adminEmail" :"VALUE",
                                          "phoneNumber" : VALUE(only accept 10 didgit and start from 6 to 9 between them),
                                          "password" :"VALUE(password at least 8 char and combination of character,number,and speical character ex- nik@1526348) "   
                                      }

        LOGIN ADMIN -
                             METHOD - GET
                             URL - localhost:8080//admin/login?email=VALUE&password=VALUE

       RESET PASSWORD -
                             METHOD - PUT
                             URL - localhost:8080/admin/forgot?email=VALUE1&newPassword=VALUE2&adminPhoneNumber=VALUE3
       FIND ADMIN -
                             METHOD - GET
                             URL - localhost:8080/admin?adminId=VALUE                      


   2) ADDRESS -
  

                SAVE ADDRESS -
                              METHOD - POST
                              URL - localhost:8080/Address
                              JSON -
                                    {
                                              "streetName" : "VALUE",
                                              "city": "VALUE",
                                              "state" : "VALUE",
                                              "pincode" : VALUE(in integer)
                                     }
                UPDATE ADDRESS -
                              METHOD - PUT
                              URL - localhost:8080/Address?addressId=VALUE
                              JSON -
                                       {
                                              "streetName" : "VALUE",
                                              "city": "VALUE",
                                              "state" : "VALUE",
                                              "pincode" : VALUE(in integer)
                                     }

                DELETE ADDRESS -
                                METHOD - DELETE
                                URL - localhost:8080/Address?addressId=VALUE

                FIND ADDRESS -  we can find address based on the id . 
                              METHOD - GET
                              URL - localhost:8080/Address?addressId=VALUE


 3) MEDICAL_STORE    

                       SAVE MEDICAL_STORE - At the time you will saving medicalstore you must to provide ADMINID and ADDRESSID
                                             [which admin create this medical and medical address]
         
                                           METHOD - POST
                                           URL - localhost:8080/medicalStore?adminId=VALUE1&addressId=VALUE2
                                           JSON -
                                               {
                                                        "storeName": "VALUE",
                                                        "managerName": "VALUE",
                                                        "managerPhoneNumber": VALUE 
                                                }
                      UPDATE MEDICAL_STORE -

                                          METHOD - PUT
                                           URL - localhost:8080/medicalStore?medicalStoreId=VALUE&adminId=VALUE
                                          JSON -
                                               
                                               {
                                                        "storeName": "VALUE",
                                                        "managerName": "VALUE",
                                                        "managerPhoneNumber": VALUE 
                                                }
                     DELETE MEDICAL_STORE -
                                             METHOD - DELETE
                                             URL - localhost:8080/medicalStore?medicalStoreId=VALUE&adminId=VALUE

                    FIND MEDICAL_STORE (Based on ID)-
                                              METHOD - GET
                                             URL - localhost:8080/medicalStore/id?medicalStoreId=VALUE
         
                    FIND MEDICAL_STORE (Based on NAME)
                                             
                                              METHOD - GET
                                             URL - localhost:8080/medicalStore/name?medicalStoreName=VALUE


   3) STAFF    --- Performing save,update , delete operation of staff admin have permission of that medical store
  

                    SAVE STAFF -
                                  METHOD - POST
                                  URL - localhost:8080//staff?adminId=VALUE&medicalStoreId=VALUE
                                  JSON -
                                        {
                                                "staffName": "VALUE",
                                                "email": "VALUE",
                                                "phoneNumber": "VALUE"
                                        }

                    UPDATE STAFF -
                                    METHOD - PUT
                                    URL -   localhost:8080/staff?staffId=VALUE&adminId=VALUE
                                    JSON -
                                          
                                        {
                                                "staffName": "VALUE",
                                                "email": "VALUE",
                                                "phoneNumber": "VALUE"
                                        }

                   DELETE STAFF -
                                METHOD - DELETE
                                URL - localhost:8080/staff?staffId=VALUE&adminId=VALUE

                   FIND STAFF (Based on ID)-
                                  METHOD - GET
                                  URL - localhost:8080/staff/id?staffId=VALUE

                    FIND STAFF (Based on NAME)-
                                  METHOD - GET
                                  URL - localhost:8080/staff/name?staffName=VALUE

  5) MEDICINE -

                 SAVE MEDICINE -  At the time saving medicine i need to send list of medicine
     
                               METHOD - POST
                               URL - localhost:8080/medicine?staffId=VALUE&medicalStoreId=VALUE
                               JSON -
                                     {
                                         "medicine" :                       
                                        [
                                          {
                                                  "medicineName": "VALUE",
                                                  "cost": VALUE,
                                                  "expiryDate": "VALUE",   ------> formate = yyyy-dd-mm
                                                  "stockQuantity": VALUE,
                                                  "manufacutrer": "VALUE",
                                                  "description":"VALUE"
                                           },
                                           {
                                                  "medicineName": "VALUE",
                                                  "cost": VALUE,
                                                  "expiryDate": "VALUE",
                                                  "stockQuantity": VALUE,
                                                  "manufacutrer": "VALUE",
                                                  "description":"VALUE"
                                           }
                                      
                                          ]
                                      }
     
                    
            UPDATE MEDICINE -
                               METHOD - PUT
                               URL -  localhost:8080/medicine?medicineId=VALUE&staffId=VALUE
                               JSON -
                                      {
                                                  "medicineName": "VALUE",
                                                  "cost": VALUE,
                                                  "expiryDate": "VALUE",
                                                  "stockQuantity": VALUE,
                                                  "manufacutrer": "VALUE",
                                                  "description":"VALUE"
                                      }

           DELETE MEDICINE -   In url i specify the satff is this staff has reponsible to delete this medicine if this staff present inside that medical store
                             METHOD - DELETE
                             URL - localhost:8080/medicine?medicineId=VALUE&staffId=VALUE

           FIND MEDICINE -
                             METHOD - GET
                             URL - localhost:8080/medicine/id?medicineId=VALUE
           FIND MEDICINE -
                             METHOD - GET
                             URL - localhost:8080/medicine/name?medicineName=VALUE

7) CUSTOMER

           SAVE CUTOMER -
                             METHOD - POST
                             URL - localhost:8080//customer?addressId=VALUE
                             JSON -
                                      {
                                          "customerName":"VALUE",
                                          "phoneNumber":VALUE,
                                          "email":"VALUE",
                                          "password":"VALUE"
                                      }

          UPDATE CUSTOMER -
                             METHOD - PUT
                             URL - localhost:8080/customer?customerId=VALUE&addressId=VALUE(IF YOU WANT ADD NEW ADDRESS OF THIS CUSTOMER)
                             JSON -
                                      {
                                          "customerName":"VALUE",
                                          "phoneNumber":VALUE,
                                          "email":"VALUE",
                                          "password":"VALUE"
                                      }
        DELETE CUSTOMER -    if you hit this url that time Custmer Delete as well as all customer booked booking will delete.
                                             
                             METHOD - DELETE
                             URL - localhost:8080/customer?customerId=VALUE

       FIND CUSTOMER (Based on ID) 

                             METHOD - GET
                             URL - localhost:8080/customer?customerId=VALUE
       LOGIN CUSTOMER -
                           METHOD - GET
                           URL - localhost:8080//customer/login?customerEmail=VALUE&customerPassword=VALUE

       RESET PASSWORD -
                         METHOD - PUT
                         URL - localhost:8080/customer/forgot?customerEmail=VALUE&newPassword=VALUE&phoneNumber=VALUE



8) BOOKING



              CREATE / SAVE BOOKING -
                           METHOD -POST
                           URL -   localhost:8080//booking/VALUE1/VALUE2?MedicineId=MedicineQty&MedicineId=MedicineQty
                                    ABOUT URL-   1) VALUE1 = this value describe the customerID which customer book this medicine
                                                 2) VALUE2 -  this value for MEDICAL_STORE which medical you want buy medicine
                                            in (?) query string you need to specify MEDICINEID and MEDICINEQTY
                                     for ex  -  I have a customer with id 1
                                                I have a medicalStore with id 9
                                                and this medical has medicine with id 5 and 3
                                                I want book this then i send url to controller
                                            localhost:8080/booking/1/9?5=3&3=1                                  
                           JSON -
                                  {
                                      "orderDate":"VALUE",
                                      "paymentMode":"VALUE",
                                      "expectedDate":"VALUE"
                                  }   


             CANCEL BOOKING -
                             METHOD - PUT
                             URL - localhost:8080/booking?bookingId=VALUE

            GET BOOKINGS -
                             METHOD - GET
                             URL - localhost:8080//booking?customerId=VALUE
           
       
                 
